package by.mycloud_zapchast.www.dao.connectionpool;

import java.util.ResourceBundle;

public class DBItemResourceManager {
	private final static String DB = "resources.properties.db_item";
	private final static DBItemResourceManager instance = new DBItemResourceManager();
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB);

	public static DBItemResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
