package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Category {
  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String name;

  @NotNull(message = "Title field is required")
  @NotEmpty(message = "Title field is required")
  private String description;

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
