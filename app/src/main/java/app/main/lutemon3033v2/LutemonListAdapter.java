package app.main.lutemon3033v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.main.lutemon3033v2.Lutemons.Lutemon;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.gallery_lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {

        holder.galleryLutemon.setImageResource(lutemons.get(position).getImage());
        holder.lutemonName.setText(lutemons.get(position).getName());
        holder.lutemonAtt.setText("Attack: " + lutemons.get(position).getAttack());
        holder.lutemonDef.setText("Defence: " + lutemons.get(position).getDefence());
        holder.lutemonXP.setText("XP: " + lutemons.get(position).getExperience());
        holder.lutemonHealth.setText("Health: " + lutemons.get(position).getHealth() + "/" + lutemons.get(position).getMaxHealth());
        holder.lutemonVictories.setText("Victories: " + lutemons.get(position).getVictories());
        holder.lutemonLoses.setText("Loses: " + lutemons.get(position).getLoses());


    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
