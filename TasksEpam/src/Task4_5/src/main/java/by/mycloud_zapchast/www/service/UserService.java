package by.mycloud_zapchast.www.service;

import by.mycloud_zapchast.www.entity.RegistrationInfo;
import by.mycloud_zapchast.www.entity.User;

public interface UserService {

	public void registerUser(RegistrationInfo info) throws ServiceException;
	public User authorizeUser(String email, String password) throws ServiceException;
}
