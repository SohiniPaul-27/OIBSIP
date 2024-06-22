import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Ticket {
    String name;
    int age;
    String coach;
    long mobno;
    int amt = 500; // Initializing amount to 500
    int totalamt;
    String trainNumber;
    String journeyDate;
    String from;
    String to;
    int pnrNumber;

    void accept() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Enter age:");
        age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter coach:");
        coach = scanner.nextLine();
        System.out.println("Enter mobile no.:");
        mobno = scanner.nextLong();
        scanner.nextLine();
    }

    void update() {
        if (coach.equals("1AC")) {
            totalamt = amt + 6500;
        } else if (coach.equals("2AC")) {
            totalamt = amt + 4500;
        } else if (coach.equals("3AC")) {
            totalamt = amt + 3200;
        } else if (coach.equals("sleeper")) {
            totalamt = amt;
        }
    }

    void generatePnrNumber() {
        Random random = new Random();
        pnrNumber = 100000 + random.nextInt(899999);
    }

    void display() {
        System.out.println("PNR Number: " + pnrNumber);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Coach: " + coach);
        System.out.println("Mobile No: " + mobno);
        System.out.println("Non-refundable Amount: " + amt);
        System.out.println("Total Amount to be paid: " + totalamt);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Journey Date: " + journeyDate);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }
}

public class Taskone {
    ArrayList<Ticket> tickets = new ArrayList<>();

    boolean validateDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Taskone railwayTicketSystem = new Taskone();
        System.out.println("Welcome to IRCTC!");

        while (true) {
            Ticket ticket = new Ticket();
            ticket.accept();

            System.out.println("Enter Train Number: ");
            ticket.trainNumber = sc.nextLine();

            while (true) {
                System.out.println("Enter Journey Date (YYYY-MM-DD): ");
                ticket.journeyDate = sc.nextLine();
                if (railwayTicketSystem.validateDate(ticket.journeyDate)) {
                    break;
                } else {
                    System.out.println("Invalid date format. Please try again.");
                }
            }

            System.out.println("Enter From Location: ");
            ticket.from = sc.nextLine();
            System.out.println("Enter To Location: ");
            ticket.to = sc.nextLine();
            ticket.generatePnrNumber();
            ticket.update();
            railwayTicketSystem.tickets.add(ticket);

            System.out.println("Record Inserted with PNR Number: " + ticket.pnrNumber);

            System.out.println("Do you want to add more tickets? (yes/no)");
            String response = sc.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        while (true) {
            System.out.println("Enter the choice: ");
            System.out.println("1. Show All Records.");
            System.out.println("2. Delete Record.");
            System.out.println("3. Exit.");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("\nAll records printing.\n");
                for (Ticket t : railwayTicketSystem.tickets) {
                    t.display();
                    System.out.println("--------------");
                }
            } else if (choice == 2) {
                System.out.println("Enter the PNR number to delete the record: ");
                int pnrNumberToDelete = sc.nextInt();
                sc.nextLine();
                boolean found = false;
                for (int i = 0; i < railwayTicketSystem.tickets.size(); i++) {
                    if (railwayTicketSystem.tickets.get(i).pnrNumber == pnrNumberToDelete) {
                        railwayTicketSystem.tickets.remove(i);
                        System.out.println("Record with PNR Number " + pnrNumberToDelete + " Deleted.");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Record with PNR Number " + pnrNumberToDelete + " not found.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting the program.\n");
                System.out.println("Thank you for visiting us!");
                break;
            } else {
                System.out.println("Invalid Choice Entered.\n");
            }
        }
    }
}
