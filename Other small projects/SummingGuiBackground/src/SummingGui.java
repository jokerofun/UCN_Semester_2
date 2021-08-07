import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import javax.swing.JButton;

public class SummingGui extends SwingWorker<String, Object> {

	private JFrame frame;
	private JTextField textFieldInput1;
	private JButton btnDSomethingElse;
	private JTextField textSumStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SummingGui window = new SummingGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SummingGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Run calculation in background");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblHeader = new JLabel("Summing");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHeader.setBounds(10, 11, 163, 33);
		frame.getContentPane().add(lblHeader);

		textFieldInput1 = new JTextField();
		textFieldInput1.setBounds(10, 169, 243, 20);
		frame.getContentPane().add(textFieldInput1);
		textFieldInput1.setColumns(10);

		JButton btnStartSequential = new JButton("Start sequential summing");
		btnStartSequential.addActionListener(e -> {
			btnStartSequential.setEnabled(false);
			textSumStatus.setText("Summing started");
			// Start worker in background
			SummingGui.this.execute();
		});
		btnStartSequential.setBounds(10, 55, 243, 23);
		frame.getContentPane().add(btnStartSequential);

		btnDSomethingElse = new JButton("Do something else");
		btnDSomethingElse.addActionListener(e -> handleSomethingElseEvent());
		btnDSomethingElse.setBounds(10, 135, 243, 23);
		frame.getContentPane().add(btnDSomethingElse);

		textSumStatus = new JTextField();
		textSumStatus.setBounds(10, 89, 243, 20);
		frame.getContentPane().add(textSumStatus);
		textSumStatus.setColumns(10);
	}


	protected void handleSomethingElseEvent() {
		String foundIntStr = textFieldInput1.getText();
		int foundInt = 0;
		try {
			foundInt = Integer.parseInt(foundIntStr);
		} catch(Exception ex) {
			foundInt = 0;
		}
		foundInt++;
		textFieldInput1.setText(""+foundInt);
	}


	// SwingWorker - execution on a worker thread
	@Override
	protected String doInBackground() throws Exception {
		return new SequentialSumming().sequentialSumming(40000, 500000);
	}

	// SwingWorker - called when doInBackground() has finished
	@Override
	protected void done() {
		try {
			textSumStatus.setText(get());
		} catch (Exception ignore) {
		}
	}

}
