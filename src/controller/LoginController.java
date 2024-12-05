package controller;


import dao.UserDAO;
import model.User;
import view.LoginView;
import view.RegisterView;

public class LoginController {
    private RegisterView registerView;
    private LoginView loginView;
    private UserDAO userDAO;

    private String username;

    private String email;
    private String password;
    private boolean isAdmin;

    public LoginController(LoginView loginView, UserDAO userDAO) {
        this.loginView = loginView;
        this.userDAO = userDAO;
        this.loginView.setLoginListener(this::handleLogin);
    }

    public LoginController(RegisterView registerView, UserDAO userDAO){
        this.registerView = registerView;
        this.userDAO = userDAO;
        this.registerView.setRegisterListener(this::handleRegister);
    }

    public void handleLogin(String username, String password) {
        if (userDAO.loginUser(username, password)) {
            User user = userDAO.getUserByUsername(username);
            loginView.showMessage("Login successful!");
            // Redirect to the main shopping or admin page
        } else {
            loginView.showMessage("Invalid credentials. Please try again.");
        }
    }

    public void handleRegister(String username, String email, String password, boolean isAdmin) {
        // Validate input
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                email == null || email.isEmpty()) {
            loginView.showMessage("All fields are required.");
            return;
        }

        // Check if username already exists
        User existingUser = userDAO.getUserByUsername(username);
        if (existingUser != null) {
            registerView.showMessage("Username is already taken. Please choose another one.");
            return;
        }

        // Create a new user object
        User newUser = new User(username, password, email, isAdmin);

        // Save the user
        if (userDAO.registerUser(username, email, password, isAdmin)) {
            registerView.showMessage("Registration successful! You can now log in.");
        } else {
            registerView.showMessage("Registration failed. Please try again.");
        }
    }


}
