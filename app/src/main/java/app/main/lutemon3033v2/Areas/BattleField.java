package app.main.lutemon3033v2.Areas;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.main.lutemon3033v2.Lutemons.Lutemon;

public class BattleField extends Storage {
    private static BattleField battleField = null;

    private BattleField (){
        super("Battle Field");

    }

    public static BattleField getInstance(){
        if(battleField == null){
            battleField = new BattleField();
        }
        return battleField;
    }

    public void fight(int id1, int id2){

    }

    public void saveLutemons(@NonNull Context context){

        System.out.println("Battle field - save lutemons ");
        try {
            //System.out.println("save lutemons try");
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons-battle.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("ERROR Battle field- save lutemons - file not found " + e);
        }catch (IOException e) {
            System.out.println("ERROR Battle field- save lutemons " + e);
            //throw new RuntimeException(e);
        }

    }

    public void loadLutemons(@NonNull Context context) {


        //System.out.println("load lutemons");
        try {
            //System.out.println("load lutemons try");
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons-battle.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();

            //System.out.println("load lutemons ok");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR Battle field catch error 1" + e);
            //throw new RuntimeException(e);

        } catch (IOException e) {
            System.out.println("ERROR Battle field - catch error 2" + e);
            //throw new RuntimeException(e);
        }

    }


}

