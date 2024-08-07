import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpenseTracker {
    private List<User> users;
    private List<Expense> expenses;
    private User currentUser;

    public ExpenseTracker() {
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
        loadData();
    }

    public void registerUser(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("User registered successfully.");
    }

    public boolean login(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.validatePassword(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public void addExpense(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please register or login first.");
            return;
        }
        try {
            System.out.println("Enter date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            System.out.println("Enter category: ");
            String category = scanner.nextLine();
            System.out.println("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            expenses.add(new Expense(date, category, amount));
            System.out.println("Expense added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public void listExpenses() {
        if (currentUser == null) {
            System.out.println("Please register or login first.");
            return;
        }
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void viewCategoryWiseSummation() {
        if (currentUser == null) {
            System.out.println("Please register or login first.");
            return;
        }
        Map<String, Double> categorySum = new HashMap<>();
        for (Expense expense : expenses) {
            categorySum.put(expense.getCategory(),
                categorySum.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }
        for (Map.Entry<String, Double> entry : categorySum.entrySet()) {
            System.out.println("Category: " + entry.getKey() + ", Total: " + entry.getValue());
        }
    }

    public void saveData() {
        try {
            FileHandler.saveUsers(users);
            FileHandler.saveExpenses(expenses);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            users = FileHandler.loadUsers();
            expenses = FileHandler.loadExpenses();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found or failed to load data: " + e.getMessage());
        }
    }
}
