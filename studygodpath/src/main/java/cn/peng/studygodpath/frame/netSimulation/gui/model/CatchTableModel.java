package cn.peng.studygodpath.frame.netSimulation.gui.model;


import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CatchTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394304554737546765L;

	public static String[] tableHeaders = {"编号", "方法", "服务器", "地址", "响应状态", "完成", "是否已缓存", "命中数"};

	List<ConnectionInfo> dataList = new ArrayList<ConnectionInfo>();
	
	public int getRowCount() {
		return dataList.size();
	}

	public int getColumnCount() {
		return tableHeaders.length;
	}

	public String getColumnName(int columnIndex) {
		return tableHeaders[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Integer.class;
		}
		return String.class;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ConnectionInfo row = dataList.get(rowIndex);
		if (columnIndex == 0) {
			return rowIndex;
		}
		return getValueOf(row, columnIndex);
	}
	
	public Object getValueOf(ConnectionInfo connInfo, int columnIndex) {
		try {
			switch (columnIndex) {
			case 0:
				return "1";
			case 1:
				return connInfo.getRequestInfo().getRequest_Method();
			case 2:
				return connInfo.getRequestInfo().getHost();
			case 3:
				return connInfo.getRequestInfo().getRequest_URL();
			case 4:
				return connInfo.getResponseInfo().getStatus();
			case 5:
				return Boolean.toString(connInfo.isFinished());
			case 6:
				return Boolean.toString(connInfo.isSaved());
			case 7:
				if (connInfo.isSaved()) {
					return Integer.toString(connInfo.getCacheAimed());
				} else {
					return "";
				}
			default:
				return "default";
			}
		} catch (Exception e) {
			return "error";
		}
	}
	
	public int addConnectionInfo(ConnectionInfo connInfo) {
		dataList.add(connInfo);
		int i = dataList.size()-1;
		fireTableRowsInserted(i, i);
		return i;
	}
	
	public int updateConnectionInfo(ConnectionInfo connInfo) {
		int i = dataList.indexOf(connInfo);
		if (i == -1) {
			return -1;
		}
		dataList.set(i, connInfo);
		fireTableRowsUpdated(i, i);
		return i;
	}
	
	public ConnectionInfo getConnectionInfo(int index) {
		return dataList.get(index);
	}
}
