package twet.controllers.dataimport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import twet.persistence.StockDAO;
import twet.persistence.StockQuote;

@Transactional
@Service
public class SampleDataImportService {

	@Autowired
	StockDAO stocks;
	
	@PostConstruct
	public void setupSampleData(){
		stocks.save(new StockQuote("twet","TWET industries PLC", 100, 1));
		stocks.save(new StockQuote("test","TestSoft", 10, 2));
		stocks.save(new StockQuote("abc","ABC", 20, -2));
	}
}
