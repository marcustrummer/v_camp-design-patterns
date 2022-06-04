package br.com.outletstore.order;

public enum OrderStatus {
	PENDING(1, "Pending"),
	PAID(2, "Paid"),
	SHIPPED(3, "Shipping"),
	COMPLETED(4, "Completed"),
	CANCELED(5, "Canceled");
	
	
	private int cod;
	private String description;
	
	private OrderStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static OrderStatus toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (OrderStatus x : OrderStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido!! Cód: " + cod + "Não existe!!");
	}
	
}
