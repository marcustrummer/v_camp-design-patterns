package br.com.outletstore.director;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import br.com.outletstore.builder.DesktopBuilder;
import br.com.outletstore.builder.NotebookBuilder;

class CatalogTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	Catalog catalog = new Catalog();
	
	@Rule
	public ErrorCollector error = new ErrorCollector();



	@Test
	public void notebookBuilderTest() {
		// Action
		NotebookBuilder builder = new NotebookBuilder();
		catalog.addProductToCatalog(1);
		builder.setBrand("Dell");
		builder.setType("G1511");
		builder.build();
		
		// Verification
		error.checkThat(catalog.getAllProducts().get(0).getSku(), is(equalTo(1)));
		error.checkThat(catalog.getAllProducts().get(0).getPrice(), is(equalTo(12000)));
		error.checkThat(catalog.getAllProducts().get(0).getWeight(), is(equalTo(4500)));
		error.checkThat(builder.getProduct().getBrand(), is(equalTo("Dell")));
		error.checkThat(builder.getProduct().getType(), is(equalTo("G1511")));
	}
	
	@Test
	public void desktopBuilderTest() {
		// Action
		DesktopBuilder builder = new DesktopBuilder();
		catalog.addProductToCatalog(2);
		builder.setBrand("Pc Gamer");
		builder.setType("AORUS");
		builder.build();
		
		// Verification
		error.checkThat(catalog.getAllProducts().get(0).getSku(), is(equalTo(2)));
		error.checkThat(catalog.getAllProducts().get(0).getPrice(), is(equalTo(6800)));
		error.checkThat(catalog.getAllProducts().get(0).getWeight(), is(equalTo(5500)));
		error.checkThat(builder.getProduct().getCpu(), is(equalTo("Pc Gamer")));
		error.checkThat(builder.getProduct().getMonitor(), is(equalTo("AORUS")));
	}
	
	@Test
	public void checkIfAllProductsWereAdded() {
		// Verification
		error.checkThat(catalog.getAllProducts().size(), is(equalTo(2)));
	}
	
	
}
