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

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;


public class FragmentBattle extends Fragment {


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


        makeRadioButtons(view);

        makeButtonOnClic(view);

        return view;
    }


    private void makeRadioButtons(View view) {

        BattleField battleField = BattleField.getInstance();

        battleField.loadLutemons(view.getContext());
        ArrayList<Lutemon> lutemons = battleField.getLutemons();

        RadioGroup rgBattleLutemons = view.findViewById(R.id.radioGroupBattle);
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


    private void makeButtonOnClic (View view){


        Button btnBattleLutemons = (Button) view.findViewById(R.id.btnBattle);

        //TODO: FIX Battle
        Button btnBattleToHome = (Button) view.findViewById(R.id.btnReturnResting);
        Button btnBattleToTraining = (Button) view.findViewById(R.id.btnReturnTraining);


        View.OnClickListener listener =  new View.OnClickListener()
        {

            public void onClick(View view)
            {
                System.out.println("Battle");
                //TODO: Get selected Lutemons and Battle


                if (view.getId() == R.id.btnBattle) {
                    System.out.println("BATTLE button");
                    lutemonBattle(view);

                } else if (view.getId() == R.id.btnReturnResting) {
                    System.out.println("Battle to HOME button");
                    lutemonBattleToHome(view);

                } else if (view.getId() == R.id.btnReturnTraining) {
                    System.out.println("Battle to Training button");
                    lutemonBattleToTrainingArena(view);

                }

            }
        };

        btnBattleLutemons.setOnClickListener(listener);
        btnBattleToHome.setOnClickListener(listener);
        btnBattleToTraining.setOnClickListener(listener);

    }


    private void lutemonBattle(View view) {

        System.out.println("FIGHT!" );
        //TODO: Find 2 checked lutemons

        /*RadioGroup rgTrainingLutemons = (RadioGroup) getView().findViewById(R.id.radioGroupTraining);

        System.out.println("TRAINING train lutemon " );


        System.out.println("TRAINING Checked butno " + rgTrainingLutemons.getCheckedRadioButtonId());


        if ((int) rgTrainingLutemons.getCheckedRadioButtonId() >= 0 && (int) rgTrainingLutemons.getCheckedRadioButtonId() < TrainingArena.getInstance().getLutemons().size()) {

            TrainingArena.getInstance().train(rgTrainingLutemons.getCheckedRadioButtonId());

            TrainingArena.getInstance().saveLutemons(getContext());
        }

        refresh(view);*/

    }

    private void lutemonBattleToHome(View view) {
        ArrayList<Lutemon> lutemons = findLutemons(view);


        for(Lutemon lutemon : lutemons) {

            System.out.println("BattleToHome 1" + lutemon.getName());

            lutemon.setHealth(2);

            System.out.println("BattleToHome 1" + lutemon.getHealth());

            lutemon.setHealth(lutemon.getMaxHealth());

            System.out.println("BattleToHome 2" + lutemon.getHealth());

            Home.getInstance().addLutemon(lutemon, view.getContext());

        }


        System.out.println( "ready" );
        TrainingArena.getInstance().listLutemons();

        // FOR EACH LUTEMON
        /*if (lutemon != null) {



            System.out.println("BATTLE lutemon name " + lutemon.getName() + "list lutemons 1: ");


            Home.getInstance().listLutemons();

            Home.getInstance().addLutemon(lutemon, view.getContext());

            System.out.println("BATTLE list lutemons 2: ");

            Home.getInstance().listLutemons();
        }
        //System.out.println( "TRAINING going to  makeRadioButtons(view);" );

        //Crashes  java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.RadioGroup.removeAllViews()
        //makeRadioButtons(view);*/

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

        // FOR EACH LUTEMON
        /*if (lutemon != null) {

            System.out.println("BATTLE lutemon name " + lutemon.getName() + " list lutemons 1: ");


            TrainingArena.getInstance().listLutemons();

            TrainingArena.getInstance().addLutemon(lutemon, view.getContext());

            System.out.println("BATTLE list lutemons 2: ");

            TrainingArena.getInstance().listLutemons();
        }
        //System.out.println( "HOME going to  makeRadioButtons(view);" );

        //Crashes  java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.RadioGroup.removeAllViews()
        //makeRadioButtons(view);
*/
        System.out.println("REfresh SOON  1");
        refresh(view);

    }
    private ArrayList<Lutemon> findLutemons(View view) {

        System.out.println("BATTLE findLutemons");

// LOOP to go through checked isChecked()

        ArrayList<Lutemon> lutemons = BattleField.getInstance().getLutemons();

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
        int i = 0;
        for(Lutemon lutemon : lutemons) {

//TODO: FIX ERROR
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

                System.out.println("IS CHECKED " + lutemon.getName());
                lutemonsChecked.add(BattleField.getInstance().getLutemon(i, view.getContext()));

            }


            i++;
        }

        return lutemonsChecked;
    }
















    public void refresh(View view) {

        //TODO: FIX to go to battle
        System.out.println("REFRESH METHOD 1");

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        startActivity(intent);

        System.out.println("REFRESH METHOD 2");

    }

}