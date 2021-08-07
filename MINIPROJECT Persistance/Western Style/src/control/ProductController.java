package control;
import db.ProductDb;
import model.Product;

public class ProductController {
	private ProductDb productDb;
	
	public ProductController() {
		productDb = new ProductDb();
	}
	
	public Product findProduct(int productId)throws NullPointerException {
		Product product = null;
		try{
			product = productDb.findById(productId);
		}
		catch(Exception e){
			throw new NullPointerException("Product not found", e);
		}
		return product;
	}

}
