package api;

import java.util.HashMap;

/**
* Interface that defines our adapter class methods.
* @author  Zach Firestone
*/
public interface Adapter {
	
	/**
	 * Every adapter needs to get the daily stock information.
	 * @param symbol The stock symbol.
	 * @return
	 */
	HashMap<String, String> getDaily(String symbol);
}
