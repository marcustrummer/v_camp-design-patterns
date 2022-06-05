package br.com.outletstore.director;

import java.util.ArrayList;
import java.util.List;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.entity.Desktop;
import br.com.outletstore.entity.Notebook;
import br.com.outletstore.entity.Product;
import br.com.outletstore.exceptions.InventoryException;

public class Catalog {
		
	public List<Product> catalog = new ArrayList<>();
	
	
	public void addProductToCatalog(int typeOfProduct) throws InventoryException {

		NotebookBuilder notebookBuilder = new NotebookBuilder();
		DesktopBuilder desktopBuilder = new DesktopBuilder();
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
			    
			default: 
				throw new InventoryException("Product not found!");
				
		}
	}
	
	public List<Product> getAllProducts(){
		return catalog;
	}
	

	
	
	

}
