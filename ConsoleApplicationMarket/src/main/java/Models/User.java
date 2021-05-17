package Models;

public class User {
    public String username;
    public String password;
    public Role role;

    public User(String username, String password,Role role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }
}
