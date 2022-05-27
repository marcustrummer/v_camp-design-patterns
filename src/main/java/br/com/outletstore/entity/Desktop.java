package br.com.outletstore.entity;

public class Desktop extends Product{
	
	public String cpu;
	
	public String monitor;
	
	
public Desktop() {}
	
	public Desktop(String cpu, String monitor) {
		this.cpu = cpu;
		this.monitor = monitor;
	}

	public String getCpu() {
		return cpu;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String toString() {
		return "PC: \n"+ "[Cpu: " +   this.getCpu() + "]\n"
		                   + "[Monitor: " + this.getMonitor() + "]\n" 
		                   + "[Price: R$" +     Double.toString(this.getPrice()) + "]\n"
		                   + "[Weight: " +    Double.toString(this.getWeight()) + "kg]\n";
		                   
	}

	
}
