package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FirstFrame extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField textField;
	private JLabel labelHi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame frame = new FirstFrame();
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
	public FirstFrame() {
		labelHi = new JLabel();
		label = new JLabel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProduct = new JMenu("Product");
		menuBar.add(mnProduct);

		JMenuItem mntmAdd = new JMenuItem("Add");
		mnProduct.add(mntmAdd);

		JMenuItem mntmView = new JMenuItem("View");
		mnProduct.add(mntmView);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_2 = new JButton("Hiiii");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked1();
			}
		});
		btnNewButton_2.setBounds(258, 137, 85, 21);
		btnNewButton_2.setBackground(Color.RED);
		contentPane.add(btnNewButton_2);

		label.setText("welp");
		label.setBounds(162, 84, 97, 20);
		contentPane.add(label);

		labelHi.setBounds(200, 50, 97, 20);

		JButton btnNewButton = new JButton("Text Field");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked3();
			}
		});
		btnNewButton.setBounds(48, 137, 153, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New Window");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked2();
			}

		});
		btnNewButton_1.setBounds(300, 219, 112, 21);
		btnNewButton_1.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_1);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					buttonClicked3();
				}
			}
		});
		textField.setBounds(10, 10, 112, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnAddProduct = new JButton("Add product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProductView();
			}
		});
		btnAddProduct.setBounds(0, 182, 150, 21);
		contentPane.add(btnAddProduct);

		JButton btnViewProducts = new JButton("View Products");
		btnViewProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProducts();
			}
		});
		btnViewProducts.setBounds(0, 213, 150, 21);
		contentPane.add(btnViewProducts);

		JButton btnUpdateProducts = new JButton("Update Products");
		btnUpdateProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateProducts();
			}
		});
		btnUpdateProducts.setBounds(0, 244, 150, 21);
		contentPane.add(btnUpdateProducts);

	}

	private void buttonClicked1() {
		label.setText("Hello, Swing!");

	}

	private void buttonClicked2() {
		Dialog dialog = new Dialog();
		dialog.setVisible(true);
	}

	private void buttonClicked3() {
		labelHi.setText("Hi: " + textField.getText());
		contentPane.add(labelHi);
	}

	private void showProductView() {
		try {
			ProductView productView = new ProductView(null);
			productView.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showProducts() {
		ShowProducts showProducts = new ShowProducts();
		showProducts.setVisible(true);
	}

	private void updateProducts() {
		UpdateProducts updateProducts = new UpdateProducts();
		updateProducts.setVisible(true);
	}
}
