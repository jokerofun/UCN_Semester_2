package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.SaleOrder;

@SuppressWarnings("serial")
public class SaleOrderListCellRenderer extends JLabel implements ListCellRenderer<SaleOrder> {

	public SaleOrderListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends SaleOrder> list, SaleOrder saleOrder, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = saleOrder.getSaleOrderNumber() + " - " + saleOrder.getCustomerName() + " - "
				+ saleOrder.getOrderDate() + " - " + saleOrder.getDueDate() + " - " + saleOrder.getOfferNumber() + " - "
				+ saleOrder.getTotalPrice();

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
