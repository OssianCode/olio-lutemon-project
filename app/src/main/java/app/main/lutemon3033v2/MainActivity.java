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

        home.loadLutemons(context);


    }

    public void playGame(View view){

        //System.out.println("Play Game button");
        Intent intent = new Intent(this, TabMainActivity.class);
        startActivity(intent);

    }


}