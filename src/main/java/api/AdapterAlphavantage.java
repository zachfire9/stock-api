package api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;

/**
* The adapter that implements the custom logic for Alphavantage.
* @author  Zach Firestone
* 
*/
public class AdapterAlphavantage extends AdapterStock {

	/**
	 * The API key for the Alphavantage API.
	 */
	private String apiKey;
	
	/**
	 * The constructor that sets the Alphavantage configuration data.
	 * @param configuration
	 */
	public AdapterAlphavantage(Properties configuration) {

		this.setEndpoint(configuration.getProperty("alphavantage.endpoint"));
		this.apiKey = configuration.getProperty("alphavantage.api_key");
	}

	/**
	 * The method for getting the latest daily stock data.
	 * @param symbol The stock symbol.
	 * @return The mapped response data.
	 */
	public HashMap<String, String> getDaily(String symbol) {
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put("function", "TIME_SERIES_INTRADAY");
		parameters.put("symbol", symbol);
		parameters.put("interval", "1min");
		parameters.put("apikey", apiKey);
		String response = this.makeRequest(parameters);
		HashMap<String, String> result = formatResponseDaily(response);
		
		return result;
	}
	
	/**
	 * Maps the response from the Alphavantage API to the standard model.
	 * @param response The response from the Alphavantage API.
	 * @return The mapped response data.
	 */
	private HashMap<String, String> formatResponseDaily(String response) {

		HashMap<String, String> result = new HashMap<String, String>();
		result.put("volume", "");
		result.put("high", "");
		result.put("low", "");
		result.put("close", "");
		result.put("open", "");

		try {
			JSONObject jsonResponse = new JSONObject(response);
			JSONObject objectTimeSeries = jsonResponse.getJSONObject("Time Series (1min)");
			
			Date newestDate = null;
			Iterator<?> keys = objectTimeSeries.keys();
			JSONObject lastTime = new JSONObject();
			while( keys.hasNext() ) {
			    String key = (String)keys.next();
			    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date date = simpleDateFormat.parse(key);

			    if (newestDate == null) {
			    		newestDate = date;
			    }

			    if ( objectTimeSeries.get(key) instanceof JSONObject && newestDate.compareTo(date) <= 0) {
			    		lastTime = new JSONObject(objectTimeSeries.get(key).toString());
			    		newestDate = date;
			    }
			}
			
			keys = lastTime.keys();
			while( keys.hasNext() ) {
			    String key = (String)keys.next();
		    		String value = lastTime.get(key).toString();
		    		String keyParsed = key.replaceAll("^[0-9]+\\.\\s", "");
		    		result.put(keyParsed, value);
			}			
		} catch (Exception e) {
			System.out.println("makeRequest Exception:");
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
