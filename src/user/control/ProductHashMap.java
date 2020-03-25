package user.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ProductHashMap {
	
	protected HashMap<Product, Integer> products;
	
	public ProductHashMap() {
	    products = new HashMap<>();
	}
	
	public void changeProductAmount(Product product, int value) {
		products.put(product, value);
	}
	
	public void addProduct(Product product) {
		if(products.containsKey(product)) {
			products.put(product, products.get(product) + 1);
		} else {
			products.put(product, 1);
		}
	}
	
	public void addProduct(Product product, int quantity) {
		if(products.containsKey(product)) {
			products.put(product, products.get(product) + quantity);
		} else {
			products.put(product, quantity);
		}
	}
	
	public void removeProduct(Product product) {
		if(products.containsKey(product)) {
			if(products.get(product) > 1) {
				products.put(product, products.get(product) - 1);
			} else {
				products.remove(product);
			}
		} 
	}
	
	public void removeAllProduct(Product product) {
		if(products.containsKey(product)) {
			products.remove(product);
		}
	}
	
	public void clearBasket() {
		products.clear();
	}
	
	public HashMap<Product, Integer> getBasket() {
		return products;
	}
	
	public List<Product> getProducts() {
		List<Product> list = new ArrayList<>();
		list.addAll(products.keySet());
		return list;
	}
	
	public int getBasketSize() {
		Collection<Integer> values = products.values();
		List<Integer> list = new ArrayList<Integer>(products.values());
		int total = 0;
		for(int i  = 0; i < values.size(); i++) {
			total += list.get(i);
		}
		return total;
	}
	
}
