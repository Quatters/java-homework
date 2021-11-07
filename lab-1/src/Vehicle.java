public class Vehicle extends TaxableItem {
	private double price;

	public Vehicle(double rate) {
		super(rate);
		price = Math.random() * 1000.0;
	}

	@Override
	public double getListPrice() {
		return price;
	}	
}
