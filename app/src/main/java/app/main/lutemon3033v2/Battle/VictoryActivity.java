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

public class VictoryActivity extends AppCompatActivity implements View.OnClickListener{


    private Context context;

    private Lutemon winner = null;


    private Button btnToBattleField, btnToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        context = VictoryActivity.this;

        btnToBattleField = (Button) findViewById(R.id.btnToBattleField);
        btnToBattleField.setOnClickListener(this);
        btnToHome = (Button) findViewById(R.id.btnToHome);
        btnToHome.setOnClickListener(this);


        winner = BattleStorage.getInstance().getWinner();

        ImageView imgWinner = findViewById(R.id.imgWinner);
        TextView txtWinnerStats = findViewById(R.id.txtWinnerStats);


        if (winner != null) {

            imgWinner.setImageResource(winner.getImage());

            String winnerStats = winner.getName() + "\n";
            winnerStats += "HP: " + winner.getHealth() + " / " + winner.getMaxHealth() + "\n" ;
            winnerStats += "+ 1 XP!" + "\n" ;
            winnerStats += "Total XP: " + winner.getExperience()+ "\n" ;
            winnerStats += "Total Victories: " + winner.getVictories() ;

            txtWinnerStats.setText(winnerStats);
        }


    }


    public void onClick(View view) {


        if (view.getId() == R.id.btnToHome) {

            Home.getInstance().addLutemon(winner, this);
        }


        /*if (view.getId() == R.id.btnToBattleField) {

        }*/

        //After any button click activate next screen

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","battle");
        intent.putExtra("tabNbr",3);
        startActivity(intent);


    }


}