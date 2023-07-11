package app.main.lutemon3033;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: everything
        Home home = Home.getInstance();
        TrainingArea trainingArea = TrainingArea.getInstance();
        BattleField battleField = BattleField.getInstance();
    }
}