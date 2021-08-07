package observerwebshop;

//This represents the CONCRETE OBSERVER in the simplified observer pattern
public class CountingProductObserver implements ProductObserverIF {

    private static int productsAddedCount = 0;
	
	@Override
	public void updateProductAdded(Product product) {
		productsAddedCount++;
		System.out.println("Total products added: " + productsAddedCount);

	}

}
