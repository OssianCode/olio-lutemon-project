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

import java.util.ArrayList;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.NewLutemonActivity;
import app.main.lutemon3033v2.R;
import app.main.lutemon3033v2.TabMainActivity;


public class FragmentTrain extends Fragment {

    RadioGroup rgTrainingLutemons = null;

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
        View view = inflater.inflate(R.layout.fragment_train, container, false);

        makeRadioButtons(view);

        makeButtonOnClic(view);

        return view;
    }


    private void makeRadioButtons(View view) {

        TrainingArena trainingArena = TrainingArena.getInstance();
        trainingArena.loadLutemons(view.getContext());

        ArrayList<Lutemon> lutemons = trainingArena.getLutemons();



        if (rgTrainingLutemons == null) {
            /*RadioGroup*/
            System.out.println( "Fragment Training - radioB == null");
            rgTrainingLutemons = (RadioGroup) view.findViewById(R.id.radioGroupTraining);

        }

        if (rgTrainingLutemons == null) {
            System.out.println( "Fragment Training - Radio buttons NULL no removeAllViews() !!");

        }
        else {

            System.out.println( "Fragment Training 1 - buttons not null");
            rgTrainingLutemons.removeAllViews();

            System.out.println( "Fragment Training 2 - buttons ");

            //RadioGroup rgTrainingLutemons = view.findViewById(R.id.radioGroupTraining);
            //rgTrainingLutemons.removeAllViews();

            RadioButton rbTrainingLutemon;
            int i = 0;
            for (Lutemon lutemon : lutemons) {

                System.out.println( "Fragment Training 3 - btn LOOP i: " + i);

                rbTrainingLutemon = new RadioButton(view.getContext());
                rbTrainingLutemon.setText(lutemon.getName() + " " + lutemon.getColor()+ " XP: " + lutemon.getExperience());
                rbTrainingLutemon.setId(i++);
                rgTrainingLutemons.addView(rbTrainingLutemon);
            }

            System.out.println( "Fragment Training 4 - btn LOOP fin");

        }

    }

    private void makeButtonOnClic (View view){


        Button btnTrainLut = (Button) view.findViewById(R.id.btnTrain);
        Button btnTrainToHome = (Button) view.findViewById(R.id.btnTrainReturnResting);
        Button btnTrainToBattle = (Button) view.findViewById(R.id.btnTrainToBattle);


        View.OnClickListener listener =  new View.OnClickListener()
        {
            public void onClick(View view) {


                if (view.getId() == R.id.btnTrain) {
                    //System.out.println("training button");
                    lutemonTrain(view);

                } else if (view.getId() == R.id.btnTrainReturnResting) {
                    //System.out.println("Training to HOME button");
                    lutemonTrainingToHome(view);

                } else if (view.getId() == R.id.btnTrainToBattle) {
                    //System.out.println("Training to Battle button");

                    lutemonTrainingToBattleField(view);
                }
            }


        };

        btnTrainLut.setOnClickListener(listener);
        btnTrainToHome.setOnClickListener(listener);
        btnTrainToBattle.setOnClickListener(listener);

    }

    private void lutemonTrain(View view) {

        RadioGroup rgTrainingLutemons = (RadioGroup) getView().findViewById(R.id.radioGroupTraining);

        //System.out.println("TRAINING train lutemon " );


        //System.out.println("TRAINING Checked butno " + rgTrainingLutemons.getCheckedRadioButtonId());


        if ((int) rgTrainingLutemons.getCheckedRadioButtonId() >= 0 && (int) rgTrainingLutemons.getCheckedRadioButtonId() < TrainingArena.getInstance().getLutemons().size()) {

            TrainingArena.getInstance().train(rgTrainingLutemons.getCheckedRadioButtonId());

            TrainingArena.getInstance().saveLutemons(getContext());
        }

        refresh(view);
        //makeRadioButtons(view);

    }

    private void lutemonTrainingToHome(View view) {
        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {


            //System.out.println("TRAINING lutemon name " + lutemon.getName() + " list lutemons 1: ");


            Home.getInstance().listLutemons();

            Home.getInstance().addLutemon(lutemon, view.getContext());

            //System.out.println("TRAINING list lutemons 2: ");

            Home.getInstance().listLutemons();
        }
        //System.out.println( "TRAINING going to  makeRadioButtons(view);" );

        //Crashes  java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.RadioGroup.removeAllViews()
        //makeRadioButtons(view);

        refresh(view);
        //makeRadioButtons(view);

    }



    private void lutemonTrainingToBattleField(View view) {

        Lutemon lutemon = findLutemon(view);

        if (lutemon != null) {
            BattleField.getInstance().addLutemon(lutemon, view.getContext());
        }
        //System.out.println( "HOME going to  makeRadioButtons(view);" );

        //Crashes  java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.RadioGroup.removeAllViews()

        //makeRadioButtons(view);
        refresh(view);
    }
    private Lutemon findLutemon(View view) {

        RadioGroup rgTrainingLutemons = (RadioGroup) getView().findViewById(R.id.radioGroupTraining);

        //System.out.println("TRAINING rgTrainingLutemons " + rgTrainingLutemons + " view.getId() " + view.getId());


        //System.out.println("TRAINING Checked butno " + rgTrainingLutemons.getCheckedRadioButtonId());

        Lutemon lutemon = null;

        if ((int) rgTrainingLutemons.getCheckedRadioButtonId() >= 0 && (int) rgTrainingLutemons.getCheckedRadioButtonId() < TrainingArena.getInstance().getLutemons().size()) {

            lutemon = TrainingArena.getInstance().getLutemon(rgTrainingLutemons.getCheckedRadioButtonId(), view.getContext());
        }

        return lutemon;
    }


    private void refresh(View view) {

        //System.out.println("REFRESH fragment training  1");

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","training");
        intent.putExtra("tabNbr",2);
        startActivity(intent);

    }

}