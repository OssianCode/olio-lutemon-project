package app.main.lutemon3033v2.Lutemons;

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
    protected int image;
    protected int victories;
    protected int loses;
    //private int idCounter;

    public Lutemon(String name, String color, int attack, int defence, int experience, int health, int maxHealth, int id, int image, int victories, int loses) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = id;
        this.image = image;
        //this.idCounter = id;
        this.victories = victories;
        this.loses = loses;
    }

    public String battleDefence (Lutemon lutemon) {


        String defenceTxt = "";

        int attackPower = lutemon.battleAttack();

        int damage =  attackPower - defence;

        System.out.println("Lutemon " + name + " defends with power " + defence);

        defenceTxt += lutemon.getName() + " attacks " + name + " with attack power " + attackPower + "\n";
        defenceTxt += "Lutemon " + name + " defends with power " + defence + " \n";

        if (damage > 0 ) {

            health = health - damage;

            if (health < 0) {
                health = 0;
            }

            System.out.println("Total damage to Lutemon " + name + " is " + damage + " (HP: " + health + "/" + maxHealth + ")" );

            defenceTxt += "Total damage to Lutemon " + name + " is " + damage + " (HP: " + health + "/" + maxHealth + ")" + "\n";

        }
        else {
            System.out.println("Lutemon " + lutemon.getName() + " missed");

            defenceTxt += "Lutemon " + lutemon.getName() + " missed" + " \n";
        }

        //System.out.println("Lutemon " + name + " health is " + health);

        //defenceTxt += "Lutemon " + name + " health is " + health + " \n";

        return defenceTxt;

    }
    public int battleAttack(){

        int attackPower = 0;

        if ((int) (Math.random() * 10) > 4 ) {

            attackPower = (attack + experience)  + (int) (Math.random() + Math.random() + Math.random() + Math.random());

            System.out.println("Lutemon " + name + " attacks with power V1 " + attackPower);

        }
        else {

            attackPower = (attack + experience)  - (int) (Math.random() + Math.random() );
            System.out.println("Lutemon " + name + " attacks with power V2 " + attackPower);

        }

        return attackPower;
    }

    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefence() {
        return defence;
    }
    public int getExperience() {
        return experience;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getId() {
        return id;
    }
    public int getImage() {
        return image;
    }
    public int getVictories() {
        return victories;
    }
    public int getLoses() {
        return loses;
    }
    public void setName(String name) { this.name = name; }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setVictories(int victories) {
        this.victories = victories;
    }
    public void setLoses(int loses) {
        this.loses = loses;
    }
}
