package user.control;

import java.util.List;

public class SaveForLaterList extends ProductHashMap {
	
    public SaveForLaterList() {
	    
	}
    
	public void addPair(Basket basket, Product product) {
		if(products.get(product) == null) {
			products.put(product, basket.getBasket().get(product));
		} else {
			products.put(product, products.get(product) + basket.getBasket().get(product));
		}
	}
	
	public void mergeWith(Basket basket) {
//	    products.putAll(basket.getBasket());
		List<Product> products = basket.getProducts();
	    for(int i = 0; i < products.size(); i++) {
	    	Product product = products.get(i);
	    	if(this.products.get(product) == null) {
				this.products.put(product, basket.getBasket().get(product));
	    	} else {
	    		this.products.put(product, this.products.get(product) + basket.getBasket().get(product));
	    	}
	    }
	}
    
}
