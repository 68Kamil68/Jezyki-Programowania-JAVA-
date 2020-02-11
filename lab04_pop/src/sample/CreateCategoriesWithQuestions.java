package sample;

import java.util.ArrayList;
import java.util.Locale;

public class CreateCategoriesWithQuestions
{
    public static ArrayList<QuizCategory> createQuiz() {
        Question q1 = new Question("Wybierz ssaka", 0,"czlowiek", "losos", "strus", "krewetka");
        Question q2 = new Question("Ktore zwierze jest najwieksze?", 3,"kon", "krowa", "mysz", "wieloryb");
        Question q3 = new Question("Ile glow ma czlowiek?", 0,"jedna", "dwie", "trzy", "duzo");
        Question q4 = new Question("Gdzie znajdziemy sarne?", 0,"w lesie", "w pracy", "w morzu", "w szkole");
        Question q5 = new Question("Do jakich krajow wylatuja bociany na zime?", 1,"zimnych", "cieplych", "odleglych", "nadbaltyckich");
        ArrayList<Question> QuestionsAnimals = new ArrayList<Question>();
        QuestionsAnimals.add(q1);
        QuestionsAnimals.add(q2);
        QuestionsAnimals.add(q3);
        QuestionsAnimals.add(q4);
        QuestionsAnimals.add(q5);
        QuizCategory qc1 = new QuizCategory(QuestionsAnimals, "Zwierzeta");
        Question qq2 = new Question("Ktora marka samochodw pochodzi z Niemiec?", 0,"BMW", "Ferrari", "Tesla", "Toyota");
        Question qq3 = new Question("Ktora marka samochodw pochodzi z Wloch?", 1,"BMW", "Ferrari", "Tesla", "Toyota");
        Question qq4 = new Question("Ktora marka samochodw pochodzi z USA?", 2,"BMW", "Ferrari", "Tesla", "Toyota");
        Question qq5 = new Question("Ktora marka samochodw pochodzi z Japonii?", 3,"BMW", "Ferrari", "Tesla", "Toyota");
        ArrayList<Question> QuestionsCars = new ArrayList<Question>();
        QuestionsCars.add(qq2);
        QuestionsCars.add(qq3);
        QuestionsCars.add(qq4);
        QuestionsCars.add(qq5);
        QuizCategory qc2 = new QuizCategory(QuestionsCars, "Samochody");
        ArrayList<QuizCategory> Quiz = new ArrayList<QuizCategory>();
        Quiz.add(qc1);
        Quiz.add(qc2);
        return Quiz;
    }
}
