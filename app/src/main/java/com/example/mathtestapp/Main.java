package com.example.mathtestapp;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Main {
    private static int level=1;
    private static boolean divisionSimple=true;
    static ArrayList<Question> incorrectQuestionsList = new ArrayList<Question>();
    private static int score=0;


    public static void addToScore(int qLevel){
        int points =0;
        switch(qLevel){
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
    public static void setDivisionSimple(boolean value){divisionSimple = value;}

    public static int getLevel(){return level;}
    public static void setLevel(int lvl){
        if(lvl>0 && lvl<=3){
            level=lvl;
        }
    }

    public static void saveState(File path){
        System.out.println("save path:" + path.toString());
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path,"savedData.txt"));
            DataOutputStream dataOut = new DataOutputStream(outputStream);
            ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);
            dataOut.writeInt(score);
            dataOut.writeInt(level);
            dataOut.writeBoolean(divisionSimple);
            dataOut.writeInt(incorrectQuestionsList.size());
            for(int i=0; i<incorrectQuestionsList.size(); i++){
                objectOut.writeObject(incorrectQuestionsList.get(i));
            }
            dataOut.close();
            objectOut.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadState(File path){
        System.out.println("load");
        try {
            FileInputStream inputStream = new FileInputStream(new File(path,"savedData.txt"));
            DataInputStream dataIn = new DataInputStream(inputStream);
            ObjectInputStream objectIn = new ObjectInputStream(inputStream);
            score = dataIn.readInt();
            level = dataIn.readInt();
            divisionSimple = dataIn.readBoolean();
            int iqListSize = dataIn.readInt();
            for(int i=0; i<iqListSize; i++){
                incorrectQuestionsList.add((Question)objectIn.readObject());
            }
            dataIn.close();
            objectIn.close();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}