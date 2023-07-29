package app.main.lutemon3033v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    Context context = InfoActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        TextView infoTxt = findViewById(R.id.txtInstructions);

        String instructions = "";

        instructions = "Gallery: Gravestone: Delete Lutemon. \n" ;
        instructions += "Home: Create, move, instructions. \n";
        instructions += "Training Arena: Train, move. \n";
        instructions += "Battle Field: Select 2 Lutemons to battle, move. \n";
        instructions += "Battle Field: Choose 1st Lutemon, click Battle. \n";
        instructions += "Battle Field: Choose 2nd Lutemon, click Battle. \n";
        instructions += "Battle: Follow button actions. \n";
        instructions += "Defeat: Move Lutemon Home to gain full health. \n";
        instructions += "Defeat: Or bury Lutemon to Graveyard. Buried Lutemons can be deleted from Gallery. \n";
        instructions += "Victory: Prize: +1 XP, +1 MaxHP, +1 Defence point. \n";
        instructions += "Victory: Move winner Home to gain full health. \n";
        instructions += "Victory: Or return winner to Battle Field. \n";
        instructions += "Hint: Choose the weaker Lutemon to Battle first. They hit first. \n";


        infoTxt.setText(instructions);

    }


    public void backHome(View view) {

        //System.out.println("REFRESH METHOD 1");

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","home");
        intent.putExtra("tabNbr",1);
        startActivity(intent);

    }

}