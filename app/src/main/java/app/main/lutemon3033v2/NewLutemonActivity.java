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

        //System.out.println("Create Lutemon button");

        // GET DATA
        EditText editName = findViewById(R.id.editName);
        RadioGroup rgColor = findViewById(R.id.radioGroupColor) ;
        RadioButton rbNewLutemonColor = findViewById(rgColor.getCheckedRadioButtonId());

        String selectedColor = "";
        //Check if a radioButton is selected
        if (rbNewLutemonColor != null) {
            selectedColor = rbNewLutemonColor.getText().toString();
        }

        int id = (int) (100000*Math.random() + Math.random()*5 + Math.random()*10);
        String name = editName.getText().toString();

        //System.out.println("New Lutemon name " + name);

        //Create Lutemon
        Lutemon lutemon = null;

        if (name.equals("")) {
            name = "Lutemon" + (int) (Math.random()*10);
        }

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

        //System.out.println("Create Lutemon created");

        //SAVE
        Home home = Home.getInstance();
        home.createLutemon(lutemon, context);

        //System.out.println("Create Lutemon saved");

        Intent intent = new Intent(view.getContext(), TabMainActivity.class);
        intent.putExtra("tabName","home");
        intent.putExtra("tabNbr",1);
        startActivity(intent);

    }

}