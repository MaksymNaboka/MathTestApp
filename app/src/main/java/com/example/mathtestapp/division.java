package com.example.mathtestapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class division {
    private int result=0, minNum, maxNum;
    private int questionNum=1;
    private boolean simple;
    Scanner input = new Scanner(System.in);
    public int divisionTest(int lvl, ArrayList<IncorrectQuestion> iqList){
        switch (lvl){
            case 1:
                minNum = 50; maxNum = 100;
                break;
            case 2:
                minNum = 100; maxNum = 1000;
                break;
            case 3:
                minNum = 1000; maxNum = 3000;
                break;
        }
        System.out.println("Division test starts now");
        System.out.println("This test is tricky, as you need to write fractions");
        System.out.println("Want to use simpe mode?(only whole numbers)");
        if(input.hasNext("yes")){
            simple = true;
        }else{
            simple=false;
        }
        input.nextLine();
        while(questionNum!=11) {
            result += test(iqList);
            questionNum++;
        }
        return result;
    }

    private int test(ArrayList<IncorrectQuestion> iqList){
        System.out.println("Question number "+questionNum+":");
        Random rand = new Random();
        if(!simple) {
            float a, b, c;
            a = rand.nextInt((maxNum+1)-minNum)+minNum;
            b = rand.nextInt((maxNum+1)-minNum)+minNum;
            if (a < b) {
                float temp = a;
                a = b;
                b = temp;
            }
            c = a / b;
            int missing = rand.nextInt(3);
            return divisionQuestionNotSimple(a,b,c,missing,iqList);
        }else{
            int a, b, c;
            a = rand.nextInt((maxNum+1)-minNum)+minNum;
            ArrayList<Integer> divisors = findAllDivisors(a);
            b = divisors.get(rand.nextInt(divisors.size()));
            c = a / b;
            int missing = rand.nextInt(3);
            return divisionQuestionSimple(a,b,c,missing,iqList);
        }
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

    public int divisionQuestionSimple(int a, int b, int c, int missing, ArrayList<IncorrectQuestion> iqList){
        int answer;
        switch (missing) {
            case 0:
                System.out.println("X / " + b + " = " + c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if (answer == a) {
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,4,0, true));
                }
                break;
            case 1:
                System.out.println(a + " / X =" + c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if (answer == b) {
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,4,1, true));
                }
                break;
            case 2:
                System.out.println(a + " / " + b + " = X");
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextInt();
                input.nextLine();
                if (answer == c) {
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,4,2, true));
                }
                break;
        }
        return 0;
    }
    public int divisionQuestionNotSimple(float a, float b, float c, int missing, ArrayList<IncorrectQuestion> iqList){
        float answer;
        switch (missing) {
            case 0:
                System.out.println("X / " + b + " = " + c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextFloat();
                input.nextLine();
                if (answer == a) {
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,4,0, false));
                }
                break;
            case 1:
                System.out.println(a + " / X =" + c);
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextFloat();
                input.nextLine();
                if (answer == b) {
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,4,1, false));
                }
                break;
            case 2:
                System.out.println(a + " / " + b + " = X");
                System.out.println("Please, type the number that should be instead of `X`");
                answer = input.nextFloat();
                input.nextLine();
                if (answer == c) {
                    System.out.println("Correct!");
                    return 1;
                }else{
                    System.out.println("Incorrect, but you can try again later");
                    iqList.add(new IncorrectQuestion(a,b,c,4,2, false));
                }
                break;
        }
        return 0;
    }
}
