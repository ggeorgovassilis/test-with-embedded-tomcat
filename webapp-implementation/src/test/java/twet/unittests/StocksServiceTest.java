package twet.unittests;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import twet.controllers.ResourceNotFoundException;
import twet.controllers.stocks.StocksService;
import twet.persistence.StockDAO;
import twet.persistence.StockQuote;
import static org.junit.Assert.*;

/**
 * Simple unit test for {@link StocksService}. Mocks the dependency on {@link StockDAO}
 * @author george georgovassilis
 *
 */
public class StocksServiceTest {

	StocksService service;
	StockDAO stocks;
	
	@Before
	public void setup(){
		service = new StocksService();
		stocks = mock(StockDAO.class);
		service.setStocks(stocks);
	}
	
	@Test
	public void testLookupStocks(){
		String testStockSymbol="test";
		StockQuote testStock = new StockQuote(testStockSymbol, "test stock", 10, 1);
		when(stocks.findOne(eq(testStockSymbol))).thenReturn(testStock);
		
		StockQuote foundStock = service.getStockQuote(testStockSymbol);
		assertEquals(testStock, foundStock);
	}

	@Test(expected=ResourceNotFoundException.class)
	public void testLookupStocks_non_existing_stock(){
		String testStockSymbol="na";
		
		service.getStockQuote(testStockSymbol);
	}
}
