import java.util.Scanner;
// Class to represent the user's bank account
class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            return false;
        }
    }
    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }
}
// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    public ATM(BankAccount account) {
        this.account = account;
    }
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("ATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
// Main class to run the ATM program
public class ATMProgram {
    public static void main(String[] args) {
        // Initialize a bank account with an initial balance
        BankAccount account = new BankAccount(500.00);
        // Initialize the ATM with the bank account
        ATM atm = new ATM(account);
        // Show the ATM menu
        atm.showMenu();
    }
}