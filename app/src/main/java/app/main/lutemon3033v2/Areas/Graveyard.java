package app.main.lutemon3033v2.Areas;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.main.lutemon3033v2.Lutemons.Lutemon;

public class Graveyard extends Storage {
        private static Graveyard graveyard = null;

        private Graveyard (){
            super("Graveyard");

        }

        public static Graveyard getInstance(){
            if(graveyard == null){
                graveyard = new Graveyard();
            }
            return graveyard;
        }


    public void saveLutemons(@NonNull Context context){

        System.out.println("Graveyard - save lutemons ");
        try {
            System.out.println("save lutemons try");
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons-grave.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("ERROR Graveyard- save lutemons - file not found " + e);
        }catch (IOException e) {
            System.out.println("ERROR Graveyard- save lutemons " + e);
            //throw new RuntimeException(e);
        }

    }

    public void loadLutemons(@NonNull Context context) {


            //TODO: Delete graveyard file action
        System.out.println("load lutemons");
        try {
            System.out.println("load lutemons try");
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons-grave.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();

            //System.out.println("load lutemons ok");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR Graveyard catch error 1 " + e);
            //throw new RuntimeException(e);

        } catch (IOException e) {
            System.out.println("ERROR Graveyard - catch error 2 " + e);
            //throw new RuntimeException(e);
        }

    }


}
