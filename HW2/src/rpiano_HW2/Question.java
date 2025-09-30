package rpiano_HW2;

/**
 * The Question class represents a single question.
 * Each Question has a text value and a related collection of Answers.
 */

public class Question {
    private String question;
    private Answers answers; // Each question owns its own set of answers

    // Constructor validates the question before creating it
    public Question(String q) {
        if (!isValidQuestion(q)) {
            System.out.println("***Error***: Invalid question. Must end with '?' and be less than 256 characters");
            this.question = null;
            this.answers = new Answers(); // still initialize so it's not null
            return;
        }
        this.question = q;
        this.answers = new Answers();
        System.out.println("Question created: " + q);
    }

    // Returns the question text
    public String getQuestion() {
        return question;
    }

    // Returns the Answers collection
    public Answers getAnswers() {
        return answers;
    }

    // Updates the text of the question (does not re-validate here)
    public void updateQuestion(String q) {
        this.question = q;
    }

    // Validates question rules
    public boolean isValidQuestion(String q) {
        return q.length() <= 256 && q.endsWith("?");
    }

    @Override
    public String toString() {
        return this.question;
    }
}

