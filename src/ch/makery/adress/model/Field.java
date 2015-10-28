/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.adress.model;

import java.util.ArrayList;

/**
 *
 * @author Администратор
 */
public class Field {

    ArrayList<Block> blocks;

    public Field() {
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 12 * 12; i++) {
            blocks.add(new Block("*"));
        }
    }

    public void setNewWord(String checkWord, ArrayList data) {
        int x, y;
       
        for (int i = 0; i < checkWord.length(); i++) {
            y = (int) data.get(i*2);
            x = (int) data.get(i*2 + 1);
           
            blocks.set(y * 12  +x, new Block(checkWord.substring(i, i + 1)));
            System.out.println("Добавили букву: "+y+" "+x + " : "+blocks.get(y * 12 + x).getText());
        }
        System.out.println("Mass:"+data.size());
        data.clear();
    }

    public String getBlock(int x, int y) {
        return blocks.get(y * x + x).getText();
    }

    public String toText() {

        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 12 * 12; i++) {

            text.append(blocks.get(i).getText());
        }
System.out.println(text.toString());
        return text.toString();
    }

}
