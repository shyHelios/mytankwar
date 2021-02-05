import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties properties = new Properties();
	static {
		try {
			properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getInt(String key) {
		if (properties == null)
			return 0;
		return Integer.parseInt((String)properties.get(key));
	}
	
}
