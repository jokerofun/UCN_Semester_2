package ctr;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class SaleCtrTest {

	@Test
	public void testExceptionIsThrown() {
		SaleCtr tester = new SaleCtr();

		assertThrows(OutOfStockException.class, () -> {
			tester.addProduct(0, 5000);
		}, "");
	}

}
