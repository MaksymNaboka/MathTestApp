package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GeneralQuestionsActivity extends AppCompatActivity {
    private int minNum, maxNum;
    private Question currentQuestion;
    Random rand = new Random();
    private boolean doingEverything = false;
    private boolean doingIncorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_questions);
        updateScore();
        Intent i = getIntent();
        int level = i.getIntExtra("level", 0);
        setDifficulty(level);
        int operation = i.getIntExtra("operation", 0);
        Button b = (Button) findViewById(R.id.SubmitButton);
        b.setEnabled(true);
        switch(operation){
            case 0:
                additionTest();
                break;
            case 1:
                subtractionTest();
                break;
            case 2:
                multiplicationTest();
                break;
            case 3:
                divisionTest();
                break;
            case 4:
                everythingTest();
                break;
            case 10:
                retryIncorrect();
                break;
        }
    }
    public void setDifficulty(int level){
        switch (level){
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
    }

    public int additionTest(){
        System.out.println("Addition test starts now");
        doingEverything = false;
        doingIncorrect = false;
        test(0);
        return 0;
    }
    public int subtractionTest(){
        System.out.println("Subtraction test starts now");
        doingEverything = false;
        doingIncorrect = false;
        test(1);
        return 0;
    }
    public int multiplicationTest(){
        System.out.println("Multiplication test starts now");
        doingEverything = false;
        doingIncorrect = false;
        test(2);
        return 0;
    }
    public int divisionTest(){
        System.out.println("Division test starts now");
        doingEverything = false;
        doingIncorrect = false;
        test(3);
        return 0;
    }
    public int everythingTest(){
        System.out.println("Everything test starts now");
        doingEverything=true;
        doingIncorrect = false;
        test(rand.nextInt(4));
        return 0;
    }

    public int retryIncorrect(){
        System.out.println("Incorrect starts now");
        if(Main.incorrectQuestionsList.isEmpty()){
            TextView tv = (TextView) findViewById(R.id.Equation);
            tv.setText("You do not have any incorrect questions");
            Button b = (Button) findViewById(R.id.SubmitButton);
            b.setEnabled(false);
        }else {
            doingEverything = false;
            doingIncorrect = true;
            Question q = Main.incorrectQuestionsList.get(0);
            currentQuestion = new Question(q.a,q.b,q.c,q.operation, q.missing,q.Simple);
            String equation = getEquation(q);
            TextView tv = (TextView) findViewById(R.id.Equation);
            tv.setText(equation);
        }
        return 0;
    }

    private int test(int operation){
        float a;
        float b;
        if(operation == 3 && Main.isDivisionSimple()){//if division and simple
            a = (int)rand.nextInt((maxNum+1)-minNum)+minNum;
            ArrayList<Integer> divisors = findAllDivisors((int)a);
            b = divisors.get(rand.nextInt(divisors.size()));
        }else if(operation == 3 && !Main.isDivisionSimple()) {
            a= rand.nextInt((maxNum+1)-minNum)+minNum;
            b= rand.nextInt((maxNum+1)-minNum)+minNum;
            if (a < b) {
                float temp = a;
                a = b;
                b = temp;
            }
        }else{
            a= rand.nextInt((maxNum+1)-minNum)+minNum;
            b= rand.nextInt((maxNum+1)-minNum)+minNum;
        }
        if(operation == 1 && a<b){
            float temp = a;
            a = b;
            b = temp;
        }

        float c = 0;
        switch(operation){
            case 0:
                c=a+b;
                break;
            case 1:
                c=a-b;
                break;
            case 2:
                c=a*b;
                break;
            case 3:
                c=a/b;
                break;
        }
        int missing = rand.nextInt(3);
        if(operation == 3 && !Main.isDivisionSimple()){missing = 2;}
        currentQuestion = new Question(a,b,c,operation, missing,true);
        String equation = getEquation(currentQuestion);
        TextView tv = (TextView) findViewById(R.id.Equation);
        tv.setText(equation);
        return 0;
    }

    private String getEquation(Question q){
        String equation = "";
        switch (q.operation){
            case 0:
                switch(q.missing){
                    case 0:
                        equation = "X + "+(int)q.b+" = "+(int)q.c;
                        break;
                    case 1:
                        equation = (int)q.a+" + X = "+(int)q.c;
                        break;
                    case 2:
                        equation = (int)q.a+" + "+(int)q.b+" = X";
                        break;
                }
                break;
            case 1:
                switch(q.missing){
                    case 0:
                        equation = "X - "+(int)q.b+" = "+(int)q.c;
                        break;
                    case 1:
                        equation = (int)q.a+" - X = "+(int)q.c;
                        break;
                    case 2:
                        equation = (int)q.a+" - "+(int)q.b+" = X";
                        break;
                }
                break;
            case 2:
                switch(q.missing){
                    case 0:
                        equation = "X * "+(int)q.b+" = "+(int)q.c;
                        break;
                    case 1:
                        equation = (int)q.a+" * X = "+(int)q.c;
                        break;
                    case 2:
                        equation = (int)q.a+" * "+(int)q.b+" = X";
                        break;
                }
                break;
            case 3:
                switch(q.missing){
                    case 0:
                        equation = "X / "+(int)q.b+" = "+(int)q.c;
                        break;
                    case 1:
                        equation = (int)q.a+" / X = "+(int)q.c;
                        break;
                    case 2:
                        equation = (int)q.a+" / "+(int)q.b+" = X";
                        break;
                }
                break;
        }
        return equation;
    }

    public void setAnswer(View view) {
        EditText answerET = (EditText) findViewById(R.id.answerText);
        String answerString = answerET.getText().toString();
        if(answerString.isEmpty()){
            return;
        }
        float answer = Float.parseFloat(answerString);
        answerET.setText("");
        evaluateAnswer(answer);
    }
    private void evaluateAnswer(float answer){
        switch (currentQuestion.missing){
            case 0:
                if(Math.abs(currentQuestion.a - answer) < 0.1
//                answer == currentQuestion.a
                ){
                    System.out.println("Correct!");
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
                    Main.addToScore();
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    Main.incorrectQuestionsList.add(currentQuestion);
                    Main.subtractFromScore();
                }
                break;
            case 1:
                if(Math.abs(currentQuestion.b - answer) < 0.1
//                        answer == currentQuestion.b
                ){
                    System.out.println("Correct!");
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
                    Main.addToScore();
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    Main.incorrectQuestionsList.add(currentQuestion);
                    Main.subtractFromScore();
                }
                break;
            case 2:
                if(Math.abs(currentQuestion.c - answer) < 0.1
//                        answer == currentQuestion.c
                ){
                    System.out.println("Correct!");
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
                    Main.addToScore();
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    Main.incorrectQuestionsList.add(currentQuestion);
                    Main.subtractFromScore();
                }
        }
        updateScore();
        if(doingEverything){
            everythingTest();
        }
        if(doingIncorrect){
            Main.incorrectQuestionsList.remove(0);
            retryIncorrect();
            return;
        }
        switch (currentQuestion.operation){
            case 0:
                additionTest();
                break;
            case 1:
                subtractionTest();
                break;
            case 2:
                multiplicationTest();
                break;
            case 3:
                divisionTest();
                break;
        }
    }

    public void updateScore(){
        TextView scoreCounter= (TextView) findViewById(R.id.ScoreCounter);
        scoreCounter.setText(String.format("%4d",Main.getScore()));
    }

    ArrayList<Integer> findAllDivisors(int number){
        ArrayList<Integer> divisors = new ArrayList<>();
        for(int i=1; i<=number; i++){
            if(number%i==0){
                divisors.add(i);
            }
        }
        if(divisors.size()>2){//if the number is not prime, remove such trivial questions as x/1 or x/x
            divisors.remove(0);
            divisors.remove(divisors.size()-1);
        }
        return divisors;
    }

    @Override
    protected void onDestroy() {
        File path = getApplicationContext().getFilesDir();
        Main.saveState(path);
        super.onDestroy();
    }
}