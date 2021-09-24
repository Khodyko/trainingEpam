package by.mycloud_zapchast.www.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static final String PASSWORD_PATTERN = "^(?=^.{6,20}$)(?=.*[0-9])(?=.*[A-Za-zА-Яа-я!@#$%^&*?]).*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	private static final String NAME_PATTERN = "^[А-Яа-яЁё//-]{3,20}$";
	private static final DaoProvider DAO_PROVIDER = DaoProvider.getInstance();
	private static final UserDao USER_DAO = DAO_PROVIDER.getUserDao();

	public void registerUser(RegistrationInfo info) throws ServiceException {
		User user = null;
		try {
			String salt = getNewSalt();
			String encryptedPassword = getEncryptedPassword(info.getPassword(), salt);
			user = new User(null, info.getName(), info.getSecondName(), UserRole.of(info.getRole()), info.getEmail(),
					info.getDepoId(), info.getSectorId(), false);
			USER_DAO.registerUser(user, encryptedPassword, salt);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Авторизация недоступна, попробуйте еще раз через некоторое время", e);
		}

	}

	public User authorizeUser(String email, String password) throws ServiceException {
		BaseUserInfo userInfo = null;
		User user = null;
		try {
			validateEmail(email);
			validatePassword(password);
			userInfo = USER_DAO.getBaseUser(email);
			if (userInfo == null) {
				throw new ServiceException("Email или пароль не верны");
			}
			if (isPasswordMatch(userInfo, password)) {
				user = USER_DAO.getUser(email);
				return user;
			} else {
				throw new ServiceException("Email или пароль не верны");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Авторизация недоступна, попробуйте еще раз через некоторое время", e);
		}

	}

	private boolean isPasswordMatch(BaseUserInfo userInfo, String passwordToMatch)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String salt = userInfo.getSalt();
		final String calculatedHash = getEncryptedPassword(passwordToMatch, salt);
		System.out.println("match=1: " + userInfo + "  2: " + calculatedHash);
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

	public void validateNotNullNotEmpty(String value, String name) throws ServiceException {
		if (value == null || value.trim().isBlank()) {
			throw new ServiceException(name + " не должен быть пустым");
		}
	}

	private boolean validateByRegexp(final String value, final String regPattern) {
		final Pattern pattern = Pattern.compile(regPattern);
		final Matcher matcher = pattern.matcher(value);

		return matcher.matches();
	}

	private void validatePassword(String password) throws ServiceException {
		validateNotNullNotEmpty(password, "Пароль");

		if (!validateByRegexp(password, PASSWORD_PATTERN)) {
			throw new ServiceException("Пароль должен содержать минимум 6 символов, из них хотя бы одну цифру");
		}
	}

	private void validateEmail(String email) throws ServiceException {
		validateNotNullNotEmpty(email, "Email");

		if (!validateByRegexp(email, EMAIL_PATTERN)) {
			throw new ServiceException("Email  не корректный");
		}
	}

	@Override
	public void validate1Registration(String name, String secondName) throws ServiceException {
		validateNotNullNotEmpty(name, "Имя");
		validateNotNullNotEmpty(secondName, "Фамилия");
		if (!validateByRegexp(name, NAME_PATTERN)) {
			throw new ServiceException("Имя должно содержать текст кириллицей, возможно тире (3-20 символов)");
		}
		if (!validateByRegexp(name, NAME_PATTERN)) {
			throw new ServiceException("Фамилия должна содержать текст кириллицей, возможно тире (3-20 символов)");

		}
	}
}
