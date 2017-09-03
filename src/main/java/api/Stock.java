package api;

import java.util.HashMap;

/**
* The stock model that holds the data returned via the endpoint.
* @author  Zach Firestone
* 
*/
public class Stock {
	
	/**
	 * The stock symbol.
	 */
	private final String symbol;
	
	/**
	 * The stock volume.
	 */
	private final String volume;
	
	/**
	 * The stock high.
	 */
	private final String high;
	
	/**
	 * The stock low.
	 */
	private final String low;
	
	/**
	 * The stock close.
	 */
	private final String close;
	
	/**
	 * The stock open.
	 */
	private final String open;
	
	/**
	 * Sets all the stock data.
	 * @param symbol The stock symbol.
	 * @param content The stock data.
	 */
    public Stock(String symbol, HashMap<String, String> content) {
    		this.symbol = symbol;
        this.volume = content.get("volume");
        this.high = content.get("high");
        this.low = content.get("low");
        this.close = content.get("close");
        this.open = content.get("open");
    }
    
    /**
     * The symbol getter.
     * @return
     */
    public String getSymbol() {
		return this.symbol;
    }
    
    /**
     * The volume getter.
     * @return
     */
    public String getVolume() {
    		return this.volume;
    }
    
    /**
     * The high getter.
     * @return
     */
    public String getHigh() {
		return this.high;
    }
    
    /**
     * The low getter.
     * @return
     */
    public String getLow() {
		return this.low;
    }
    
    /**
     * The close getter.
     * @return
     */
    public String getClose() {
		return this.close;
    }
    
    /**
     * The open getter.
     * @return
     */
    public String getOpen() {
		return this.open;
    }
}
