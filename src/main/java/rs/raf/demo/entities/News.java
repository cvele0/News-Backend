package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

public class News {
  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String title;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String text;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private LocalTime timeCreated;

  private String author;

  private List<Comment> comments;

  private List<Tag> tags;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private Category category;

  public News(String title, String text, LocalTime timeCreated, Category category) {
    this.title = title;
    this.text = text;
    this.timeCreated = timeCreated;
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
