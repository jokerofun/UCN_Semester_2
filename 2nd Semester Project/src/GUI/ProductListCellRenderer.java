package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Product;

@SuppressWarnings("serial")
public class ProductListCellRenderer extends JLabel implements ListCellRenderer<Product> {

	public ProductListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Product> list, Product product, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = product.getProductName() + " - " + product.getDescription() + " - " + product.getPrice()
				+ " - " + product.getQuantity();

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
