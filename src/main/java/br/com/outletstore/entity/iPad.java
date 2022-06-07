package br.com.outletstore.entity;

public class iPad extends Product{
	
    public String memory;
	
	public String version;

	
	
	public String toString() {
		return "\niPad: \n"+ "[Memory: " +   this.getMemory() + "]\n"
		                   + "[Version: " + this.getVersion() + "]\n" 
		                   + "[Price: R$" +     Double.toString(this.getPrice()) + "]\n"
		                   + "[Weight: " +    Double.toString(this.getWeight()) + "kg]\n";                   
	}

	public iPad() {}
	
	
	
	
	//GETTERS AND SETTERS	
	public iPad(String memory, String version) {
		this.memory = memory;
		this.version = version;
	}

	public String getMemory() {
		return memory;
	}

	public String getVersion() {
		return version;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public void setVersion(String version) {
		this.version = version;
	}


}
