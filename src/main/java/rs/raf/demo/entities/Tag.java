package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Tag {
  private Integer id;

  @NotNull(message = "Name field is required")
  @NotEmpty(message = "Name field is required")
  private String name;

  private List<News> news;

  public Tag() {}

  public Tag(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<News> getNews() {
    return news;
  }

  public void setNews(List<News> news) {
    this.news = news;
  }
}
