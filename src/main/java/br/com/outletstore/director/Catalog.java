package br.com.outletstore.director;

import java.util.ArrayList;
import java.util.List;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.entity.Desktop;
import br.com.outletstore.entity.Notebook;
import br.com.outletstore.entity.Product;

public class Catalog {
		
	public List<Product> catalog = new ArrayList<>();
	
	
	public void addProductToCatalog(int typeOfProduct) {

		NotebookBuilder notebookBuilder = new NotebookBuilder();
		DesktopBuilder desktopBuilder = new DesktopBuilder();
		switch(typeOfProduct) {		
			case 1: 
				notebookBuilder.setSku(1);
				notebookBuilder.setPrice(12000);
				notebookBuilder.setWeight(4500);
				notebookBuilder.setStockQuantity(10);
				notebookBuilder.setStockReserved(0);
				notebookBuilder.setBrand("Dell");
				notebookBuilder.setType("G1511");
				Notebook notebook = notebookBuilder.build();
			    catalog.add(notebook);
			    break;
			case 2:
				desktopBuilder.setSku(2);
				desktopBuilder.setPrice(6800);
				desktopBuilder.setWeight(5500);
				desktopBuilder.setStockQuantity(10);
				desktopBuilder.setStockReserved(0);
				desktopBuilder.setBrand("Pc Gamer");
				desktopBuilder.setType("AORUS");
				Desktop desktop = desktopBuilder.build();
				catalog.add(desktop);
				break;
			    
			default: 
				System.out.println("Type of product does not exists");
		}
	}
	
	public List<Product> getAllProducts(){
		return catalog;
	}
	

	
	
	

}
