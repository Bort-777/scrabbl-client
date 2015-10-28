/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.adress.model;

/**
 *
 * @author Администратор
 */
public class User {

    String name;
    Boolean needWord;
    private boolean waite;
    public boolean isWait;

    public User(String name) {
        this.name = new String(name);
        this.needWord = false;
        this.waite = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean needWord() {
        return needWord;
    }

    public void setNeedWord() {
        this.needWord = true;
    }

    public void deleteAcsess() {
        needWord = false;
    }

    public void setAcsess() {
        needWord = true;
    }

    public boolean needWaite() {
        return waite;
    }

    public void setWait() {
        waite = true;
    }

    public boolean isWait() {
        return waite;
    }

    public void deletWait() {
        waite = false;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return this.name.equals(((User)obj).name); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
}
