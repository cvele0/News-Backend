package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.News;

import java.util.List;

public interface NewsRepository {
  public News addNews(News news);
  public List<News> allNews();
//  public News findNews(Integer id);
  public void deleteNews(Integer id);
}
