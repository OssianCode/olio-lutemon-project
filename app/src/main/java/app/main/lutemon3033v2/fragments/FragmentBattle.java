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

        Button btnBattleToHome = (Button) view.findViewById(R.id.btnReturnResting);
        Button btnBattleToTraining = (Button) view.findViewById(R.id.btnReturnTraining);

        View.OnClickListener listener =  new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //System.out.println("Battle");

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

    }

    private void returnLutemonsTOBattleField(ArrayList<Lutemon> lutemons, View view){

        for (Lutemon lutemon: lutemons) {

            BattleField.getInstance().addLutemon(lutemon, view.getContext());

        }
    }

    private void lutemonBattleToHome(View view) {

        System.out.println("lutemonBattleToHome 1" );

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