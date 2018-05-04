package com.example.meda.minesweeper.logic;

import android.util.Log;

import com.example.meda.minesweeper.view.MineSweeperView;

import java.util.Random;

/**
 * Created by meda on 2/25/16.
 */
public class MineSweeperModel {
    private static MineSweeperModel instance = null;
    private short checker; //checks how many mines are near spot clicked
    private short currentValue = 0;
    private int numberOfMines;


    public static MineSweeperModel getInstance() {
        if (instance == null) {
            instance = new MineSweeperModel();
        }//if
        return instance;
    }//MineSweeperModel

    public static final short BLANK = -1;
    public static final short EMPTY = 0;
    public static final short ONE = 1;
    public static final short TWO = 2;
    public static final short THREE = 3;
    public static final short MINE = 4;
    public static final short FLAG = 5;

    private short[][] model = {
            {BLANK, BLANK, BLANK, BLANK, BLANK},
            {BLANK, BLANK, BLANK, BLANK, BLANK},
            {BLANK, BLANK, BLANK, BLANK, BLANK},
            {BLANK, BLANK, BLANK, BLANK, BLANK},
            {BLANK, BLANK, BLANK, BLANK, BLANK},
    };

    public void resetModel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                model[i][j] = BLANK;
            }//for
        }//outer for
    }//reset

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public short setFieldContent(int x, int y, short content) {
        return model[x][y] = content;
    }//setFieldContent

    public boolean hasMine(int x, int y){
        return model[x][y] >= MINE;
    }//hasMine


    public void setBomb() {
        Random rand = new Random(System.currentTimeMillis());
        numberOfMines = 0;
        while(numberOfMines != 3){
            int row = rand.nextInt(5);
            int column = rand.nextInt(5);
            if (!(hasMine(row,column))) {
                model[row][column] = MINE;
                numberOfMines++;
            }//if there is mine or no mine
        }//while
    }//setBomb

    public short mineChecker(int x, int y) {
        checker = 0;
        if(!(hasMine(x,y))) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int height = x - 1 + i;
                    int width = y - 1 + j;
                    if (height >= 0 && width >= 0 && height <= 4 && width <= 4) {
                        if (hasMine(height, width)) {
                            checker++;
                        }//inner if
                    }//outer if
                }//inner for
            }//outer for
        }else{
            return MINE;
        }//if-else
        return checker;
    }//mineChecker

    public short checkFlagValue(int x, int y){
        currentValue = model[x][y];

        if(currentValue == BLANK){
            return BLANK;
        }else if(currentValue == MINE){
            return MINE;
        }else if(currentValue== FLAG){
            return FLAG;
        }//if-else

        return currentValue;
    }//checkFlagValue

}
