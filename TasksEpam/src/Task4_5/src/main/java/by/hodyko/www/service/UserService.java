package by.hodyko.www.service;

import by.hodyko.www.bean.RegistrationInfo;
import by.hodyko.www.bean.User;

public interface UserService {

	void registration(RegistrationInfo info) throws ServiceException;

	User authorization(RegistrationInfo info) throws ServiceException;
}
