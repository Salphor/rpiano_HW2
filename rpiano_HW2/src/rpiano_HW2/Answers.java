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
    
    /**
	 * Marks only one answer as correct, if another is marked correct,
	 * will clear and set new as correct
	 * will print error if target does not belong to question
     */
    
    public void setOnlyCorrect(Answer target) {
        if (!answers.contains(target)) {
            System.out.println("***ERROR***: Answer not part of this question.");
            return;
        }
        for (Answer a : answers) a.markAsCorrect(false);
        target.markAsCorrect(true);
        System.out.println("Correct/accepted answer set: " + target.getAnswer());
    }

    // Unmark any correct/accepted answer; question becomes unresolved
    
    public void clearCorrect() {
        for (Answer a : answers) a.markAsCorrect(false);
    }

    // Count unread answers, for simple UI badges
    public int getUnreadCount() {
        int count = 0;
        for (Answer a : answers) if (!a.isRead) count++;
        return count;
    }

}
