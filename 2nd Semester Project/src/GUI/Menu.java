package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.SchedulerController;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;
	private SynchronizationController synchronizationController;
	private SchedulerController schedulerController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Menu() throws SQLException {
		synchronizationController = new SynchronizationController();
		try {
			schedulerController = SchedulerController.getSchedulerControllerInstance();
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						schedulerController.schedule();
					} catch (DataAccessException e) {
						e.printStackTrace();
					}
				}
			});
			thread.run();
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(710, 430));
		setBounds(100, 100, 710, 433);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton customerButton = new JButton("Customer");
		customerButton.setBounds(190, 200, 100, 75);
		customerButton.addActionListener(e -> openCustomerFrame());

		JButton employeeButton = new JButton("Employee");
		employeeButton.setBounds(420, 200, 100, 75);
		employeeButton.addActionListener(e -> openEmployeeFrame());

		JButton offerButton = new JButton("Offer");
		offerButton.setBounds(140, 90, 150, 80);
		offerButton.addActionListener(e -> openOfferFrame());

		JButton saleOrderButton = new JButton("Sale Order");
		saleOrderButton.setBounds(420, 90, 150, 80);
		saleOrderButton.addActionListener(e -> openSaleOrderFrame());

		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setBounds(400, 304, 80, 70);
		inventoryButton.addActionListener(e -> openInventoryFrame());

		JButton schedulerButton = new JButton("Scheduler");
		schedulerButton.setBounds(240, 304, 80, 70);
		schedulerButton.addActionListener(e -> openSchedulerFrame());
		contentPane.setLayout(null);
		contentPane.add(customerButton);
		contentPane.add(employeeButton);
		contentPane.add(offerButton);
		contentPane.add(saleOrderButton);
		contentPane.add(schedulerButton);
		contentPane.add(inventoryButton);
	}

	private void openCustomerFrame() {
		CustomerFrame customerFrame = new CustomerFrame(synchronizationController);
		customerFrame.setVisible(true);
	}

	private void openEmployeeFrame() {
		EmployeeFrame employeeFrame = new EmployeeFrame(synchronizationController);
		employeeFrame.setVisible(true);
	}

	private void openOfferFrame() {
		OfferFrame offerFrame = new OfferFrame(synchronizationController);
		offerFrame.setVisible(true);
	}

	private void openSaleOrderFrame() {
		SaleOrderFrame saleOrderFrame = new SaleOrderFrame(synchronizationController);
		saleOrderFrame.setVisible(true);
	}

	private void openInventoryFrame() {
	}

	private void openSchedulerFrame() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					schedulerController.schedule();
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
			}});
		thread.run();
		SchedulerFrame schedulerFrame = new SchedulerFrame(synchronizationController);
		schedulerFrame.setVisible(true);
	}

}
