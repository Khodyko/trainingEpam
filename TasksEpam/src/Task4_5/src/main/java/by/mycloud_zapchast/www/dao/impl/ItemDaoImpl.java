package by.mycloud_zapchast.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.mycloud_zapchast.www.dao.DAOException;
import by.mycloud_zapchast.www.dao.ItemDao;
import by.mycloud_zapchast.www.dao.connectionpool.ConnectionPoolException;
import by.mycloud_zapchast.www.dao.connectionpool.ItemConnectionPool;
import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.Depo;
import by.mycloud_zapchast.www.entity.Sector;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.entity.User;

public class ItemDaoImpl implements ItemDao {
	private static final String TECHNICAL_MSG_SQL_EXCP="DAO: SQL EXCEPTION";
	private static final String TECHNICAL_MSG_POOL_EXCP="DAO: POOL EXCEPTION";
	private static final String TECHNICAL_MSG_SOME_UNEXPECTED_EXCP="DAO: SOME UNEXPECTED EXCEPTION";
	private static final String USER_MSG_SQL_EXCP="У нас технические неполадки, попробуйте зайти на страницу через некоторое время";
	
	private static final String PARAM_USER_ID_SECTOR = "user.id_sector";
	private static final String PARAM_USER_ID_USER = "user.id_user";
	private static final String PARAM_TABLE_YEAR = "year.year";
	private static final String PARAM_DEPO_NAME = "depo_list.depo_name";
	private static final String PARAM_DEPO_ID = "depo_list.depo_id";
	private static final String PARAM_APP_DEPO_NUM = "depo_application.num";
	private static final String PARAM_APP_DEPO_ITEM_ID = "depo_application.id_item";
	private static final String PARAM_APP_DEPO_YEAR = "depo_application.year";
	private static final String PARAM_APP_DEPO_ID = "depo_application.id_depo";
	private static final String PARAM_ITEM_ID = "item.id_item";
	private static final String PARAM_ITEM_NN = "item.nn";
	private static final String PARAM_ITEM_NNSAP = "item.nnsap";
	private static final String PARAM_ITEM_NAME = "item.name";
	private static final String PARAM_ITEM_UNITS = "item.units";
	private static final String PARAM_SECTOR_PARENT_DEPO = "sector_list.parent_depo";
	private static final String PARAM_SECTOR_NAME = "sector_list.name_sector";
	private static final String PARAM_SECTOR_SECTOR_ID = "sector_list.id_sector";
	private static final String PARAM_APP_SECTOR_ITEM_ID = "sector_application.id_item";
	private static final String PARAM_APP_SECTOR_YEAR = "sector_application.year";
	private static final String PARAM_APP_SECTOR_NUM = "sector_application.num";
	// (?=0)
	private static final String SQL_GET_YEAR = "SELECT * FROM year";
	// (?=0)
	private static final String SQL_GET_DEPO = "SELECT * FROM depo_list";
	// (?=1)
	private static final String SQL_GET_SECTOR = "SELECT * FROM sector_list JOIN depo_parent_sector ON id_depo=? && sector_list.id_sector=depo_parent_sector.id_sector";

	private static final String SQL_GET_STANDART_SEARCH_ITEM = "SELECT * FROM item WHERE " + PARAM_ITEM_NN
			+ " LIKE ? && " + PARAM_ITEM_NNSAP + " LIKE ? && " + PARAM_ITEM_NAME + " LIKE ?";
	// (?=2)
	private static final String SQL_GET_APP_DEPO_BY_YEAR_BY_DEPO = "SELECT depo_num, id_item FROM depo_application WHERE year=? and id_depo=?";
	// (?=2)
	private static final String SQL_GET_APP_SECTOR_SUMM_BY_YEAR_BY_DEPO = "SELECT sum(sector_num) as sector_sum_num, id_item FROM sector_application "
			+ " WHERE year=? AND id_parent_depo=? group by id_item, id_parent_depo ";
	// sector_sum_num is from SQL_GET_APP_SECTOR_SUMM_BY_YEAR_BY_DEPO
	private static final String SQL_GET_APP_SEARCH_ITEM = "SELECT t0.id_item, t0.nn, t0.nnsap, t0.name , t0.units , t1.depo_num, t2.sector_sum_num, t1.id_item, t2.id_item "
			+
			// (?=3)
			" FROM ( " + SQL_GET_STANDART_SEARCH_ITEM + " ) as t0 " + " LEFT JOIN " +
			// (?=2)
			"( " + SQL_GET_APP_DEPO_BY_YEAR_BY_DEPO + " ) as t1 " + " ON t0.id_item=t1.id_item " + " LEFT JOIN " +
			// (?=2)
			"( " + SQL_GET_APP_SECTOR_SUMM_BY_YEAR_BY_DEPO + " ) as t2 " + " on t0.id_item=t2.id_item "
			+ " WHERE (t1.depo_num is not null) OR (t2.sector_sum_num is not null)";

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
			System.out.println(TECHNICAL_MSG_SQL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (ConnectionPoolException e) {
			System.out.println(TECHNICAL_MSG_POOL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (Exception e) {
			System.out.println(TECHNICAL_MSG_SOME_UNEXPECTED_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		}

		return year;
	}

	@Override
	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSearchItem, User user) throws DAOException {
		String name = appSearchItem.getName();
		String nn = appSearchItem.getNn();
		String nnSap = appSearchItem.getNnSap();
		String year = appSearchItem.getYear();
		AppSearchItem appSearchItemBd = null;
		System.out.println("appsearchItem= " + appSearchItem);
		System.out.println("user= " + user);
		List<AppSearchItem> appSearchItemList = new ArrayList<AppSearchItem>();
		System.out.println(SQL_GET_APP_SEARCH_ITEM);
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_APP_SEARCH_ITEM);) {

			pr.setString(1, "%" + nn + "%");
			pr.setString(2, "%" + nnSap + "%");
			pr.setString(3, "%" + name + "%");
			pr.setString(4, appSearchItem.getYear());
			pr.setInt(5, user.getId_depo());
			pr.setString(6, appSearchItem.getYear());
			pr.setInt(7, user.getId_depo());

			ResultSet result = pr.executeQuery();

			while (result.next()) {
				appSearchItemBd = new AppSearchItem();
				appSearchItemBd.setIdItem(result.getInt("id_item"));
				appSearchItemBd.setName(result.getString("name"));
				appSearchItemBd.setNn(result.getString("nn"));
				appSearchItemBd.setNnSap(result.getString("nnsap"));
				appSearchItemBd.setDepoFinalApp(result.getDouble("depo_num"));
				appSearchItemBd.setSectorsSummApp(result.getDouble("sector_sum_num"));
				appSearchItemBd.setUnits(result.getString("units"));
				appSearchItemBd.setYear(year);
				appSearchItemList.add(appSearchItemBd);
				System.out.println("Big sql request appSearchItem " + appSearchItemBd);

			}

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

		return appSearchItemList;
	}

	@Override
	public List<Depo> getDepos() throws DAOException {
		List<Depo> depoList = new ArrayList();
		Depo depo = null;
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_DEPO);) {
			ResultSet result = pr.executeQuery();

			while (result.next()) {
				Integer id_depo = result.getInt(PARAM_DEPO_ID);
				String name = result.getString(PARAM_DEPO_NAME);
				depo = new Depo(id_depo, name);
				depoList.add(depo);

			}
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
		return depoList;
	}

	@Override
	public List<Sector> getSector(Integer parentDepoId) throws DAOException {

		List<Sector> sectorList = new ArrayList();
		Sector sector = null;
		try (Connection connection = ItemConnectionPool.getInstance().takeConnection();
				PreparedStatement pr = connection.prepareStatement(SQL_GET_SECTOR);) {
			pr.setInt(1, parentDepoId);
			ResultSet result = pr.executeQuery();

			while (result.next()) {
				Integer idSector = result.getInt(PARAM_SECTOR_SECTOR_ID);
				String name = result.getString(PARAM_SECTOR_NAME);
				sector = new Sector(idSector, name);
				sectorList.add(sector);

			}
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
		return sectorList;
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
			System.out.println(TECHNICAL_MSG_SQL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (ConnectionPoolException e) {
			System.out.println(TECHNICAL_MSG_POOL_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		} catch (Exception e) {
			System.out.println(TECHNICAL_MSG_SOME_UNEXPECTED_EXCP);
			throw new DAOException(USER_MSG_SQL_EXCP, e);
		}
		return standartSearchItemList;
	}
}
