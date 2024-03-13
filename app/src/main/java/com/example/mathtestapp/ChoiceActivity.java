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
        Intent intent = new Intent(this, GeneralQuestionsActivity.class);
        intent.putExtra("operation", 0);
        intent.putExtra("level", Main.level);
        startActivity(intent);
//            Main.ad.additionTest(Main.level, Main.incorrectQuestionsList);
    }
    public void SubtractionTesting(View view){
//            setContentView(R.layout.addition_testing);
        Intent intent = new Intent(this, GeneralQuestionsActivity.class);
        intent.putExtra("operation", 1);
        intent.putExtra("level", Main.level);
        startActivity(intent);
//            Main.ad.additionTest(Main.level, Main.incorrectQuestionsList);
    }
    public void MultiplicationTesting(View view){
//            setContentView(R.layout.addition_testing);
        Intent intent = new Intent(this, GeneralQuestionsActivity.class);
        intent.putExtra("operation", 2);
        intent.putExtra("level", Main.level);
        startActivity(intent);
//            Main.ad.additionTest(Main.level, Main.incorrectQuestionsList);
    }
    public void DivisionTesting(View view){
//            setContentView(R.layout.addition_testing);
        Intent intent = new Intent(this, GeneralQuestionsActivity.class);
        intent.putExtra("operation", 3);
        intent.putExtra("level", Main.level);
        startActivity(intent);
//            Main.ad.additionTest(Main.level, Main.incorrectQuestionsList);
    }
    public void EverythingTesting(View view){
//            setContentView(R.layout.addition_testing);
        Intent intent = new Intent(this, GeneralQuestionsActivity.class);
        intent.putExtra("operation", 4);
        intent.putExtra("level", Main.level);
        startActivity(intent);
//            Main.ad.additionTest(Main.level, Main.incorrectQuestionsList);
    }
    public void RetryIncorrect(View view){
//            setContentView(R.layout.addition_testing);
        Intent intent = new Intent(this, GeneralQuestionsActivity.class);
        intent.putExtra("operation", 10);
        intent.putExtra("level", Main.level);
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
        if(Main.incorrectQuestionsList.isEmpty()) {
            findViewById(R.id.RetryButton).setEnabled(false);
        }else{
            findViewById(R.id.RetryButton).setEnabled(true);
        }
    }
}