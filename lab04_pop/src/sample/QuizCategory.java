package sample;

import java.util.ArrayList;

public class QuizCategory {

    private ArrayList<Question> questions;
    private String categoryName;

    public QuizCategory(ArrayList<Question> questions, String categoryName)
    {
        this.categoryName = categoryName;
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions()
    {
        return questions;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
}
