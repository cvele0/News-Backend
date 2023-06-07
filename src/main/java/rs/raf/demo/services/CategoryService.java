package rs.raf.demo.services;

import rs.raf.demo.entities.Category;
import rs.raf.demo.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

  public CategoryService() {
    System.out.println(this);
  }

  @Inject
  private CategoryRepository categoryRepository;

  public Category addCategory(Category category) {
    return this.categoryRepository.addCategory(category);
  }

  public List<Category> allCategories() {
    return this.categoryRepository.allCategories();
  }

//  public Subject findNews(Integer id) {
//    return this.newsRepository.findNews(id);
//  }

  public void deleteCategory(Integer id) {
    this.categoryRepository.deleteCategory(id);
  }
}
