package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        int level = Main.getLevel();
        boolean division = Main.isDivisionSimple();
        if(division) {
            findViewById(R.id.divisionHard).setEnabled(false);
        }else {
            findViewById(R.id.divisionSimple).setEnabled(false);
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
            Main.divisionSimple=true;
            findViewById(R.id.divisionSimple).setEnabled(false);
            findViewById(R.id.divisionHard).setEnabled(true);
        }else if(view.getId()==R.id.divisionHard){
            Main.divisionSimple=false;
            findViewById(R.id.divisionSimple).setEnabled(true);
            findViewById(R.id.divisionHard).setEnabled(false);
        }
    }

    public void setDifficulty(View view){
        String tag = view.getTag().toString();
        if(tag.contains("level1")){Main.level=1;
            findViewById(R.id.difficulty1).setEnabled(false);
            findViewById(R.id.difficulty2).setEnabled(true);
            findViewById(R.id.difficulty3).setEnabled(true);
        }else
        if(tag.contains("level2")){Main.level=2;
            findViewById(R.id.difficulty1).setEnabled(true);
            findViewById(R.id.difficulty2).setEnabled(false);
            findViewById(R.id.difficulty3).setEnabled(true);
        }else
        if(tag.contains("level3")){Main.level=3;
            findViewById(R.id.difficulty1).setEnabled(true);
            findViewById(R.id.difficulty2).setEnabled(true);
            findViewById(R.id.difficulty3).setEnabled(false);
        }
    }
}