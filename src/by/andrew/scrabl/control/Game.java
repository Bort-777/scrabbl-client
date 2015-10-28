/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.andrew.scrabl.control;

import ch.makery.adress.model.Field;
import ch.makery.adress.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Администратор
 */
class Game {

    private List<User> users;
    private String checkWord;
    private Field field;
    private ArrayList data;

    public Game() {
        users = new ArrayList<>();
        checkWord = "defoult";
        field = new Field();
    }

    public String getCheckWord() {
        return checkWord;
    }

    boolean needWordAnswer(String name) {
        int userNum = findUser(name);
        //Требуется ли от пользователя ввод слова
       return users.get(userNum).needWord() ;
        
    }

    void newUser(String name) {
        User user = new User(name);
        users.add(user);
        System.out.println("New user " + name);
        if (users.size() == 1) {
            users.get(0).setNeedWord();
        }
    }

    int findUser(String name) {
        for (int iter = 0; iter < users.size(); iter++) {
            if (users.get(iter).getName().equals(name)) {
                return iter;

            }
        }
        return -1;
    }

    void nextStep(String name) {
        int userNum = findUser(name);
        users.get(userNum).deleteAcsess();

        if (userNum + 1 == users.size()) {
            //Если этот игрок последний
            users.get(0).setAcsess();
        } else {
            users.get(userNum + 1).setAcsess();
        }
    }

    boolean needWaite(String name) {
        int userNum = findUser(name);
        return users.get(userNum).needWaite();
    }

    void setAnswer(String name, String answer, ArrayList data) {
        checkWord = answer;
        this.data = data;
        if (users.size() != 1) {
            //Если игрок не один - ожидать;
            setWait(name);
            System.out.print(this.data.size());
            deletAcsess(name);
        } else {
            //Если игрок один
            nextStep(name);
            field.setNewWord(checkWord, data);
        }

    }

    void setWait(String name) {
        int userNum = findUser(name);
        users.get(userNum).setWait();
    }

    void checkWord(String name, String answer) {
        if (answer.equals("да")) {
            int userNum = findWaitUser();
            users.get(userNum).setAcsess();
            users.get(userNum).deletWait();
            field.setNewWord(checkWord, data);
            checkWord = "defoult";
        } else if (answer.equals("нет")) {
            int userNum = findWaitUser();
            users.get(userNum).setAcsess();
            users.get(userNum).deletWait();
            checkWord = "defoult";
        }
    }

    private int findWaitUser() {
        for (int iter = 0; iter < users.size(); iter++) {
            if (users.get(iter).isWait()) {
                return iter;

            }
        }
        return -1;
    }

    private void deletAcsess(String name) {
        int userNum = findUser(name);
        users.get(userNum).deleteAcsess();
    }

    Field getField() {
        return field;
    }
}
