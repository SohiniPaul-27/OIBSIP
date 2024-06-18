import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Question {
    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class User {
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> userDatabase = new HashMap<>();
    static List<Question> questions = new ArrayList<>();

    static {
        // Initialize questions
        initializeQuestions();
    }

    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void registerUser() {
        System.out.println("Enter a username for registration:");
        String newUsername = sc.next();
        System.out.println("Enter a password for registration:");
        String newPassword = sc.next();

        if (userDatabase.containsKey(newUsername)) {
            System.out.println("Username already exists. Please try again.");
        } else {
            userDatabase.put(newUsername, newPassword);
            System.out.println("Registration successful! You can now log in.");
        }
    }

    public static void loginUser() {
        System.out.println("Enter username:");
        String username = sc.next();
        System.out.println("Enter password:");
        String password = sc.next();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            System.out.println("\nLOGIN SUCCESSFULLY...!");
            User user = new User(username, password);
            user.homepage();
        } else {
            System.out.println("\nInvalid login credentials! Try again later.");
        }
    }

    private void homepage() {
        System.out.println("Welcome to the homepage, " + username + "!");
        System.out.println("1. Start the test. Best of Luck!");
        System.out.println("2. Logout");

        System.out.println("Select option:");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                startTest();
                break;
            case 2:
                System.out.println("\nLogged out Successfully!");
                System.out.println("Thank You for Attending the Test.");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid Option Choice. Try again.");
                homepage();
                break;
        }
    }

    private void startTest() {
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.questionText);
            for (int i = 0; i < question.options.size(); i++) {
                System.out.println((i + 1) + ". " + question.options.get(i));
            }
            System.out.print("Enter answer (1-" + question.options.size() + "): ");
            int answer = sc.nextInt();
            if (answer == question.correctOption) {
                score += 2; // 2 points for each correct answer
            }
        }

        System.out
                .println("Congrats " + username + "! You scored " + score + " marks out of " + (questions.size() * 2));
        homepage();
    }

    public static void initializeQuestions() {
        List<String> options1 = new ArrayList<>();
        options1.add("6");
        options1.add("3");
        options1.add("4");
        options1.add("more than 6");

        List<String> options2 = new ArrayList<>();
        options2.add("4");
        options2.add("6");
        options2.add("10");
        options2.add("8");

        List<String> options3 = new ArrayList<>();
        options3.add("2");
        options3.add("4");
        options3.add("0");
        options3.add("1");

        List<String> options4 = new ArrayList<>();
        options4.add("2");
        options4.add("4");
        options4.add("3");
        options4.add("1");

        List<String> options5 = new ArrayList<>();
        options5.add("40");
        options5.add("21");
        options5.add("20");
        options5.add("41");

        questions.add(new Question("3x + 4|y| = 33. How many integer values of (x, y) are possible?", options1, 4));
        questions.add(new Question("(|x| - 3) (|y| + 4) = 12. How many pairs of integers (x, y) satisfy this equation?",
                options2, 3));
        questions.add(new Question("x + |y| = 8, |x| + y = 6. How many pairs of x, y satisfy these two equations?",
                options3, 1));
        questions.add(
                new Question("What is the number of real solutions of the equation x^2 - 7|x| - 18 = 0?", options4, 1));
        questions.add(
                new Question("x^2 - 9x + |k| = 0 has real roots. How many integer values can 'k' take?", options5, 4));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("----Welcome to The Online Examination Portal----");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you for using the portal.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}