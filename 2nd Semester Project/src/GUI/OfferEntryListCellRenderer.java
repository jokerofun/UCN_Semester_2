package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.OfferEntry;

@SuppressWarnings("serial")
public class OfferEntryListCellRenderer extends JLabel implements ListCellRenderer<OfferEntry> {

	public OfferEntryListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends OfferEntry> list, OfferEntry offerEntry, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = offerEntry.getProductName() + " - " + offerEntry.getQuantity();

		setText(productInfo);

//		if (isSelected) {
//			setBackground(list.getSelectionBackground());
//			setForeground(list.getSelectionForeground());
//		} else {
//			setBackground(list.getBackground());
//			setForeground(list.getForeground());
//		}

		return this;
	}
}
