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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.BattleStorage;
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

    private RadioGroup rgBattleLutemons = null;
    private TextView txtBattleInfo;
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

        txtBattleInfo = view.findViewById(R.id.txtBattleInfo);

        if (BattleStorage.getInstance().getLutemonA() != null) {
            txtBattleInfo.setText("Select 2nd for a battle");

        }



        makeRadioButtons(view);

        makeButtonOnClic(view);

        return view;
    }


    private void makeRadioButtons(View view) {

        BattleField battleField = BattleField.getInstance();

        battleField.loadLutemons(view.getContext());
        ArrayList<Lutemon> lutemons = battleField.getLutemons();


        //TODO: one idea: radiobuttons to move lutemons, and activity: select first -> activity select second -> put to BattleStorage -> then BattleActivity

        if (rgBattleLutemons == null) {

            rgBattleLutemons = (RadioGroup) view.findViewById(R.id.radioGroupBattle);
        }

        if (rgBattleLutemons == null) {
        System.out.println( "Radio buttonit NULL Ei poisteta!!");
        }
        else {
            rgBattleLutemons.removeAllViews();

            RadioButton rbBattleLutemon;
            int i = 0;
            for (Lutemon lutemon : lutemons) {
                rbBattleLutemon = new RadioButton(view.getContext());
                rbBattleLutemon.setText(lutemon.getName() + " " + lutemon.getColor() + " HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() + " XP: " + lutemon.getExperience()+ " A: " + lutemon.getAttack() + " D: "  + lutemon.getDefence());
                rbBattleLutemon.setId(i);
                rgBattleLutemons.addView(rbBattleLutemon);

                i++;
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


        //Get first checked

        // update text txtBattleInfo -> to "Select 2nd for a battle"

        //Get second checked

        // add selected to
        //BattleStorage.getInstance().setLutemonA(lutemons.get(0));
        //BattleStorage.getInstance().setLutemonB(lutemons.get(1));


        //then
        //Intent intent = new Intent(view.getContext(), BattleActivity.class);
        //startActivity(intent);

        System.out.println("FIGHT!" );


        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {

            if (BattleStorage.getInstance().getLutemonA() == null) {
                BattleStorage.getInstance().setLutemonA(lutemon);
                txtBattleInfo.setText("Select 2nd for a battle");

                refresh(view);

            }
            else if (BattleStorage.getInstance().getLutemonB() == null) {
                BattleStorage.getInstance().setLutemonB(lutemon);

                //Activate
                Intent intent = new Intent(view.getContext(), BattleActivity.class);
                startActivity(intent);
            }
        }


        //TODO: Find 2 checked lutemons

        //ArrayList<Lutemon> lutemons = findLutemons(view);

        /*if (lutemons.size() > 1 ) {
            BattleStorage.getInstance().setLutemonA(lutemons.get(0));
            BattleStorage.getInstance().setLutemonB(lutemons.get(1));

            returnLutemonsTOBattleField(lutemons, view);


            Intent intent = new Intent(view.getContext(), BattleActivity.class);
            startActivity(intent);
        }
        else {

            returnLutemonsTOBattleField(lutemons, view);
            refresh(view);
        }
*/
        //TODO: remove this


        //FOR TEST
        /*
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
       // int idA = 0; //TODO: for test set 0 and 1 - get real selected!
       // int idB = 1;

        //TODO FIX: when take first, the index of second changes! then out of bounds OR find wrong lutemon
        // use lutemon.id then?
        //Intent intent = new Intent(view.getContext(), BattleActivity.class);
        //intent.putExtra("idA",idA);
        //intent.putExtra("idB",idB);
        //startActivity(intent);


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

    private void returnLutemonsTOBattleField(ArrayList<Lutemon> lutemons, View view){

        for (Lutemon lutemon: lutemons) {

            BattleField.getInstance().addLutemon(lutemon, view.getContext());

        }
    }

    private void lutemonBattleToHome(View view) {

        System.out.println("lutemonBattleToHome 1" );
        /*ArrayList<Lutemon> lutemons = findLutemons(view);

        System.out.println("lutemonBattleToHome 2 lutemons ok??" );


        //TODO: test move battle to home  multiple
        for(Lutemon lutemon : lutemons) {

            System.out.println("lutemonBattleToHome 3" + lutemon.getName());

            Home.getInstance().addLutemon(lutemon, view.getContext());

        }
        System.out.println( "lutemonBattleToHome ready" );
        Home.getInstance().listLutemons();*/


        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {

            Home.getInstance().listLutemons();

            Home.getInstance().addLutemon(lutemon, view.getContext());

            Home.getInstance().listLutemons();
        }

        refresh(view);

    }


    private void lutemonBattleToTrainingArena(View view) {

        System.out.println("BattleToTrainingArena 1" );

        /*ArrayList<Lutemon> lutemons = findLutemons(view);

        System.out.println("BattleToTrainingArena 2 LUTEMONS OK?" );


        //TODO: test move battle to training multiple
        for(Lutemon lutemon : lutemons) {

            System.out.println("BattleToTrainingArena 3" + lutemon.getName());

            TrainingArena.getInstance().addLutemon(lutemon, view.getContext());

        }

        System.out.println( "BattleToTrainingArena ready" );
        TrainingArena.getInstance().listLutemons();*/

        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {

            TrainingArena.getInstance().listLutemons();

            TrainingArena.getInstance().addLutemon(lutemon, view.getContext());

            TrainingArena.getInstance().listLutemons();
        }

        refresh(view);

    }
    private ArrayList<Lutemon> findLutemons(View view) {

        System.out.println( "Start find lutemons" );
        BattleField.getInstance().listLutemons();

        ArrayList<Lutemon> battleLutemons = BattleField.getInstance().getLutemons();

        ArrayList<Lutemon> lutemonsChecked = new ArrayList<>();





        /*for(Lutemon lutemon : battleLutemons) {

            System.out.println("BATTLE loop " + i + " lutemon name: " + lutemon.getName() + " id: " + lutemon.getId());

            //TODO: FIX ERROR - maaybe do radiobuttons to move...
            //java.util.ConcurrentModificationException


            CheckBox checkBox = (CheckBox)getView().findViewById(i);
            boolean isChecked =  checkBox.isChecked();

            System.out.println("boolean isChecked: " + isChecked  + " i: " + i);

            if (isChecked) {

                System.out.println("IS CHECKED  " + lutemon.getName() );
                //For test
                //lutemonsChecked.add(BattleField.getInstance().getLutemon(i, view.getContext()));

            }

            i++;
        }*/

        //int i = battleLutemons.size();

        //Try iterator - from last to first not working

        /*ListIterator<Lutemon> itrLutemon = battleLutemons.listIterator(battleLutemons.size());
        while (itrLutemon.hasPrevious()) {

            i--;
            System.out.println("While has prev i: " + i);
            Lutemon lutemonFound = itrLutemon.previous(); // Fails here on second round!!

            System.out.println("PREV lutemonFound from battlefield  " + lutemonFound.getName() );

            CheckBox checkBox = (CheckBox)getView().findViewById(i);
            boolean isChecked =  checkBox.isChecked();

            System.out.println("boolean isChecked: " + isChecked  + " i: " + i);

            if (isChecked && lutemonFound != null) {

                System.out.println("IS CHECKED  name " + lutemonFound.getName() );
                //For test

                Lutemon lutemonForBattle = BattleField.getInstance().getLutemon(i, view.getContext());

                System.out.println("lutemonForBattle name " + lutemonForBattle.getName() );

                lutemonsChecked.add(lutemonForBattle);

            }

        }*/

        /*Iterator<Lutemon> itrLutemon = battleLutemons.iterator();
        while (itrLutemon.hasNext()) {

            System.out.println("While has next i: " + i);

            Lutemon lutemonFound = null;

            if (itrLutemon.next() != null) {
                lutemonFound = itrLutemon.next();

                System.out.println("NEXT LUTEMON at battlefield " + lutemonFound.getName() );

            }



            CheckBox checkBox = (CheckBox)getView().findViewById(i);
            boolean isChecked =  checkBox.isChecked();

            System.out.println("boolean isChecked: " + isChecked  + " i: " + i);

            if (isChecked && lutemonFound != null) {

                System.out.println("IS CHECKED  name " + lutemonFound.getName() );
                //For test

                Lutemon lutemonForBattle = BattleField.getInstance().getLutemon(i, view.getContext());

                System.out.println("lutemonForBattle name " + lutemonForBattle.getName() );

                lutemonsChecked.add(lutemonForBattle);

            }
            i++;

        }*/


        System.out.println( "findLutemons ready" );
        BattleField.getInstance().listLutemons();

        return lutemonsChecked;
    }

    private Lutemon findLutemon(View view) {


        System.out.println("Battle Find lutemon");
        RadioGroup rgBattleLutemons = (RadioGroup) getView().findViewById(R.id.radioGroupBattle);

        //System.out.println("Battle rgTrainingLutemons " + rgTrainingLutemons + " view.getId() " + view.getId());


        System.out.println("Battle Checked butno " + rgBattleLutemons.getCheckedRadioButtonId());

        Lutemon lutemon = null;

        if ((int) rgBattleLutemons.getCheckedRadioButtonId() >= 0 && (int) rgBattleLutemons.getCheckedRadioButtonId() < BattleField.getInstance().getLutemons().size()) {

            System.out.println("Found a lutemon");
            lutemon = BattleField.getInstance().getLutemon(rgBattleLutemons.getCheckedRadioButtonId(), view.getContext());

            System.out.println("Found a lutemon " + lutemon.getName());
        }

        return lutemon;
    }

    private void refresh(View view) {

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","battle");
        intent.putExtra("tabNbr",3);
        startActivity(intent);

    }

}