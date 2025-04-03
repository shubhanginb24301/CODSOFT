package numbergame;




import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 5;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    
    public static void main(String[] args) {
        boolean playAgain = true;
        int totalScore = 0;
        int roundsWon = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;
            
            System.out.println("New round! Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ". You have " + MAX_ATTEMPTS + " attempts.");
            
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = getUserInput();
                
                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    totalScore += attemptsLeft * 10; // More points for fewer attempts
                    roundsWon++;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }
            
            if (!guessedCorrectly) {
                System.out.println("You've run out of attempts! The correct number was " + numberToGuess);
            }
            
            System.out.println("Your total score: " + totalScore);
            System.out.println("Rounds won: " + roundsWon);
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score: " + totalScore);
        System.out.println("Total rounds won: " + roundsWon);
        scanner.close();
    }
    
    private static int getUserInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
