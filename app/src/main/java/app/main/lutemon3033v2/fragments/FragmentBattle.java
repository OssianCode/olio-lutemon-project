package app.main.lutemon3033v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import app.main.lutemon3033v2.R;


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
        //TODO: checkbox lutemons at battle area
        //TODO: move home and restore health
        //TODO: move to training

        return view;
    }
}