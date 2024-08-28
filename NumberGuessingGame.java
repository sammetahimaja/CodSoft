import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfAttempts = 0;
            int maxAttempts = 10;
            boolean guessedCorrectly = false;
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");
            while (numberOfAttempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfAttempts++;
                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the correct number in " + numberOfAttempts + " attempts.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all " + maxAttempts + " attempts. The number was " + numberToGuess + ".");
            }
            System.out.print("Do you want to play again? (yes/no): ");
            String userResponse = scanner.next();
            if (!userResponse.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
        System.out.println("Thank you for playing the Number Guessing Game! Goodbye!");
        scanner.close();
    }
}