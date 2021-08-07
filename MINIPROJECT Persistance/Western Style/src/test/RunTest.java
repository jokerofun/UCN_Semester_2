package test;

import java.sql.SQLException;

import control.CustomerController;
import control.DataAccessException;

public class RunTest {

	private CustomerController customerController;

	public RunTest() throws DataAccessException {
		customerController = new CustomerController();
	}

	public static void main(String[] args) throws DataAccessException, SQLException {
		RunTest run = new RunTest();

		// Customer test
		System.out.println(
				run.customerController.addCustomer("San Dogan", "1234565", "d63hfd", "vmghc 45", "FEhj", "8645"));
		// System.out.println(run.customerController.removeCustomer(7));
		// System.out.println(run.customerController.findCustomerById(1).getCustomerName());
	}
}
