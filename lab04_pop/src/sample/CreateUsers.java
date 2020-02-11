package sample;

import java.util.ArrayList;
import java.util.List;

public class CreateUsers
{
    static ArrayList<User> createUsers()
    {
        User kamilek = new User("Kamilek");
        kamilek.setIncorrectAnswers(10);
        kamilek.setCorrectAnswers(50);
        User profesor = new User("Profesor");
        profesor.setIncorrectAnswers(1);
        profesor.setCorrectAnswers(150);
        User grzes = new User("Grzesio");
        grzes.setIncorrectAnswers(100);
        grzes.setCorrectAnswers(50);
        ArrayList<User> users = new ArrayList<>();
        users.add(kamilek);
        users.add(profesor);
        users.add(grzes);
        return users;
    }
}