package api;

import java.util.Properties;

/**
* Factory class that loads the adapter defined in the config.
* @author  Zach Firestone
* 
*/
public class StockFactory {

	/**
	 * Factory method for getting the corresponding adapter class.
	 * @param adapterName The name of the adapter we want to get the corresponding class for.
	 * @param configuration The config object.
	 * @return The initialized adapter class.
	 */
	public static AdapterStock getStockAdapter (String adapterName, Properties configuration) {
		
		AdapterStock adapter;
		
		switch (adapterName) {
			case "alphavan":  
				adapter = new AdapterAlphavantage(configuration);
				break;
			default: 
				adapter = new AdapterAlphavantage(configuration);
		}
		
		return adapter;
	}
}
