package cn.peng.studygodpath.frame.netSimulation.gui;


import cn.peng.studygodpath.frame.netSimulation.service.ConfigService;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@SuppressWarnings("serial")
public class ConfigEditDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField urlRegexField;
	private JLabel lblNewLabel;
	private JTextField saveFolderField;
	private JCheckBox readCacheCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigEditDialog dialog = new ConfigEditDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ConfigEditDialog() {
		this(null);
	}
	/**
	 * Create the dialog.
	 */
	public ConfigEditDialog(Frame frame) {
		super(frame);
		setTitle("配置");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setModal(true);
		
		Rectangle rect = frame.getBounds();
		
		int width  = 400;
		int height = 200;
		
		setBounds(rect.x+rect.width/2 - width/2, rect.y + rect.height/2 - height/2, width, height);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			lblNewLabel = new JLabel("\u8FC7\u6EE4\u8868\u8FBE\u5F0F");
			contentPanel.add(lblNewLabel, "4, 2, right, default");
		}
		{
			urlRegexField = new JTextField();
			lblNewLabel.setLabelFor(urlRegexField);
			contentPanel.add(urlRegexField, "6, 2, 3, 1, fill, default");
			urlRegexField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u6587\u4EF6\u4FDD\u5B58\u8DEF\u5F84");
			contentPanel.add(lblNewLabel_1, "4, 4, right, default");
		}
		{
			saveFolderField = new JTextField();
			contentPanel.add(saveFolderField, "6, 4, fill, default");
			saveFolderField.setColumns(10);
		}
		{
			JButton button = new JButton("\u6D4F\u89C8");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser(saveFolderField.getText());
					chooser.setFileFilter(new FileFilter() {
						
						@Override
						public String getDescription() {
							return "文件夹";
						}
						
						@Override
						public boolean accept(File f) {
							return f.isDirectory();
						}
					});
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    int returnVal = chooser.showOpenDialog(getDialogItself());
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	saveFolderField.setText(chooser.getSelectedFile().getAbsolutePath());
				    }
				}
			});
			contentPanel.add(button, "8, 4");
		}
		{
			JLabel label = new JLabel("\u5F00\u542F\u7F13\u5B58\u8BFB\u53D6\u6A21\u5F0F");
			contentPanel.add(label, "4, 6");
		}
		{
			readCacheCheckBox = new JCheckBox("");
			contentPanel.add(readCacheCheckBox, "6, 6");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveConfigData();
						getDialogItself().setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getDialogItself().setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		fillConfigData();
	}
	
	public void fillConfigData() {
		urlRegexField.setText(ConfigService.getInstance().urlRegex);
		saveFolderField.setText(ConfigService.getInstance().local);
		readCacheCheckBox.setSelected(ConfigService.getInstance().isLocal);
	}
	public void saveConfigData() {
		ConfigService.getInstance().urlRegex = urlRegexField.getText();
		ConfigService.getInstance().local = saveFolderField.getText();
		ConfigService.getInstance().isLocal = readCacheCheckBox.isSelected();
	}

	public JDialog getDialogItself() {
		return this;
	}
}
