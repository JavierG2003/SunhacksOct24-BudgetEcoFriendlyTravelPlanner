import java.io.*;
import java.util.Scanner;

public class LoginProgram {
    private static final String FILE_NAME = "users.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                createAccount(scanner);
            } else if (choice == 2) {
                login(scanner);
            } else if (choice == 3) {
                System.out.println("\nExiting program.");
                break;
            } else {
                System.out.println("\nInvalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("\nEnter a new username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter a new password: ");
        String newPassword = scanner.nextLine();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(newUsername + "," + newPassword);
            writer.newLine();
            writer.close();

            System.out.println("Account created successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the account.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("\nEnter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    found = true;
                    break;
                }
            }

            reader.close();

            if (found) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during login.");
        }
    }
}
