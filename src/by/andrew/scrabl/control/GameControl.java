package by.andrew.scrabl.control;

import ch.makery.adress.model.Field;
import java.util.List;

import java.util.ArrayList;

public class GameControl {

    private final Game game;

    public GameControl() {
        game = new Game();
        ;
    }

    public void newUser(String name) {
        game.newUser(name);
    }

    public Field getField(String name) {
        return game.getField();
    }

    public String getAsk(String name) {
        if (game.needWordAnswer(name)) {
            //Если ему требуется ввести слово
            return NEED_WORD;
        } else {
            //Если от него требуется ожидание/потверждение
            if (game.needWaite(name)) {
                //Ожидание
                return WAIT;
            } else {
                //Потверждение
                return ASK + game.getCheckWord();
            }
        }
    }
    private static final String ASK = "Правильное слово? : ";
    private static final String WAIT = "Ждите";
    private static final String NEED_WORD = "Введите слово!";

    public void setAnswer(String name, String answer, ArrayList data) {

        if (game.needWordAnswer(name)) {
            //Если ожидает новое слово
            if (answer.equals(NEXT)) {
                //Если игрок пропцскает ход
                game.nextStep(name);
            } else {
                //Если игрок говорит новое слово
                game.setAnswer(name, answer, data);
            }
        } else {
            //Если ожидается потверждение слова
            game.checkWord(name, answer);
        }

    }
    private static final String NEXT = "Далее";

}
