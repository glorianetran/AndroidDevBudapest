package com.example.meda.minesweeper.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.meda.minesweeper.MainActivity;
import com.example.meda.minesweeper.R;
import com.example.meda.minesweeper.logic.MineSweeperModel;


/**
 * Created by meda on 2/21/16.
 */
public class MineSweeperView extends View {

    private Paint paintLine;
    private Paint paintNumber;
    private Bitmap backGround;

    private int clickCounter = 0;
    private short mineCheck = 0;
    private int endMineCounter = 0; //counts the mines
    private short flagCheck = 0;
    private boolean hitMine = false;
    public boolean flagMode = false;
    private boolean canClick = false;

    public MineSweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintLine = new Paint();
        paintLine.setColor(Color.BLACK);
        paintLine.setStrokeWidth(3);
        paintLine.setTextSize(25);
        paintLine.setStyle(Paint.Style.STROKE);

        paintNumber = new Paint();
        paintNumber.setColor(Color.BLACK);
        paintNumber.setStrokeWidth(3);
        paintNumber.setTextSize(25);
        paintNumber.setStyle(Paint.Style.STROKE);

        backGround = BitmapFactory.decodeResource(getResources(),
                R.drawable.orangebaby);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backGround, 0, 0, null);
        drawGameArea(canvas);
        drawNumbers(canvas);
        drawFlags(canvas);
        if (hitMine == true) {
            drawMines(canvas);
        }//if you hit a mine, all mines will appear
    }//onDraw

    //draws the game Area
    private void drawGameArea(Canvas canvas) {

        //border of Game Area
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        //four horizontal lines
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5, paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(), 2 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(), 3 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(), 4 * getHeight() / 5, paintLine);
        //four vertical lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(), paintLine);
    }//drawGameArea

    private void drawNumbers(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MineSweeperModel.getInstance().getFieldContent(i, j) == MineSweeperModel.EMPTY) {

                    paintLine.setColor(Color.BLACK);
                    float x = i * getWidth() / 5 + getWidth() / 10;
                    float y = j * getHeight() / 5 + getHeight() / 10;
                    canvas.drawText("0", x, y, paintLine);

                }else if (MineSweeperModel.getInstance().getFieldContent(i, j) == MineSweeperModel.ONE) {

                    paintNumber.setColor(Color.BLUE);
                    float x = i * getWidth() / 5 + getWidth() / 10;
                    float y = j * getHeight() / 5 + getHeight() / 10;
                    canvas.drawText("1", x, y, paintNumber);

                } else if (MineSweeperModel.getInstance().getFieldContent(i, j) == MineSweeperModel.TWO) {

                    paintNumber.setColor(Color.GREEN);
                    float x = i * getWidth() / 5 + getWidth() / 10;
                    float y = j * getHeight() / 5 + getHeight() / 10;
                    canvas.drawText("2", x, y, paintNumber);

                } else if (MineSweeperModel.getInstance().getFieldContent(i, j) == MineSweeperModel.THREE) {

                    paintNumber.setColor(Color.RED);
                    float x = i * getWidth() / 5 + getWidth() / 10;
                    float y = j * getHeight() / 5 + getHeight() / 10;
                    canvas.drawText("3", x, y, paintNumber);

                }//if-else if
            }//for
        }//outer for
    }//drawNumbers

    private void drawMines(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((MineSweeperModel.getInstance().getFieldContent(i, j) == MineSweeperModel.MINE)) {
                    paintLine.setColor(Color.BLACK);
                    float centerX = i * getWidth() / 5 + getWidth() / 10;
                    float centerY = j * getHeight() / 5 + getHeight() / 10;
                    int radius = getHeight() / 10 - 2;

                    canvas.drawCircle(centerX, centerY, radius, paintLine);
                }//if
            }//for
        }//outer for
    }//drawMines

    private void drawFlags(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if(MineSweeperModel.getInstance().getFieldContent(i,j) == MineSweeperModel.FLAG) {
                    canvas.drawLine(i * getWidth() / 5, j * getHeight() / 5,
                            (i + 1) * getWidth() / 5,
                            (j + 1) * getHeight() / 5, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 5, j * getHeight() / 5,
                            i * getWidth() / 5, (j + 1) * getHeight() / 5, paintLine);
                }//if
            }//for
        }//outer for
    }//drawFlags

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);

            if (tX < 5 && tY < 5) {

                if(clickCounter == 0) {
                    MineSweeperModel.getInstance().setBomb();
                    clickCounter++;
                }else{
                    if (flagMode) {
                        if (!canClick) {

                            //in this method I want to check if the spot the user clicked was a mine or blank or a flag
                            flagCheck = MineSweeperModel.getInstance().checkFlagValue(tX, tY);

                            if (flagCheck == MineSweeperModel.BLANK) {
                                hitMine = true;
                                ((MainActivity) getContext()).showSnackbarMessage(getContext().getString(R.string.notMine));
                                canClick = true;
                                MineSweeperModel.getInstance().setFieldContent(tX, tY, MineSweeperModel.FLAG);

                            } else if (flagCheck == MineSweeperModel.MINE) {
                                MineSweeperModel.getInstance().setFieldContent(tX, tY, MineSweeperModel.FLAG);
                                endMineCounter++;
                            }
                        }

                        endGame(); //endGame

                    } else {

                        if (!canClick) {
                            //checks mines
                            mineCheck = MineSweeperModel.getInstance().mineChecker(tX, tY);
                            MineSweeperModel.getInstance().setFieldContent(tX, tY, mineCheck);

                            if (MineSweeperModel.getInstance().getFieldContent(tX, tY) == MineSweeperModel.MINE) {
                                ((MainActivity) getContext()).showSnackbarMessage(getContext().getString(R.string.hitMine));
                                hitMine = true;
                                canClick = true;
                            }//if you hit a mine
                        }
                    }
                }//clickCounter check
            }//checks bounds
            invalidate();

        }//onTouchEvent
        return super.onTouchEvent(event);
    }

    private void endGame(){
        if(endMineCounter == 3){
            ((MainActivity)getContext()).showSnackbarMessage(getContext().getString(R.string.win));
            }//if
    }//endGame

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);

        backGround = Bitmap.createScaledBitmap(backGround, d, d, false);
    }//onMeasure

    public void clearGameField() {
        clickCounter = 0;
        endMineCounter = 0;
        hitMine = false;
        flagMode = false;
        canClick = false;
        MineSweeperModel.getInstance().resetModel();
        invalidate();
    }//clears game field

}//View

