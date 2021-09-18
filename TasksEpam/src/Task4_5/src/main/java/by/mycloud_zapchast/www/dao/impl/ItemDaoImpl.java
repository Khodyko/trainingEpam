package by.mycloud_zapchast.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.mycloud_zapchast.www.dao.connectionpool.ConnectionPoolException;

import by.mycloud_zapchast.www.dao.DAOException;
import by.mycloud_zapchast.www.dao.ItemDao;
import by.mycloud_zapchast.www.dao.connectionpool.ItemConnectionPool;
import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.StandartSearchItem;

public class ItemDaoImpl implements ItemDao {
	private static final String PARAM_TABLE_YEAR = "year";
	private static final String PARAM_TABLE_DEPO_NAME = "depo_name";
	private static final String PARAM_ITEM_ID = "id_item";
	private static final String PARAM_ITEM_NN = "nn";
	private static final String PARAM_ITEM_NNSAP = "nnsap";
	private static final String PARAM_ITEM_NAME = "name";
	private static final String PARAM_ITEM_UNITS = "units";
	private static final String PARAM_SECTOR_PARENT_DEPO = "parent_depo";
	private static final String PARAM_SECTOR_NAME = "name_sector";
	private static final String SQL_GET_YEAR = "SELECT * FROM year";
	private static final String SQL_GET_DEPO = "SELECT * FROM depo_list";
	private static final String SQL_GET_SECTOR = "SELECT * FROM depo_sector WHERE "+PARAM_SECTOR_PARENT_DEPO+"=?;";
	private static final String SQL_GET_STANDART_SEARCH_ITEM = "SELECT * FROM item WHERE (" + PARAM_ITEM_NN + " LIKE ? && "
			+ PARAM_ITEM_NNSAP + " LIKE ? && " + PARAM_ITEM_NAME + " LIKE ?);";
	private static final String SQL_GET_APP_SEARCH_ITEM = "SELECT * FROM item WHERE (" + PARAM_ITEM_NN + " LIKE ? && "
			+ PARAM_ITEM_NNSAP + " LIKE ? && " + PARAM_ITEM_NAME + " LIKE ?);";

	@Override
	public List<String> getYear() throws DAOException {
		List<String> year = new ArrayList();
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_YEAR);) {
			ResultSet result = pr.executeQuery();

			while (result.next()) {
				year.add(result.getString(PARAM_TABLE_YEAR));
			}
		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
		
		return year;
	}

	@Override
	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSerchItem, Integer userId) throws DAOException {
		String name = appSerchItem.getName();
		String nn = appSerchItem.getNn();
		String nnSap = appSerchItem.getNnSap();
		String year=appSerchItem.getYear();
		AppSearchItem baseItem;

		List<AppSearchItem> baseItemList = new ArrayList<AppSearchItem>();

		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_APP_SEARCH_ITEM);) {

			pr.setString(1, "%" + nn + "%");
			pr.setString(2, "%" + nnSap + "%");
			pr.setString(3, "%" + name + "%");
			ResultSet result = pr.executeQuery();
			while (result.next()) {
				baseItem = new AppSearchItem();
				baseItem.setName(result.getString(PARAM_ITEM_NAME));
				baseItem.setNn(result.getString(PARAM_ITEM_NN));
				baseItem.setNnSap(result.getString(PARAM_ITEM_NNSAP));
				baseItemList.add(baseItem);
				

			}

		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);

		}

		return baseItemList;
	}

	@Override
	public List<String> getDepos() throws DAOException {
		List<String> depo = new ArrayList();
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_DEPO);) {
			ResultSet result = pr.executeQuery();

			while (result.next()) {
				depo.add(result.getString(PARAM_TABLE_DEPO_NAME));
				
			}
		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
		return depo;
	}
	@Override
	public List<String> getSector(String parentDepo) throws DAOException {
		List<String> sector = new ArrayList();
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_SECTOR);) {
			pr.setString(1, parentDepo);
			ResultSet result = pr.executeQuery();

			while (result.next()) {
				sector.add(result.getString(PARAM_SECTOR_NAME));
				System.out.println(result.getString(PARAM_SECTOR_NAME));
			}
		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);
		}
		return sector;
	}

	@Override
	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem) throws DAOException {
		String name = standartSearchItem.getName();
		String nn = standartSearchItem.getNn();
		String nnSap = standartSearchItem.getNnSap();
		StandartSearchItem standartSearchItemDb;

		List<StandartSearchItem> standartSearchItemList = new ArrayList<StandartSearchItem>();

		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_STANDART_SEARCH_ITEM);) {

			pr.setString(1, "%" + nn + "%");
			pr.setString(2, "%" + nnSap + "%");
			pr.setString(3, "%" + name + "%");
			ResultSet result = pr.executeQuery();
			while (result.next()) {
				standartSearchItemDb = new StandartSearchItem();
				standartSearchItemDb.setIdItem(Integer.parseInt(result.getString(PARAM_ITEM_ID)));
				standartSearchItemDb.setName(result.getString(PARAM_ITEM_NAME));
				standartSearchItemDb.setNn(result.getString(PARAM_ITEM_NN));
				standartSearchItemDb.setNnSap(result.getString(PARAM_ITEM_NNSAP));
				standartSearchItemList.add(standartSearchItemDb);
				

			}

		} catch (SQLException e) {
			throw new DAOException("Remote server could not be connected", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Connection pool is falldown", e);
		} catch (Exception e) {
			throw new DAOException("False query", e);

		}

		return standartSearchItemList;
	}
}
