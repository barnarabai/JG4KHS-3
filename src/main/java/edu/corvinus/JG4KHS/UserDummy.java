package edu.corvinus.JG4KHS;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_data")
public class UserDummy {

    @Id //Elsődleges kulcs
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @NotBlank(message = "Felhasználónév kötelező")
    @Size(min = 6)
    private String username;

    @NotBlank(message = "A név megadása kötelező")
    @Size(min = 2)
    private String name;

    @NotBlank(message = "Jelszó megadása kötelező")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Generate ToString()
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}