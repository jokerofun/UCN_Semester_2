package observerwebshop;

import java.util.ArrayList;
import java.util.List;

//This represents the SUBJECT in the simplified observer pattern
public class Shop {
	private List<Product> products = new ArrayList<>();
	private List<ProductObserverIF> observers = new ArrayList<>();

	//setState
	public void addProduct (Product product) {
		this.products.add(product);
		this.notifyProductObserver(product);		
	}

	public void registerProductObserver (ProductObserverIF observer) {
		this.observers.add(observer);
	}
	
	public void unregisterProductObserver (ProductObserverIF observer) {
		this.observers.remove(observer);
	}
	
	protected void notifyProductObserver (Product product) {
		this.observers.forEach(observer -> observer.updateProductAdded(product));
	}
	
	
}
