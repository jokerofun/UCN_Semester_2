package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Position;

@SuppressWarnings("serial")
public class PositionListCellRenderer extends JLabel implements ListCellRenderer<Position> {

	public PositionListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Position> list, Position position, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = position.getPositionName() + " - " + position.getClearance();

		setText(productInfo);

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
