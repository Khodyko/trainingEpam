package by.mycloud_zapchast.www.service;

import java.util.List;

import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.Depo;
import by.mycloud_zapchast.www.entity.Sector;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.entity.User;

public interface ItemService {
	public List<String> getYears() throws ServiceException;

	public List<AppSearchItem> getAppSearchItem(AppSearchItem baseItemTags, User user, Integer currentPageNumber) throws ServiceException;

	public List<Depo> getDepos() throws ServiceException;

	public List<Sector> getSectors(Integer parentDepo) throws ServiceException;

	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem, Integer currentPageNumber)
			throws ServiceException;
	
	public Integer getStandartSearchItemMaxNumber(StandartSearchItem itemBd) throws ServiceException;
	public Integer getAppSearchItemMaxNumber(AppSearchItem itemBd, User user) throws ServiceException;
}
