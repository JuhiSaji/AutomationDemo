package Utils;

import java.util.HashMap;
import java.util.Map;

public class testUserData {
	  static Map<String, Object> userInfo = new HashMap<>();

	    public static void set(String key, Object value) {
	    	userInfo.put(key, value);
	    }

	    public static Object get(String key) {
	        return userInfo.get(key);
	    }

	    public static void clear() {
	    	userInfo.clear();
	    }
}
