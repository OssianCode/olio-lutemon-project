package app.main.lutemon3033v2.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.main.lutemon3033v2.R;

public class BattleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);


        //TODO: when lost: +1 loses
        //TODO: when won: +1 victories + 1 exp
    }
}