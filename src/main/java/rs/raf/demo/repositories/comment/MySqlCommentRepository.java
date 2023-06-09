package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Category;
import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {
  @Override
  public Comment addComment(Comment comment) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      String[] generatedColumns = {"id"};

      preparedStatement = connection.prepareStatement("INSERT INTO comment (author, text, timeCreated, news_id) VALUES(?, ?, ?, ?)", generatedColumns);
      preparedStatement.setString(1, comment.getAuthor());
      preparedStatement.setString(2, comment.getText());
      preparedStatement.setTimestamp(3, comment.getTimeCreated());
      preparedStatement.setInt(4, comment.getNews().getId());

      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        comment.setId(resultSet.getInt(1));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return comment;
  }

  private Category getCategory(Connection connection, Integer categoryId) {
    Category category = null;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
      preparedStatement.setInt(1, categoryId);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int id = resultSet.getInt("id");
        String categoryName = resultSet.getString("name");
        String categoryDescription = resultSet.getString("description");

        // Create the Category object
        category = new Category(id, categoryName, categoryDescription);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
    }

    return category;
  }

  private News getNews(Connection connection, Integer newsId) {
    News news = null;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ?");
      preparedStatement.setInt(1, newsId);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int id = resultSet.getInt("id");
        String newsTitle = resultSet.getString("title");
        String newsText = resultSet.getString("text");
        Timestamp newsTimeCreated = resultSet.getTimestamp("time_created");
        String newsAuthor = resultSet.getString("author");
        int newsViewCount = resultSet.getInt("view_count");

        // Retrieve the associated Category object
        Category category = getCategory(connection, resultSet.getInt("category_id"));

        // Create the News object
        news = new News(id, newsTitle, newsText, newsTimeCreated, newsAuthor, newsViewCount, category);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
    }

    return news;
  }

  @Override
  public List<Comment> byNewsId(Integer id) {
    List<Comment> comments = new ArrayList<>();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = this.newConnection();

      preparedStatement = connection.prepareStatement("select * from comment where news_id = ?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        News news = getNews(connection, id);
        comments.add(new Comment(
                resultSet.getInt("id"),
                resultSet.getString("author"),
                resultSet.getString("text"),
                resultSet.getTimestamp("timeCreated"),
                news));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.closeStatement(preparedStatement);
      this.closeResultSet(resultSet);
      this.closeConnection(connection);
    }

    return comments;
  }
}
