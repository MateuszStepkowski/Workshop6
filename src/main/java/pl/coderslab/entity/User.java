package pl.coderslab.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validationGroups.UserLoggingValidationGroup;
import pl.coderslab.validationGroups.UserRegistrationValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(groups = UserRegistrationValidationGroup.class)
    private String username;

    @NotBlank(groups = {UserRegistrationValidationGroup.class, UserLoggingValidationGroup.class})
    private String password;

    private boolean enabled;

    @NotBlank(groups = {UserRegistrationValidationGroup.class, UserLoggingValidationGroup.class})
    @Email(groups = {UserRegistrationValidationGroup.class, UserLoggingValidationGroup.class})
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
