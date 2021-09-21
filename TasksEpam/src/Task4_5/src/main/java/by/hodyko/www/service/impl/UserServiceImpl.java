package by.hodyko.www.service.impl;

import by.hodyko.www.bean.RegistrationInfo;
import by.hodyko.www.bean.User;
import by.hodyko.www.dao.DAOException;
import by.hodyko.www.dao.DaoProvider;
import by.hodyko.www.dao.UserDao;
import by.hodyko.www.dao.impl.UserDaoImpl;
import by.hodyko.www.service.ServiceException;
import by.hodyko.www.service.UserService;

public class UserServiceImpl implements UserService {
	private static final DaoProvider DAO_PROVIDER = DaoProvider.getInstance();
	private static final UserDao USER_DAO_IMPL = DAO_PROVIDER.getRegistrationInfoDaoImpl();

	public void registration(RegistrationInfo info) throws ServiceException {
		try {
			validation(info);
			USER_DAO_IMPL.registration(info);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public User authorization(RegistrationInfo info) throws ServiceException {
		User user = null;
		try {
			validation(info);
			user = USER_DAO_IMPL.authorization(info);
		} catch (DAOException e) {
			
			throw new ServiceException(e.getMessage(), e);
		}
		if(user==null) {throw new ServiceException("User is not found");}
		return user;
	}

	private boolean validation(RegistrationInfo info) throws ServiceException {
		String login = info.getLogin();
		String password = info.getPassword();
		if (login.length() >=5 && password.length() >= 5) {
			return true;
		} else {
			throw new ServiceException("Login or password is not valid"); // concrete
		}
	}

}
