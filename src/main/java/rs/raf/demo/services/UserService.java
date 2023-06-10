package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
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

  public String login(String name, String password) {
    String hashedPassword = DigestUtils.sha256Hex(password);

    User user = this.userRepository.findUser(name);
    if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
      return null;
    }

    Date issuedAt = new Date();
    Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // One day

    Algorithm algorithm = Algorithm.HMAC256("secret");

    return JWT.create()
            .withIssuedAt(issuedAt)
            .withExpiresAt(expiresAt)
            .withSubject(name)
            .withClaim("type", user.getType())
            .withClaim("status", user.getStatus())
            .sign(algorithm);
  }

  public boolean isAuthorized(String token) {
    Algorithm algorithm = Algorithm.HMAC256("secret");
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT jwt = verifier.verify(token);

    String name = jwt.getSubject();
    String type = jwt.getClaim("type").asString();

    User user = this.userRepository.findUser(name);

    if (user == null) return false;
    return type.equalsIgnoreCase("admin");
  }
}
