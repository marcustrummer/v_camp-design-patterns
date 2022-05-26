package br.com.outletstore.director;

import br.com.outletstore.builder.NotebookBuilder;
import br.com.outletstore.entity.Notebook;

public class Director {
	
	NotebookBuilder notebookBuilder = new NotebookBuilder();
	
	
	public Notebook notebookBuilder() {

		notebookBuilder.setSku(1).setPrice(12000).setWeight(3200);
		notebookBuilder.setStockQuantity(10);
		notebookBuilder.setStockReserved(0);
		notebookBuilder.setType("G15 L1114");
		notebookBuilder.setBrand("Dell");
		
		return notebookBuilder.build();
	}



	
}
