package app.main.lutemon3033v2.Areas;

import android.content.Context;

import java.util.ArrayList;

import app.main.lutemon3033v2.Lutemons.Lutemon;


public abstract class Storage {
    public String name;
    public ArrayList<Lutemon> lutemons = new ArrayList<>();

    public Storage(String name) {
        this.name = name;
    }

    public void addLutemon(Lutemon lutemon, Context context){

        lutemons.add(lutemon);

        saveLutemons(context);

    }

    // Returns wanted Lutemon, removes from area
    // Used when moving a Lutemon from an area to another
    public Lutemon getLutemon(int id, Context context) {

        Lutemon lutemonToMove  = lutemons.remove(id);

        saveLutemons(context);

        return lutemonToMove;

    }

    public void listLutemons(){

        System.out.println("STORAGE LIST LUTEMONS Storage name: " + name);
        int i = 0;
        for(Lutemon lutemon : lutemons){


            System.out.println(name + " " + i + ": " + lutemon.getName());

            i++;

        }



    }

    public void saveLutemons(Context context) {

        System.out.println("WRONG - NOTHING Storage save lutemons  " );
    }

    public void loadLutemons(Context context) {
        System.out.println("WRONG - NOTHING Storage load lutemons  " );
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public boolean deleteLutemon(int id, Context context) {

        boolean ok = false;


        int i = 0;
        for (Lutemon lutemon: lutemons) {
            if (lutemon.getId() == id) {
                System.out.println("DELETE lutemon found in graveyard name: " + lutemon.getName());
                ok = true;
                break;
            }
            i++;
        }

        if (ok) {
            lutemons.remove(i);
        }

        //FOR TEST --->
        i = 0;


        for (Lutemon lutemon: lutemons) {
            if (lutemon.getId() == id) {
                System.out.println("LUTEMON STILL FOUND IN Grveyard");
                ok = false;
                break;
            }
            i++;
        }//<--- FOR TEST

        //If delete ok, then ok = true

        saveLutemons(context);

        return ok;
    }
}
