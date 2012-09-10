package org.jege.user.user;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.jege.abs.entity.AbstractEntity;

@Entity
@XmlRootElement
@Table(name = "users",
        uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
@NamedQueries( {
    @NamedQuery(name = User.findAll, query = "from User"),
    @NamedQuery(name = User.findByUsername, query = "from User where username = :username")
})
public class User extends AbstractEntity {
    public static final String CLASS_NAME = "User";
    public static final String findAll = CLASS_NAME + AbstractEntity.FIND_ALL;
    public static final String findByUsername = CLASS_NAME + ".findByUsername";
    
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
