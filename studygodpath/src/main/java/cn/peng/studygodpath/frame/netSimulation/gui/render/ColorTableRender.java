package cn.peng.studygodpath.frame.netSimulation.gui.render;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ColorTableRender extends DefaultTableCellRenderer {
	
	List<Color> foregroundColorList = new ArrayList<Color>();
	List<Color> backgroundColorList = new ArrayList<Color>();
	
	public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
		
		if (foregroundColorList.size() > row) {
			setForeground(ifNull(foregroundColorList.get(row), Color.BLACK));
		} else {
			setForeground(Color.BLACK);
		}
		if (backgroundColorList.size() > row) {
			setBackground(ifNull(backgroundColorList.get(row), Color.WHITE));
		} else {
			setBackground(Color.WHITE);
		}

        return super.getTableCellRendererComponent(table, value,  
                isSelected, hasFocus, row, column);  
    }
	
	public void setBackgroundColor(int index, Color color) {
		if (index > backgroundColorList.size()) {
			int ext = index - backgroundColorList.size() + 1;
			while (ext-- > 0) {
				backgroundColorList.add(Color.WHITE);
			}
		}
		backgroundColorList.set(index, color);
	}
	
	public void  setForegroundColor(int index, Color color) {
		if (index >= foregroundColorList.size()) {
			int ext = index - foregroundColorList.size() + 1;
			while (ext-- > 0) {
				foregroundColorList.add(Color.BLACK);
			}
		}
		foregroundColorList.set(index, color);
	}
	
	public static Color ifNull(Color c, Color def) {
		if (c == null) {
			return def;
		}
		return c;
	}
}
