package rpiano_HW2;

import java.util.ArrayList;
import java.util.List;

/**
 * The Questions class manages a collection of Question objects.
 * It allows creating, deleting, retrieving, and searching questions.
 */

public class Questions {
    private List<Question> questions;

    // Constructor to initialize the questions list
    public Questions() {
        this.questions = new ArrayList<>();
    }

    // Adds a new question to the list if it is valid
    public void addQuestion(Question q) {
        if (q != null && q.getQuestion() != null) {
            questions.add(q);
            System.out.println("Question successfully added to thread");
        } else {
            System.out.println("***ERROR***: Cannot add an invalid question");
        }
    }

    // Deletes a question from the list
    public void deleteQuestion(Question q) {
        questions.remove(q);
    }

    // Returns all questions in the list
    public List<Question> getQuestions() {
        return questions;
    }

    // Finds questions containing a keyword (case-insensitive)
    public List<Question> findRelatedQuestions(String keyword) {
        List<Question> relatedQuestions = new ArrayList<>();
        for (Question q : questions) {
            if (q.getQuestion().toLowerCase().contains(keyword.toLowerCase())) {
                relatedQuestions.add(q);
            }
        }
        return relatedQuestions;
    }
}
