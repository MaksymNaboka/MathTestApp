package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_screen);
        if(Main.incorrectQuestionsList.isEmpty()) {
            findViewById(R.id.RetryButton).setEnabled(false);
        }else{
            findViewById(R.id.RetryButton).setEnabled(true);
        }
        updateScore();
    }

    public void AdditionTesting(View view){
//            setContentView(R.layout.addition_testing);
        Intent intent = new Intent(this, additionActivity.class);
        startActivity(intent);
//            Main.ad.additionTest(Main.level, Main.incorrectQuestionsList);
    }

    public void updateScore(){
        TextView scoreCounter= (TextView) findViewById(R.id.ScoreCounter);
        scoreCounter.setText(String.format("%4d",Main.getScore()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateScore();
        Log.d("aboba", "IQLIST SIZE " + Main.incorrectQuestionsList.size());
        if(Main.incorrectQuestionsList.isEmpty()) {
            findViewById(R.id.RetryButton).setEnabled(false);
        }else{
            findViewById(R.id.RetryButton).setEnabled(true);
        }
    }
}