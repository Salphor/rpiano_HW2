package rpiano_HW2;


/**
 * CrudTesting Class
 *
 * A demonstration of semi-automated tests for the Questions, Question,
 * Answers, and Answer classes. Each test prints results directly so the
 * developer can confirm expected behavior.
 */

public class CrudTesting {

    // Global collection of all questions for testing
    static Questions questions = new Questions();

    public static void main(String[] args) {
        System.out.println("========== Automated Testing Begin ==========\n");

        createQuestionTestCase();
        System.out.println("------------------------------------------");

        createAnswerTestCase();
        System.out.println("------------------------------------------");

        updateQuestionTestCase();
        System.out.println("------------------------------------------");

        markAnswerAsCorrectTestCase();
        System.out.println("------------------------------------------");

        getCorrectAnswerTestCase();
        System.out.println("------------------------------------------");

        findRelatedQuestionsTestCase();
        System.out.println("------------------------------------------");

        deleteAnswerTestCase();
        System.out.println("------------------------------------------");

        deleteQuestionTestCase();
        System.out.println("------------------------------------------");

        edgeCaseTesting();
        System.out.println("------------------------------------------");

        System.out.println("========== Automated Testing Complete ==========");
    }

    /**
     * Tests creating valid and invalid questions
     */
    public static void createQuestionTestCase() {
        System.out.println("Testing createQuestionTestCase...");

        // Valid questions
        Question q1 = new Question("Is this a valid question?");
        Question q2 = new Question("What am I supposed to do?");
        questions.addQuestion(q1);
        questions.addQuestion(q2);

        // Invalid question (missing '?')
        Question invalidQ = new Question("This is not a valid question.");
        questions.addQuestion(invalidQ); // should reject

        // Print stored questions
        System.out.println("Stored Questions:");
        for (Question q : questions.getQuestions()) {
            System.out.println(" - " + q.getQuestion());
        }
    }

    /**
     * Tests creating valid and invalid answers
     */
    public static void createAnswerTestCase() {
        System.out.println("Testing createAnswerTestCase...");

        // Get first valid question
        Question firstQuestion = questions.getQuestions().get(0);

        // Valid answers
        Answer a1 = new Answer("This is a valid answer.", false);
        Answer a2 = new Answer("Another possible response.", false);

        // Invalid answer (missing '.')
        Answer invalidA = new Answer("Invalid answer?", false);

        // Add answers to the question
        firstQuestion.getAnswers().addAnswer(a1);
        firstQuestion.getAnswers().addAnswer(a2);
        firstQuestion.getAnswers().addAnswer(invalidA); // should reject

        // Print stored answers
        System.out.println("Stored Answers for first question:");
        for (Answer a : firstQuestion.getAnswers().getAnswers()) {
            System.out.println(" - " + a.getAnswer());
        }
    }

    /**
     * Tests updating a question
     */
    public static void updateQuestionTestCase() {
        System.out.println("Testing updateQuestionTestCase...");

        Question firstQuestion = questions.getQuestions().get(0);
        System.out.println("Before update: " + firstQuestion.getQuestion());

        firstQuestion.updateQuestion("Is this an updated version of the question?");
        System.out.println("After update: " + firstQuestion.getQuestion());
    }

    /**
     * Tests marking an answer as correct
     */
    public static void markAnswerAsCorrectTestCase() {
        System.out.println("Testing markAnswerAsCorrectTestCase...");

        Question firstQuestion = questions.getQuestions().get(0);
        Answer firstAnswer = firstQuestion.getAnswers().getAnswers().get(0);

        firstAnswer.markAsCorrect();
        System.out.println("Marked as correct: " + firstAnswer.getAnswer());
    }

    /**
     * Tests retrieving the correct answer
     */
    public static void getCorrectAnswerTestCase() {
        System.out.println("Testing getCorrectAnswerTestCase...");

        Question firstQuestion = questions.getQuestions().get(0);
        Answer correct = firstQuestion.getAnswers().getCorrectAnswer();

        if (correct != null) {
            System.out.println("Correct answer found: " + correct.getAnswer());
        } else {
            System.out.println("No correct answer set.");
        }
    }

    /**
     * Tests searching for related questions by keyword
     */
    public static void findRelatedQuestionsTestCase() {
        System.out.println("Testing findRelatedQuestionsTestCase...");

        String keyword = "what";
        System.out.println("Searching for questions containing '" + keyword + "'...");
        for (Question q : questions.findRelatedQuestions(keyword)) {
            System.out.println(" - " + q.getQuestion());
        }
    }

    /**
     * Tests deleting an answer from a question
     */
    public static void deleteAnswerTestCase() {
        System.out.println("Testing deleteAnswerTestCase...");

        Question firstQuestion = questions.getQuestions().get(0);
        Answer answerToDelete = firstQuestion.getAnswers().getAnswers().get(1);

        System.out.println("Deleting answer: " + answerToDelete.getAnswer());
        firstQuestion.getAnswers().deleteAnswer(answerToDelete);

        System.out.println("Remaining Answers:");
        for (Answer a : firstQuestion.getAnswers().getAnswers()) {
            System.out.println(" - " + a.getAnswer());
        }
    }

    /**
     * Tests deleting a question
     */
    public static void deleteQuestionTestCase() {
        System.out.println("Testing deleteQuestionTestCase...");

        Question toDelete = questions.getQuestions().get(1);
        System.out.println("Deleting question: " + toDelete.getQuestion());
        questions.deleteQuestion(toDelete);

        System.out.println("Remaining Questions:");
        for (Question q : questions.getQuestions()) {
            System.out.println(" - " + q.getQuestion());
        }
    }

    /**
     * Edge case testing for safety
     */
    public static void edgeCaseTesting() {
        System.out.println("Testing edge cases...");

        // Attempt to delete from empty answers list
        Question newQ = new Question("Does this handle empty answers?");
        questions.addQuestion(newQ);
        newQ.getAnswers().deleteAnswer(new Answer("Not stored.", false)); // should silently do nothing

        // Attempt to find correct answer when none exist
        Answer correct = newQ.getAnswers().getCorrectAnswer();
        if (correct == null) {
            System.out.println("Correctly handled: No correct answer found in empty set.");
        }

        // Add invalid questions directly
        Question badQ = new Question("Invalid question.");
        questions.addQuestion(badQ);
    }
}

