import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskTwo {
    public static void guessingNumberGame() {
        Scanner scanner = new Scanner(System.in);
        int number;
        number = 1 + (int) (100 * Math.random());

        int K = 3; // Number of trials
        int totalScore = 0;
        int attempts = 0;
        int Score = 0;
        System.out.println(
                "A number is chosen between 1 to 100. Guess the number within 3 trials.");

        for (int i = 0; i < K; i++) {
            System.out.println("Guess the number:");
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next(); // Clear the invalid input
                i--; // Don't count this as an attempt
                continue;
            }

            attempts++;

            if (number == guess) {
                System.out.println("Congrats! You guessed the correct number.");
                int score = 100 - (10 * (attempts - 1)); // Score logic: higher score for fewer attempts
                totalScore += score;
                break;
            } else if (number > guess && i != K - 1) {
                System.out.println("The number is greater than " + guess);
            } else if (number < guess && i != K - 1) {
                System.out.println("The number is less than " + guess);
            }

            // Checking if the guess is near the correct number (+5 or -5 range)
            if ((number - 5 <= guess && guess < number) || (number < guess && guess <= number + 5)) {
                System.out.println("You are +/-5 to the correct number!");
                totalScore += 5; // Awarding some points for being close
            }
        }

        if (attempts == K && totalScore == 0) { // if no correct guess was made and all attempts were used
            System.out.println("You have exhausted " + K + " trials.");
            System.out.println("The number was " + number);
        }

        System.out.printf("Game Over. Total Score=%d\n", totalScore);
        System.out.println("The number was " + number);
        scanner.close();
    }

    public static void main(String[] args) {
        guessingNumberGame();
    }
}