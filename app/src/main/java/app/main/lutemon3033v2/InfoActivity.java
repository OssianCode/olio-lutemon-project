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

        instructions = "Gallery: Delete buried Lutemons from Gravestone image. \n" ;
        instructions += "Home: Create new Lutemons, move Lutemons. \n";
        instructions += "Training Arena: Train Lutemons, move Lutemons. \n";
        instructions += "Battle Field: Select 2 Lutemons for a battle, move Lutemons. \n";
        instructions += "Battle Field: Choose first Lutemon, click Battle. \n";
        instructions += "Battle Field: Choose second Lutemon, click Battle. \n";
        instructions += "Battle Field: The battle begins. Follow button actions. \n";
        instructions += "Defeat: Move Lutemon back Home, to gain full health. \n";
        instructions += "Defeat: Or bury Lutemon to Graveyard. Buried Lutemons can be deleted from Gallery. \n";
        instructions += "Victory: Move winner back Home, to gain full health. \n";
        instructions += "Victory: Or return winner back to Battle Field. \n";


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