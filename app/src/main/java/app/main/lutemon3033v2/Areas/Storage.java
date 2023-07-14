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

    public void addLutemon(Lutemon lutemon){
        lutemons.add(lutemon);
    }

    public Lutemon getLutemon(int id) {

        return lutemons.get(id);
    }

    public void listLutemons(){

    }

    public void saveLutemons(Context context) {

    }

    public void loadLutemons(Context context) {

    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }
}
