package by.andrew.scrabl.control;

import java.util.List;

import by.andrew.scrabl.control.GameControl;
import ch.makery.adress.model.Field;
import java.util.ArrayList;

public class ServDProv implements DataProv {

    private final GameControl gameControl;

    public  ServDProv(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public synchronized void newUser(String name) {
        gameControl.newUser(name);
    }

    @Override
    public synchronized Field getField(String name) {
        return gameControl.getField(name);
    }

    @Override
    public synchronized String getAsk(String name) {
        return gameControl.getAsk(name);
    }

    @Override
    public synchronized void setAnswer(String name, String answer, ArrayList data) {
        System.out.println("Mass2:"+data.size());
        gameControl.setAnswer(name, answer, data);
    }

}
