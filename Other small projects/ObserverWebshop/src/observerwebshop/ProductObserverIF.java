package observerwebshop;

//This represents the OBSERVER in the simplified observer pattern
@FunctionalInterface
public interface ProductObserverIF {
	public void updateProductAdded (Product product);

}
