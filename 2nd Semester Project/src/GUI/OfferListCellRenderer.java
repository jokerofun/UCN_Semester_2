package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Offer;

@SuppressWarnings("serial")
public class OfferListCellRenderer extends JLabel implements ListCellRenderer<Offer> {

	public OfferListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Offer> list, Offer offer, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = offer.getOfferNumber() + " - " + offer.getTotalPrice() + " - " + offer.getMaterialPrice()
				+ " - " + offer.getDueDate();

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
