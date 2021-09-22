package by.mycloud_zapchast.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.mycloud_zapchast.www.dao.DAOException;
import by.mycloud_zapchast.www.dao.UserDao;
import by.mycloud_zapchast.www.dao.connectionpool.ConnectionPoolException;
import by.mycloud_zapchast.www.dao.connectionpool.ItemConnectionPool;
import by.mycloud_zapchast.www.entity.BaseUserInfo;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.entity.UserRole;

public class UserDaoImpl implements UserDao {
	private static final String TECHNICAL_MSG_SQL_EXCP="DAO: SQL EXCEPTION";
	private static final String TECHNICAL_MSG_POOL_EXCP="DAO: POOL EXCEPTION";
	private static final String TECHNICAL_MSG_SOME_UNEXPECTED_EXCP="DAO: SOME UNEXPECTED EXCEPTION";
	
	private static final String USER_MSG_SQL_EXCP="У нас технические неполадки, попробуйте зайти на страницу через некоторое время";
	private static final String PARAMETR_USER_ID = "user.id_user";
	private static final String PARAMETR_USER_NAME = "user.name";
	private static final String PARAMETR_USER_SECOND_NAME = "user.surname";
	private static final String PARAMETR_USER_PASSWORD = "user.password";
	private static final String PARAMETR_USER_SALT = "user.salt";
	private static final String PARAMETR_USER_ROLE = "user.role";
	private static final String PARAMETR_USER_EMAIL = "user.email";
	private static final String PARAMETR_USER_ID_SECTOR = "user.id_sector";
	private static final String PARAMETR_USER_ID_DEPO = "user.id_depo";
	private static final String PARAMETR_USER_AGREED = "user.agreed";
	private static final String PARAMETR_SECTOR_NAME = "sector_list.name_sector";
	private static final String PARAMETR_SECTOR_ID = "sector_list.id_sector";

	//	private static final String SQL_GET_ID_SECTOR_BY_NAME = "(SELECT " + PARAMETR_USER_ID_SECTOR
//			+ " FROM sector_list WHERE( " + PARAMETR_SECTOR_NAME + "= ? AND " + PARAMETR_SECTOR_DEPO + "= ?))";
//	
	private static final String SQL_INSERT_USER = "INSERT INTO user (" + PARAMETR_USER_NAME + ", "
			+ PARAMETR_USER_SECOND_NAME + ", " + PARAMETR_USER_PASSWORD + ", " + PARAMETR_USER_SALT + ", "
			+ PARAMETR_USER_ROLE + "," + PARAMETR_USER_EMAIL + ", " + PARAMETR_USER_ID_SECTOR + ", "
			+ PARAMETR_USER_ID_DEPO + ", " + PARAMETR_USER_AGREED + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_GET_USER_BY_EMAIL = "(SELECT * FROM user WHERE( " + PARAMETR_USER_EMAIL + "= ? ));";
	private static final String SQL_GET_USER_JOIN_SECTOR_TABLE_BY_EMAIL = "SELECT * FROM (user INNER JOIN sector_list ON "
			+ PARAMETR_USER_ID_SECTOR + "=" + PARAMETR_SECTOR_ID + ") WHERE " + PARAMETR_USER_EMAIL + "= ? ;";
//																			

	@Override
	public void registerUser(User user, String encryptedPassword, String salt) throws DAOException {

		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_INSERT_USER);) {
			pr.setString(1, user.getName());
			pr.setString(2, user.getSecondName());
			pr.setString(3, encryptedPassword);
			pr.setString(4, salt);
			pr.setString(5, user.getRole().getValue());
			pr.setString(6, user.getEmail());
			pr.setInt(7, user.getId_sector());
			pr.setInt(8, user.getId_depo());
			if (user.getAgreed()) {
				pr.setInt(9, 1);
			} else {
				pr.setInt(9, 0);
			}
			System.out.println("Remote DB connection established");
			pr.executeUpdate();
		} catch (SQLException e) {
			System.out.println(TECHNICAL_MSG_SQL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (ConnectionPoolException e) {
			System.out.println(TECHNICAL_MSG_POOL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (Exception e) {
			System.out.println(TECHNICAL_MSG_SOME_UNEXPECTED_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		}
	}

	@Override
	public BaseUserInfo getBaseUser(String email) throws DAOException {
		BaseUserInfo baseUserInfo = null;
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_USER_BY_EMAIL);) {
			pr.setString(1, email);
			ResultSet result = pr.executeQuery();
			while (result.next()) {
				String password = result.getString(PARAMETR_USER_PASSWORD);
				String salt = result.getString(PARAMETR_USER_SALT);
				baseUserInfo = new BaseUserInfo(email, password, salt);
			}
			return baseUserInfo;

		} catch (SQLException e) {
			System.out.println(TECHNICAL_MSG_SQL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (ConnectionPoolException e) {
			System.out.println(TECHNICAL_MSG_POOL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (Exception e) {
			System.out.println(TECHNICAL_MSG_SOME_UNEXPECTED_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		}

	}

	@Override
	public User getUser(String email) throws DAOException {
		User user = null;
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_USER_BY_EMAIL);) {
			pr.setString(1, email);
			ResultSet result = pr.executeQuery();
			while (result.next()) {
				System.out.println(Integer.parseInt(result.getString(PARAMETR_USER_ID)));
				Integer id_userBd = Integer.parseInt(result.getString(PARAMETR_USER_ID));
				String nameBd = result.getString(PARAMETR_USER_NAME);
				String secondNameBd = result.getString(PARAMETR_USER_SECOND_NAME);
				UserRole roleBd = UserRole.valueOf(result.getString(PARAMETR_USER_ROLE).toUpperCase());
				String emailBd = result.getString(PARAMETR_USER_EMAIL);
				Integer idDepoBd = Integer.valueOf(result.getString(PARAMETR_USER_ID_DEPO));
				Integer idSectorBd = Integer.valueOf(result.getString(PARAMETR_USER_ID_SECTOR));
				Boolean agreedBd = Boolean.valueOf(result.getString(PARAMETR_USER_AGREED));
				user = new User(id_userBd, nameBd, secondNameBd, roleBd, emailBd, idDepoBd, idSectorBd, agreedBd);
				System.out.println(user);
			}
			return user;

		} catch (SQLException e) {
			System.out.println(TECHNICAL_MSG_SQL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (ConnectionPoolException e) {
			System.out.println(TECHNICAL_MSG_POOL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (Exception e) {
			System.out.println(TECHNICAL_MSG_SOME_UNEXPECTED_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		}

	}
}
