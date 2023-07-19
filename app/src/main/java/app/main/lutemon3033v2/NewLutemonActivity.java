package app.main.lutemon3033v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import app.main.lutemon3033v2.Areas.Home;
import app.main.lutemon3033v2.Lutemons.*;



public class NewLutemonActivity extends AppCompatActivity {

    Context context = NewLutemonActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lutemon);
    }

    public void createNewLutemon(View view){

        System.out.println("Create Lutemon button");

        // GET DATA
        EditText editName = findViewById(R.id.editName);
        RadioGroup rgColor = findViewById(R.id.radioGroupColor) ;
        RadioButton rbNewLutemonColor = findViewById(rgColor.getCheckedRadioButtonId());
        String selectedColor = rbNewLutemonColor.getText().toString();
        int id = (int) (1 + Math.random()*5); //TODO: FIX what id? REMOVE ID TOTALLY?
        String name = editName.getText().toString();

        System.out.println("New Lutemon name " + name);

        //Create Lutemon
        Lutemon lutemon = null;

        switch (selectedColor) {
            case "White":
                lutemon = new White(name, id);
                break;
            case "Green":
                lutemon = new Green(name, id);
                break;
            case "Pink":
                lutemon = new Pink(name, id);
                break;
            case "Orange":
                lutemon = new Orange(name, id);
                break;
            default:
                lutemon = new Black(name, id);
                break;

        }

        System.out.println("Create Lutemon created");

        //SAVE
        Home home = Home.getInstance();
        home.createLutemon(lutemon, context);

        System.out.println("Create Lutemon saved");

/* // RELOCATED IN STORAGE
        home.saveLutemons(context);

        System.out.println("Create Lutemons SAVED TO FILE"); //TODO: Save file in home.createlutemon??*/


        //finish();





        Intent intent = new Intent(this, TabMainActivity.class);
        startActivity(intent);
        //TODO: Return to HOME fragment TEST

    }


}