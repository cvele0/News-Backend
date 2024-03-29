package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.News;

import java.util.List;

public interface NewsRepository {
  News addNews(News news);
  List<News> allNews();
  void deleteNews(Integer id);
  List<News> lastTen();
  List<News> mostRead();
  List<News> byCategory(Integer id);
  News getNews(Integer id);
  void incrementViewCount(Integer id, Integer count);
  void updateNews(News news);
}
