package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;

import java.util.List;

public interface CategoryRepository {
  Category addCategory(Category category);
  List<Category> allCategories();
  //  public Category findCategory(Integer id);
  void deleteCategory(Integer id);
  void updateCategory(Category category);
  Category getCategory(Integer id);
}
