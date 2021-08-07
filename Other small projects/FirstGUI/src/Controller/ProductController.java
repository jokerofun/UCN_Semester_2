package Controller;

import java.util.ArrayList;

import Model.Product;
import Model.ProductContainer;

public class ProductController {
	public ProductController() {
		products = ProductContainer.getProductContainer();

	}

	private ProductContainer products;

	public void createProduct(String name, double price) {
		Product p = new Product(name, price);
		products.addProduct(p);
	}

	public void deleteProduct(Product product) {
		products.removeProduct(product);
	}

	public Product findProduct(String name) {
		return products.findProductByName(name);
	}

	public void changePrice(String name, double price) {
		Product p = products.findProductByName(name);
		p.setPrice(price);
	}

	public void updateProduct(Product product, String name, double price) {
		product.setName(name);
		product.setPrice(price);
	}

	public ArrayList<Product> getAll() {
		return products.getAll();
	}
}
