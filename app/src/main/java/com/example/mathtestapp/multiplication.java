package com.example.mathtestapp;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class multiplication {
    private int result=0, minNum, maxNum;
    private int questionNum=1;
    Scanner input = new Scanner(System.in);
    public int multiplicationTest(int lvl, ArrayList<Question> iqList){
        switch (lvl){
            case 1:
                minNum = 0; maxNum = 9;
                break;
            case 2:
                minNum = 1; maxNum = 20;
                break;
            case 3:
                minNum = 10; maxNum = 30;
                break;
        }
        System.out.println("Multiplication test starts now");
        while(questionNum!=11) {
            result += test(iqList);
            questionNum++;
        }
        return result;
    }

    private int test(ArrayList<Question> iqList){
        System.out.println("Question number "+questionNum+":");
        Random rand = new Random();
        int a,b,c;
        a= rand.nextInt((maxNum+1)-minNum)+minNum;
        b= rand.nextInt((maxNum+1)-minNum)+minNum;
        c=a*b;
        int missing = rand.nextInt(3);
        return multiplicationQuestion(a,b,c,missing,iqList);
    }

    public int multiplicationQuestion(int a, int b, int c, int missing, ArrayList<Question> iqList){
        int answer;
        switch (missing){
            case 0:
                System.out.println("X * "+b+" = "+c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if(b==0){//X can be any number so answer is always correct
                    System.out.println("Correct!");
                    return 1;
                }
                if(answer == a){
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new Question(a,b,c,3,0, true));
                }
                break;
            case 1:
                System.out.println(a+ " * X ="+c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if(a==0){//X can be any number so answer is always correct
                    System.out.println("Correct!");
                    return 1;
                }
                if(answer == b){
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new Question(a,b,c,3,1, true));
                }
                break;
            case 2:
                System.out.println(a + " * " + b + " = X");
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if(answer == c){
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new Question(a,b,c,3,2, true));
                }
                break;
        }
        return 0;
    }
}
