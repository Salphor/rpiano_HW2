package rpiano_HW2;
import java.util.List;
import java.util.ArrayList;

/**
 * The Answers class manages a collection of Answer objects.
 * It allows adding, deleting, and retrieving answers (including the correct one).
 */

public class Answers {
    private List<Answer> answers;

    // Constructor to initialize the answers list
    public Answers() {
        this.answers = new ArrayList<>();
    }

    // Adds an answer if valid (not null)
    public void addAnswer(Answer a) {
        if (a != null && a.getAnswer() != null) {
            answers.add(a);
            System.out.println("Answer successfully added to question");
        } else {
            System.out.println("***ERROR***: Cannot add invalid answer");
        }
    }

    // Deletes an answer
    public void deleteAnswer(Answer a) {
        answers.remove(a);
    }

    // Returns all answers
    public List<Answer> getAnswers() {
        return answers;
    }

    // Returns the first correct answer found (or null if none)
    public Answer getCorrectAnswer() {
        for (Answer a : answers) {
            if (a.getIsCorrect()) {
                return a;
            }
        }
        return null;
    }
}
