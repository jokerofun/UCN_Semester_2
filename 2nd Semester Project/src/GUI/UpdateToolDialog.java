package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ToolController;
import model.Tool;

public class UpdateToolDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ToolController toolController = new ToolController();
	private JTextField name;
	private JTextField quantity;
	private Tool tool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateToolDialog dialog = new UpdateToolDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param tool
	 */
	public UpdateToolDialog(Tool tool) {
		this.tool = tool;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			name = new JTextField(tool.getName());
			GridBagConstraints gbc_name = new GridBagConstraints();
			gbc_name.insets = new Insets(0, 0, 5, 0);
			gbc_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_name.gridx = 3;
			gbc_name.gridy = 1;
			contentPanel.add(name, gbc_name);
			name.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Qty");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			quantity = new JTextField(tool.getInStockQuantity());
			GridBagConstraints gbc_quantity = new GridBagConstraints();
			gbc_quantity.insets = new Insets(0, 0, 5, 0);
			gbc_quantity.fill = GridBagConstraints.HORIZONTAL;
			gbc_quantity.gridx = 3;
			gbc_quantity.gridy = 2;
			contentPanel.add(quantity, gbc_quantity);
			quantity.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// updateTool(name.getText(), Integer.parseInt(quantity.getText()));
						close();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
			}
		}
	}

//	private void updateTool(String name, int quantity) {
//		try {
//			if (name != null) {
//				tool.setName(name);
//			}
//			if (quantity != tool.getInStockQuantity()) {
//				tool.setInStockQuantity(quantity);
//			}
//			toolController.updateTool(tool);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//	}

	private void close() {
		this.dispose();
	}

}
