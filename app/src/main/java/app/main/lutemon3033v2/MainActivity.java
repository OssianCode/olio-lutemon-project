package app.main.lutemon3033v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Graveyard;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.Lutemons.Lutemon;
public class MainActivity extends AppCompatActivity {

    Context context = MainActivity.this;
    private String frontStats = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtFrontStats = findViewById(R.id.txtFrontInfo);

        //GET TXT DATA

        //HOME
        Home home = Home.getInstance();
        Home.getInstance().loadLutemons(this);

        ArrayList<Lutemon> lutemonsAlive = home.getLutemons();

        //TRAINING ARENA
        TrainingArena trainingArena = TrainingArena.getInstance();
        trainingArena.loadLutemons(this);

        lutemonsAlive.addAll(trainingArena.getLutemons());

        //BATTLE FIELD
        BattleField battleField = BattleField.getInstance();
        battleField.loadLutemons(this);

        lutemonsAlive.addAll(battleField.getLutemons());

        //GRAVEYARD
        Graveyard graveyard = Graveyard.getInstance();
        graveyard.loadLutemons(this);
        ArrayList<Lutemon> lutemonsBuried = graveyard.getLutemons();

        int wins = 0;
        int defeats = 0;
        int xp = 0;
        for (Lutemon lutemon: lutemonsAlive) {
            wins += lutemon.getVictories();
            defeats += lutemon.getLoses();
            xp += lutemon.getExperience();
        }

        //SET TEXT
        frontStats = "Lutemons " + lutemonsAlive.size()  +"\n";
        frontStats += "Victories " + wins  +"\n";
        frontStats += "Defeats " + defeats  +"\n";
        frontStats += "XP " + xp  +"\n";
        frontStats += "Buried " + lutemonsBuried.size() ;
        
        txtFrontStats.setText(frontStats);
        

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


    }

    public void playGame(View view){

        //System.out.println("Play Game button");
        Intent intent = new Intent(this, TabMainActivity.class);
        startActivity(intent);

    }


}