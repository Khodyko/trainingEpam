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
	private static final String PARAMETR_USER_ID="id_user";
	private static final String PARAMETR_USER_NAME = "name";
	private static final String PARAMETR_USER_SECOND_NAME = "surname";
	private static final String PARAMETR_USER_PASSWORD = "password";
	private static final String PARAMETR_USER_SALT = "salt";
	private static final String PARAMETR_USER_ROLE = "role";
	private static final String PARAMETR_USER_EMAIL = "email";
	private static final String PARAMETR_USER_ID_SECTOR = "id_sector";
	private static final String PARAMETR_USER_AGREED = "agreed";
	private static final String PARAMETR_SECTOR_NAME = "name_sector";
	private static final String PARAMETR_SECTOR_ID = "id_sector";
	private static final String PARAMETR_SECTOR_DEPO = "parent_depo";
	private static final String SQL_GET_ID_SECTOR_BY_NAME = "(SELECT " + PARAMETR_USER_ID_SECTOR
			+ " FROM depo_sector WHERE( " + PARAMETR_SECTOR_NAME + "= ? AND " + PARAMETR_SECTOR_DEPO + "= ?))";
	
	private static final String SQL_INSERT_USER = "INSERT INTO user (" + PARAMETR_USER_NAME + ", "
			+ PARAMETR_USER_SECOND_NAME + ", " + PARAMETR_USER_PASSWORD + ", " + PARAMETR_USER_SALT + ", "
			+ PARAMETR_USER_ROLE + "," + PARAMETR_USER_EMAIL + ", " + PARAMETR_USER_ID_SECTOR + ", " + PARAMETR_USER_AGREED
			+ ") VALUES (?, ?, ?, ?, ?, ?, "+SQL_GET_ID_SECTOR_BY_NAME+", ?)";
	private static final String SQL_GET_USER_BY_EMAIL ="(SELECT * FROM user WHERE( " + PARAMETR_USER_EMAIL + "= ? ));";
	private static final String SQL_GET_USER_JOIN_SECTOR_TABLE_BY_EMAIL ="SELECT * FROM (user INNER JOIN depo_sector ON user."+PARAMETR_USER_ID_SECTOR+"=depo_sector."+PARAMETR_SECTOR_ID+") WHERE user." + PARAMETR_USER_EMAIL + "= ? ;";
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
			pr.setString(7, user.getNameSector());
			pr.setString(8, user.getNameDepo());
			if(user.getAgreed()) {
			pr.setInt(9, 1);}
			else {
				pr.setInt(9, 0);
			}
			System.out.println("Remote DB connection established");
			pr.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected SQLException", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
	}
	
	@Override
	public BaseUserInfo getBaseUser(String email) throws DAOException{
		BaseUserInfo baseUserInfo=null;
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement( SQL_GET_USER_BY_EMAIL);) {
			pr.setString(1, email);
			ResultSet result = pr.executeQuery();
			while(result.next()) {
				String password= result.getString(PARAMETR_USER_PASSWORD);
				String salt= result.getString(PARAMETR_USER_SALT);
				baseUserInfo=new BaseUserInfo(email, password, salt);
			}
		return baseUserInfo;
			
		}catch (SQLException e) {
			throw new DAOException("Remote server could not be connected SQLException", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
		
		
	}
	
	@Override
	public User getUser(String email) throws DAOException{
		User user=null;
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_USER_JOIN_SECTOR_TABLE_BY_EMAIL);) {
			pr.setString(1, email);
			ResultSet result = pr.executeQuery();
			while(result.next()) {
				System.out.println(Integer.parseInt(result.getString(PARAMETR_USER_ID)));
				Integer id_userBd= Integer.parseInt(result.getString(PARAMETR_USER_ID));
				String nameBd= result.getString(PARAMETR_USER_NAME);
				String secondNameBd= result.getString(PARAMETR_USER_SECOND_NAME);
				UserRole roleBd= UserRole.valueOf(result.getString(PARAMETR_USER_ROLE).toUpperCase());
				String emailBd= result.getString(PARAMETR_USER_EMAIL);
				String nameDepoBd= result.getString(PARAMETR_SECTOR_DEPO);
				String nameSectorBd= result.getString(PARAMETR_USER_ID_SECTOR);
				Boolean agreedBd= Boolean.valueOf(result.getString(PARAMETR_USER_AGREED));
				user=new User(id_userBd, nameBd, secondNameBd, roleBd, emailBd, nameDepoBd, nameSectorBd, agreedBd);
				System.out.println(user);
			}
		return user;
			
		}catch (SQLException e) {
			throw new DAOException("Remote server could not be connected SQLException", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
		
		
	}
}
