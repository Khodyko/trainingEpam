package by.mycloud_zapchast.www.dao;

import java.util.List;

import by.mycloud_zapchast.www.entity.AppSearchItem;
import by.mycloud_zapchast.www.entity.Depo;
import by.mycloud_zapchast.www.entity.Sector;
import by.mycloud_zapchast.www.entity.StandartSearchItem;
import by.mycloud_zapchast.www.entity.User;

public interface ItemDao {

	public List<String> getYear() throws DAOException;

	public List<AppSearchItem> getAppSearchItem(AppSearchItem appSearchItem, User user) throws DAOException;

	public List<Depo> getDepos() throws DAOException;

	public List<Sector> getSector(Integer parentDepoId) throws DAOException;

	public List<StandartSearchItem> getStandartSearchItem(StandartSearchItem standartSearchItem) throws DAOException;
}
