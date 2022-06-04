package br.com.outletstore.entity;

public class Notebook extends Product{

	public String brand;
	
	public String type;

	public String toString() {
		return "\nNotebook: \n"+ "[Brand: " +   this.getBrand() + "]\n"
		                   + "[Type: " + this.getType() + "]\n" 
		                   + "[Price: R$" +     Double.toString(this.getPrice()) + "]\n"
		                   + "[Weight: " +    Double.toString(this.getWeight()) + "kg]\n";
		                   
	}

	public Notebook() {}
	
	public Notebook(String brand, String type) {
		this.type = type;
		this.brand = brand;
	}


	
	//GETTERS AND SETTERS	
	public String getBrand() {
		return brand;
	}

	public String getType() {
		return type;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setType(String type) {
		this.type = type;
	}

}
