package controller;


import dao.ProductDAO;
import dao.UserDAO;
import javafx.stage.Stage;
import model.User;
import view.AdminView;
import view.LoginView;
import view.RegisterView;
import view.ShoppingView;

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

            if (user.isAdmin()) {
                loginView.showMessage("Welcome, Admin!");
                redirectToAdminDashboard(user);
            } else {
                loginView.showMessage("Welcome, " + user.getUsername() + "!");
                redirectToShoppingPage(user);
            }
        } else {
            loginView.showMessage("Invalid credentials. Please try again.");
        }
    }

    private void redirectToAdminDashboard(User user) {
        // Initialize the admin view
        AdminView adminView = new AdminView(new Stage());
        adminView.setUser(user);
        adminView.show(); // Show admin dashboard
        loginView.close(); // Close login window
    }

    private void redirectToShoppingPage(User user) {
        // Initialize the user view
        ShoppingView shoppingView = new ShoppingView(new Stage());
        shoppingView.setUser(user);
        shoppingView.show(); // Show user shopping page
        loginView.close(); // Close login window
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
