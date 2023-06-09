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

      preparedStatement = connection.prepareStatement("INSERT INTO news (title, text, time_created, author, view_count, category_id) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
      preparedStatement.setString(1, news.getTitle());
      preparedStatement.setString(2, news.getText());
      preparedStatement.setTimestamp(3, news.getTimeCreated());
      preparedStatement.setString(4, news.getAuthor());
      preparedStatement.setInt(5, news.getViewCount());
      preparedStatement.setInt(6, news.getCategory().getId());

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

  private Category getCategory(Connection connection, int categoryId) throws Exception {
    Category category = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSetCategory = null;
    preparedStatement = connection.prepareStatement("select * from category where id = ?");
    preparedStatement.setInt(1, categoryId);
    resultSetCategory = preparedStatement.executeQuery();

    if (resultSetCategory.next()) {
      int id = resultSetCategory.getInt("id");
      String name = resultSetCategory.getString("name");
      String description = resultSetCategory.getString("description");
      category = new Category(id, name, description);
    }
    return category;
  }

  @Override
  public List<News> allNews() {
    List<News> news = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from news order by time_created desc");

      while (resultSet.next()) {
        Category category = getCategory(connection, resultSet.getInt("category_id"));

        news.add(new News(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getTimestamp("time_created"),
                resultSet.getString("author"),
                resultSet.getInt("view_count"),
                category));
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

  @Override
  public List<News> lastTen() {
    List<News> news = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from news order by time_created desc limit 10");
      while (resultSet.next()) {
        Category category = getCategory(connection, resultSet.getInt("category_id"));
        news.add(new News(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getTimestamp("time_created"),
                resultSet.getString("author"),
                resultSet.getInt("view_count"),
                category));
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
  public List<News> mostRead() {
    List<News> news = new ArrayList<>();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM news\n" +
              "WHERE time_created >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)\n" +
              "ORDER BY view_count DESC\n" +
              "LIMIT 10");
      while (resultSet.next()) {
        Category category = getCategory(connection, resultSet.getInt("category_id"));
        news.add(new News(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getTimestamp("time_created"),
                resultSet.getString("author"),
                resultSet.getInt("view_count"),
                category));
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
  public List<News> byCategory(Integer id) {
    List<News> news = new ArrayList<>();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      preparedStatement = connection.prepareStatement("select * from news where category_id = ?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Category category = getCategory(connection, resultSet.getInt("category_id"));
        news.add(new News(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getTimestamp("time_created"),
                resultSet.getString("author"),
                resultSet.getInt("view_count"),
                category));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return news;
  }

  @Override
  public News getNews(Integer id) {
    News news = null;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      preparedStatement = connection.prepareStatement("select * from news where id = ?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Category category = getCategory(connection, resultSet.getInt("category_id"));
        news = new News(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getTimestamp("time_created"),
                resultSet.getString("author"),
                resultSet.getInt("view_count"),
                category);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return news;
  }

  @Override
  public void incrementViewCount(Integer id, Integer count) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = this.newConnection();

      preparedStatement = connection.prepareStatement("UPDATE news SET view_count = ? WHERE id = ?");
      preparedStatement.setInt(1, count);
      preparedStatement.setInt(2, id);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeConnection(connection);
    }
  }
}
