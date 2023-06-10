package rs.raf.demo.services;

import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserService {

  public UserService() {
    System.out.println(this);
  }

  @Inject
  private UserRepository userRepository;

  public User addUser(User user) {
    return this.userRepository.addUser(user);
  }

  public List<User> allUsers() {
    return this.userRepository.allUsers();
  }

  public void deleteUser(Integer id) {
    this.userRepository.deleteUser(id);
  }

  public void updateUser(User user) {
    this.userRepository.updateUser(user);
  }

  public User getUser(Integer id) {
    return this.userRepository.getUser(id);
  }
}
