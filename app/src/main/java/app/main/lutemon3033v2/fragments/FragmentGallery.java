package app.main.lutemon3033v2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.main.lutemon3033v2.Areas.Home;
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


        Home home = Home.getInstance();

        ArrayList<Lutemon> lutemons = home.getLutemons();

        //TODO: get lutemons from home, training, battle and graveyard
        //TrainingArena trainingArena = TrainingArena.getInstance();
        //BattleField battleField = BattleField.getInstance();
        //Graveyard graveyard = Graveyard.getInstance();



        recyclerView = view.findViewById(R.id.gallery_lutemon_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new LutemonListAdapter(view.getContext(), lutemons));


        return view;
    }
}