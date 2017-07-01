package twet.unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import twet.currencyconverter.CurrencyConverter;
import twet.currencyconverter.EuroDollarConverter;

public class TestEuroDollarConverter {

	@Test
	public void testConvertion(){
		CurrencyConverter cc = new EuroDollarConverter();
		assertEquals(4.56, cc.convert(4),0.01);
	}
}
