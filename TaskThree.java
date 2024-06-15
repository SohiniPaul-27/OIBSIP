import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskThree {
    private double balance;
    private double depositAmount;
    private double withdrawAmount;
    private double transferAmount;

    private String name;
    private int accountNumber;
    private int atmpin;

    List<String> history = new ArrayList<>(); // Transaction history

    // Constructor
    public TaskThree(String name, int accountNumber, int atmpin) {
        this.balance = 0.0; // Initialize balance to 0.0
        this.name = name;
        this.accountNumber = accountNumber;
        this.atmpin = atmpin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public void checkBalance() {
        System.out.println("Available Balance is: " + getBalance());
    }

    public void withdraw(double withdraw) {
        if (withdraw > getBalance()) {
            System.out.println("Insufficient Balance.");
        } else {
            history.add("Amount withdrawn: " + withdraw);
            System.out.println(withdraw + " Amount withdrawn successfully!");
            setBalance(getBalance() - withdraw);
            checkBalance();
        }
    }

    public void deposit(double deposit) {
        history.add("Amount deposited: " + deposit);
        System.out.println(deposit + " Deposited successfully!");
        setBalance(getBalance() + deposit);
        checkBalance();
    }

    public void transfer(double transfer) {
        if (transfer > getBalance()) {
            System.out.println("Insufficient Balance for Transfer.");
        } else {
            history.add("Amount transferred: " + transfer);
            System.out.println(transfer + " Transfer successful!");
            setBalance(getBalance() - transfer);
            checkBalance();
        }
    }

    public void showHistory() {
        System.out.println("Transaction History:");
        for (String entry : history) {
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initial input of user details
        System.out.println("Welcome to ATM");
        System.out.println("Enter your Name:");
        String name = sc.nextLine();
        System.out.println("Enter Account Number:");
        int accountNumber = sc.nextInt();
        System.out.println("Enter ATM PIN:");
        int atmpin = sc.nextInt();

        TaskThree atm = new TaskThree(name, accountNumber, atmpin);
        System.out.println("Account created successfully!");

        while (true) {
            // Verification step
            System.out.println("Enter Account Number to access ATM:");
            int enteredAccountNumber = sc.nextInt();
            System.out.println("Enter ATM PIN:");
            int enteredAtmpin = sc.nextInt();

            if (enteredAccountNumber == atm.accountNumber && enteredAtmpin == atm.atmpin) {
                System.out.println("Verification successful!");
                while (true) {
                    System.out.println(
                            "1. Check Balance\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Transaction History\n6. Exit");
                    System.out.println("Enter your choice:");
                    int opt = sc.nextInt();

                    switch (opt) {
                        case 1:
                            atm.checkBalance();
                            break;
                        case 2:
                            System.out.println("Enter the amount you want to withdraw:");
                            double withdraw = sc.nextDouble();
                            atm.withdraw(withdraw);
                            break;
                        case 3:
                            System.out.println("Enter the amount you want to deposit:");
                            double deposit = sc.nextDouble();
                            atm.deposit(deposit);
                            break;
                        case 4:
                            System.out.println("Enter recipient's account no.:");
                            int no = sc.nextInt();
                            System.out.println("Enter the amount which will be transferred:");
                            double trans = sc.nextDouble();
                            atm.transfer(trans);
                            break;
                        case 5:
                            atm.showHistory();
                            break;
                        case 6:
                            System.out.println("Thank you for using our ATM! Please visit later.");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Kindly enter the correct choice.");
                    }
                }
            } else {
                System.out.println("Incorrect Account Number or PIN. Please try again.");
            }
        }
    }
}