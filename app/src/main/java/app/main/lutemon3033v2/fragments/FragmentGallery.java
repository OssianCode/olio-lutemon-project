package app.main.lutemon3033v2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.main.lutemon3033v2.Areas.BattleField;
import app.main.lutemon3033v2.Areas.Graveyard;
import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Areas.TrainingArena;
import app.main.lutemon3033v2.Lutemons.Lutemon;
import app.main.lutemon3033v2.LutemonListAdapter;
import app.main.lutemon3033v2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGallery#newInstance} factory method to
 * create an instance of this fragment.
 */


public class FragmentGallery extends Fragment {

    private RecyclerView recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        //HOME
        Home home = Home.getInstance();
        home.loadLutemons(view.getContext());

        ArrayList<Lutemon> lutemonsAll = home.getLutemons();

        //TRAINING ARENA
        TrainingArena trainingArena = TrainingArena.getInstance();
        trainingArena.loadLutemons(view.getContext());

        lutemonsAll.addAll(trainingArena.getLutemons());

        //BATTLE FIELD
        BattleField battleField = BattleField.getInstance();
        battleField.loadLutemons(view.getContext());

        lutemonsAll.addAll(battleField.getLutemons());

        //GRAVEYARD
        Graveyard graveyard = Graveyard.getInstance();
        graveyard.saveLutemons(view.getContext()); //TODO: for test empty graveyard THIS EMPTIES
        graveyard.loadLutemons(view.getContext()); //TODO: for test empty graveyard - load empty
        lutemonsAll.addAll(graveyard.getLutemons()); //TODO: for test empty graveyard


        recyclerView = view.findViewById(R.id.gallery_lutemon_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new LutemonListAdapter(view.getContext(), lutemonsAll));


        return view;
    }
}