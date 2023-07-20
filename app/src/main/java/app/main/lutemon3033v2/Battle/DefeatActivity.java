package app.main.lutemon3033v2.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.BattleStorage;
import app.main.lutemon3033v2.Areas.Graveyard;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;

public class DefeatActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Lutemon loser = null;

    private Button btnDefeatHome, btnDefeatBury;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defeat);


        context = DefeatActivity.this;


        btnDefeatHome = (Button) findViewById(R.id.btnDefeatHome);
        btnDefeatHome.setOnClickListener(this);
        btnDefeatBury = (Button) findViewById(R.id.btnDefeatBury);
        btnDefeatBury.setOnClickListener(this);


        loser = BattleStorage.getInstance().getLoser();

        ImageView imgLoser = findViewById(R.id.imgDefeatedLut);
        TextView txtLoserStats = findViewById(R.id.txtLoserStats);

        if (loser != null) {

            imgLoser.setImageResource(loser.getImage());


            String loserStats = loser.getName() + "\n";
            loserStats += "HP: " + loser.getHealth() + " / " + loser.getMaxHealth() + "\n" ;
            loserStats += "Total Defeats: " + loser.getLoses() ;

            txtLoserStats.setText(loserStats);

        }

    }

    public void onClick(View view) {


        if (view.getId() == R.id.btnDefeatHome) {

            if (loser != null) {
                Home.getInstance().addLutemon(loser, this);
            }

        }
        else if (view.getId() == R.id.btnDefeatBury) {

            if (loser != null) {
                Graveyard.getInstance().addLutemon(loser, this);
            }
        }

        //After any button click activate next screen

        Intent intent = new Intent(view.getContext(), VictoryActivity.class);

        startActivity(intent);


    }


}