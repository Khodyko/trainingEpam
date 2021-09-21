package by.hodyko.www.service;

import java.util.ArrayList;
import java.util.List;

import by.hodyko.www.bean.News;

public interface NewsService {
	void create(News news) throws ServiceException;

	void update(News news) throws ServiceException;

	List<News> getNewsList(Integer currentPageNumber) throws ServiceException;

	public Integer getNewsMaxNumber() throws ServiceException;

	public void deleteNews(Integer id) throws ServiceException;

	News getNews(Integer chosenId) throws ServiceException;

}
