package GUI;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.ToolController;
import model.Tool;

public class ToolList extends JFrame {

	private JList<Tool> list;
	private DefaultListModel<Tool> listRepresentation;
	// private ToolContainer ToolContainer =
	// ToolContainer.getToolContainer();
	private ToolController ToolController = new ToolController();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolList frame = new ToolList();
					frame.setVisible(true);
					System.out.println("Hello World on " + Thread.currentThread());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ToolList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton = new JButton("Add Tool(dialog)");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToolDialog();
			}
		});

		JButton btnNewButton_1 = new JButton("Update Tool(dialog)");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateToolDialog(list.getSelectedValue().getId());
			}
		});

		list = new JList();
		list.setCellRenderer(new ToolRenderer());
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 7;
		gbc_list.gridheight = 3;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 3;
		gbc_list.gridy = 1;
		contentPane.add(list, gbc_list);

		// Testing the Timer
		Timer timer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (list.isSelectionEmpty()) {
						fillList();
					} else {
						Timer timer1 = new Timer(5000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								list.clearSelection();
							}
						});
						timer1.setRepeats(false);
						timer1.start();
					}
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		JButton btnNewButton_3 = new JButton("Refresh");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 2;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fillList();
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_2 = new JButton("Delete Tool");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 3;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTool(list.getSelectedValue().getId());
			}
		});
	}

	private void fillList() throws DataAccessException {
		listRepresentation = new DefaultListModel<Tool>();
		ArrayList<Tool> modelList = (ArrayList<Tool>) ToolController.getTools();
//		if (modelList.isEmpty() || modelList == null || modelList.contains("")) {
//			listRepresentation.addElement("No Tools found...");
//		} else {
//			
//
//		}

		for (Tool p : modelList) {
			listRepresentation.addElement(p);
		}

		list.setModel(listRepresentation);
	}

	private void addToolDialog() {
		JDialog addToolDialog = new AddToolDialog();
		addToolDialog.setVisible(true);
	}

	private void updateToolDialog(int id) {
		Tool Tool = null;
		try {
			Tool = ToolController.findTool(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		JDialog updateToolDialog = new UpdateToolDialog(Tool);
		updateToolDialog.setVisible(true);
	}

	private void deleteTool(int id) {
		try {
			ToolController.deleteTool(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
