package com.example.mathtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class addition {
    private int result = 0, minNum, maxNum;
    private int questionNum = 1;
    private int answer = -1;
    Scanner input = new Scanner(System.in);
    ConstraintLayout additionLayout;
    MainActivity ma;

    public int additionTest(int lvl, ArrayList<Question> iqList) {
        switch (lvl) {
            case 1:
                minNum = 10;
                maxNum = 50;
                break;
            case 2:
                minNum = 100;
                maxNum = 1000;
                break;
            case 3:
                minNum = 1000;
                maxNum = 3000;
                break;
        }
        System.out.println("Addition test starts now");
        while (questionNum != 11) {
            result += test(iqList);
            questionNum++;
        }
        return result;
    }

    private int test(ArrayList<Question> iqList) {
        System.out.println("Question number " + questionNum + ":");
        Random rand = new Random();
        int a, b, c;
        a = rand.nextInt((maxNum + 1) - minNum) + minNum;
        b = rand.nextInt((maxNum + 1) - minNum) + minNum;
        c = a + b;
        int missing = rand.nextInt(3);
        return additionQuestion(a, b, c, missing, iqList);
    }

    public int additionQuestion(int a, int b, int c, int missing, ArrayList<Question> iqList) {
//        int answer=-1;
        EditText answerET = null;
        switch (missing) {
            case 0:
                for (int i = 0; i < additionLayout.getChildCount(); i++) {
                    if (additionLayout.getChildAt(i).getId() == R.id.Equation) {
                        TextView tv = (TextView) additionLayout.getChildAt(i);
                        tv.setText("X + " + b + " = " + c);
                    }
                }

//                System.out.println("X + "+b+" = "+c);
//                System.out.println("Please, type the number that should be instead of `X`");
//                answer = input.nextInt();
//                input.nextLine();
                if (answer == a) {
                    System.out.println("Correct!");
                    return 1;
                } else {
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new Question(a, b, c, 1, 0, true));
                }
                break;
            case 1:
                for (int i = 0; i < additionLayout.getChildCount(); i++) {
                    if (additionLayout.getChildAt(i).getId() == R.id.Equation) {
                        TextView tv = (TextView) additionLayout.getChildAt(i);
                        tv.setText("X + " + b + " = " + c);
                    }
                }
                System.out.println(a + " + X =" + c);
                System.out.println("Please, type the number that should be instead of `X`");
//                answer = input.nextInt();
//                input.nextLine();
                answer = b;
                if (answer == b) {
                    System.out.println("Correct!");
                    return 1;
                } else {
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new Question(a, b, c, 1, 1, true));
                }
                break;
            case 2:
                for (int i = 0; i < additionLayout.getChildCount(); i++) {
                    if (additionLayout.getChildAt(i).getId() == R.id.Equation) {
                        TextView tv = (TextView) additionLayout.getChildAt(i);
                        tv.setText("X + " + b + " = " + c);
                    }
                }
                System.out.println(a + " + " + b + " = X");
                System.out.println("Please, type the number that should be instead of `X`");
//                answer = input.nextInt();
//                input.nextLine();
                answer = c;
                if (answer == c) {
                    System.out.println("Correct!");
                    return 1;
                } else {
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new Question(a, b, c, 1, 2, true));
                }
                break;
        }
        return 0;
    }
}
