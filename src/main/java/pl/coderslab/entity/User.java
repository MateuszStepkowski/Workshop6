package pl.coderslab.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validationGroups.UserLoggingValidationGroup;
import pl.coderslab.validationGroups.UserRegistrationValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(groups = UserRegistrationValidationGroup.class)
    private String username;

    @NotEmpty(groups = {UserRegistrationValidationGroup.class, UserLoggingValidationGroup.class})
    private String password;

    private boolean enabled;

    @NotBlank(groups = {UserRegistrationValidationGroup.class, UserLoggingValidationGroup.class})
    @Email(groups = {UserRegistrationValidationGroup.class, UserLoggingValidationGroup.class})
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    List<Tweet> tweets;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "sender")
    List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    List<Message> receivedMessages;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isEnabled() == user.isEnabled() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUsername(), getPassword(), isEnabled(), getEmail());
    }
}
