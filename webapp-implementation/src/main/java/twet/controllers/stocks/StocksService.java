package twet.controllers.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import twet.controllers.ResourceNotFoundException;
import twet.persistence.StockDAO;
import twet.persistence.StockQuote;

@RestController
@Transactional
@RequestMapping("/api/stocks/")
public class StocksService {

	@Autowired
	StockDAO stocks;

	public void setStocks(StockDAO stocks) {
		this.stocks = stocks;
	}

	@RequestMapping(value = "{symbol}", method = RequestMethod.GET)
	public StockQuote getStockQuote(@PathVariable("symbol") String symbol) {
		StockQuote stock = stocks.findOne(symbol);
		if (stock == null)
			throw new ResourceNotFoundException();
		return stock;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resource not found")
	@ExceptionHandler(ResourceNotFoundException.class)
	public @ResponseBody String resourceNotFound() {
		return "Resource not found";
	}
}
