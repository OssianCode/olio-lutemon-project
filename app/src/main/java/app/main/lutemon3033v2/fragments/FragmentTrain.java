package app.main.lutemon3033v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import app.main.lutemon3033v2.R;


public class FragmentTrain extends Fragment {


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

        //TODO: Training area - TRAIN add training points (what logic?)
        //TODO: radiobuttons lutemons at training area
        //TODO: move home
        //TODO: move to battle

        return view;
    }
}