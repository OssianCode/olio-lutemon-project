package app.main.lutemon3033v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import app.main.lutemon3033v2.R;


public class FragmentHome extends Fragment {

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

        //TODO: Home
        //TODO: home: create lutemon
        //TODO: test: save and load created lutemons
        //TODO: radiobuttons lutemons at home
        //TODO: move to training
        //TODO: move to battle
        // TODO: restore health points at home

        //For test - how to catch data from mainActivity
        /*TextView txtHeader = view.findViewById(R.id.txtHomeHeader);

        if (getArguments() != null) {

            //System.out.println("Argumentteja löytyi");
            String headerText = getArguments().getString("dataID");
            txtHeader.setText(headerText);

            //System.out.println("Argumentteja headerText " + headerText);
        }*/

        return view;
    }
}