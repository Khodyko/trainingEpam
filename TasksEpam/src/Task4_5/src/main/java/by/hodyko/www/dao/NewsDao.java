package by.hodyko.www.dao;

import java.util.ArrayList;
import java.util.List;

import by.hodyko.www.bean.News;

public interface NewsDao {
	public void create(News entity) throws DAOException;

	List<News> getNewsList(Integer countOf5NewsPage) throws DAOException;

	void update(News entity) throws DAOException;

	void delete(Integer id) throws DAOException;

	Integer getNewsMaxNumber() throws DAOException;

	News getNews(Integer chosenId) throws DAOException;
}
