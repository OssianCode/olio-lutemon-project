package app.main.lutemon3033;

import java.util.HashMap;

public abstract class Storage {
    public String name;

    public HashMap<Integer, Lutemon> lutemons;

    public Storage(String name) {
        this.name = name;
    }

    public void addLutemon(Lutemon lutemon){
        lutemons.put(lutemon.id, lutemon);
    }

    public Lutemon getLutemon(int id) {

        return lutemons.get(id);
    }

    public void listLutemons(){

    }
}
