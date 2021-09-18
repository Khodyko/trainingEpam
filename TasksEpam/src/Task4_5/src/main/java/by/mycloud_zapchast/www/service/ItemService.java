package by.mycloud_zapchast.www.service;

import java.util.List;

import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.StandartSearchItem;

public interface ItemService {
	public List<String> getYears() throws ServiceException;
	public List<AppSearchItem> getAppSearchItem(AppSearchItem baseItemTags, Integer userId) throws ServiceException;
	public List<String> getDepos() throws ServiceException;
	public List<String> getSectors(String parentDepo) throws ServiceException;
	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem) throws ServiceException;
}
