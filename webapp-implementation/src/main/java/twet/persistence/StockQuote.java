package twet.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockQuote implements Serializable{

	@Id
	protected String symbol;
	
	@Column
	protected String name;

	@Column
	protected double value;
	
	@Column
	protected double change;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}
	
	public StockQuote() {
	}

	public StockQuote(String symbol, String name, double value, double change) {
		this.symbol = symbol;
		this.name = name;
		this.value = value;
		this.change = change;
	}
}
