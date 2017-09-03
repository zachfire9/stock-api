package api;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

/**
 * Tests for the Stock model.
 * @author  Zach Firestone
 *
 */
public class StockTest {

	/**
	 * 
	 */
    @Test public void testInitAndGetters() {

    		HashMap<String, String> content = new HashMap<String, String>();
        content.put("volume", "3073038");
        content.put("high", "73.9900");
        content.put("low", "73.9100");
        content.put("open", "73.9400");
        content.put("close", "73.9400");
    		Stock classUnderTest = new Stock("AMZN", content);

    		assertEquals(classUnderTest.getSymbol(), "AMZN");
    		assertEquals(classUnderTest.getVolume(), "3073038");
    		assertEquals(classUnderTest.getHigh(), "73.9900");
    		assertEquals(classUnderTest.getLow(), "73.9100");
    		assertEquals(classUnderTest.getOpen(), "73.9400");
    		assertEquals(classUnderTest.getClose(), "73.9400");
    }
}
