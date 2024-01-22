import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    private static final int QUESTION_COUNT = 3;
    private static final int TIME_LIMIT_PER_QUESTION_SECONDS = 10;

    private static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who is the author of Romeo and Juliet?"
    };

    private static String[][] options = {
            {"A) Madrid", "B) Paris", "C) Berlin", "D) Rome"},
            {"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
            {"A) Charles Dickens", "B) William Shakespeare", "C) Jane Austen", "D) Mark Twain"}
    };

    private static char[] correctAnswers = {'B', 'B', 'B'};

    private static int userScore = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Game!");

        for (int i = 0; i < QUESTION_COUNT; i++) {
            presentQuestion();
            waitForAnswer(scanner);
        }

        displayResult();
        scanner.close();
    }

    private static void presentQuestion() {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);

        for (String option : options[currentQuestionIndex]) {
            System.out.println(option);
        }

        startTimer();
    }

    private static void waitForAnswer(Scanner scanner) {
        System.out.print("\nEnter your answer (A/B/C/D): ");
        String userAnswer = scanner.next().toUpperCase();

        if (userAnswer.length() == 1 && userAnswer.charAt(0) == correctAnswers[currentQuestionIndex]) {
            System.out.println("Correct!");
            userScore++;
        } else {
            System.out.println("Incorrect. The correct answer is " + correctAnswers[currentQuestionIndex]);
        }

        currentQuestionIndex++;
        stopTimer();
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                currentQuestionIndex++;
                displayResult();
            }
        }, TIME_LIMIT_PER_QUESTION_SECONDS * 1000);
    }

    private static void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private static void displayResult() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score: " + userScore + " out of " + QUESTION_COUNT);

        // Additional details such as correct/incorrect answers summary can be added here
    }
}
