package twet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDAO extends JpaRepository<StockQuote, String>{

}
