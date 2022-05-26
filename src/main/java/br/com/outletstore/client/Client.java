package br.com.outletstore.client;

import br.com.outletstore.director.Catalog;

public class Client {
	
	
	public static void main(String[] args) {
		
		Catalog catalog = new Catalog();
		
		catalog.addProductToCatalog(1);
		
		
		System.out.println(catalog.catalog.get(0));
//				
//		NotebookBuilder builder = new NotebookBuilder();
//		builder.setSku(1).setPrice(12000).setWeight(3200);
//		builder.setStockQuantity(10);
//		builder.setStockReserved(0);
//		builder.setType("G15 L1114");
//		builder.setBrand("Dell");
//		
//		Notebook notebook = builder.build();
//		
//
//		Catalog catalog = new Catalog();
//		catalog.catalog.add(builder.getProduct());		
//		System.out.println(notebook.toString());

		

		
		
	}

}
