package rpiano_HW2;


/**
 * CrudTesting
 *
 *  Test driver that demonstrates CRUD processes for the Questions,
 *  Question, Answers, and Answer classes. Also tests implementations
 *  of search and derived Questions (questions based on other questions), unread counting
 *  and validation of single "correct" answer that resolved problem
 */
public class CrudTesting {

    // Global collection of all questions for testing
    static Questions questions = new Questions();

    public static void main(String[] args) {
        System.out.println("========== Automated Testing Begin ==========\n");

        createQuestionTestCase();
        System.out.println("------------------------------------------");

        deriveQuestionFlowTestCase();
        System.out.println("------------------------------------------");

        createAnswerTestCase();
        System.out.println("------------------------------------------");

        markCorrectViaQuestionTestCase();
        System.out.println("------------------------------------------");

        getCorrectAnswerTestCase();
        System.out.println("------------------------------------------");

        getUnreadCountTestCase();
        System.out.println("------------------------------------------");
        
        updateQuestionTestCase();
        System.out.println("------------------------------------------");


        resolvedFiltersTestCase();
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

    // Create valid and invalid questions; store only valid ones. 
    public static void createQuestionTestCase() {
        System.out.println("____Testing createQuestionTestCase____");

        Question q1 = new Question("Is this a valid question?");
        Question q2 = new Question("What am I supposed to do?");
        questions.addQuestion(q1);
        questions.addQuestion(q2);

        // Invalid (no '?') should be rejected by addQuestion
        Question invalidQ = new Question("This is not a valid question.");
        questions.addQuestion(invalidQ);

        System.out.println("Stored Questions:");
        for (Question q : questions.getQuestions()) {
            System.out.println(" - " + q.getQuestion());
        }
    }

    // Demonstrate creating a derived question and listing all children of a parent
    public static void deriveQuestionFlowTestCase() {
        System.out.println("____Testing deriveQuestionFlowTestCase____ ");

        Question parent = questions.getQuestions().get(0);
        Question child1 = questions.deriveQuestion(parent, "Refined: can you show an example?");
        Question child2 = questions.deriveQuestion(parent, "Refined: what about edge cases?");

        System.out.println("Parent Question: " + parent.getQuestion());
     // Direct check: show parent linkage for each child
        System.out.println("Direct check of parent-child link: ");
        System.out.println("Child1: " + child1.getQuestion() + " | derived from -> " + child1.getDerivedFrom().getQuestion());
        System.out.println("Child2: " + child2.getQuestion() + " | derived from -> " + child2.getDerivedFrom().getQuestion());
        
        System.out.println("Derived Questions from method getDerivedQuestions:");
        for (Question q : questions.getDerivedQuestions(parent)) {
            System.out.println(" - " + q.getQuestion());
        }
    }

    // Create valid/invalid answers; invalid is rejected. 
    public static void createAnswerTestCase() {
        System.out.println("____Testing createAnswerTestCase____ ");

        Question firstQuestion = questions.getQuestions().get(0);

        Answer a1 = new Answer("This is a valid answer.", false);
        Answer a2 = new Answer("Another possible response.", false);
        Answer invalidA = new Answer("Invalid answer?", false); // no '.'

        firstQuestion.getAnswers().addAnswer(a1);
        firstQuestion.getAnswers().addAnswer(a2);
        firstQuestion.getAnswers().addAnswer(invalidA); // rejected

        System.out.println("Stored Answers for first question:");
        for (Answer a : firstQuestion.getAnswers().getAnswers()) {
            System.out.println(" - " + a.getAnswer() + " (Correct: " + a.getIsCorrect() + ", Read: " + a.isRead + ")");
        }
    }

    // Mark a single answer correct and switch it to another; verify uniqueness
    public static void markCorrectViaQuestionTestCase() {
        System.out.println("____Testing markCorrectViaQuestionTestCase____ ");
        Question q = questions.getQuestions().get(0);

        Answer a1 = q.getAnswers().getAnswers().get(0);
        Answer a2 = q.getAnswers().getAnswers().get(1);

        q.markAnswerAsCorrect(a1);
        System.out.println("Correct now: " + q.getAnswers().getCorrectAnswer().getAnswer());

        // Switch correctness to a2 and verify uniqueness
        q.markAnswerAsCorrect(a2);
        System.out.println("After switch, correct: " + q.getAnswers().getCorrectAnswer().getAnswer());
        System.out.println("a1 correct? " + a1.getIsCorrect() + " | a2 correct? " + a2.getIsCorrect());
        System.out.println("Question resolved? " + q.isResolved());
    }

    // Retrieve the correct answer
    public static void getCorrectAnswerTestCase() {
        System.out.println("____Testing getCorrectAnswerTestCase____ ");

        Question firstQuestion = questions.getQuestions().get(0);
        Answer correct = firstQuestion.getAnswers().getCorrectAnswer();

        if (correct != null) {
            System.out.println("Correct answer found: " + correct.getAnswer());
        } else {
            System.out.println("No correct answer set.");
        }
    }

    // Show unread count and how it changes after reading an answer
    public static void getUnreadCountTestCase() {
        System.out.println("____Testing getUnreadCountTestCase____ ");

        Question firstQuestion = questions.getQuestions().get(0);

        // Initial unread state (all answers unread by default)
        System.out.println("Initial unread count: " + firstQuestion.getAnswers().getUnreadCount());

        Answer a1 = firstQuestion.getAnswers().getAnswers().get(0);
        Answer a2 = firstQuestion.getAnswers().getAnswers().get(1);

        // Check default flags
        System.out.println("a1.isRead = " + a1.isRead);
        System.out.println("a2.isRead = " + a2.isRead);

        // Mark one as read
        a1.markAsRead();
        System.out.println("After marking a1 read:");
        System.out.println("a1.isRead = " + a1.isRead);
        System.out.println("a2.isRead = " + a2.isRead);
        System.out.println("Unread count: " + firstQuestion.getAnswers().getUnreadCount());

        // Mark the other as read too
        a2.markAsRead();
        System.out.println("After marking both read:");
        System.out.println("a1.isRead = " + a1.isRead);
        System.out.println("a2.isRead = " + a2.isRead);
        System.out.println("Unread count: " + firstQuestion.getAnswers().getUnreadCount());
    }


    // Filters for resolved vs unresolved questions.
    public static void resolvedFiltersTestCase() {
        System.out.println("____Testing resolved/unresolved filters____ ");
        System.out.println("Resolved questions:");
        for (Question q : questions.getResolvedQuestions()) System.out.println(" - " + q.getQuestion());
        System.out.println("Unresolved questions:");
        for (Question q : questions.getUnresolvedQuestions()) System.out.println(" - " + q.getQuestion());
    }

    // Keyword search demonstration.
    public static void findRelatedQuestionsTestCase() {
        System.out.println("____Testing findRelatedQuestionsTestCase____ ");

        String keyword = "what";
        System.out.println("Searching for questions containing '" + keyword + "'...");
        for (Question q : questions.findRelatedQuestions(keyword)) {
            System.out.println(" - " + q.getQuestion());
        }
    }
    
    // Test updating a question with valid and invalid text. 
    public static void updateQuestionTestCase() {
        System.out.println("____Testing updateQuestionTestCase____ ");

        // Grab the first stored question
        Question q = questions.getQuestions().get(0);
        System.out.println("Original: " + q.getQuestion());

        // Valid update
        q.updateQuestion("Updated: Is this working?");
        System.out.println("After valid update: " + q.getQuestion());

        // Invalid update (missing '?')
        q.updateQuestion("This better fail validation");
        System.out.println("After invalid update attempt: " + q.getQuestion());

        // Too long (257 chars, fails validation)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) sb.append("x");
        q.updateQuestion(sb.toString());
        System.out.println("After too-long update attempt: " + q.getQuestion());
    }


    // Delete an answer and show the remaining ones. 
    public static void deleteAnswerTestCase() {
        System.out.println("____Testing deleteAnswerTestCase____ ");

        Question firstQuestion = questions.getQuestions().get(0);
        if (firstQuestion.getAnswers().getAnswers().size() > 1) {
            Answer toDelete = firstQuestion.getAnswers().getAnswers().get(1);
            System.out.println("Deleting answer: " + toDelete.getAnswer());
            firstQuestion.getAnswers().deleteAnswer(toDelete);
        }
        System.out.println("Remaining Answers:");
        for (Answer a : firstQuestion.getAnswers().getAnswers()) {
            System.out.println(" - " + a.getAnswer());
        }
    }

    // Delete a question and show remaining ones. 
    public static void deleteQuestionTestCase() {
        System.out.println("____Testing deleteQuestionTestCase____ ");

        if (questions.getQuestions().size() > 1) {
            Question toDelete = questions.getQuestions().get(1);
            System.out.println("Deleting question: " + toDelete.getQuestion());
            questions.deleteQuestion(toDelete);
        }
        System.out.println("Remaining Questions:");
        for (Question q : questions.getQuestions()) {
            System.out.println(" - " + q.getQuestion());
        }
        // Space a line for easier visibility
        System.out.println("");
        // also want to test deletion of questions with parent/child relationships
        
        System.out.println("____Testing deleteQuestionTestCase with derived questions (parent/child)____ ");
        
        // create a parent and child
        Question parent = new Question("Parent: Can we delete this?");
        questions.addQuestion(parent);
        Question child = questions.deriveQuestion(parent, "Child: follow-up question?");
        
        // attempt to delete parent (should block)
        System.out.println("Attempting to delete parent (should fail): ");
        questions.deleteQuestion(parent);
        
        // verify parent exists still
        boolean parentExists = questions.getQuestions().contains(parent);
        System.out.println("Parent still present? " + parentExists);
        
        // delete child
        System.out.println("Deleting child question");
        questions.deleteQuestion(child);
        
        // attempt to delete parent again
        System.out.println("Attempting to delete parent again (Should succeed): ");
        questions.deleteQuestion(parent);
        
        // check if parent was deleted
        parentExists = questions.getQuestions().contains(parent);
        System.out.println("Parent still present? " + parentExists);
    }

    // Edge case testing around empty lists, invalid inserts, etc
    public static void edgeCaseTesting() {
        System.out.println("____Testing edge cases____ ");

        // Empty answers deletion (no-op)
        Question newQ = new Question("Does this handle empty answers?");
        questions.addQuestion(newQ);
        newQ.getAnswers().deleteAnswer(new Answer("Not stored.", false));

        // Correct answer retrieval when none exist
        Answer correct = newQ.getAnswers().getCorrectAnswer();
        if (correct == null) {
        	System.out.println("Correctly handled: No correct answer in empty set.");
        } else {
        	System.out.println("Unexpected: found a correct answer.");
        }

        // Adding invalid question (no '?')
        Question badQ = new Question("Invalid question.");
        questions.addQuestion(badQ);
    }
}
