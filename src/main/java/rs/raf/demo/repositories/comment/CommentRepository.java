package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;

public interface CommentRepository {
  Comment addComment(Comment comment);
  Comment byNewsId(Integer id);
}
