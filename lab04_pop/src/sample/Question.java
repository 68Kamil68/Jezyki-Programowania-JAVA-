package sample;

import java.util.Arrays;
import java.util.List;

public class Question {

    private String questionText;
    private List<String> answers;
    private int correctAnswerIndex;

    public Question(String questionText, int correctAnswerIndex, String... answers)
    {
        this.answers = Arrays.asList(answers);
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer()
    {
        return answers.get(correctAnswerIndex);
    }
}
