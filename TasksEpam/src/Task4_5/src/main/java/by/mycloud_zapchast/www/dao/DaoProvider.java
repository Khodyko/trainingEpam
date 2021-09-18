package by.mycloud_zapchast.www.dao;



import by.mycloud_zapchast.www.dao.impl.ItemDaoImpl;
import by.mycloud_zapchast.www.dao.impl.UserDaoImpl;

public class DaoProvider {
	private static final DaoProvider INSTANCE = new DaoProvider();
	private static final UserDao USERDAO = new UserDaoImpl();
	private static final ItemDaoImpl ITEMDAO = new ItemDaoImpl();

	
	
	private DaoProvider() {
	}

	public static DaoProvider getInstance() {
		
		return INSTANCE;
	}

	public UserDao getUserDao() {
		return USERDAO;
	}
	public ItemDao getItemDao() {
		return ITEMDAO;
	}	


}
