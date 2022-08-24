package cn.peng.studygodpath.frame.netSimulation.proxy;

import cn.peng.studygodpath.frame.netSimulation.service.event.ServerStatusChangeEventDefine;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpProxy {	
	int myTcpPort = 8181;
	private ServerSocket myServerSocket;
	private Thread myThread;
	private boolean stop = false;
	
	public ThreadPoolExecutor pool;
	
	private PropertyChangeSupport eventSupport = new PropertyChangeSupport(this);
	
	public HttpProxy(int port){
		eventSupport.firePropertyChange("statusChange", null, "STARTING");
		myTcpPort = port;
	}
	
	public HttpProxy() {
	}
	
	private void initPool() {
		pool = new ThreadPoolExecutor(30, 1000,
                120L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
		
	}
	
	public void start() throws IOException {
		try {
			eventSupport.firePropertyChange(ServerStatusChangeEventDefine.EVENT_NAME, null, ServerStatusChangeEventDefine.Status.STARTING);
			
			initPool();
			
			myServerSocket = new ServerSocket(myTcpPort);
			myThread = new Thread(new Runnable() {
				public void run() {
					boolean stopWithException = false;
					try {
						while (!stop) {
							HTTPSession session = new HTTPSession(myServerSocket.accept(),eventSupport);
							pool.execute(session);
						}
						
					} catch (IOException ioe) {
						System.out.println(ioe.getLocalizedMessage());
					}
					if (!stopWithException) {
						try {
							pool.awaitTermination(120L, TimeUnit.SECONDS);
						} catch (InterruptedException e) {
						}
						eventSupport.firePropertyChange(ServerStatusChangeEventDefine.EVENT_NAME, null, ServerStatusChangeEventDefine.Status.STOPED);
					}
				}
			});
			myThread.setDaemon(true);
			myThread.start();
			System.out.println("start!");
			eventSupport.firePropertyChange(ServerStatusChangeEventDefine.EVENT_NAME, null, ServerStatusChangeEventDefine.Status.RUNNING);
		} catch (Exception e) {
			e.printStackTrace();
			eventSupport.firePropertyChange(ServerStatusChangeEventDefine.EVENT_NAME, e, ServerStatusChangeEventDefine.Status.STOPED);
		}
	}
	
	/**
	 * Stops the server.
	 */
	public void stop() {
		eventSupport.firePropertyChange(ServerStatusChangeEventDefine.EVENT_NAME, null, ServerStatusChangeEventDefine.Status.STOPPING);
		pool.shutdown();
		stop = true;
		try {
			myServerSocket.close();
		} catch (IOException e) {
		}
	}
	
	public void waitForStop() {
		try {
			if (!myServerSocket.isClosed()) {
				myServerSocket.close();
			}
			myThread.join();
		} catch (InterruptedException e) {
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		HttpProxy proxy = new HttpProxy(8580);
		try {
			proxy.start();
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
			System.exit(-1);
		}
		
		try {
			Scanner scan = new Scanner(System.in);
			while (scan.hasNextLine()) {
				String cmd = scan.nextLine();
				if ("poolsize".equals(cmd)) {
					System.out.println(proxy.pool.getActiveCount());
				}
				if ("stop".equals(cmd)) {
					proxy.stop();
				}
				if ("noinput".equals(cmd)) {
					break;
				}
			}
			scan.close();
		} catch (Throwable t) {
		}
		System.out.println("stop!");
	}

	/**
	 * @return the eventSupport
	 */
	public PropertyChangeSupport getEventSupport() {
		return eventSupport;
	}

	/**
	 * @return the myTcpPort
	 */
	public int getMyTcpPort() {
		return myTcpPort;
	}

	/**
	 * @param myTcpPort the myTcpPort to set
	 */
	public void setMyTcpPort(int myTcpPort) {
		this.myTcpPort = myTcpPort;
	}
	
	public int getActiveConnectionCount() {
		if (pool == null) {
			return 0;
		}
		return pool.getActiveCount();
	}
}
