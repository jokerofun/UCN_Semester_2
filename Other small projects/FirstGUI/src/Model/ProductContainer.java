package Model;

import java.util.ArrayList;

public class ProductContainer {
	private static ProductContainer instance;
	private ArrayList<Product> products;

	private ProductContainer() {
		products = new ArrayList<>();
	}

	public static ProductContainer getProductContainer() {
		if (instance == null) {
			instance = new ProductContainer();
		}
		return instance;
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public void removeProduct(Product product) {
		products.remove(product);
	}

	public Product findProductByName(String name) {
		boolean found = false;
		int i = 0;
		while (i < products.size() && !found) {
			if (products.get(i).getName().equals(name)) {
				found = true;
			} else {
				i++;
			}
		}
		if (!found) {
			return null;
		} else {
			return products.get(i);
		}
	}

	public String printInfo() {
		String res = "";
		for (Product p : products) {
			res += p.getInfo() + "\n";
		}
		return res;
	}

	public ArrayList<Product> getAll() {
		ArrayList<Product> all = new ArrayList<>();
		all.addAll(products);
		return all;
	}

}
