package user.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Basket extends ProductHashMap{
		
	public Basket() {
	    
	}
	
	public float getTotal() {
		float total = 0;
		
		List<Product> keys = this.getProducts();
		for(int i = 0; i < keys.size(); i++) {
			total += products.get(keys.get(i)) * keys.get(i).getRetailPrice();
		}
		
		return total;
	}
	
	public void addPair(SaveForLaterList basket, Product product) {
		if(products.get(product) == null) {
			products.put(product, basket.getBasket().get(product));
		} else {
			products.put(product, products.get(product) + basket.getBasket().get(product));
		}
	}
	
	public void mergeWith(SaveForLaterList sfl_list) {
//	    products.putAll(sfl_list.getBasket());
	    List<Product> products = sfl_list.getProducts();
	    for(int i = 0; i < products.size(); i++) {
	    	Product product = products.get(i);
	    	if(this.products.get(product) == null) {
				this.products.put(product, sfl_list.getBasket().get(product));
	    	} else {
	    		this.products.put(product, this.products.get(product) + sfl_list.getBasket().get(product));
	    	}
	    }
	}

}
