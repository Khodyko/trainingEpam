package by.mycloud_zapchast.www.dao;

import java.util.List;

import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.StandartSearchItem;

public interface ItemDao {

	public List<String> getYear() throws DAOException;
	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSearchItem, Integer userId) throws DAOException;
	public List<String> getDepos() throws DAOException;
	public List<String> getSector(String parentDepo) throws DAOException;
	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem) throws DAOException;
}
