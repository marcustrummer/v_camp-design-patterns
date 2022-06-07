package br.com.outletstore.director;

import java.util.ArrayList;
import java.util.List;

import br.com.outletstore.builder.AlexaBuilder;
import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.builder.iPadBuilder;
import br.com.outletstore.entity.Alexa;
import br.com.outletstore.entity.Desktop;
import br.com.outletstore.entity.Notebook;
import br.com.outletstore.entity.Product;
import br.com.outletstore.entity.iPad;
import br.com.outletstore.exceptions.InventoryException;

public class Catalog {
		
	public List<Product> catalog = new ArrayList<>();
	
	
	public void addProductToCatalog(int typeOfProduct) throws InventoryException {

		NotebookBuilder notebookBuilder = new NotebookBuilder();
		DesktopBuilder desktopBuilder = new DesktopBuilder();
		iPadBuilder iPadBuilder = new iPadBuilder();
		AlexaBuilder alexaBuilder = new AlexaBuilder();
		switch(typeOfProduct) {		
			case 1: 
				notebookBuilder.setSku(1);
				notebookBuilder.setPrice(12000);
				notebookBuilder.setWeight(4.5);
				notebookBuilder.setBrand("Dell");
				notebookBuilder.setType("G1511");
				Notebook notebook = notebookBuilder.build();
			    catalog.add(notebook);
			    break;
			case 2:
				desktopBuilder.setSku(2);
				desktopBuilder.setPrice(6800);
				desktopBuilder.setWeight(5.5);
				desktopBuilder.setMonitor("Pc Gamer");
				desktopBuilder.setCpu("AORUS");
				Desktop desktop = desktopBuilder.build();
				catalog.add(desktop);
				break;
			case 3:
				iPadBuilder.setSku(3);
				iPadBuilder.setPrice(2800);
				iPadBuilder.setWeight(0.8);
				iPadBuilder.setMemory("8gb");
				iPadBuilder.setVersion("iPadMini");
				iPad iPad = iPadBuilder.build();
				catalog.add(iPad);
				break;
			case 4:
				alexaBuilder.setSku(4);
				alexaBuilder.setPrice(300);
				alexaBuilder.setWeight(0.3);
				alexaBuilder.setColor("Black");
				alexaBuilder.setVoice("Male");
				Alexa alexa = alexaBuilder.build();
				catalog.add(alexa);
				break;
			default: 
				throw new InventoryException("Product not found!");
				
		}
	}
	
	public List<Product> getAllProducts(){
		return catalog;
	}
	

	
	
	

}
