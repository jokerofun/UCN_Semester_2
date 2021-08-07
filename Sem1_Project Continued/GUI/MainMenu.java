package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		this.setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel1 = new JPanel();
		contentPane.add(buttonPanel1, BorderLayout.WEST);
		GridBagLayout gbl_buttonPanel1 = new GridBagLayout();
		gbl_buttonPanel1.columnWidths = new int[] { 120, 18, 24, 26, 56, 0 };
		gbl_buttonPanel1.rowHeights = new int[] { 19, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_buttonPanel1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_buttonPanel1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		buttonPanel1.setLayout(gbl_buttonPanel1);

		JButton btnNewButton_2 = new JButton("Manage Order");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		buttonPanel1.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_2.setBackground(new Color(173, 216, 230));

		JButton btnNewButton_4 = new JButton("Rent Object");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 2;
		buttonPanel1.add(btnNewButton_4, gbc_btnNewButton_4);
		btnNewButton_4.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_4.setBackground(new Color(173, 216, 230));

		JButton btnNewButton_1 = new JButton("Manage Person");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3;
		buttonPanel1.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_1.setBackground(new Color(173, 216, 230));

		JPanel quitPanel = new JPanel();
		contentPane.add(quitPanel, BorderLayout.SOUTH);
		quitPanel.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton_5 = new JButton("Quit");
		quitPanel.add(btnNewButton_5, BorderLayout.WEST);
		btnNewButton_5.setBackground(new Color(173, 216, 230));
		btnNewButton_5.setFont(new Font("Monospaced", Font.PLAIN, 11));

		JPanel buttonPanel2 = new JPanel();
		contentPane.add(buttonPanel2, BorderLayout.EAST);
		GridBagLayout gbl_buttonPanel2 = new GridBagLayout();
		gbl_buttonPanel2.columnWidths = new int[] { 0, 0 };
		gbl_buttonPanel2.rowHeights = new int[] { 0, 13, 0, 0, 0, 0 };
		gbl_buttonPanel2.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonPanel2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		buttonPanel2.setLayout(gbl_buttonPanel2);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		buttonPanel2.add(label, gbc_label);

		JButton btnNewButton_6 = new JButton("Create/Edit Warehouse");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6.gridx = 0;
		gbc_btnNewButton_6.gridy = 2;
		buttonPanel2.add(btnNewButton_6, gbc_btnNewButton_6);
		btnNewButton_6.setBackground(new Color(173, 216, 230));
		btnNewButton_6.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageWarehouse();
			}
		});

		JButton btnNewButton = new JButton("Manage Inventory");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		buttonPanel2.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(173, 216, 230));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageInventory();
			}
		});

		JButton btnNewButton_3 = new JButton("Create Statistics");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 4;
		buttonPanel2.add(btnNewButton_3, gbc_btnNewButton_3);
		btnNewButton_3.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_3.setBackground(new Color(173, 216, 230));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
	}

	private void manageInventory() {
		try {
			ManageInventory manageInventory = new ManageInventory();
			manageInventory.setVisible(true);
			// this.setVisible(false);
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void manageWarehouse() {
		try {
			EditWarehouse w = new EditWarehouse();
			w.setVisible(true);
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void quit() {
		System.exit(0);
	}

}
