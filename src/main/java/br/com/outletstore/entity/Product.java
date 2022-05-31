package br.com.outletstore.entity;

public class Product {
	
	public int sku;
	
	public double price;
	
	public double weight;


	

	
	public Product() {}
	
	
	
	//CONSTRUCTOR	
	public Product(int sku, double price, double weight, int stockQuantity, int stockReserved) {
		super();
		this.sku = sku;
		this.price = price;
		this.weight = weight;
	}
	
	
	

	//GETTERS AND SETTERS	
	public int getSku() {
		return sku;
	}

	public double getPrice() {
		return price;
	}

	public double getWeight() {
		return weight;
	}




	
//	public String toString() {
//		return 
//				"Notebook: \n"+ "[Brand: " +   this.notebook.getBrand() + "]\n"
//		                    "[Type: " + this.notebook + "]\n" 
//		                   +Double.toString(this.getStockQuantity()) +  "[Price: R$" +     Double.toString(this.getPrice()) + "]\n"
//		                   + "[Weight: " +    Double.toString(this.getWeight()) + "kg]\n";
//		                   
//	}





	


	
	

}
