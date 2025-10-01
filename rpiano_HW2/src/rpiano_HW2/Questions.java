package rpiano_HW2;

import java.util.ArrayList;
import java.util.List;

/**
 * "Questions is a simple list of Question with helpers for search, filters,
 *  and lineage (derived questions)."
 *
 * Note: All operations use in-memory ArrayList. 
 * - Can swap to DB later if team likes this implementation
 * 	 by adding question ids and a repository interface
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
    /** Return all resolved questions. O(N * M) because isResolved() scans answers. */
    public List<Question> getResolvedQuestions() {
        List<Question> resolved = new ArrayList<>();
        for (Question q : questions) if (q != null && q.isResolved()) resolved.add(q);
        return resolved;
    }
    /** Return all unresolved questions. O(N * M) because isResolved() scans answers. */
    public List<Question> getUnresolvedQuestions() {
        List<Question> unresolved = new ArrayList<>();
        for (Question q : questions) if (q != null && !q.isResolved()) unresolved.add(q);
        return unresolved;
    }
    /**
     * Create + add a derived question from a given parent and refined text.
     * Returns the newly added child, or null if invalid. O(1) amortized.
     */
    public Question deriveQuestion(Question parent, String refinedText) {
        if (parent == null) {
            System.out.println("***ERROR***: Parent question is null.");
            return null;
        }
        Question child = new Question(refinedText, parent);
        addQuestion(child);
        return child;
    }
    /**
     * Return all questions that were derived from the given parent. O(N).
     * Uses reference equality (==) because we are in-memory. If you later
     * move to a DB with IDs, compare by parentId instead.
     */
    public List<Question> getDerivedQuestions(Question parent) {
        List<Question> children = new ArrayList<>();
        if (parent == null) return children;
        for (Question q : questions) {
            if (q != null && q.getDerivedFrom() == parent) {
                children.add(q);
            }
        }
        return children;
    }

}
