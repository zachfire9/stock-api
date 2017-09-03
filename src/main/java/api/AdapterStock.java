package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
* Our stock adapters will extend this and apply their custom business logic.
* @author  Zach Firestone
* 
*/
public abstract class AdapterStock implements Adapter {
	
	/**
	 * The endpoint of the adapter.
	 */
	private String endpoint;
	
	/**
	 * The method that makes requests to the API.
	 * @param queryParams The query params for the GET request.
	 * @return
	 */
	public String makeRequest (Map<String, String> queryParams) {
		
		StringBuffer output = new StringBuffer();
		String response = "";
		
		try {
			String uri = this.endpoint + this.getParamsString(queryParams);
			Logger RequestLog = new Logger("request", uri);
			RequestLog.start();
			URL url = new URL(uri);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(
			  new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				output.append(inputLine);
			}
			in.close();
			
			con.disconnect();
			
			response = output.toString().trim();		
		} catch (Exception e) {
			System.out.println("makeRequest Exception:");
			System.out.println(e.getMessage());
		}
		
		return response;
	}
	
	/**
	 * Convert the params hash map to a query string.
	 * @param params The query params hash map.
	 * @return The query params string.
	 * @throws Exception
	 */
    public String getParamsString(Map<String, String> params) throws Exception {

        StringBuilder result = new StringBuilder("?");
 
        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }
 
        String resultString = result.toString();
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
    }

    /**
     * The endpoint getter.
     * @return The endpoint of the adapter API.
     */
	public String getEndpoint() {

		return endpoint;
	}

	/**
	 * The endpoint setter.
	 * @param endpoint The endpoint of the adapter API.
	 */
	public void setEndpoint(String endpoint) {

		this.endpoint = endpoint;
	}
}
