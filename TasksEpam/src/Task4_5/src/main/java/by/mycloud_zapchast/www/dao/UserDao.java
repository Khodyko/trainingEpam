package by.mycloud_zapchast.www.dao;



import by.mycloud_zapchast.www.entity.BaseUserInfo;
import by.mycloud_zapchast.www.entity.RegistrationInfo;
import by.mycloud_zapchast.www.entity.User;

public interface UserDao {
	public void registerUser(User user, String encryptedPassword, String Salt) throws DAOException;
	public BaseUserInfo getBaseUser(String email) throws DAOException;
	User getUser(String email) throws DAOException;

}

