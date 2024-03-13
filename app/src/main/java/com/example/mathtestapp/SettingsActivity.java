package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        int level = Main.getLevel();
        boolean division = Main.isDivisionSimple();
        if(division) {
            findViewById(R.id.divisionSimple).setEnabled(false);
        }else {
            findViewById(R.id.divisionHard).setEnabled(false);
        }
        switch (level){
            case 1:
                findViewById(R.id.difficulty1).setEnabled(false);
                break;
            case 2:
                findViewById(R.id.difficulty2).setEnabled(false);
                break;
            case 3:
                findViewById(R.id.difficulty3).setEnabled(false);
                break;
        }
    }

    public void setDivisionMode(View view){
        if(view.getId()==R.id.divisionSimple){
            Main.setDivisionSimple(true);
            findViewById(R.id.divisionSimple).setEnabled(false);
            findViewById(R.id.divisionHard).setEnabled(true);
        }else if(view.getId()==R.id.divisionHard){
            Main.setDivisionSimple(false);
            findViewById(R.id.divisionSimple).setEnabled(true);
            findViewById(R.id.divisionHard).setEnabled(false);
        }
    }

    public void setDifficulty(View view){
        String tag = view.getTag().toString();
        if(tag.contains("level1")){Main.setLevel(1);
            findViewById(R.id.difficulty1).setEnabled(false);
            findViewById(R.id.difficulty2).setEnabled(true);
            findViewById(R.id.difficulty3).setEnabled(true);
        }else
        if(tag.contains("level2")){Main.setLevel(2);
            findViewById(R.id.difficulty1).setEnabled(true);
            findViewById(R.id.difficulty2).setEnabled(false);
            findViewById(R.id.difficulty3).setEnabled(true);
        }else
        if(tag.contains("level3")){Main.setLevel(3);
            findViewById(R.id.difficulty1).setEnabled(true);
            findViewById(R.id.difficulty2).setEnabled(true);
            findViewById(R.id.difficulty3).setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        File path = getApplicationContext().getFilesDir();
        Main.saveState(path);
        super.onDestroy();
    }
}