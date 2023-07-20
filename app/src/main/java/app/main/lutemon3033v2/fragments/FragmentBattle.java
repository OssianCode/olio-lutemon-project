package app.main.lutemon3033v2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Graveyard;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.Battle.BattleActivity;
import app.main.lutemon3033v2.Lutemons.Green;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.Lutemons.White;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;


public class FragmentBattle extends Fragment {

    RadioGroup rgBattleLutemons = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        //TODO: Battle area - battle
        // TODO: Dead/lost - return, delete(?)
        //TODO: Won, plus exp point, total wins, to arena - to home to rest

        //TODO: move home and restore health
        //TODO: move to training


        makeCheckBoxes(view);

        makeButtonOnClic(view);

        return view;
    }


    private void makeCheckBoxes(View view) {

        BattleField battleField = BattleField.getInstance();

        battleField.loadLutemons(view.getContext());
        ArrayList<Lutemon> lutemons = battleField.getLutemons();

        /*
        //RadioGroup rgBattleLutemons = view.findViewById(R.id.radioGroupBattle);
        //rgBattleLutemons.removeAllViews();

        CheckBox cbBattleLutemon;
        int i = 0;
        for (Lutemon lutemon : lutemons) {
            cbBattleLutemon = new CheckBox(view.getContext());
            cbBattleLutemon.setText(lutemon.getName() + " " + lutemon.getColor() + " HP-XP-A-D: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth()+ "-" + lutemon.getExperience()+ "-" + lutemon.getAttack() + "-" + lutemon.getDefence());
            cbBattleLutemon.setId(i++);
            rgBattleLutemons.addView(cbBattleLutemon);
        }*/


        //TODO: test
        if (rgBattleLutemons == null) {

            rgBattleLutemons = (RadioGroup) view.findViewById(R.id.radioGroupBattle);
        }

        if (rgBattleLutemons == null) {
        System.out.println( "Radio buttonit NULL Ei poisteta!!");
        }
        else {
            rgBattleLutemons.removeAllViews();

            CheckBox cbBattleLutemon;
            int i = 0;
            for (Lutemon lutemon : lutemons) {
                cbBattleLutemon = new CheckBox(view.getContext());
                cbBattleLutemon.setText(lutemon.getName() + " " + lutemon.getColor() + " HP-XP-A-D: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth()+ "-" + lutemon.getExperience()+ "-" + lutemon.getAttack() + "-" + lutemon.getDefence());
                cbBattleLutemon.setId(i++);
                rgBattleLutemons.addView(cbBattleLutemon);
            }
        }
    }


    private void makeButtonOnClic (View view){


        Button btnBattleLutemons = (Button) view.findViewById(R.id.btnBattle);

        //TODO: FIX Battle
        Button btnBattleToHome = (Button) view.findViewById(R.id.btnReturnResting);
        Button btnBattleToTraining = (Button) view.findViewById(R.id.btnReturnTraining);


        View.OnClickListener listener =  new View.OnClickListener()
        {

            public void onClick(View view)
            {
                //System.out.println("Battle");
                //TODO: Get selected Lutemons and Battle


                if (view.getId() == R.id.btnBattle) {
                    //System.out.println("BATTLE button");
                    lutemonBattle(view);

                } else if (view.getId() == R.id.btnReturnResting) {
                    //System.out.println("Battle to HOME button");
                    lutemonBattleToHome(view);

                } else if (view.getId() == R.id.btnReturnTraining) {
                    //System.out.println("Battle to Training button");
                    lutemonBattleToTrainingArena(view);

                }

            }
        };

        btnBattleLutemons.setOnClickListener(listener);
        btnBattleToHome.setOnClickListener(listener);
        btnBattleToTraining.setOnClickListener(listener);

    }


    private void lutemonBattle(View view) {

/*
        System.out.println("FIGHT!" );
        //TODO: Find 2 checked lutemons
        //TODO: to add XP
        //TODO: UI
        //TODO: Activity and pics
        //TODO: delete or add home a lost lutemon(?)
        //TODO: bury from graveyard (delete)
        //TODO: add won battles, lost battles
        //TODO: Add trained days(????)

        //TODO: remove this
        //FOR TEST
        Lutemon lutemonA1 = new White("Olio", 200);
        Lutemon lutemonB1 = new Green("Kamu", 201);
        BattleField.getInstance().addLutemon(lutemonA1, getContext());
        BattleField.getInstance().addLutemon(lutemonB1, getContext());


        Lutemon lutemonA = BattleField.getInstance().getLutemon(0, getContext());
        Lutemon lutemonB = BattleField.getInstance().getLutemon(1, getContext());

        boolean fighting = true;
        while (fighting) {

            if (lutemonB.getHealth() > 0) {
                System.out.println("FIGHT Lutemon B attacks A ");
                lutemonA.battleDefence(lutemonB);
            }
            else {
                System.out.println("Lutemon B is dead ");
                lutemonB.setHealth(0);

                Graveyard.getInstance().addLutemon(lutemonB, getContext());
                BattleField.getInstance().addLutemon(lutemonA, getContext());

                System.out.println("Lutemon A WON! ");

                fighting = false;
            }

            if (lutemonA.getHealth() > 0) {

                System.out.println("Lutemon A attacks B ");
                lutemonB.battleDefence(lutemonA);

            }
            else {

                System.out.println("Lutemon A is dead ");
                lutemonA.setHealth(0);

                Graveyard.getInstance().addLutemon(lutemonA, getContext());
                BattleField.getInstance().addLutemon(lutemonB, getContext());

                System.out.println("Lutemon B WON! ");

                fighting = false;
            }

        }
*/
        int idA = 0; //TODO: for test set 0 and 1 - get real selected!
        int idB = 1;

        Intent intent = new Intent(view.getContext(), BattleActivity.class);
        intent.putExtra("idA",idA);
        intent.putExtra("idB",idB);
        startActivity(intent);


        //DOES NOT WORK
        //ArrayList<Lutemon> lutemons = findLutemons(view);


        /*RadioGroup rgTrainingLutemons = (RadioGroup) getView().findViewById(R.id.radioGroupTraining);

        System.out.println("TRAINING train lutemon " );


        System.out.println("TRAINING Checked butno " + rgTrainingLutemons.getCheckedRadioButtonId());


        if ((int) rgTrainingLutemons.getCheckedRadioButtonId() >= 0 && (int) rgTrainingLutemons.getCheckedRadioButtonId() < TrainingArena.getInstance().getLutemons().size()) {

            TrainingArena.getInstance().train(rgTrainingLutemons.getCheckedRadioButtonId());

            TrainingArena.getInstance().saveLutemons(getContext());
        }*/

        //refresh(view);

    }

    private void lutemonBattleToHome(View view) {
        ArrayList<Lutemon> lutemons = findLutemons(view);


        //TODO: test
        for(Lutemon lutemon : lutemons) {

            //Health max out refactored to Home (Storage).addLutemon
            /*System.out.println("BattleToHome 1" + lutemon.getName());

            lutemon.setHealth(2);

            System.out.println("BattleToHome 1" + lutemon.getHealth());

            lutemon.setHealth(lutemon.getMaxHealth());

            System.out.println("BattleToHome 2" + lutemon.getHealth());*/

            Home.getInstance().addLutemon(lutemon, view.getContext());

        }
        System.out.println( "ready" );
        Home.getInstance().listLutemons();

        refresh(view);

    }


    private void lutemonBattleToTrainingArena(View view) {
        ArrayList<Lutemon> lutemons = findLutemons(view);


        for(Lutemon lutemon : lutemons) {

            System.out.println("BattleToTrainingArena " + lutemon.getName());

            TrainingArena.getInstance().addLutemon(lutemon, view.getContext());

        }


        System.out.println( "ready" );
        TrainingArena.getInstance().listLutemons();

        refresh(view);

    }
    private ArrayList<Lutemon> findLutemons(View view) {

        //System.out.println("BATTLE findLutemons");

// LOOP to go through checked isChecked()

        ArrayList<Lutemon> battleLutemons = BattleField.getInstance().getLutemons();

        //Copy of battleLutemons
        ArrayList<Lutemon> battleLutemons2 = battleLutemons;

        //Reverse copied list
        Collections.reverse(battleLutemons2);

        ArrayList<Lutemon> lutemonsChecked = new ArrayList<>();

        /*

                int i = 0;
        for (Lutemon lutemon : lutemons) {
            cbBattleLutemon = new CheckBox(view.getContext());
            cbBattleLutemon.setText(lutemon.getName() + " " + lutemon.getColor() + " HP-XP-A-D: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth()+ "-" + lutemon.getExperience()+ "-" + lutemon.getAttack() + "-" + lutemon.getDefence());
            cbBattleLutemon.setId(i++);
            rgBattleLutemons.addView(cbBattleLutemon);
        }

         */
        //Starting counting from last, in reverse order
        int i = battleLutemons2.size();
        for(Lutemon lutemon : battleLutemons2) {

            //Starting counting from last, in reverse order
            //index is size -1
            i--;

            //TODO: FIX ERROR - maaybe do radiobuttons to move...
            /*
            D  Shutting down VM
            2023-07-16 19:02:34.714 32719-32719 AndroidRuntime          app.main.lutemon3033v2               E  FATAL EXCEPTION: main
            Process: app.main.lutemon3033v2, PID: 32719
            java.util.ConcurrentModificationException
            at java.util.ArrayList$Itr.next(ArrayList.java:860)
            at app.main.lutemon3033v2.fragments.FragmentBattle.findLutemons(FragmentBattle.java:258)
            at app.main.lutemon3033v2.fragments.FragmentBattle.lutemonBattleToHome(FragmentBattle.java:148)
            */

            CheckBox checkBox = (CheckBox)getView().findViewById(i);
            boolean isChecked =  checkBox.isChecked();

            if (isChecked) {

                System.out.println("IS CHECKED i " + lutemon.getName() + " " + i);
                lutemonsChecked.add(BattleField.getInstance().getLutemon(i, view.getContext()));

            }


        }

        System.out.println( "ready" );
        BattleField.getInstance().listLutemons();

        return lutemonsChecked;
    }



    private void refresh(View view) {

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","battle");
        intent.putExtra("tabNbr",3);
        startActivity(intent);

    }

}