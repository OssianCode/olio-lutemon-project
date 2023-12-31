package app.main.lutemon3033v2.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import app.main.lutemon3033v2.Areas.BattleStorage;
import app.main.lutemon3033v2.Lutemons.Black;
import app.main.lutemon3033v2.Lutemons.Green;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.Lutemons.Orange;
import app.main.lutemon3033v2.Lutemons.Pink;
import app.main.lutemon3033v2.Lutemons.White;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Lutemon lutemonA = null;
    private Lutemon lutemonB = null;

    private ImageView imgLutemonA, imgLutemonB;
    private TextView txtRound, txtAtoB, txtBtoA, txtLutAStats, txtLutBStats;
    private Button btnActivity;

    private int round = 0;
    private int lutemonPic = 0;

    private String nextInTurn = "A"; //Start value is A



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        context = BattleActivity.this;

        btnActivity = (Button) findViewById(R.id.btnFight);
        btnActivity.setOnClickListener(this);

        imgLutemonA = findViewById(R.id.imgLutemonA);
        imgLutemonB = findViewById(R.id.imgLutemonB);

        txtRound = findViewById(R.id.txtRound);
        txtAtoB = findViewById(R.id.txtAtoB);
        txtBtoA = findViewById(R.id.txtBtoA);


        lutemonA = BattleStorage.getInstance().getLutemonA();
        lutemonB = BattleStorage.getInstance().getLutemonB();

        txtLutAStats = findViewById(R.id.txtLutAStats);
        txtLutBStats = findViewById(R.id.txtLutBStats);

        txtAtoB.setText("");
        txtBtoA.setText("");
        txtRound.setText("");

        if (lutemonA != null) {

            lutemonPic = getPic(lutemonA, "flipped");

            imgLutemonA.setImageResource(lutemonPic);

            updateStatsA();

        }
        if (lutemonB != null) {

            imgLutemonB.setImageResource(lutemonB.getImage());

            updateStatsB();

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

        if (btnActivity.getText().toString().equals("Go!") || btnActivity.getText().toString().equals("Next turn")) {

            //lutemonBattle(view);

            if (nextInTurn.equals("A")) {
                battleNextInTurnA(view);
            }
            else if (nextInTurn.equals("B")) {
                battleNextInTurnB(view);
            }
            else {
                System.out.println("ERROR in Battle, who's turn it is?" );
            }

        }
        else if (btnActivity.getText().toString().equals("END OF BATTLE")) {

            endOfBattle(view);
        }
        else if (btnActivity.getText().toString().equals("Next")) {

            Intent intent = new Intent(view.getContext(), DefeatActivity.class);

            startActivity(intent);
        }

    }



    private void battleNextInTurnA(View view) {

        if (lutemonA == null || lutemonB == null) {
            System.out.println("No lutemons found, lutemons null" );
            returnToBattle(view);
            return;
        }

        System.out.println("FIGHT! A's turn!" );

        String battleText = "";

        //Next in turn A

        round++;
        txtRound.setText("ROUND " + round + "!"); //TEST: remove plus, just is // Make one round at a time

        if (lutemonA.getHealth() > 0) {
            System.out.println("FIGHT Lutemon A attacks B ");

            lutemonPic = getPic(lutemonA, "fightFlipped");
            imgLutemonA.setImageResource(lutemonPic);

            lutemonPic = getPic(lutemonB, "");
            imgLutemonB.setImageResource(lutemonPic);

            //Attack
            battleText += lutemonB.battleDefence(lutemonA);

            //Update stats
            updateStatsA();
            updateStatsB();

            //Next in turn B
            nextInTurn = "B";

            txtAtoB.setText(battleText);
            txtBtoA.setText("");

            //IF LUTEMON B DEAD!!! DO NOT CONTINUE FIGHT!!
            if (lutemonB.getHealth() <= 0) {
                btnActivity.setText("END OF BATTLE");
                //endOfBattle(view);
                return;
            }

            btnActivity.setText("Next turn");

        }
        else {

            endOfBattle(view);

        }


    }

    private void battleNextInTurnB(View view) {

        //Next in turn B ***********************
        if (lutemonA == null || lutemonB == null) {
            System.out.println("No lutemons found, lutemons null" );
            returnToBattle(view);
            return;
        }

        System.out.println("FIGHT! B's turn!" );

        String battleText = "";


        if (lutemonB.getHealth() > 0) {

            System.out.println("Lutemon B attacks A ");
            //battleText += lutemonB.getName() + " attacks " + lutemonA.getName()+ " \n";

            lutemonPic = getPic(lutemonB, "fight");
            imgLutemonB.setImageResource(lutemonPic);
            lutemonPic = getPic(lutemonA, "flipped");
            imgLutemonA.setImageResource(lutemonPic);

            //Attack
            battleText += lutemonA.battleDefence(lutemonB);

            //Update stats
            updateStatsA();
            updateStatsB();

            //Next in turn A
            nextInTurn = "A";

            txtAtoB.setText("");
            txtBtoA.setText(battleText);



            //IF LUTEMON A DEAD!!! DO NOT CONTINUE FIGHT!!
            if (lutemonA.getHealth() <= 0) {
                btnActivity.setText("END OF BATTLE");
                //endOfBattle(view);
                return;
            }

            //Next in turn A
            btnActivity.setText("Next turn");

        }
        else {

            endOfBattle(view);
            // A WON B LOST

        }

    }

    private void endOfBattle(View view) {

        String battleText = "";

        //IF A DIED update stats and leave battle
        if (lutemonA.getHealth() <= 0) {

            System.out.println("Lutemon A is dead ");

            lutemonPic = getPic(lutemonA, "dead");
            imgLutemonA.setImageResource(lutemonPic);
            lutemonPic = getPic(lutemonB, "winner");
            imgLutemonB.setImageResource(lutemonPic);

            battleText += "\n \n Lutemon " + lutemonA.getName() + " is dead \n";
            //txtBattleText.setText(battleText);

            System.out.println("Lutemon B WON!");
            battleText += "Lutemon " + lutemonB.getName() + " WON! \n";
            //txtBattleText.setText(battleText);

            txtRound.setText(battleText);

            txtAtoB.setText("");
            txtBtoA.setText("");

            //Update stats

            //Update visible stats before loses, victories and xp
            updateStatsA();
            updateStatsB();

            lutemonA.setLoses(lutemonA.getLoses() + 1);
            lutemonB.setVictories(lutemonB.getVictories() + 1);
            lutemonB.setExperience(lutemonB.getExperience() + 1);
            //NEW:
            lutemonB.setMaxHealth(lutemonB.getMaxHealth() + 1);
            lutemonB.setDefence(lutemonB.getDefence() + 1);

            BattleStorage.getInstance().setWinner(lutemonB);
            BattleStorage.getInstance().setLoser(lutemonA);
            BattleStorage.getInstance().setLutemonA(null);
            BattleStorage.getInstance().setLutemonB(null);

            btnActivity.setText("Next");

        }
        else if (lutemonB.getHealth() <= 0){

            // A WON B LOST

            lutemonPic = getPic(lutemonA, "winner");
            imgLutemonA.setImageResource(lutemonPic);
            lutemonPic = getPic(lutemonB, "dead");
            imgLutemonB.setImageResource(lutemonPic);

            System.out.println("Lutemon B is dead ");
            battleText += "\n \n Lutemon " + lutemonB.getName() + " is dead \n";
            //txtBattleText.setText(battleText);

            System.out.println("Lutemon A WON! ");
            battleText += "Lutemon " + lutemonA.getName() + " WON! \n";
            //txtBattleText.setText(battleText);

            txtRound.setText(battleText);

            txtAtoB.setText("");
            txtBtoA.setText("");


            //Update visible stats before loses, victories and xp
            updateStatsA();
            updateStatsB();

            lutemonB.setLoses(lutemonB.getLoses() + 1);
            lutemonA.setVictories(lutemonA.getVictories() + 1);
            lutemonA.setExperience(lutemonA.getExperience() + 1);
            //NEW:
            lutemonA.setMaxHealth(lutemonA.getMaxHealth() + 1);
            lutemonA.setDefence(lutemonA.getDefence() + 1);

            BattleStorage.getInstance().setWinner(lutemonA);
            BattleStorage.getInstance().setLoser(lutemonB);
            BattleStorage.getInstance().setLutemonA(null);
            BattleStorage.getInstance().setLutemonB(null);


            btnActivity.setText("Next");

        }


    }


    private void returnToBattle(View view) {

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","battle");
        intent.putExtra("tabNbr",3);
        startActivity(intent);

    }

    private int getPic(Lutemon lutemon, String mode) {

        int pic = 0;

        switch (mode) {

            case "fight":
                if (lutemon instanceof Black) {
                    pic = R.drawable.blacklargeclearfight;
                } else if (lutemon instanceof Green) {
                    pic = R.drawable.greenlargeclearfight;
                } else if (lutemon instanceof Orange) {
                    pic = R.drawable.orangelargeclearfight;
                } else if (lutemon instanceof Pink) {
                    pic = R.drawable.pinklargeclearfight;
                } else if (lutemon instanceof White) {
                    pic = R.drawable.whitelargeclearfight;
                } else {
                    pic = lutemon.getImage();
                }
                break;
            case "fightFlipped":
                if (lutemon instanceof Black) {
                    pic = R.drawable.blacklargeclearfightflipped;
                } else if (lutemon instanceof Green) {
                    pic = R.drawable.greenlargeclearfightflipped;
                } else if (lutemon instanceof Orange) {
                    pic = R.drawable.orangelargeclearfightflipped;
                } else if (lutemon instanceof Pink) {
                    pic = R.drawable.pinklargeclearfightflipped;
                } else if (lutemon instanceof White) {
                    pic = R.drawable.whitelargeclearfightflipped;
                } else {
                    pic = lutemon.getImage();
                }
                break;
            case "dead":
                pic = R.drawable.ripimage;
                /*
                if (lutemon instanceof Black) {
                    pic = R.drawable.blacklargeclearflipped;
                } else if (lutemon instanceof Green) {
                    pic = R.drawable.greenlargeclearflipped;
                } else if (lutemon instanceof Orange) {
                    pic = R.drawable.orangelargeclearflipped;
                } else if (lutemon instanceof Pink) {
                    pic = R.drawable.pinklargeclearflipped;
                } else if (lutemon instanceof White) {
                    pic = R.drawable.whitelargeclearflipped;
                } else {
                    pic = lutemon.getImage();
                }*/
                break;
            case "winner":
                pic = R.drawable.winner;
                /*if (lutemon instanceof Black) {
                    pic = R.drawable.blacklargeclearflipped;
                } else if (lutemon instanceof Green) {
                    pic = R.drawable.greenlargeclearflipped;
                } else if (lutemon instanceof Orange) {
                    pic = R.drawable.orangelargeclearflipped;
                } else if (lutemon instanceof Pink) {
                    pic = R.drawable.pinklargeclearflipped;
                } else if (lutemon instanceof White) {
                    pic = R.drawable.whitelargeclearflipped;
                } else {
                    pic = lutemon.getImage();
                }*/
                break;

            //flipped basic image
            case "flipped":
                if (lutemon instanceof Black) {
                    pic = R.drawable.blacklargeclearflipped;
                } else if (lutemon instanceof Green) {
                    pic = R.drawable.greenlargeclearflipped;
                } else if (lutemon instanceof Orange) {
                    pic = R.drawable.orangelargeclearflipped;
                } else if (lutemon instanceof Pink) {
                    pic = R.drawable.pinklargeclearflipped;
                } else if (lutemon instanceof White) {
                    pic = R.drawable.whitelargeclearflipped;
                } else {
                    pic = lutemon.getImage();
                }
                break;
            //basic image
            default:
                if (lutemon instanceof Black) {
                    pic = R.drawable.blacklargeclear;
                } else if (lutemon instanceof Green) {
                    pic = R.drawable.greenlargeclear;
                } else if (lutemon instanceof Orange) {
                    pic = R.drawable.orangelargeclear;
                } else if (lutemon instanceof Pink) {
                    pic = R.drawable.pinklargeclear;
                } else if (lutemon instanceof White) {
                    pic = R.drawable.whitelargeclear;
                } else {
                    pic = lutemon.getImage();
                }
                break;

        }

        return pic;
    }


}