package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository {

  @Override
  public News addNews(News news) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      String[] generatedColumns = {"id"};

//      preparedStatement = connection.prepareStatement("INSERT INTO news (title, text, time_created, author) VALUES(?, ?, ?, ?)", generatedColumns);
      preparedStatement = connection.prepareStatement("INSERT INTO news (title, text, time_created, author, category_id) VALUES(?, ?, ?, ?, ?)", generatedColumns);
      preparedStatement.setString(1, news.getTitle());
      preparedStatement.setString(2, news.getText());
      preparedStatement.setString(3, news.getTimeCreated());
      preparedStatement.setString(4, news.getAuthor());
      //change this line
      preparedStatement.setInt(5, 1);

      news.setCategory(new Category(0));

      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        news.setId(resultSet.getInt(1));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return news;
  }

  @Override
  public List<News> allNews() {
    List<News> news = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      //TODO dodati kategoriju
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from news");
      while (resultSet.next()) {
        news.add(new News(resultSet.getInt("id"), resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getString("time_created"),
                resultSet.getString("author")));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(statement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return news;
  }

  @Override
  public void deleteNews(Integer id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = this.newConnection();

      preparedStatement = connection.prepareStatement("DELETE FROM news where id = ?");
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
