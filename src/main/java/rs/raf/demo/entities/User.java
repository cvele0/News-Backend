package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String name;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String surname;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String type;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String status;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String hashedPassword;

    public User(String name, String surname, String email, String type, String status, String hashedPassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.status = status;
        this.hashedPassword = hashedPassword;
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
