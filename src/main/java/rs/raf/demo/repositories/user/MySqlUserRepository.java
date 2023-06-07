package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository {
  @Override
  public User addUser(User user) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      String[] generatedColumns = {"id"};

      preparedStatement = connection.prepareStatement(
              "INSERT INTO user (name, surname, email, type, status, hashed_password) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
      preparedStatement.setString(1, user.getName());
      preparedStatement.setString(2, user.getSurname());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getType());
      preparedStatement.setString(5, user.getStatus());
      preparedStatement.setString(6, user.getHashedPassword());
      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        user.setId(resultSet.getInt(1));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return user;
  }

  @Override
  public List<User> allUsers() {
    List<User> users = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from user");
      while (resultSet.next()) {
        users.add(new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("email"),
                resultSet.getString("type"),
                resultSet.getString("status"),
                resultSet.getString("hashed_password")));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(statement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return users;
  }

  @Override
  public void deleteUser(Integer id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = this.newConnection();

      preparedStatement = connection.prepareStatement("DELETE FROM user where id = ?");
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();

      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeConnection(connection);
    }
  }
}
