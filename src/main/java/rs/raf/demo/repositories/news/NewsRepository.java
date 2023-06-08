package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.News;

import java.util.List;

public interface NewsRepository {
  News addNews(News news);
  List<News> allNews();
//  News findNews(Integer id);
  void deleteNews(Integer id);
  List<News> lastTen();
  List<News> mostRead();
  List<News> byCategory(Integer id);
}
