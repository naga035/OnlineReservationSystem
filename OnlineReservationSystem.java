import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters

    public boolean validateLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

class Reservation {
    private String pnr;
    private String trainNumber;
    private String passengerName;
    private String source;
    private String destination;
    private String dateOfJourney;
    private String classType;

    public Reservation(String pnr, String trainNumber, String passengerName, String source, String destination, String dateOfJourney, String classType) {
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.passengerName = passengerName;
        this.source = source;
        this.destination = destination;
        this.dateOfJourney = dateOfJourney;
        this.classType = classType;
    }

    // Getters

    @Override
    public String toString() {
        return "PNR: " + pnr +
                "\nTrain Number: " + trainNumber +
                "\nPassenger Name: " + passengerName +
                "\nSource: " + source +
                "\nDestination: " + destination +
                "\nDate of Journey: " + dateOfJourney +
                "\nClass Type: " + classType;
    }
}

class ReservationSystem {
    private Map<String, Reservation> reservations;
    private int nextPNR;

    public ReservationSystem() {
        this.reservations = new HashMap<>();
        this.nextPNR = 1;
    }

    public void makeReservation(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Train Number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Passenger Name: ");
        String passengerName = scanner.nextLine();

        System.out.print("Source: ");
        String source = scanner.nextLine();

        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Date of Journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Class Type: ");
        String classType = scanner.nextLine();

        String pnr = "PNR" + nextPNR++;
        Reservation reservation = new Reservation(pnr, trainNumber, passengerName, source, destination, dateOfJourney, classType);
        reservations.put(pnr, reservation);
        
        System.out.println("\nReservation Successful!");
        System.out.println(reservation);

        scanner.close();
    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PNR: ");
        String pnr = scanner.nextLine();

        Reservation reservation = reservations.get(pnr);

        if (reservation != null) {
            System.out.println("\nReservation Details:");
            System.out.println(reservation);

            System.out.print("\nAre you sure you want to cancel the reservation? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                reservations.remove(pnr);
                System.out.println("Reservation Cancelled!");
            } else {
                System.out.println("Cancellation Aborted!");
            }
        } else {
            System.out.println("Reservation not found!");
        }

        scanner.close();
    }
}

public class OnlineReservationSystem {
    private static User currentUser;
    private static ReservationSystem reservationSystem;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        reservationSystem = new ReservationSystem();

        // Login Form
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = new User(username, password);

        if (currentUser.validateLogin(username, password)) {
            // Display main menu
            displayMainMenu();
        } else {
            System.out.println("Invalid login credentials. Exiting...");
        }

        scanner.close();
    }

    public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    reservationSystem.makeReservation(currentUser);
                    break;
                case 2:
                    reservationSystem.cancelReservation();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}