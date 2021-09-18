package by.mycloud_zapchast.www.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import by.mycloud_zapchast.www.dao.DAOException;
import by.mycloud_zapchast.www.dao.DaoProvider;
import by.mycloud_zapchast.www.dao.ItemDao;
import by.mycloud_zapchast.www.dao.UserDao;
import by.mycloud_zapchast.www.entity.BaseUserInfo;
import by.mycloud_zapchast.www.entity.RegistrationInfo;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.entity.UserRole;
import by.mycloud_zapchast.www.service.ServiceException;
import by.mycloud_zapchast.www.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static final DaoProvider DAO_PROVIDER=DaoProvider.getInstance();
	private static final UserDao USER_DAO= DAO_PROVIDER.getUserDao();
	
	public void registerUser(RegistrationInfo info) throws ServiceException {
		try {
			String salt= getNewSalt();
			String encryptedPassword = getEncryptedPassword(info.getPassword(), salt);
			User user = new User(info.getName(), info.getSecondName(), UserRole.of(info.getRole()), info.getEmail(), info.getNameDepo(), info.getSector(), false);
			USER_DAO.registerUser(user, encryptedPassword, salt);
		} catch (DAOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw new ServiceException();
			// TODO: handle exception
		}
	
	}
	
	public User authorizeUser(String email, String password) throws ServiceException {
		BaseUserInfo userInfo=null;
		User user=null;
		try {
			System.out.println("email= "+email);
			userInfo=USER_DAO.getBaseUser(email);
			if(userInfo==null) {
				System.out.println("email or password is wrong");
				throw new ServiceException("email or password is wrong");
			}
			if(isLoginPasswordMatch(userInfo, password)) {
				user=USER_DAO.getUser(email);
				return user;
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException();
			// TODO: handle exception
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
			
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
			
		}
		return user;
	}
	
	 private boolean isLoginPasswordMatch( BaseUserInfo userInfo,  String passwordToMatch)
		      throws NoSuchAlgorithmException, InvalidKeySpecException {
		    final String salt = userInfo.getSalt();
		    final String calculatedHash = getEncryptedPassword(passwordToMatch, salt);
		    System.out.println("match=1: "+userInfo+"  2: "+calculatedHash);
		    return calculatedHash.equals(userInfo.getPassword());
		  }
	
	 private String getEncryptedPassword(final String password, final String salt)
		      throws NoSuchAlgorithmException, InvalidKeySpecException {
		    final String algorithm = "PBKDF2WithHmacSHA1";
		    final int derivedKeyLength = 160;
		    final int iterations = 20000;

		    final byte[] saltBytes = Base64.getDecoder().decode(salt);
		    final KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
		    final SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		    final byte[] encBytes = f.generateSecret(spec).getEncoded();
		    return Base64.getEncoder().encodeToString(encBytes);
		  }
	private String getNewSalt() throws NoSuchAlgorithmException {
	    final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	    final byte[] salt = new byte[8];
	    random.nextBytes(salt);
	    return Base64.getEncoder().encodeToString(salt);
	  }
}
