package by.hodyko.www.dao;


import by.hodyko.www.dao.impl.NewsDaoImpl;
import by.hodyko.www.dao.impl.UserDaoImpl;

public class DaoProvider {
	private static final DaoProvider INSTANCE = new DaoProvider();
	private final UserDao userDao = new UserDaoImpl();
	private final NewsDao newDao = new NewsDaoImpl();
	private final UserDaoImpl registrationInfoDaoImpl = new UserDaoImpl();
	
	private DaoProvider() {
	}

	public static DaoProvider getInstance() {
		return INSTANCE;
	}

	public UserDaoImpl getRegistrationInfoDaoImpl() {
		return registrationInfoDaoImpl;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public NewsDao getNewDao() {
		return newDao;
	}

}
