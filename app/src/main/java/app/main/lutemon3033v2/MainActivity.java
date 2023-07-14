package app.main.lutemon3033v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


        Home home = Home.getInstance();
        //TODO: load training and battle saved lutemons
        //TODO: save training and battle lutemons

        //TrainingArena trainingArena = TrainingArena.getInstance();
        //BattleField battleField = BattleField.getInstance();
        //Graveyard graveyard = Graveyard.getInstance();

        //For testing create Lutemons
       /*Lutemon lutemonPink = new Pink("PinkyPastel", 1);

        System.out.println("Pinkki luotu " +  lutemonPink.getName());

        Lutemon lutemonGreen = new Green("Greenie", 2);

        Lutemon lutemonOrange = new Orange("Orangejuice", 3);

        home.createLutemon(lutemonPink);
        home.createLutemon(lutemonGreen);
        home.createLutemon(lutemonOrange);

        System.out.println("Pinkki lisätty Homeen");

        home.saveLutemons(context);

        System.out.println("Save lutemons ohi");*/

        home.loadLutemons(context);

        System.out.println("Load lutemons ohi");

        //Button btnPlayGame = findViewById(R.id.btnPlay);



    }

    public void playGame(View view){

        System.out.println("Play Game button");
        Intent intent = new Intent(this, TabMainActivity.class);
        startActivity(intent);

    }
}