package app.main.lutemon3033v2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder{

        ImageView galleryLutemon, imgDelete;
        TextView lutemonName, lutemonAtt, lutemonDef, lutemonXP, lutemonHealth, lutemonVictories, lutemonLoses;

        public LutemonViewHolder(@NonNull View itemView) {
            super(itemView);

            galleryLutemon = itemView.findViewById(R.id.imgGalleryLutemon);
            lutemonName = itemView.findViewById(R.id.txtGalleryLutemonName);
            lutemonAtt = itemView.findViewById(R.id.txtGalleryLutemonAttack);
            lutemonDef = itemView.findViewById(R.id.txtGalleryLutemonDefend);
            lutemonXP = itemView.findViewById(R.id.txtGalleryLutemonExp);
            lutemonHealth = itemView.findViewById(R.id.txtGalleryLutemonHealth);
            lutemonVictories = itemView.findViewById(R.id.txtGalleryLutemonVictories);
            lutemonLoses = itemView.findViewById(R.id.txtGalleryLutemonLoses);
            imgDelete = itemView.findViewById(R.id.imgDelete);

        }
    }
