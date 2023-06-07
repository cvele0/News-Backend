package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class Comment {
  @NotNull(message = "Id field is required")
  private Integer id;

  @NotNull(message = "Author field is required")
  @NotEmpty(message = "Author field is required")
  private String author;

  @NotNull(message = "Text field is required")
  @NotEmpty(message = "Text field is required")
  private String text;

  @NotNull(message = "Time Created field is required")
  @NotEmpty(message = "Time Created field is required")
  private String timeCreated;

  @NotNull(message = "News field is required")
  @NotEmpty(message = "News field is required")
  private News news;

  public Comment() {}

  public Comment(Integer id, String author, String text, String timeCreated, News news) {
    this.id = id;
    this.author = author;
    this.text = text;
    this.timeCreated = timeCreated;
    this.news = news;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(String timeCreated) {
    this.timeCreated = timeCreated;
  }

  public News getNews() {
    return news;
  }

  public void setNews(News news) {
    this.news = news;
  }
}
