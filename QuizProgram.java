import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private List<Boolean> results;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.results = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            // Timer for each question
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    synchronized (scanner) {
                        scanner.notify();
                    }
                }
            };
            timer.schedule(task, 10000);  // 10 seconds for each question

            System.out.print("Your answer (1-4): ");
            synchronized (scanner) {
                try {
                    scanner.wait(10000);  // Wait for the user input for up to 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                if (answer == question.getCorrectAnswer()) {
                    score++;
                    results.add(true);
                } else {
                    results.add(false);
                }
            } else {
                results.add(false);
                scanner.next();  // Consume the invalid input
            }

            task.cancel();  // Cancel the timer for the current question
        }

        timer.cancel();  // Cancel the overall timer
        scanner.close();

        displayResults();
    }

    private void displayResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            String result = results.get(i) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + result);
        }
    }
}

public class QuizProgram {

    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Adding questions with specific options
        List<String> options1 = List.of("Berlin", "Paris", "Rome", "Madrid");
        quiz.addQuestion(new Question("What is the capital of France?", options1, 2));

        List<String> options2 = List.of("Venus", "Saturn", "Mars", "Jupiter");
        quiz.addQuestion(new Question("Which planet is known as the Red Planet?", options2, 3));

        List<String> options3 = List.of("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean");
        quiz.addQuestion(new Question("What is the largest ocean on Earth?", options3, 4));

        // Start the quiz
        quiz.start();
    }
}