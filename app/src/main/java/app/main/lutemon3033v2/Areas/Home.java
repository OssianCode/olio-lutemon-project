package app.main.lutemon3033v2.Areas;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.main.lutemon3033v2.Lutemons.Lutemon;

public class Home extends Storage {

    private static Home home = null;

    private Home (){
        super("Home");

    }

    public static Home getInstance(){
        if(home == null){
            home = new Home();
        }
        return home;
    }

    public void createLutemon(Lutemon lutemon){


        addLutemon(lutemon);
    }

    public void saveLutemons(@NonNull Context context){

        System.out.println("Home - save lutemons ");
        try {
            //System.out.println("save users try");
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons-home.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("ERROR Home- save lutemons - file not found " + e);
        }catch (IOException e) {
            System.out.println("ERROR Home- save lutemons " + e);
            //throw new RuntimeException(e);
        }

    }

    public void loadLutemons(@NonNull Context context) {


        //System.out.println("load users");
        try {
            //System.out.println("load users try");
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons-home.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();

            //System.out.println("load users ok");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR Home- catch error 1" + e);
            //throw new RuntimeException(e);

        } catch (IOException e) {
            System.out.println("ERROR Home- catch error 2" + e);
            //throw new RuntimeException(e);
        }

    }

}
