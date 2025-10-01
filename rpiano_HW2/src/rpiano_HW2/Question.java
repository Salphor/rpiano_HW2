package rpiano_HW2;

/**
 * The Question class represents a single question.
 * Each Question has a text value and a related collection of Answers.
 */

public class Question {
    private String question;
    private Answers answers; // Each question owns its own set of answers
    private Question derivedFrom; // can be null if question is original

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
    // Create a new question derived from an existing "parent" question
    public Question(String q, Question parent) {
        if (!isValidQuestion(q)) {
            System.out.println("***Error***: Invalid question. Must end with '?' and be less than 256 characters");
            this.question = null;
            this.answers = new Answers();
            this.derivedFrom = parent; // still keep link for lineage even though invalid
            return;
        }
        this.question = q;
        this.answers = new Answers();
        this.derivedFrom = parent;
        System.out.println("Derived question created" + (parent != null ? " based on: " + parent.getQuestion() : "") );
    }

    // Returns the question text
    public String getQuestion() {
        return question;
    }

    // Returns the Answers collection
    public Answers getAnswers() {
        return answers;
    }
    // If this is a child question, returns the original parent; otherwise null
    public Question getDerivedFrom() {
    	return derivedFrom; 
    	}

    // Convenience: accept a specific Answer as the single correct answer
    public void markAnswerAsCorrect(Answer a) {
    	answers.setOnlyCorrect(a);
    	}

    // Resolved IF a correct/accepted answer exists
    public boolean isResolved() {
    	return answers.getCorrectAnswer() != null;
    	}

    // method of finding correct answer based on if question is Resolved
    public Answer getResolvedAnswer() {
    	return answers.getCorrectAnswer(); 
    	}

    // Updates the text of the question
    public void updateQuestion(String q) {
    	if(isValidQuestion(q)) {
            this.question = q;
            System.out.println("Question Updated: " + q);
    	}
    	else {
            System.out.println("***Error***: Invalid question. Must end with '?' and be less than 256 characters");
    	}
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

