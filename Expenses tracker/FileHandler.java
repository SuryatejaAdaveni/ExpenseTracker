import java.io.*;
import java.util.List;

public class FileHandler {
    private static final String USERS_FILE = "users.dat";
    private static final String EXPENSES_FILE = "expenses.dat";

    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
            throw e;
        }
    }

    public static void saveExpenses(List<Expense> expenses) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXPENSES_FILE))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
            throw e;
        }
    }

    public static List<User> loadUsers() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
            throw e;
        }
    }

    public static List<Expense> loadExpenses() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXPENSES_FILE))) {
            return (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading expenses: " + e.getMessage());
            throw e;
        }
    }
}
