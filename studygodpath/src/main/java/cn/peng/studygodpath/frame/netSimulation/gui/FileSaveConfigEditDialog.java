package cn.peng.studygodpath.frame.netSimulation.gui;

import cn.peng.studygodpath.frame.netSimulation.gui.model.CatchTableModel;
import cn.peng.studygodpath.frame.netSimulation.service.ConfigService;
import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;
import cn.peng.studygodpath.frame.netSimulation.service.bean.RequestInfo;
import cn.peng.studygodpath.frame.netSimulation.util.FileTransformUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
public class FileSaveConfigEditDialog extends JDialog {
	JPanel jPanel = null;
	JCheckBox[] checkBoxs;
	JLabel lblNewLabel;
	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("cancel");
	List<String> params;
	private CatchTableModel model;
	private ConnectionInfo connectionInfo = null;
	private static Logger logger = LoggerFactory.getLogger(FileSaveConfigEditDialog.class);
	int index;
	public void setConnectionInfo(CatchTableModel dataModel,int index) {
		connectionInfo = dataModel.getConnectionInfo(index);
		model = dataModel;
		this.index = index;
		if(connectionInfo!=null){
			RequestInfo request = connectionInfo.getRequestInfo();
			String[] str = request.getRequest_URL().split("\\?");
			if(str.length>1){
				params.addAll(Arrays.asList(str[1].split("&")));
			}
			if(!StringUtils.isBlank(request.getPostParams())){
				params.addAll(Arrays.asList(request.getPostParams().split("&")));
			}
			jPanel = (JPanel) this.getContentPane();
			jPanel.setLayout(null);
			checkBoxs = new JCheckBox[params.size()];
			for (int i = 0; i < params.size(); i++) {
				checkBoxs[i] = new JCheckBox(params.get(i).split("=")[0]);
			}
			for (int i = 0; i < params.size(); i++) {
				checkBoxs[i].setBounds(new Rectangle(60, 20+25*i, 200, 25));
				jPanel.add(checkBoxs[i]);
			}
			submitButton.setBounds(new Rectangle(200, 20+25*checkBoxs.length+25, 75, 25));
			jPanel.add(submitButton);
			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(bt_actionPerformed(e)){
						getDialogItself().setVisible(false);
					}else{
						
					}
				}
			});
			cancelButton.setBounds(new Rectangle(300, 20+25*checkBoxs.length+25, 75, 25));
			jPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getDialogItself().setVisible(false);
				}
			});
		}
	}

	public FileSaveConfigEditDialog() {
		setTitle("配置文件名");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 835, 520);
		Rectangle rect = getBounds();
		int width  = 400;
		int height = 200;
		setBounds(rect.x+rect.width/2 - width/2, rect.y + rect.height/2 - height/2, width, height);
		
		lblNewLabel = new JLabel("保存文件失败");
		lblNewLabel.setBounds(300, 20, 75, 25);
		lblNewLabel.setForeground(Color.red);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		checkBoxs = null;
		submitButton = new JButton("Submit");
		cancelButton = new JButton("cancel");
		params = new ArrayList<String>();
	}

	public Boolean bt_actionPerformed(ActionEvent e) {
		List<String> fileNameParams = new ArrayList<String>();
		for (int i = 0;i<checkBoxs.length;i++) {
			if(checkBoxs[i].isSelected()){
				fileNameParams.add(params.get(i));
			}
		}
		String file = "";
		RequestInfo request = connectionInfo.getRequestInfo();
		String host = request.getHost();
		if(host.toLowerCase().startsWith("host:")){
			host = host.replace("host:", "").trim();
		}
		String Request_URL = host+request.getRequest_URL();
		String[]str = Request_URL.split("\\?");
		file += (File.separatorChar + str[0]);
		file = StringUtils.replace(file, "//", "#"); // replace // to #
		file = StringUtils.replace(file, ":", "#"); // replace : to #
		file = StringUtils.replaceChars(file, '/', File.separatorChar );
		StringBuffer fileUrl = new StringBuffer();
		fileUrl.append(ConfigService.getInstance().local).append(file).append(File.separatorChar).append(request.getRequest_Method());
		fileUrl.append(File.separatorChar).append(StringUtils.join(fileNameParams.toArray(), "_"));
		fileUrl.append(".har");
		try {
			FileTransformUtil.httpFileTransform(connectionInfo.getTempFileRes(), new File(fileUrl.toString()));
			connectionInfo.setSaved(true);
			model.fireTableRowsUpdated(index, index);
			lblNewLabel.setVisible(false);
			return true;
		} catch (Exception e1) {
			logger.error("保存缓存文件出错", e1);
			lblNewLabel.setVisible(true);
			return false;
		}
	}
	
	public JDialog getDialogItself() {
		return this;
	}
}
