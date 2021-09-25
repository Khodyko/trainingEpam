package by.mycloud_zapchast.www.dao;

import java.util.List;

import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.Depo;
import by.mycloud_zapchast.www.entity.Sector;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.entity.User;
import by.mycloud_zapchast.www.service.ServiceException;

public interface ItemDao {

	public List<String> getYear() throws DAOException;

	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSearchItem, User user,  Integer currentPageNumber) throws DAOException;

	public List<Depo> getDepos() throws DAOException;

	public List<Sector> getSector(Integer parentDepoId) throws DAOException;

	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem, Integer currentPage ) throws DAOException;
	
	public Integer getStandartSearchItemMaxNumber(StandartSearchItem itemBd) throws DAOException;
	
	public Integer getAppSearchItemMaxNumber(AppSearchItem itemBd, User user) throws DAOException;
}
