package app.main.lutemon3033v2.Areas;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.main.lutemon3033v2.Lutemons.Lutemon;

public class TrainingArena extends Storage {
    private static TrainingArena trainingArena = null;

    private TrainingArena(){
        super("Training arena");

    }

    public static TrainingArena getInstance(){
        if(trainingArena == null){
            trainingArena = new TrainingArena();
        }
        return trainingArena;
    }

    public void train(int id){

        //System.out.println("TRAINING ARENA storage xp: " + lutemons.get(id).getExperience());

        lutemons.get(id).setExperience(lutemons.get(id).getExperience() + 1);

        //System.out.println("TRAINING ARENA storage xp: " + lutemons.get(id).getExperience());

    }

    public void saveLutemons(@NonNull Context context){

        //System.out.println("Training - save lutemons ");
        try {
            //System.out.println("save lutemons try");
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons-training.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();
            //System.out.println("save lutemons ok filesdir " + context.getFilesDir().toString());


        } catch (FileNotFoundException e) {
            System.out.println("ERROR Training- save lutemons - file not found " + e);
        }catch (IOException e) {
            System.out.println("ERROR Training- save lutemons " + e);
            //throw new RuntimeException(e);
        }

    }

    public void loadLutemons(@NonNull Context context) {


        //System.out.println("TRAINING load lutemons FROM FILE");
        try {
            //System.out.println("TRAINING load lutemons try");
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons-training.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();

            //System.out.println("load lutemons ok filesdir " + context.getFilesDir().toString());
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR Training catch error 1 " + e);
            //throw new RuntimeException(e);

        } catch (IOException e) {
            System.out.println("ERROR Training- catch error 2 " + e);
            //throw new RuntimeException(e);
        }

    }


}
