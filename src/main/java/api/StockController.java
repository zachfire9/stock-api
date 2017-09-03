package api;

import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* The controller for stock endpoints.
* @author  Zach Firestone
* 
*/
@RestController
public class StockController {
	
	/**
	 * The name of the adapter.
	 */
	@Value("${adapter}")
	private String adapterName;

	/**
	 * The daily stock resource path.
	 * @param symbol The stock symbol.
	 * @return The Stock model initialized with the stock data.
	 */
    @RequestMapping("/daily")
    public Stock daily(@RequestParam(value="symbol", defaultValue="AMZN") String symbol) {
    	
    		HashMap<String, String> content = new HashMap<String, String>();
 
    		try {
			Properties configuration = PropertiesLoader.loadProperties("application.properties");
			AdapterStock adapter = StockFactory.getStockAdapter(adapterName, configuration);
			content = adapter.getDaily(symbol);
    		} catch (Exception e) {
    			System.out.println("daily Exception:");
    			System.out.println(e.getMessage());
    		}
 
    		return new Stock(symbol, content);
    }
}


