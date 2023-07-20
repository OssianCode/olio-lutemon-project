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
import app.main.lutemon3033v2.Lutemons.Black;
import app.main.lutemon3033v2.Lutemons.Green;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.Lutemons.Pink;
import app.main.lutemon3033v2.Lutemons.White;
import app.main.lutemon3033v2.MainActivity;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Lutemon lutemonA = null;
    private Lutemon lutemonB = null;

    private ImageView imgLutemonA;
    private ImageView imgLutemonB;
    private TextView txtBattleText;

    private Button btnActivity;


    private int idA = -1;
    private int idB = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);


        context = BattleActivity.this;


        btnActivity = (Button) findViewById(R.id.btnFight);
        btnActivity.setOnClickListener(this);


        imgLutemonA = findViewById(R.id.imgLutemonA);
        imgLutemonB = findViewById(R.id.imgLutemonB);
        txtBattleText = findViewById(R.id.txtBattleText);


        // Find selected lutemons

        Bundle selectedLutemons = getIntent().getExtras();
        if (selectedLutemons != null) {
            idA = selectedLutemons.getInt("idA");
            idB = selectedLutemons.getInt("idB");

            System.out.println("BATTLE lutemon idA: " + idA + " idB: " + idB);

        }

        if (idA >= 0 && idB >= 0 && idA != idB){

            //System.out.println("BATTLE find lutemons next");

            lutemonA = BattleField.getInstance().getLutemon(idA, this);
            lutemonB = BattleField.getInstance().getLutemon(idB, this);

            TextView txtLutAStats = findViewById(R.id.txtLutAStats);
            TextView txtLutBStats = findViewById(R.id.txtLutBStats);


            if (lutemonA != null) {

                imgLutemonA.setImageResource(lutemonA.getImage());

                String lutAStats = lutemonA.getName() + "\n";
                lutAStats += "HP: " + lutemonA.getHealth() + " / " + lutemonA.getMaxHealth() + "\n" ;
                lutAStats += "Attack: " + lutemonA.getAttack() + "\n" ;
                lutAStats += "XP: " + lutemonA.getExperience() + "\n" ;
                lutAStats += "Defence: " + lutemonA.getDefence() + "\n" ;
                lutAStats += "Victories: " + lutemonA.getVictories() + "\n" ;
                lutAStats += "Defeats: " + lutemonA.getLoses() ;

                txtLutAStats.setText(lutAStats);
            }
            if (lutemonB != null) {

                imgLutemonB.setImageResource(lutemonB.getImage());

                String lutBStats = lutemonB.getName() + "\n";
                lutBStats += "HP: " + lutemonB.getHealth() + " / " + lutemonB.getMaxHealth() + "\n";
                lutBStats += "Attack: " + lutemonB.getAttack() + "\n";
                lutBStats += "XP: " + lutemonB.getExperience() + "\n";
                lutBStats += "Defence: " + lutemonB.getDefence() + "\n" ;
                lutBStats += "Victories: " + lutemonB.getVictories() + "\n";
                lutBStats += "Defeats: " + lutemonB.getLoses()  ;

                txtLutBStats.setText(lutBStats);
            }


            imgLutemonB.setImageResource(lutemonB.getImage());

        }


    }

    @Override
    public void onClick(View view) {

        if (btnActivity.getText().toString().equals("Go!")) {

            lutemonBattle(view);
        }
        else if (btnActivity.getText().toString().equals("Next")) {

            Intent intent = new Intent(view.getContext(), DefeatActivity.class);

            startActivity(intent);
        }


    }




    public void lutemonBattle(View view) {

        if (lutemonA == null || lutemonB == null) {
            System.out.println("No lutemons found, lutemons null" );
            returnToBattle(view);
            return;
        }


        System.out.println("FIGHT!" );

        //TODO: Animate pics?
        //TODO: Get selected lutemons from battlefield

        String battleText = "";
        int i = 0;
        boolean fighting = true;
        while (fighting) {

            i++;
            battleText += "ROUND " + i + "! ";

            if (lutemonA.getHealth() > 0) {
                System.out.println("FIGHT Lutemon A attacks B ");

                //battleText += lutemonA.getName() + " attacks " + lutemonB.getName()+ " \n";



                battleText += lutemonB.battleDefence(lutemonA);

                txtBattleText.setText(battleText);

            }
            else {
                // B WON A LOST
                System.out.println("Lutemon A is dead ");

                battleText += "Lutemon " + lutemonA.getName() + " is dead \n";
                txtBattleText.setText(battleText);

                //moved this to Defeat action and victory action
                //Graveyard.getInstance().addLutemon(lutemonA, this);

                System.out.println("Lutemon B WON!");
                battleText += "Lutemon " + lutemonB.getName() + " WON! \n";
                txtBattleText.setText(battleText);


                //Update stats
                lutemonA.setHealth(0);
                lutemonA.setLoses(lutemonA.getLoses() + 1);
                lutemonB.setVictories(lutemonB.getVictories() + 1);
                lutemonB.setExperience(lutemonB.getExperience() + 1);
                BattleStorage.getInstance().setWinner(lutemonB);
                BattleStorage.getInstance().setLoser(lutemonA);


                fighting = false;
                break;
            }

            if (lutemonB.getHealth() > 0) {

                System.out.println("Lutemon B attacks A ");
                //battleText += lutemonB.getName() + " attacks " + lutemonA.getName()+ " \n";


                battleText += lutemonA.battleDefence(lutemonB);

                txtBattleText.setText(battleText);

            }
            else {

                // A WON B LOST

                System.out.println("Lutemon B is dead ");
                battleText += "Lutemon " + lutemonB.getName() + " is dead \n";
                txtBattleText.setText(battleText);

                System.out.println("Lutemon A WON! ");
                battleText += "Lutemon " + lutemonA.getName() + " WON! \n";
                txtBattleText.setText(battleText);

                //Update stats
                lutemonB.setHealth(0);
                lutemonB.setLoses(lutemonB.getLoses() + 1);
                lutemonA.setVictories(lutemonA.getVictories() + 1);
                lutemonA.setExperience(lutemonA.getExperience() + 1);
                BattleStorage.getInstance().setWinner(lutemonA);
                BattleStorage.getInstance().setLoser(lutemonB);


                fighting = false;
                break;
            }

        }

        btnActivity.setText("Next");

    }

    private void returnToBattle(View view) {

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","battle");
        intent.putExtra("tabNbr",3);
        startActivity(intent);

    }


}