package app.main.lutemon3033v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;

import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Lutemons.Green;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.Lutemons.Orange;
import app.main.lutemon3033v2.Lutemons.Pink;

public class MainActivity extends AppCompatActivity {

    Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Home home = Home.getInstance();

        home.loadLutemons(context);*/


        //TODO: FOR TEST remove files

        //FOR TEST -->
        /*File dir = getFilesDir();
        File file = new File(dir, "lutemons-battle.data");
        boolean deleted = file.delete();

        System.out.println("Battle file deleted " + deleted);

        file = new File(dir, "lutemons-grave.data");
        deleted = file.delete();

        System.out.println("grave file deleted " + deleted);

        file = new File(dir, "lutemons-home.data");
        deleted = file.delete();

        System.out.println("home file deleted " + deleted);

        file = new File(dir, "lutemons-training.data");
        deleted = file.delete();

        System.out.println("training file deleted " + deleted);
        *///<-- FOR TEST

        //TODO: ADD stats to start page!


    }

    public void playGame(View view){

        //System.out.println("Play Game button");
        Intent intent = new Intent(this, TabMainActivity.class);
        startActivity(intent);

    }


}