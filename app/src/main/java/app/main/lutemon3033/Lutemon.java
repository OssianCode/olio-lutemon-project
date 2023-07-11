package app.main.lutemon3033;

import java.io.Serializable;

public class Lutemon implements Serializable {
    protected String name;
    protected String color;
    protected int attack;
    protected int defence;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;
    private int idCounter;

    public Lutemon(String name, String color, int attack, int defence, int experience, int health, int maxHealth, int id) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = id;
        this.idCounter = id;
    }

    public void defence (Lutemon lutemon) {

    }
    public int attack(){
        return 0;
    }
    public int getNumberOfCreatedLutemons(){
        return 0;
    }


}
