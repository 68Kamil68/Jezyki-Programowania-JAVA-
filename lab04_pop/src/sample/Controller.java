package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Controller {

    public ComboBox<String> CategoryField;
    public TextArea QuestionArea;
    public Button Answr1;
    public Button Answr3;
    public Button Answr2;
    public Button Answr4;
    public ComboBox<String> UserField;
    public Button ShowStatsButton;
    ArrayList<QuizCategory> Quiz = CreateCategoriesWithQuestions.createQuiz();
    ArrayList<User> Users = CreateUsers.createUsers();
    private Question currentQuestion;
    private int nextQuestionIndex;
    private int currentCategoryIndex;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private User currentUser = null;

    public void quizSetup(ActionEvent event)
    {
        String category = CategoryField.getValue();
        String username = UserField.getValue();
        for (QuizCategory quizCategory:Quiz)
        {
            if (quizCategory.getCategoryName() == category)
            {
                currentCategoryIndex = Quiz.indexOf(quizCategory);
            }
        }
        for (User user : Users)
        {
            if (user.getUsername() == username)
            {
                currentUser = user;
            }
        }
        setQuestion(Quiz.get(currentCategoryIndex).getQuestions().get(0));
    }

    public void setQuestion(Question question)
    {
        currentQuestion = question;
        List<Button> buttons = new ArrayList<Button>();
        buttons.add(Answr1);
        buttons.add(Answr2);
        buttons.add(Answr3);
        buttons.add(Answr4);
        QuestionArea.setText(question.getQuestionText());
        for (int i = 0; i < 4; i++)
        {
            buttons.get(i).setText(question.getAnswers().get(i));
        }
        nextQuestionIndex = Quiz.get(currentCategoryIndex).getQuestions().indexOf(currentQuestion)+1;
    }

    public void checkAnswer(ActionEvent event)
    {
        Button clicked = (Button) event.getSource();
        if(Quiz.get(currentCategoryIndex).getQuestions().indexOf(currentQuestion) <
                Quiz.get(currentCategoryIndex).getQuestions().size()-1)
        {
            if (clicked.getText() == currentQuestion.getCorrectAnswer()) {
                currentUser.incrementCorrectAnswers();
                correctAnswers++;
                System.out.println("git");
                nextQuestion();
            } else {
                currentUser.incrementIncorrectAnswers();
                incorrectAnswers++;
                System.out.println("nie git");
                nextQuestion();
            }
        }
        else
        {
            if (clicked.getText() == currentQuestion.getCorrectAnswer())
            {
                correctAnswers++;
                currentUser.incrementCorrectAnswers();
                QuestionArea.setText(getQuizSummary());
                correctAnswers = 0;
                System.out.println("git i koniec");
            }
            else {
                currentUser.incrementIncorrectAnswers();
                incorrectAnswers++;
                QuestionArea.setText(getQuizSummary());
                incorrectAnswers = 0;
                System.out.println("nie git i koniec");
            }
        }
    }

    private void nextQuestion()
    {
        setQuestion(Quiz.get(currentCategoryIndex).getQuestions().get(nextQuestionIndex));
    }

    public void initialize()
    {
        for (User user: Users)
        {
            UserField.getItems().add(user.getUsername());
        }
        for (QuizCategory qc: Quiz)
        {
            CategoryField.getItems().add(qc.getCategoryName());
        }
        QuestionArea.setText("Wybierz uzytkownika, kategorie i nacisnij przycisk 'rozpocznij test'");
    }

    public String getQuizSummary()
    {
        return "Wszystkich odpowiedzi: " + (correctAnswers+incorrectAnswers) + "Poprawnych odpowiedzi: " +
                correctAnswers + "Blednych odpowiedzi: " + incorrectAnswers + "Bilans poprawnych odp: " +
                String.format("%.2g%n", (double)correctAnswers/(correctAnswers+incorrectAnswers)*100)+ "%";
    }

    public void showUserStats(ActionEvent event)
    {
        for (User user : Users)
            {
                if (user.getUsername() == UserField.getValue())
                {
                    currentUser = user;
                }
            }
        QuestionArea.setText(currentUser.getStats());
    }

}
