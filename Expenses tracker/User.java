import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L; // Add this line
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
