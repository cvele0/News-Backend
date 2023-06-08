package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class News {
  private Integer id;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String title;

  @NotNull(message = "Text field is required")
  @NotEmpty(message = "Text field is required")
  private String text;

  @NotNull(message = "Time created field is required")
  private Timestamp timeCreated;

  @NotNull(message = "Author field is required")
  @NotEmpty(message = "Author field is required")
  private String author;

  private int viewCount;

  private List<Comment> comments;

  private List<Tag> tags;

  private Category category;

  public News() {}

  public News(Integer id, String title, String text, Timestamp timeCreated, String author, int viewCount, Category category) {
    this.id = id;
    this.title = title;
    this.text = text;
    this.timeCreated = timeCreated;
    this.author = author;
    this.viewCount = viewCount;
    this.category = category;
  }

  //2nd version
  public News(Integer id, String title, String text, Timestamp timeCreated, String author) {
    this.id = id;
    this.title = title;
    this.text = text;
    this.timeCreated = timeCreated;
    this.author = author;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
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

  public Timestamp getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(Timestamp timeCreated) {
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
