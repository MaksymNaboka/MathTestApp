package com.example.mathtestapp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Main {
    static int result;
    static addition ad = new addition();
    static multiplication mp = new multiplication();
    static subtraction st = new subtraction();
    static division dv = new division();
    static int choice, level=1;
    static boolean repeat=true;
    static boolean divisionSimple=false;
    static ArrayList<Question> incorrectQuestionsList = new ArrayList<Question>();
    static Scanner s = new Scanner(System.in);
    static private int score=0;

    public static void main(String[] args) {
        System.out.println("Hello, this is math knowledge tester");
        while(repeat) {
            repeat = false;
            System.out.println("Please choose what you want to test:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Everything");
            System.out.println("6. Retry incorrect");
            System.out.println("\n0. Exit");
            choice = s.nextInt();
            s.nextLine();
            if (choice == 0) {
                continue;
            }
            if (choice < 0 || choice > 6) {
                System.out.println("Incorrect choice");
                repeat = true;
                continue;
            }
            if (choice == 6) {
                retryIncorrect(incorrectQuestionsList);
                System.out.println("Want to test again? (yes/no)");
                repeat = s.hasNext("yes");
                s.nextLine();
                continue;
            }

            System.out.println("Now, choose the difficulty 1-3 (higher levels mean higher numbers)");
            level = s.nextInt();
            s.nextLine();
            if (level < 1 || level > 3) {
                System.out.println("I have not implemented this difficulty");
                continue;
            }

            switch (choice) {
                case 1:
                    result = ad.additionTest(level, incorrectQuestionsList);
                    break;
                case 2:
                    result = st.subtractionTest(level, incorrectQuestionsList);
                    break;
                case 3:
                    result = mp.multiplicationTest(level, incorrectQuestionsList);
                    break;
                case 4:
                    result = dv.divisionTest(level, incorrectQuestionsList);
                    break;
                case 5:
                    result = everythingTest(level, incorrectQuestionsList);
                    break;
            }
            System.out.println("Your result is: " + result);
            if (choice != 5) {
                if (result <= 3) {
                    System.out.println("You need to study more");
                } else if (result >= 7) {
                    System.out.println("Excellent, probably you are the smartest in your class");
                } else {
                    System.out.println("You know material well enough");
                }
            } else {
                if (result <= 15) {
                    System.out.println("You need to study more");
                } else if (result >= 30) {
                    System.out.println("Excellent, probably you are the smartest in your class");
                } else {
                    System.out.println("You know material well enough");
                }
            }
            System.out.println("Want to test again? (yes/no)");
            repeat = s.hasNext("yes");
            s.nextLine();
        }
        if(java.time.LocalTime.now().getHour()<=10){
            System.out.println("Have a good morning!");
        }else
        if(java.time.LocalTime.now().getHour()<=17){
            System.out.println("Have a good day!");
        }else{
            System.out.println("Have a good night!");
        }
    }

    private static int everythingTest(int lvl, ArrayList<Question> iqList){
        System.out.println("Warning: you will not be able to leave the test until you do all 40 questions");
        System.out.println("Do you want to continue?");
        if(s.hasNext("no")){s.nextLine(); return 0;}else{
            s.nextLine();
            System.out.println("Let's go!");
        }
        int result=0;
        result+= ad.additionTest(lvl, iqList);
        result+= st.subtractionTest(lvl, iqList);
        result+= dv.divisionTest(lvl, iqList);
        result+= mp.multiplicationTest(lvl, iqList);
        return result;
    }
    private static void retryIncorrect(ArrayList<Question> iqList){
        Scanner s = new Scanner(System.in);
        System.out.println("Now you can retry your incorrectly answered questions");
        System.out.println("Every 5 questions you will be asked to continue, if you wish to leave, say no");
        int questionCount=0;
        while(!iqList.isEmpty()){
            System.out.println("questions left in list: " + iqList.size());
            if(questionCount!=0 && questionCount%5==0){
                System.out.println("Do you want to continue?");
                if(s.hasNext("no")){
                    return;
                }
                s.nextLine();
            }
            Question question = iqList.get(0);
            switch (question.operation){
                case 0:
                     ad.additionQuestion((int)question.a, (int)question.b, (int)question.c, question.missing, iqList);
                    break;
                case 1:
                     st.subtractionQuestion((int)question.a, (int)question.b, (int)question.c, question.missing, iqList);
                    break;
                case 2:
                     mp.multiplicationQuestion((int)question.a, (int)question.b, (int)question.c, question.missing, iqList);
                    break;
                case 3:
                    if(question.Simple){
                       dv.divisionQuestionSimple((int)question.a, (int)question.b, (int)question.c, question.missing, iqList);
                    }else{
                       dv.divisionQuestionNotSimple(question.a, question.b, question.c, question.missing, iqList);
                    }
                    break;
            }
            iqList.remove(0);
            questionCount++;
        }
        System.out.println("You have answered all your previously incorrect questions!");
    }


    public static void addToScore(){
        int points =0;
        switch(level){
            case 1:
                points = 1;
                break;
            case 2:
                points = 2;
                break;
            case 3:
                points = 3;
                break;
        }
        if(points>0) {
            score += points;
        }
    }

    public static void subtractFromScore(){
        if(score>0){
            score-=1;
        }
    }

    public static int getScore() {
        return score;
    }
    public static boolean isDivisionSimple(){return divisionSimple; }

    public static int getLevel(){return level;}
}