package GUI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyListCellRenderer implements ListCellRenderer<String> {
	private DefaultListCellRenderer dfcr;

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String item, int index,
			boolean isSelected, boolean cellHasFocus) {
		dfcr = new DefaultListCellRenderer();
		item += " is what I like";
		return dfcr.getListCellRendererComponent(list, item, index, isSelected, cellHasFocus);
	}
}
