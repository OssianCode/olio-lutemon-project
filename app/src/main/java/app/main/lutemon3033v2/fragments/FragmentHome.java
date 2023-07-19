package app.main.lutemon3033v2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.NewLutemonActivity;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;


public class FragmentHome extends Fragment {

    RadioGroup rgHomeLutemons = null;

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

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //TODO: move to training
        //TODO: move to battle
        // TODO: restore health points at home

        makeRadioButtons(view);

        makeButtonOnClic(view);

        return view;
    }

    private void makeRadioButtons(View view) {

        Home home = Home.getInstance();
        home.loadLutemons(view.getContext());

        ArrayList<Lutemon> lutemons = home.getLutemons();

        //TODO: test - try to move this elsewhere

        if (rgHomeLutemons == null) {
            /*RadioGroup*/
            rgHomeLutemons = (RadioGroup) view.findViewById(R.id.radioGroupHome); //TODO: test viev changed

        }

        if (rgHomeLutemons == null) {
            System.out.println( "Radio buttonit NULL Ei poisteta!!");

        }
        else {

            rgHomeLutemons.removeAllViews();

            RadioButton rbHomeLutemon;
            int i = 0;
            for (Lutemon lutemon : lutemons) {
                rbHomeLutemon = new RadioButton(view.getContext());
                rbHomeLutemon.setText(lutemon.getName() + " " + lutemon.getColor()+ " HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
                rbHomeLutemon.setId(i);
                rgHomeLutemons.addView(rbHomeLutemon);
                i++;
            }

        }

    }


    private void makeButtonOnClic (View view){

        Button btnNewLut = (Button) view.findViewById(R.id.btnNewLutemon);
        Button btnHomeToTrain = (Button) view.findViewById(R.id.btnHomeMoveToTraining);
        Button btnHomeToBattle = (Button) view.findViewById(R.id.btnHomeMoveToBattle);


        View.OnClickListener listener =  new View.OnClickListener()
        {
            public void onClick(View view)
            {

                if (view.getId() == R.id.btnNewLutemon) {
                    //System.out.println("New Lutemon button");
                    Intent intent = new Intent(view.getContext(), NewLutemonActivity.class);
                    startActivity(intent);



                }
                else if (view.getId() == R.id.btnHomeMoveToTraining) {
                    //System.out.println("Home to Training button");
                    lutemonToTrainingArena(view);

                }
                else if (view.getId() == R.id.btnHomeMoveToBattle) {
                    //System.out.println("Home to Battle button");
                    lutemonToBattleField(view);

                }

                /*System.out.println("REfresh SOON view.getId() " + view.getId());
                refresh();*/

            }
        };


        btnNewLut.setOnClickListener(listener);
        btnHomeToTrain.setOnClickListener(listener);
        btnHomeToBattle.setOnClickListener(listener);


    }


    private void lutemonToTrainingArena(View view) {
        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {


            //System.out.println("HOME lutemon name " + lutemon.getName() + " list lutemons 1: ");


            TrainingArena.getInstance().listLutemons();

            TrainingArena.getInstance().addLutemon(lutemon, view.getContext());

            //System.out.println("HOME list lutemons 2: ");

            TrainingArena.getInstance().listLutemons();
        }
        //System.out.println( "HOME going to  makeRadioButtons(view);" );

        //Crashes  java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.RadioGroup.removeAllViews()
        //makeRadioButtons(view);

        //System.out.println("REfresh SOON  1");
        refresh(view);
        //makeRadioButtons(view);

        //TODO: addLutemon and getLutemon at STORAGE MUST REMOVE from FILE!!
    }



    private void lutemonToBattleField(View view) {

        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {
            BattleField.getInstance().addLutemon(lutemon, view.getContext());
        }
        //System.out.println( "HOME going to  makeRadioButtons(view);" );

        //Crashes  java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.RadioGroup.removeAllViews()

        //makeRadioButtons(view);

        //System.out.println("REfresh SOON 2 ");
        refresh(view);
        //makeRadioButtons(view);
    }
    private Lutemon findLutemon(View view) {

        /*RadioGroup*/ rgHomeLutemons = (RadioGroup) getView().findViewById(R.id.radioGroupHome);

        //System.out.println("HOME rgHomeLutemons " + rgHomeLutemons + " view.getId() " + view.getId());


        //System.out.println("HOME Checked butno " + rgHomeLutemons.getCheckedRadioButtonId());

        Lutemon lutemon = null;

        if ((int) rgHomeLutemons.getCheckedRadioButtonId() >= 0 && (int) rgHomeLutemons.getCheckedRadioButtonId() < Home.getInstance().getLutemons().size()) {

            lutemon = Home.getInstance().getLutemon(rgHomeLutemons.getCheckedRadioButtonId(), view.getContext());
        }

        return lutemon;
    }

    public void refresh(View view) {

        System.out.println("REFRESH METHOD 1");

/*
            Fragment fragmentHome = null;
            fragmentHome = getParentFragmentManager().findFragmentByTag("FragmentHome");
            final FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.detach(fragmentHome);
            fragmentTransaction.attach(fragmentHome);
            fragmentTransaction.commit();*/


        //TODO: fix, does not work
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();*/


        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        startActivity(intent);


        //makeRadioButtons(view);

        //System.out.println("REFRESH METHOD 2");

    }




}

