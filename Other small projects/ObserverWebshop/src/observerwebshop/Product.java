package observerwebshop;

//This represents the STATE in the simplified observer pattern
public class Product {

	private String name;

	public Product (String name ){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
