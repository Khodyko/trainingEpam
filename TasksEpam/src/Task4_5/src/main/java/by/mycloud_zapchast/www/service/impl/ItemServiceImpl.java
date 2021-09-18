package by.mycloud_zapchast.www.service.impl;

import java.util.List;

import by.mycloud_zapchast.www.dao.DAOException;
import by.mycloud_zapchast.www.dao.DaoProvider;
import by.mycloud_zapchast.www.dao.ItemDao;
import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.service.ItemService;
import by.mycloud_zapchast.www.service.ServiceException;

public class ItemServiceImpl implements ItemService {
	private static final DaoProvider DAO_PROVIDER=DaoProvider.getInstance();
	private static final ItemDao ITEM_DAO= DAO_PROVIDER.getItemDao();

	public List<String> getYears() throws ServiceException {
		List<String> year=null;
		try {
			year= ITEM_DAO.getYear();
			
			return year;
	}catch (DAOException e) {
		throw new ServiceException(e.getMessage(), e);
	}
		
	
}

	@Override
	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSearchItem, Integer userId) throws ServiceException {
		List<AppSearchItem> appSearchItemList;
		try {
			appSearchItemList = ITEM_DAO.getAppSearchItem(appSearchItem, userId);
			return appSearchItemList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	
	@Override
	public List<String> getDepos() throws ServiceException {
		List<String> depo=null;
		try {
			depo= ITEM_DAO.getDepos();
			
			return depo;
	}catch (DAOException e) {
		throw new ServiceException(e.getMessage(), e);
	}
	
}

	@Override
	public List<String> getSectors(String parentDepo) throws ServiceException {
		List<String> sector=null;
		try {
			sector= ITEM_DAO.getSector(parentDepo);
			
			return sector;
	}catch (DAOException e) {
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
}