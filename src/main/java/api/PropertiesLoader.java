package api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* This loads the config file and formats it as an object.
* @author  Zach Firestone
* 
*/
public class PropertiesLoader {

	/**
	 * Takes the config file name and creates a object with the data.
	 * @param resourceFileName The config file name.
	 * @return The config object.
	 * @throws IOException
	 */
    public static Properties loadProperties(String resourceFileName) throws IOException {

        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
          .getClassLoader()
          .getResourceAsStream(resourceFileName);
        configuration.load(inputStream);
        inputStream.close();

        return configuration;
    }
}
