package rpiano_HW2;

/**
 * The Answer class represents a single answer.
 * It stores the answer text and whether or not it is correct.
 */

public class Answer {
    private String answer;
    private boolean isCorrect;
    public boolean isRead;

    // Constructor validates the answer before creating it
    public Answer(String a, boolean isCorrect) {
        if (!isValidAnswer(a)) {
            System.out.println("***Error***: Invalid Answer. Must end with '.' and be less than 256 characters");
            this.answer = null;
            return;
        }
        this.answer = a;
        this.isCorrect = isCorrect;
        this.isRead = false;
        System.out.println("Answer created: " + a);
    }

    // Returns whether the answer is marked correct
    public boolean getIsCorrect() {
        return this.isCorrect;
    }

    // Returns the answer text
    public String getAnswer() {
        return answer;
    }

    // Marks this answer as correct
    public void markAsCorrect(boolean value) {
        this.isCorrect = value;
    }
    // Mark as read
    public void markAsRead() {
    	this.isRead = true;
    }

    // Validates answer rules
    public boolean isValidAnswer(String a) {
        return a.length() <= 256 && a.endsWith(".");
    }

    @Override
    public String toString() {
        return this.answer;
    }
}
