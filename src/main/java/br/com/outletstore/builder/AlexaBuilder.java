package br.com.outletstore.builder;

import br.com.outletstore.entity.Alexa;

public class AlexaBuilder implements IProductBuilder{

	public int sku;
	
	public double price;
	
	public double weight;
	
	public String color;
	
	public String voice;
	
	private Alexa alexa;
	
	public AlexaBuilder setSku(int sku) {
		this.sku = sku;
		return this;
	}


	public AlexaBuilder setPrice(double price) {
		this.price = price;
		return this;
	}


	public AlexaBuilder setWeight(double weight) {
		this.weight = weight;
		return this;
	}
	


	public AlexaBuilder setColor(String color) {
		this.color = color;
		return this;
	}
	
	public AlexaBuilder setVoice(String voice) {
		this.voice = voice;
		return this;
		
	}


	@Override
	public Alexa build() {
		this.alexa = new Alexa();
		alexa.sku = this.sku;
		alexa.price = this.price;
		alexa.weight = this.weight;
		alexa.color = this.color;
		alexa.voice = this.voice;
		return alexa;
	}

	


	public Alexa getProduct() {
		return alexa;
	}

}
