package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class Comment {
  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String author;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String text;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private LocalTime timeCreated;

  private News news;

  public Comment(String author, String text, LocalTime timeCreated) {
    this.author = author;
    this.text = text;
    this.timeCreated = timeCreated;
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

  public LocalTime getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(LocalTime timeCreated) {
    this.timeCreated = timeCreated;
  }

  public News getNews() {
    return news;
  }

  public void setNews(News news) {
    this.news = news;
  }
}
