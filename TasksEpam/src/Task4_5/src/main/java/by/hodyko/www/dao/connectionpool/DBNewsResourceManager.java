package by.hodyko.www.dao.connectionpool;

import java.util.ResourceBundle;

public class DBNewsResourceManager {
	private final static String DB = "resources.properties.db_news";
	private final static DBNewsResourceManager instance = new DBNewsResourceManager();
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB);

	public static DBNewsResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
