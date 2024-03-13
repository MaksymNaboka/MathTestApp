package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ConstraintLayout choiceLayout;
String lastCalled="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File path = getApplicationContext().getFilesDir();
        Main.loadState(path);
        updateScore();
    }

    public void launchSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void startTesting(View view){
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);

//        setContentView(R.layout.choice_screen);
    }

    private void updateScore(){
        TextView scoreCounter= (TextView) findViewById(R.id.ScoreCounter);
        scoreCounter.setText(String.format("%4d",Main.getScore()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateScore();
    }

//    @Override
//    protected void onDestroy() {
//        File path = getApplicationContext().getFilesDir();
//        Main.saveState(path);
//        super.onDestroy();
//    }
}