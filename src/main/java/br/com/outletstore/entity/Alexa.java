package br.com.outletstore.entity;

public class Alexa extends Product{
	
	public String color;
	
	public String voice;

	public String toString() {
		return "\nAlexa: \n"+ "[Color: " +   this.getColor() + "]\n"
		                   + "[Voice: " + this.getVoice() + "]\n" 
		                   + "[Price: R$" +     Double.toString(this.getPrice()) + "]\n"
		                   + "[Weight: " +    Double.toString(this.getWeight()) + "kg]\n";
		                   
	}

	public Alexa() {}
	
	public Alexa(String color, String voice) {
		this.color = color;
		this.voice = voice;
	}

	public String getColor() {
		return color;
	}

	public String getVoice() {
		return voice;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	
	
	

	
	//GETTERS AND SETTERS	


}
