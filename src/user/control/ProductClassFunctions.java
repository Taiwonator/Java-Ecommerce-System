package user.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.OutputStreamWriter;

import user.control.Product;

public class ProductClassFunctions {

	public static List<Product> getProductInfo() {
		List<Product> product_list = new ArrayList<Product>();
		boolean error = false;
		
		try {
			File file = new File("text/ProductData.txt");
			Scanner file_scanner;
			file_scanner = new Scanner(file);
			//Keeps searching till file has no more lines
			while(file_scanner.hasNextLine()) {
				String data = file_scanner.nextLine();
				//Returns a line of data in an array
				List<String> data_listed = returnList(data);
				//Creates a user from the data in a line
				product_list.add(formateProductData(data_listed));
				
			}
			file_scanner.close();
		} catch (FileNotFoundException e) { // Error will be thrown if the file cannot be found
			e.printStackTrace();
			error = true;
		}
		
		if(!error) {
		    return product_list;
		} else {
		    return null;
		}

	}
	
	public static Product formateProductData(List<String> data) {
		Product product = new Product();
		
		product.setBarcode(Integer.parseInt(data.get(0)));
		product.setDeviceName(data.get(1));
		product.setDeviceType(data.get(2));
		product.setBrand(data.get(3));
		product.setColor(data.get(4));
		product.setConnectivity(data.get(5));
		product.setQuantityInStock(Integer.parseInt(data.get(6)));
		product.setOriginalPrice(Float.parseFloat(data.get(7)));
		product.setRetailPrice(Float.parseFloat(data.get(8)));
		
        if(product.getDeviceName().equals("keyboard")) {
        	product.setLayout(data.get(9));
        } else if (product.getDeviceName().equals("mouse")) {
        	product.setButtonCount(Integer.parseInt(data.get(9)));
        }   
        
		return product;
	}
	
	public static List<String> returnList(String line) {
		List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
		return items;
	}
	
	public static void updateProduct(Product product) {
		try {
			  List<Product> products = getProductInfo();
		      File file = new File("text/ProductDataTest.txt");
		      FileOutputStream output_stream = new FileOutputStream(file);
		      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output_stream));
		      for(int i = 0; i < products.size(); i++) {
		    	  if(products.get(i).getBarcode() != product.getBarcode()) {
			    	  writer.write(formatProductObject(products.get(i)));
			    	  writer.newLine();
		    	  } else {
		    		  writer.write(formatProductObject(product));
			    	  writer.newLine();
		    	  }
		      }
		      writer.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static String formatProductObject(Product product) {
		String output = "";
		
		output += Integer.toString(product.getBarcode()) + ", ";
		output += product.getDeviceName() + ", ";
		output += product.getDeviceType() + ", ";
		output += product.getBrand() + ", ";
		output += product.getColor() + ", ";
		output += product.getConnectivity() + ", ";
		output += Integer.toString(product.getQuantityInStock()) + ", ";
		output += Float.toString(product.getOriginalPrice()) + ", ";
		output += Float.toString(product.getRetailPrice()) + ", ";
		if(product.getDeviceName().equals("keyboard")) {
			output += product.getLayout();
		} else {
			output += Integer.toString(product.getButtonCount());
		}

		return output;
	}
	
	public static void addProduct(Product product) {
		try {
		      product.setBarcode(createBarcode());
			  List<Product> products = getProductInfo();
		      File file = new File("text/ProductData.txt");
		      FileOutputStream output_stream = new FileOutputStream(file);
		      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output_stream));
		      for(int i = 0; i < products.size(); i++) {
		    	  writer.write(formatProductObject(products.get(i)));
			  	  writer.newLine();
		      }
	    	  writer.write(formatProductObject(product));
		      writer.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static int createBarcode() {
		int barcode = 0;
		
		List<Product> all_products = getProductInfo();
		
		Random random = new Random();
		int lower_bound = 100000;
		int upper_bound = 999999;
		int result = random.nextInt(upper_bound - lower_bound) + lower_bound;
		
		for(int i = 0; i < all_products.size(); i++) {
			while(result == all_products.get(i).getBarcode()) {
				createBarcode();
			}
		}
		
		barcode = result;
		
		return barcode;
	}
	
	public static List<String> getAllBrands() {
		List<String> brands = new ArrayList<>();
		List<Product> products = getProductInfo();
		
		for(int i = 0; i < products.size(); i++) {
			String brand = products.get(i).getBrand();
			if(!brands.contains(brand)) {
				brands.add(brand);
			}
		}
		
		return brands;
	}
	
}
