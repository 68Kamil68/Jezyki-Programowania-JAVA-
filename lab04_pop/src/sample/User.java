package sample;

public class User
{
    private String username;
    private int correctAnswers;
    private int incorrectAnswers;

    public User(String username)
    {
        this.username = username;
    }

    public void incrementCorrectAnswers()
    {
        correctAnswers++;
    }

    public void incrementIncorrectAnswers()
    {
        incorrectAnswers++;
    }

    public String getStats()
    {
        return "Wszystkich odpowiedzi: " + (correctAnswers+incorrectAnswers) + " Poprawnych odpowiedzi: " +
                correctAnswers + " Blednych odpowiedzi: " + incorrectAnswers + " Bilans poprawnych odp: " +
                String.format("%.2g%n", (double)correctAnswers/(correctAnswers+incorrectAnswers)*100)+ "%";
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getUsername() {
        return username;
    }
}
