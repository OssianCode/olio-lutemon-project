package app.main.lutemon3033v2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.InfoActivity;
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

        //TODO: make nicer buttons to HOME, TRAINING, BATTLE

        makeRadioButtons(view);

        makeButtonOnClic(view);

        return view;
    }

    private void makeRadioButtons(View view) {

        Home home = Home.getInstance();
        home.loadLutemons(view.getContext());

        ArrayList<Lutemon> lutemons = home.getLutemons();


        if (rgHomeLutemons == null) {
            /*RadioGroup*/
            rgHomeLutemons = (RadioGroup) view.findViewById(R.id.radioGroupHome);
        }

        if (rgHomeLutemons == null) {
            System.out.println( "Radio buttons NULL no removeAllViews()!!");
        }
        else {
            rgHomeLutemons.removeAllViews();

            RadioButton rbHomeLutemon;
            int i = 0;
            for (Lutemon lutemon : lutemons) {
                rbHomeLutemon = new RadioButton(view.getContext());
                rbHomeLutemon.setText(lutemon.getName() + " " + lutemon.getColor() + " HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() + " XP: " + lutemon.getExperience()+ " A: " + lutemon.getAttack() + " D: "  + lutemon.getDefence());
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
        ImageView imgInfo = (ImageView) view.findViewById(R.id.imgInfo);


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
                else if (view.getId() == R.id.imgInfo) {
                    System.out.println("Home INSTRUCTIONS clicked");

                    Intent intent = new Intent(view.getContext(), InfoActivity.class);
                    startActivity(intent);

                }
                /*System.out.println("REfresh SOON view.getId() " + view.getId());
                refresh();*/

            }
        };


        btnNewLut.setOnClickListener(listener);
        btnHomeToTrain.setOnClickListener(listener);
        btnHomeToBattle.setOnClickListener(listener);
        imgInfo.setOnClickListener(listener);

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

        //System.out.println("REfresh SOON  1");
        refresh(view);

    }



    private void lutemonToBattleField(View view) {

        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {
            BattleField.getInstance().addLutemon(lutemon, view.getContext());
        }

        //System.out.println("REfresh SOON 2 ");
        refresh(view);

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

    private void refresh(View view) {

        //System.out.println("REFRESH METHOD 1");

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","home");
        intent.putExtra("tabNbr",1);
        startActivity(intent);

    }

}

