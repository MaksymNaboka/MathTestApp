package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class additionActivity extends AppCompatActivity {
    private int result=0, minNum, maxNum;
    private int questionNum=1;
    private int answer=-1;
    Scanner input = new Scanner(System.in);
    ConstraintLayout additionLayout;
    GeneralQuestions GQ = new GeneralQuestions();
    private int a,b,c;
    private int missing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_question_layout);
        additionTest(Main.level, Main.incorrectQuestionsList);
        TextView scoreCounter= (TextView) findViewById(R.id.ScoreCounter);
        scoreCounter.setText(String.format("%4d",Main.getScore()));
    }

    public int additionTest(int lvl, ArrayList<Question> iqList){
        switch (lvl){
            case 1:
                minNum = 10; maxNum = 50;
                break;
            case 2:
                minNum = 100; maxNum = 1000;
                break;
            case 3:
                minNum = 1000; maxNum = 3000;
                break;
        }
        System.out.println("Addition test starts now");
        test(iqList);
        return result;
    }

    private int test(ArrayList<Question> iqList){
        System.out.println("Question number "+questionNum+":");
        Random rand = new Random();
        a= rand.nextInt((maxNum+1)-minNum)+minNum;
        b= rand.nextInt((maxNum+1)-minNum)+minNum;
        c=a+b;
        missing = rand.nextInt(3);
        String equation ="";
        switch(missing){
            case 0:
                equation = "X + "+b+" = "+c;
            case 1:
                equation = a+" + X = "+c;
            case 2:
                equation = a+" + "+b+" = X";
        }
        TextView tv = (TextView) findViewById(R.id.Equation);
//        return GeneralQuestions.displayQuestion(a,b,c,missing,iqList,equation);
        return 0;
    }

    public int additionQuestion(int a, int b, int c, int missing, ArrayList<Question> iqList, String equation){
        additionLayout = findViewById(R.id.addition_layout);
        TextView tv = (TextView) findViewById(R.id.Equation);
        switch (missing){
            case 0:
                tv.setText(equation);
                break;
            case 1:
               tv.setText(equation);
                break;
            case 2:
                tv.setText(equation);
                break;
        }

        return 0;
    }

    private void evaluateAnswer(){
        switch (missing){
            case 0:
                if(answer == a){
                    System.out.println("Correct!");
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
                    Main.addToScore();
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    Main.incorrectQuestionsList.add(new Question(a,b,c,1,0, true));
                    Main.subtractFromScore();
                }
                break;
            case 1:
                if(answer == b){
                    System.out.println("Correct!");
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
                    Main.addToScore();
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    Main.incorrectQuestionsList.add(new Question(a,b,c,1,0, true));
                    Main.subtractFromScore();
                }
                break;
            case 2:
                if(answer == c){
                    System.out.println("Correct!");
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
                    Main.addToScore();
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    Main.incorrectQuestionsList.add(new Question(a,b,c,1,0, true));
                    Main.subtractFromScore();
                }
        }
        updateScore();
        questionNum++;
        additionTest(Main.level, Main.incorrectQuestionsList);
    }

//    public void setAnswer(View view) {
//                EditText answerET = (EditText) findViewById(R.id.answerText);
//                String answerString = answerET.getText().toString();
//                if(answerString.isEmpty()){
//                    return;
//                }
//                answer = Integer.parseInt(answerString);
//                answerET.setText("");
//                evaluateAnswer();
//    }

    public void updateScore(){
        TextView scoreCounter= (TextView) findViewById(R.id.ScoreCounter);
        scoreCounter.setText(String.format("%4d",Main.getScore()));
    }
}