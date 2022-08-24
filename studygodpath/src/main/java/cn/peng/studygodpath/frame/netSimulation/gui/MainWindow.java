package cn.peng.studygodpath.frame.netSimulation.gui;

import cn.peng.studygodpath.frame.netSimulation.gui.model.CatchTableModel;
import cn.peng.studygodpath.frame.netSimulation.gui.render.ColorTableRender;
import cn.peng.studygodpath.frame.netSimulation.proxy.HttpProxy;
import cn.peng.studygodpath.frame.netSimulation.service.ConfigService;
import cn.peng.studygodpath.frame.netSimulation.service.DataPreparationService;
import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;
import cn.peng.studygodpath.frame.netSimulation.service.event.CatchConnectionAddEventDefine;
import cn.peng.studygodpath.frame.netSimulation.service.event.CatchConnectionUpdateEventDefine;
import cn.peng.studygodpath.frame.netSimulation.service.event.ServerStatusChangeEventDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class MainWindow {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MainWindow.class);
	private static String CIRCLE = "●";
	private JFrame frame;
	private JFormattedTextField portField;
	private JButton startServerButton;
	private JLabel proxyStatusLabel;
	private JSeparator separator;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private final Action action = new StartProxyAction();
	private JPanel panel_1;
	private JTable table;
	private CatchTableModel dataModel = new CatchTableModel();
	private JScrollPane scrollPane;
	
	private HttpProxy httpProxyServer = new HttpProxy();
	private JLabel threadCount;
	private JMenuItem menuItem_setup;
	
	private ConfigEditDialog configEditDialog;
	private FileSaveConfigEditDialog fileSaveConfigEditDialog;
	
	private ColorTableRender tableRender = new ColorTableRender();
	private JPopupMenu popupMenu;
	private JMenuItem menuItem_1;
	private JMenuItem mntmurl;
	private JMenuItem menuItem_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6293\u5305\u53CA\u5206\u6790\u5DE5\u5177");
		frame.setBounds(100, 100, 835, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\u4EE3\u7406\u7AEF\u53E3\u53F7\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setGroupingUsed(false);
		portField = new JFormattedTextField(numberFormat);
		portField.setText("8580");
		panel.add(portField);
		portField.setColumns(10);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator);
		
		startServerButton = new JButton("\u542F\u52A8\u4EE3\u7406\u670D\u52A1");
		startServerButton.setHorizontalAlignment(SwingConstants.TRAILING);
		startServerButton.setFont(new Font("宋体", Font.PLAIN, 13));
		startServerButton.setAction(action);
		panel.add(startServerButton);
		
		proxyStatusLabel = new JLabel(CIRCLE + "已停止");
		proxyStatusLabel.setForeground(Color.RED);
		panel.add(proxyStatusLabel);
		
		threadCount = new JLabel("0");
		panel.add(threadCount);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane(getTable());
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menu = new JMenu("\u9009\u9879");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("\u9000\u51FA");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuItem_setup = new JMenuItem("\u8BBE\u7F6E");
		menuItem_setup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configEditDialog.setVisible(true);
			}
		});
		menu.add(menuItem_setup);
		menu.add(menuItem);
		
		configEditDialog = new ConfigEditDialog(frame);
		
		bindProxyEvent();
		startUpdateCount();
	}
	
	private void bindProxyEvent() {
		// 代理服务器状态变更监听
		httpProxyServer.getEventSupport().addPropertyChangeListener(ServerStatusChangeEventDefine.EVENT_NAME, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Object o = evt.getNewValue();
				if (o != null && o instanceof ServerStatusChangeEventDefine.Status) {
					ServerStatusChangeEventDefine.Status status = (ServerStatusChangeEventDefine.Status)o;
					
					proxyStatusLabel.setText(CIRCLE + status.getName());
					proxyStatusLabel.setForeground(new Color(status.getColor()));
					startServerButton.setEnabled(status.isButtonEnable());
					
					
					if (status == ServerStatusChangeEventDefine.Status.RUNNING) {
						startServerButton.getAction().putValue("Name", "停止代理服务");
					} else if (status == ServerStatusChangeEventDefine.Status.STOPED) {
						startServerButton.getAction().putValue("Name", "启动代理服务");
					}
					
					Object old = evt.getOldValue();
					if (old != null && old instanceof Exception) {
						Exception e = (Exception)old;
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		// 代理服务器抓包事件监听
		httpProxyServer.getEventSupport().addPropertyChangeListener(CatchConnectionAddEventDefine.EVENT_NAME, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Object o = evt.getNewValue();
				if (o != null && o instanceof ConnectionInfo) {
					int i = dataModel.addConnectionInfo((ConnectionInfo)o);
					tableRender.setForegroundColor(i, Color.BLUE);
				}
			}
		});
		// 代理服务器更新包事件监听
		httpProxyServer.getEventSupport().addPropertyChangeListener(CatchConnectionUpdateEventDefine.EVENT_NAME, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Object o = evt.getNewValue();
				if (o != null && o instanceof ConnectionInfo) {
					ConnectionInfo connInfo = (ConnectionInfo)o;
					int i = dataModel.updateConnectionInfo(connInfo);
					if (i != -1) {
						if (connInfo.isFinished()) {
							tableRender.setForegroundColor(i, Color.BLACK);
						}
					}
				}
			}
		});
	}

	private class StartProxyAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2837131097020843304L;
		
		public StartProxyAction() {
			putValue(NAME, "启动代理服务");
			putValue(SHORT_DESCRIPTION, "启动或停止代理服务");
		}
		public void actionPerformed(ActionEvent e) {
			try {
//				dataModel.addConnectionInfo(new ConnectionInfo());
//				table.updateUI();
				
				String name = (String)getValue(NAME);
				if ("启动代理服务".equals(name)) {
					httpProxyServer.setMyTcpPort(Integer.parseInt(portField.getText()));
					httpProxyServer.start();
				} else if ("停止代理服务".equals(name)) {
					httpProxyServer.stop();
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, e1.getLocalizedMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public JTable getTable() {
		if (table == null) {
			table = new JTable(dataModel);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			setTableRenderColor(getTable(), tableRender);
			
			popupMenu = new JPopupMenu();
			addPopup(table, popupMenu);
			
			menuItem_1 = new JMenuItem("\u4FDD\u5B58\u4E3A\u7F13\u5B58\u6587\u4EF6");
			
			menuItem_1.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int[] selecteds = table.getSelectedRows();
					for (int i : selecteds) {
						if(!DataPreparationService.checkSavedFile(dataModel.getConnectionInfo(i))){
							fileSaveConfigEditDialog = new FileSaveConfigEditDialog();
							fileSaveConfigEditDialog.setConnectionInfo(dataModel,i);
						}
					}
					fileSaveConfigEditDialog.setVisible(true);
				}
			});
			
			popupMenu.add(menuItem_1);
			
			mntmurl = new JMenuItem("\u590D\u5236URL");
			mntmurl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
					int i = table.getSelectedRow();
					if (i != -1) {
						// 设置剪切板
						ConnectionInfo connInfo = dataModel.getConnectionInfo(i);
						StringSelection contents = new StringSelection(connInfo.getRequestInfo().getRequest_URL());
						clip.setContents(contents, null);
					}
				}
			});
			popupMenu.add(mntmurl);
			
			menuItem_2 = new JMenuItem("\u6253\u5F00\u7F13\u5B58\u6587\u4EF6\u5939");
			menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = table.getSelectedRow();
					if (i != -1) {
						// 打开文件夹
						ConnectionInfo connInfo = dataModel.getConnectionInfo(i);
						String cacheFile = DataPreparationService.fileUrlPrepare(connInfo.getRequestInfo(), ConfigService.getInstance().local);
						// 打开目录：
						File file=new File(cacheFile);
						if (!file.isFile()) {
							JOptionPane.showMessageDialog(frame, "未缓存该请求，无法打开缓存目录");
							return;
						}
						try {
							Desktop.getDesktop().open(file.getParentFile());
						} catch (IOException e1) {
						}
					}
				}
			});
			popupMenu.add(menuItem_2);
		}
		return table;
	}
	
    public static void setTableRenderColor(JTable table, TableCellRenderer tableRender) {
        try {
            int columnCount = table.getColumnCount();  
            for (int i = 0; i < columnCount; i++) {  
                table.getColumn(table.getColumnName(i)).setCellRenderer(tableRender);  
            }  
        } catch (Exception ex) {
            ex.printStackTrace();  
        }  
    }  
	
	public void startUpdateCount() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					
					int connCount = httpProxyServer.getActiveConnectionCount();
					threadCount.setText(Integer.toString(connCount));
					
					try {
						Thread.sleep(1200L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
