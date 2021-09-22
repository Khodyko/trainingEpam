package by.mycloud_zapchast.www.service.impl;

import java.util.List;

import by.mycloud_zapchast.www.dao.DAOException;
import by.mycloud_zapchast.www.dao.DaoProvider;
import by.mycloud_zapchast.www.dao.ItemDao;
import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.Depo;
import by.mycloud_zapchast.www.entity.Sector;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;

public class ItemServiceImpl implements ItemService {
	private static final String DB_PROBLEM_MESSAGE="У нас технические неполадки, попробуйте зайти на страницу через некоторое время";
	private static final DaoProvider DAO_PROVIDER = DaoProvider.getInstance();
	private static final ItemDao ITEM_DAO = DAO_PROVIDER.getItemDao();
	

	public List<String> getYears() throws ServiceException {
		List<String> year = null;
		try {
			
			year = ITEM_DAO.getYear();
			if(year==null) {throw new ServiceException(DB_PROBLEM_MESSAGE);}
			return year;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSearchItem, User user) throws ServiceException {
		List<AppSearchItem> appSearchItemList;
		try {
			appSearchItemList = ITEM_DAO.getAppSearchItem(appSearchItem, user);
			return appSearchItemList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<Depo> getDepos() throws ServiceException {
		List<Depo> depo = null;
		try {
			depo = ITEM_DAO.getDepos();
			if(depo==null) {throw new ServiceException(DB_PROBLEM_MESSAGE);}
			return depo;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public List<Sector> getSectors(Integer parentDepoId) throws ServiceException {
		List<Sector> sector = null;
		try {
			sector = ITEM_DAO.getSector(parentDepoId);
			if(sector==null) {throw new ServiceException(DB_PROBLEM_MESSAGE);}
			return sector;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem)
			throws ServiceException {
		List<StandartSearchItem> standartSearchItemList;
		try {
			standartSearchItemList = ITEM_DAO.getStandartSearchItem(standartSearchItem);
			return standartSearchItemList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public void validateNotNullNotEmpty(String value, String name) throws ServiceException {
		if (value == null || value.trim().isBlank()) {
			throw new ServiceException(name + " не должен быть пустым");
		}
	}
}