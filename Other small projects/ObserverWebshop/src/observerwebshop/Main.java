package observerwebshop;

public class Main {

	public static void main(String[] args) {
		Shop shop = new Shop();
			
	    shop.registerProductObserver(new CountingProductObserver());

		shop.addProduct(new Product("Shoes"));
	    
		shop.registerProductObserver(
           product -> System.out.println("LAMBDA: Added a new product with name '" + product.getName() + "'")
        );		

		shop.addProduct(new Product("Socks"));
		shop.addProduct(new Product("Shirt"));
		shop.addProduct(new Product("Trouser"));
		shop.addProduct(new Product("Jacket"));
	}

}
