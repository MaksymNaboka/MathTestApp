package com.example.mathtestapp;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class subtraction {
    private int result=0, minNum, maxNum;
    private int questionNum=1;
    Scanner input = new Scanner(System.in);
    public int subtractionTest(int lvl, ArrayList<IncorrectQuestion> iqList){
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
        System.out.println("Subtraction test starts now");
        while(questionNum!=11) {
            result += test(iqList);
            questionNum++;
        }
        return result;
    }

    private int test(ArrayList<IncorrectQuestion> iqList){
        System.out.println("Question number "+questionNum+":");
        Random rand = new Random();
        int a,b,c;
        a= rand.nextInt((maxNum+1)-minNum)+minNum;
        b= rand.nextInt((maxNum+1)-minNum)+minNum;
        if(a<b){
            int temp = a;
            a=b;
            b=temp;
        }
        c=a-b;
        int missing = rand.nextInt(3);
        return subtractionQuestion(a,b,c,missing,iqList);
    }

    public int subtractionQuestion(int a, int b, int c, int missing, ArrayList<IncorrectQuestion> iqList){
        int answer;
        switch (missing){
            case 0:
                System.out.println("X - "+b+" = "+c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if(answer == a){
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,2,0, true));
                }
                break;
            case 1:
                System.out.println(a+ " - X ="+c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if(answer == b){
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,2,1, true));
                }
                break;
            case 2:
                System.out.println(a + " - " + b + " = X");
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if(answer == c){
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,2,2, true));
                }
                break;
        }
        return 0;
    }
}
