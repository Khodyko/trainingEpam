package by.hodyko.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.hodyko.www.bean.RegistrationInfo;
import by.hodyko.www.bean.RoleEnum;
import by.hodyko.www.bean.User;
import by.hodyko.www.dao.DAOException;
import by.hodyko.www.dao.UserDao;
import by.hodyko.www.dao.connectionpool.ConnectionPoolException;
import by.hodyko.www.dao.connectionpool.NewsConnectionPool;

public class UserDaoImpl implements UserDao {
	private static final String PARAMETR_NAME = "user_name";
	private static final String PARAMETR_PASSWORD = "password";
	private static final String PARAMETR_ROLE = "role";
	private static final String SQL_INSERT_USER = "INSERT INTO users(user_name, password, role) VALUES (?, ?, ?)";
	private static String SQL_GET_AUTHORIZATION = "SELECT * from users WHERE(" + PARAMETR_NAME + "= ? AND "
			+ PARAMETR_PASSWORD + "= ?)";

	public void registration(RegistrationInfo entity) throws DAOException {

		try (Connection connection = NewsConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_INSERT_USER);) {
			pr.setString(1, entity.getLogin());
			pr.setString(2, entity.getPassword());
			pr.setString(3, entity.getRole().toString());
			System.out.println("Remote DB connection established");
			pr.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected SQLException", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("False query", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
	}

	public User authorization(RegistrationInfo entity) throws DAOException {
		// we don't check the role!!! we only get it here!
		User sqlUser;

		try (Connection connection = NewsConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_AUTHORIZATION);) {
			pr.setString(1, entity.getLogin());
			pr.setString(2, entity.getPassword());
			System.out.println("Remote DB connection established");
			ResultSet result = pr.executeQuery();
			if (!result.next()) {
				sqlUser = null;

			} else {
				String login = result.getString(PARAMETR_NAME);
				String roleString = result.getString(PARAMETR_ROLE);
				try {
					RoleEnum roleRole = RoleEnum.valueOf(roleString);
					sqlUser = new User(login, roleRole);
				} catch (IllegalArgumentException e) {
					throw new DAOException("Role of DB is not contains in RoleEnum", e);
				}
			}
			return sqlUser;

		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected SQLException", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("False query", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}

	}

	public void updateLoginPassword(RegistrationInfo target, RegistrationInfo forChanging) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void delete(RegistrationInfo entity) throws DAOException {
		// TODO Auto-generated method stub

	}

}
