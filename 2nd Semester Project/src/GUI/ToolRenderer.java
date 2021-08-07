package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Tool;

public class ToolRenderer extends JLabel implements ListCellRenderer<Tool> {

	public ToolRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Tool> list, Tool tool, int index, boolean isSelected,
			boolean cellHasFocus) {
		String toolInfo = tool.getName() + " - " + tool.getType() + " - " + tool.getLength() + " - "
				+ tool.getDiameter() + " - " + tool.getWear() + " - " + tool.getInStockQuantity();

		setText(toolInfo);

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		return this;
	}

}
