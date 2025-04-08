package atminterface;

import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! Current balance: " + String.format("%.2f", balance));
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! Current balance: " + String.format("%.2f", balance));
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            processChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Current Balance: " + String.format("%.2f", account.checkBalance()));
                break;
            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = getValidAmount();
                account.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter withdrawal amount: ");
                double withdrawAmount = getValidAmount();
                account.withdraw(withdrawAmount);
                break;
            case 4:
                System.out.println("Thank you for using the ATM! Goodbye.");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private double getValidAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid amount.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextDouble();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}