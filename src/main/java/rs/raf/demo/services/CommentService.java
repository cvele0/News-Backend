package rs.raf.demo.services;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {
  public CommentService() {
    System.out.println(this);
  }

  @Inject
  private CommentRepository commentRepository;

  public Comment addComment(Comment comment) {
    return this.commentRepository.addComment(comment);
  }

  public List<Comment> byNewsId(Integer id) {
    return this.commentRepository.byNewsId(id);
  }
  public void deleteByNewsId(Integer id) {
    this.commentRepository.deleteByNewsId(id);
  }
}
