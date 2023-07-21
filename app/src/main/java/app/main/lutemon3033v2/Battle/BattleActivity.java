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

    private ImageView imgLutemonA, imgLutemonB;
    private TextView txtBattleText, txtLutAStats, txtLutBStats;
    private Button btnActivity;

    private int round = 0;

   // private int idA = -1;
   // private int idB = -1;




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

        lutemonA = BattleStorage.getInstance().getLutemonA();
        lutemonB = BattleStorage.getInstance().getLutemonB();

        txtLutAStats = findViewById(R.id.txtLutAStats);
        txtLutBStats = findViewById(R.id.txtLutBStats);

        if (lutemonA != null) {

            if (lutemonA instanceof Black) {
                imgLutemonA.setImageResource(R.drawable.blacklargeclearflipped);
            }
            else {

                imgLutemonA.setImageResource(lutemonA.getImage());
            }

            updateStatsA();

            /*
            String lutAStats = lutemonA.getName() + "\n";
            lutAStats += "HP: " + lutemonA.getHealth() + " / " + lutemonA.getMaxHealth() + "\n" ;
            lutAStats += "Attack: " + lutemonA.getAttack() + "\n" ;
            lutAStats += "XP: " + lutemonA.getExperience() + "\n" ;
            lutAStats += "Defence: " + lutemonA.getDefence() + "\n" ;
            lutAStats += "Victories: " + lutemonA.getVictories() + "\n" ;
            lutAStats += "Defeats: " + lutemonA.getLoses() ;

            txtLutAStats.setText(lutAStats);

             */
        }
        if (lutemonB != null) {

            imgLutemonB.setImageResource(lutemonB.getImage());

            updateStatsB();

            /*String lutBStats = lutemonB.getName() + "\n";
            lutBStats += "HP: " + lutemonB.getHealth() + " / " + lutemonB.getMaxHealth() + "\n";
            lutBStats += "Attack: " + lutemonB.getAttack() + "\n";
            lutBStats += "XP: " + lutemonB.getExperience() + "\n";
            lutBStats += "Defence: " + lutemonB.getDefence() + "\n" ;
            lutBStats += "Victories: " + lutemonB.getVictories() + "\n";
            lutBStats += "Defeats: " + lutemonB.getLoses()  ;

            txtLutBStats.setText(lutBStats);*/
        }


        imgLutemonB.setImageResource(lutemonB.getImage());

    }

    private void updateStatsB() {

        String lutBStats = lutemonB.getName() + "\n";
        lutBStats += "HP: " + lutemonB.getHealth() + " / " + lutemonB.getMaxHealth() + "\n";
        lutBStats += "Attack: " + lutemonB.getAttack() + "\n";
        lutBStats += "XP: " + lutemonB.getExperience() + "\n";
        lutBStats += "Defence: " + lutemonB.getDefence() + "\n" ;
        lutBStats += "Victories: " + lutemonB.getVictories() + "\n";
        lutBStats += "Defeats: " + lutemonB.getLoses()  ;

        txtLutBStats.setText(lutBStats);
    }

    private void updateStatsA() {

        String lutAStats = lutemonA.getName() + "\n";
        lutAStats += "HP: " + lutemonA.getHealth() + " / " + lutemonA.getMaxHealth() + "\n" ;
        lutAStats += "Attack: " + lutemonA.getAttack() + "\n" ;
        lutAStats += "XP: " + lutemonA.getExperience() + "\n" ;
        lutAStats += "Defence: " + lutemonA.getDefence() + "\n" ;
        lutAStats += "Victories: " + lutemonA.getVictories() + "\n" ;
        lutAStats += "Defeats: " + lutemonA.getLoses() ;

        txtLutAStats.setText(lutAStats);

    }


    @Override
    public void onClick(View view) {

        if (btnActivity.getText().toString().equals("Go!") || btnActivity.getText().toString().equals("Next round")) {

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

        String battleText = "";

        boolean fighting = true;
        while (fighting) {

            round++;
            battleText = "ROUND " + round + "! " + " \n" + " \n"; //TEST: remove plus, just is // Make one round at a time

            if (lutemonA.getHealth() > 0) {
                System.out.println("FIGHT Lutemon A attacks B ");


                battleText += lutemonB.battleDefence(lutemonA);

                txtBattleText.setText(battleText);

            }
            else {
                //This maybe never happens?
                // Leave it here for now to be sure no dead lutemons fight
                // B WON A LOST
                System.out.println("Lutemon A is dead ");

                battleText += "Lutemon " + lutemonA.getName() + " is dead \n";
                txtBattleText.setText(battleText);

                System.out.println("Lutemon B WON!");
                battleText += "Lutemon " + lutemonB.getName() + " WON! \n";
                txtBattleText.setText(battleText);


                //Update stats
                //lutemonA.setHealth(0);

                //Update visible stats before loses, victories and xp
                updateStatsA();
                updateStatsB();

                lutemonA.setLoses(lutemonA.getLoses() + 1);
                lutemonB.setVictories(lutemonB.getVictories() + 1);
                lutemonB.setExperience(lutemonB.getExperience() + 1);


                BattleStorage.getInstance().setWinner(lutemonB);
                BattleStorage.getInstance().setLoser(lutemonA);
                BattleStorage.getInstance().setLutemonA(null);
                BattleStorage.getInstance().setLutemonB(null);

                btnActivity.setText("Next");
                fighting = false;
                break;
            }

            if (lutemonB.getHealth() > 0) {

                System.out.println("Lutemon B attacks A ");
                //battleText += lutemonB.getName() + " attacks " + lutemonA.getName()+ " \n";


                battleText += lutemonA.battleDefence(lutemonB);

                txtBattleText.setText(battleText);

                //IF A DIED update stats and leave battle
                if (lutemonA.getHealth() <= 0) {

                    System.out.println("Lutemon A is dead ");

                    battleText += "Lutemon " + lutemonA.getName() + " is dead \n";
                    txtBattleText.setText(battleText);

                    System.out.println("Lutemon B WON!");
                    battleText += "Lutemon " + lutemonB.getName() + " WON! \n";
                    txtBattleText.setText(battleText);

                    //Update stats


                    //Update visible stats before loses, victories and xp
                    updateStatsA();
                    updateStatsB();

                    lutemonA.setLoses(lutemonA.getLoses() + 1);
                    lutemonB.setVictories(lutemonB.getVictories() + 1);
                    lutemonB.setExperience(lutemonB.getExperience() + 1);

                    BattleStorage.getInstance().setWinner(lutemonB);
                    BattleStorage.getInstance().setLoser(lutemonA);
                    BattleStorage.getInstance().setLutemonA(null);
                    BattleStorage.getInstance().setLutemonB(null);

                    btnActivity.setText("Next");
                    fighting = false;
                    break;
                }

                updateStatsA();
                updateStatsB();

                btnActivity.setText("Next round");
                break; //TEST, go to next round


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
                //lutemonB.setHealth(0);

                //Update visible stats before loses, victories and xp
                updateStatsA();
                updateStatsB();

                lutemonB.setLoses(lutemonB.getLoses() + 1);
                lutemonA.setVictories(lutemonA.getVictories() + 1);
                lutemonA.setExperience(lutemonA.getExperience() + 1);

                BattleStorage.getInstance().setWinner(lutemonA);
                BattleStorage.getInstance().setLoser(lutemonB);
                BattleStorage.getInstance().setLutemonA(null);
                BattleStorage.getInstance().setLutemonB(null);


                fighting = false;

                btnActivity.setText("Next");
                break;
            }

        }


        //btnActivity.setText("Next");

    }

    private void returnToBattle(View view) {

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","battle");
        intent.putExtra("tabNbr",3);
        startActivity(intent);

    }


}