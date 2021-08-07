package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.ProcedureController;

@SuppressWarnings("serial")
public class DeleteProcedureDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ProcedureController procedureController;
	private SynchronizationController synchronizationController;

	/**
	 * Create the dialog.
	 */
	public DeleteProcedureDialog(int code, SynchronizationController synchronizationController) {
		try {
			procedureController = new ProcedureController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Delete Menu");
		setBounds(100, 100, 433, 60);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel = new JLabel("Are you sure you want to delete?");
				lblNewLabel.setForeground(Color.RED);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				buttonPane.add(lblNewLabel);
			}
			{
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(e -> {
					try {
						confirm(code);
					} catch (DataAccessException e1) {
						e1.printStackTrace();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(e -> cancel());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void confirm(int code) throws DataAccessException {
		procedureController.deleteProcedure(code);
		synchronizationController.set();
		this.dispose();
	}

	private void cancel() {
		this.dispose();
	}
}
