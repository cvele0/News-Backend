package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.User;

import java.util.List;

public interface UserRepository {
    User addUser(User user);
    List<User> allUsers();
    void deleteUser(Integer id);
}
