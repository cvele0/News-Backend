package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    private Integer id;

    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name field is required")
    private String name;

    @NotNull(message = "Surname field is required")
    @NotEmpty(message = "Surname field is required")
    private String surname;

    @NotNull(message = "Email field is required")
    @NotEmpty(message = "Email field is required")
    private String email;

    @NotNull(message = "Type field is required")
    @NotEmpty(message = "Type field is required")
    private String type;

    @NotNull(message = "Status field is required")
    @NotEmpty(message = "Status field is required")
    private String status;

    @NotNull(message = "Hashed Password field is required")
    @NotEmpty(message = "Hashed Password field is required")
    private String hashedPassword;

    public User() {}

    public User(Integer id, String name, String surname, String email, String type, String status, String hashedPassword) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.status = status;
        this.hashedPassword = hashedPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
