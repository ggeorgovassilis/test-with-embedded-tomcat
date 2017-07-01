package twet.currencyconverter;

public class EuroDollarConverter implements CurrencyConverter{

	@Override
	public double convert(double amount) {
		return (1.14*amount);
	}

}
