import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Expense Tracker!");

        // Prompt user to login or register
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    expenseTracker.registerUser(scanner);
                    break;
                case 2:
                    if (expenseTracker.login(scanner)) {
                        System.out.println("Login successful.");
                        break;
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if (expenseTracker.isUserLoggedIn()) {
                break; // Exit loop if user successfully logs in
            }
        }

        // Main menu
        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. List Expenses");
            System.out.println("3. View Category-wise Summation");
            System.out.println("4. Save and Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    expenseTracker.addExpense(scanner);
                    break;
                case 2:
                    expenseTracker.listExpenses();
                    break;
                case 3:
                    expenseTracker.viewCategoryWiseSummation();
                    break;
                case 4:
                    expenseTracker.saveData();
                    System.out.println("Data saved. Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
