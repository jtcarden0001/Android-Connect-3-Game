package com.johncarden.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    boolean blackTurn = true;

    public void firstTurn(){
        Random rand = new Random();
        int firstToGo = rand.nextInt(2);
        if(firstToGo == 0)
            Toast.makeText(this, "Black gets the first turn!", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(this, "Red gets the first turn!", Toast.LENGTH_SHORT).show();
            blackTurn = false;
    }}

    class Connect3{

        int[][] rows = {{0,0,0},
                        {0,0,0},
                        {0,0,0}};
        int[][] columns = {{0,0,0},
                            {0,0,0},
                            {0,0,0}};
        int[] lDiag = {0,0,0};
        int[] rDiag = {0,0,0};



        public boolean isWinner() {
            boolean winner = false;
            for (int i = 0; i < rows.length; i++) {
                winner = isWin(rows[i]);
                if(winner) return true;
                winner = isWin(columns[i]);
                if(winner) return true;
            }
            if(isWin(lDiag)) return true;
            if(isWin(rDiag)) return true;
            return winner;
        }


        public boolean isFull() {
            for (int i = 0; i < rows.length; i++) {
                if (lDiag[i] == 0) return false;
                if (rDiag[i] == 0) return false;
                for (int j = 0; j < rows[i].length; j++) {
                    if (rows[i][j] == 0) return false;
                    if (columns[i][j] == 0) return false;
                }
            }
            return true;
        }

        public boolean freeze(){
            for(int i = 0; i < rows.length; i++)
                for(int j = 0; j < rows[i].length; j++)
                    rows[i][j] = 1;
            return true;
        }


        public boolean isWin(int [] a){
        if(a[0] != 0){
                for(int i = 0; i < a.length-1; i++)
                    if(a[i] != a[i+1])
                        return false;
                return true;
            }
            return false;
        }


        public void reset(){
            for(int i = 0; i < rows.length; i++){
                lDiag[i] = 0;
                rDiag[i] = 0;
                for(int j = 0; j < rows[i].length; j++){
                    rows[i][j] = 0;
                    columns[i][j] = 0;
                }
            }
        }


        public boolean isClear(int row, int col){
            return rows[row-1][col-1] == 0;
        }


        public boolean insert(int row, int col, int color, boolean turn){
            rows[row-1][col-1] = color;
            columns[col-1][row-1] = color;
            if(row == 3 && col == 1)
                rDiag[0] = color;
            if(row == 1 && col == 1)
                lDiag[0] = color;
            if(row == 2 && col == 2){
                rDiag[1] = color;
                lDiag[1] = color;
            }
            if(row == 1 && col == 3)
                rDiag[2] = color;
            if(row == 3 && col == 3)
                lDiag[2] = color;
            return !turn;
        }
    }


    public void insertRed(int column) {
        if(freeze){
            Toast.makeText(this, "Click Reset Button to play again", Toast.LENGTH_SHORT).show();
        }else {

            Button resetButton = (Button) findViewById(R.id.resetButton);
            ImageView b11 = (ImageView) findViewById(R.id.r11);
            ImageView b21 = (ImageView) findViewById(R.id.r21);
            ImageView b31 = (ImageView) findViewById(R.id.r31);
            ImageView b12 = (ImageView) findViewById(R.id.r12);
            ImageView b22 = (ImageView) findViewById(R.id.r22);
            ImageView b32 = (ImageView) findViewById(R.id.r32);
            ImageView b13 = (ImageView) findViewById(R.id.r13);
            ImageView b23 = (ImageView) findViewById(R.id.r23);
            ImageView b33 = (ImageView) findViewById(R.id.r33);
            TextView gameStatus = (TextView) findViewById(R.id.gameStatus);

            if (currentGame.isClear(3, column)) {
                switch (column) {
                    case 1:
                        b31.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(3, column, 1, blackTurn);
                        break;
                    case 2:
                        b32.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(3, column, 1, blackTurn);
                        break;
                    case 3:
                        b33.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(3, column, 1, blackTurn);
                        break;
                    default:
                        break;
                }
            } else if (currentGame.isClear(2, column)) {
                switch (column) {
                    case 1:
                        b21.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(2, column, 1, blackTurn);
                        break;
                    case 2:
                        b22.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(2, column, 1, blackTurn);
                        break;
                    case 3:
                        b23.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(2, column, 1, blackTurn);
                        break;
                    default:
                        break;
                }
            } else if (currentGame.isClear(1, column)) {
                switch (column) {
                    case 1:
                        b11.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(1, column, 1, blackTurn);
                        break;
                    case 2:
                        b12.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(1, column, 1, blackTurn);
                        break;
                    case 3:
                        b13.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(1, column, 1, blackTurn);
                        break;
                    default:
                        break;
                }
            } else {
                Toast.makeText(this, "Please Choose a valid column", Toast.LENGTH_SHORT).show();
            }
            if (currentGame.isWinner()) {
                freeze = currentGame.freeze();
                resetButton.animate().translationYBy(250f).setDuration(1000);
                if(blackTurn)
                    gameStatus.setText("Congratulations Red Team, You Win!  Click reset to play again!");
                else
                    gameStatus.setText("Congratulations Black Team, You Win!  Click reset to play again!");
                gameStatus.setAlpha(1f);
            } else if (currentGame.isFull()){
                freeze = currentGame.freeze();
            resetButton.animate().translationYBy(250f).setDuration(1000);
        }
    }}


    public void insertBlack(int column) {
        if(freeze){
            Toast.makeText(this, "Click Reset Button to play again", Toast.LENGTH_SHORT).show();
        }else{

            Button resetButton = (Button) findViewById(R.id.resetButton);
            ImageView b11 = (ImageView) findViewById(R.id.b11);
            ImageView b21 = (ImageView) findViewById(R.id.b21);
            ImageView b31 = (ImageView) findViewById(R.id.b31);
            ImageView b12 = (ImageView) findViewById(R.id.b12);
            ImageView b22 = (ImageView) findViewById(R.id.b22);
            ImageView b32 = (ImageView) findViewById(R.id.b32);
            ImageView b13 = (ImageView) findViewById(R.id.b13);
            ImageView b23 = (ImageView) findViewById(R.id.b23);
            ImageView b33 = (ImageView) findViewById(R.id.b33);
            TextView gameStatus = (TextView) findViewById(R.id.gameStatus);

        if (currentGame.isClear(3,column)){
            switch(column){
                case 1:
                    b31.animate().translationYBy(250f).setDuration(1000);
                    blackTurn = currentGame.insert(3, column, 2, blackTurn);
                    break;
                case 2:
                    b32.animate().translationYBy(250f).setDuration(1000);
                    blackTurn = currentGame.insert(3, column, 2, blackTurn);
                    break;
                case 3:
                    b33.animate().translationYBy(250f).setDuration(1000);
                    blackTurn = currentGame.insert(3, column, 2, blackTurn);
                    break;
                default:
                    break;
            }}else if (currentGame.isClear(2, column)){
                switch(column){
                    case 1:
                        b21.animate().translationYBy(250f).setDuration(1000);
                        blackTurn =  currentGame.insert(2, column, 2, blackTurn);
                        break;
                    case 2:
                        b22.animate().translationYBy(250f).setDuration(1000);
                        blackTurn = currentGame.insert(2, column, 2, blackTurn);
                        break;
                    case 3:
                        b23.animate().translationYBy(250f).setDuration(1000);
                        blackTurn =  currentGame.insert(2, column, 2, blackTurn);
                        break;
                    default:
                        break;
            }}else if(currentGame.isClear(1, column)){
            switch(column){
                case 1:
                    b11.animate().translationYBy(250f).setDuration(1000);
                    blackTurn = currentGame.insert(1, column, 2, blackTurn);
                    break;
                case 2:
                    b12.animate().translationYBy(250f).setDuration(1000);
                    blackTurn =  currentGame.insert(1, column, 2, blackTurn);
                    break;
                case 3:
                    b13.animate().translationYBy(250f).setDuration(1000);
                    blackTurn = currentGame.insert(1, column, 2, blackTurn);
                    break;
                default:
                    break;
        }} else{
            Toast.makeText(this, "Please Choose a valid column", Toast.LENGTH_SHORT).show();
        }
        if(currentGame.isWinner()){
            freeze = currentGame.freeze();
            resetButton.animate().translationYBy(250f).setDuration(1000);
            if(blackTurn)
                gameStatus.setText("Congratulations Red Team, You Win!  Click reset to play again!");
            else
                gameStatus.setText("Congratulations Black Team, You Win!  Click reset to play again!");
            gameStatus.setAlpha(1f);
        }else if (currentGame.isFull()){
            freeze = currentGame.freeze();
            resetButton.animate().translationYBy(250f).setDuration(1000);
            gameStatus.setText("The board is full, click reset to play again!");
            gameStatus.setAlpha(1f);
        }

    }}


    public void clickColumn1(View v) {
        if(blackTurn)
            insertBlack(1);
        else
            insertRed(1);
    }


    public void clickColumn2(View v){
        if(blackTurn)
            insertBlack(2);
        else
            insertRed(2);
    }


    public void clickColumn3(View v){
        if(blackTurn)
            insertBlack(3);
        else
            insertRed(3);
    }


    public void clickResetBoard(View v){
        resetBoard();
    }

    public void resetBoard(){
        ImageView b11 = (ImageView) findViewById(R.id.b11);
        ImageView b12 = (ImageView) findViewById(R.id.b12);
        ImageView b13 = (ImageView) findViewById(R.id.b13);
        ImageView b21 = (ImageView) findViewById(R.id.b21);
        ImageView b22 = (ImageView) findViewById(R.id.b22);
        ImageView b23 = (ImageView) findViewById(R.id.b23);
        ImageView b31 = (ImageView) findViewById(R.id.b31);
        ImageView b32 = (ImageView) findViewById(R.id.b32);
        ImageView b33 = (ImageView) findViewById(R.id.b33);
        ImageView r11 = (ImageView) findViewById(R.id.r11);
        ImageView r12 = (ImageView) findViewById(R.id.r12);
        ImageView r13 = (ImageView) findViewById(R.id.r13);
        ImageView r21 = (ImageView) findViewById(R.id.r21);
        ImageView r22 = (ImageView) findViewById(R.id.r22);
        ImageView r23 = (ImageView) findViewById(R.id.r23);
        ImageView r31 = (ImageView) findViewById(R.id.r31);
        ImageView r32 = (ImageView) findViewById(R.id.r32);
        ImageView r33 = (ImageView) findViewById(R.id.r33);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        TextView gameStatus = (TextView) findViewById(R.id.gameStatus);
        b11.setTranslationY(-250f);
        b12.setTranslationY(-250f);
        b13.setTranslationY(-250f);
        b21.setTranslationY(-250f);
        b22.setTranslationY(-250f);
        b23.setTranslationY(-250f);
        b31.setTranslationY(-250f);
        b32.setTranslationY(-250f);
        b33.setTranslationY(-250f);
        r11.setTranslationY(-250f);
        r12.setTranslationY(-250f);
        r13.setTranslationY(-250f);
        r21.setTranslationY(-250f);
        r22.setTranslationY(-250f);
        r23.setTranslationY(-250f);
        r31.setTranslationY(-250f);
        r32.setTranslationY(-250f);
        r33.setTranslationY(-250f);
        resetButton.setTranslationY(-250f);
        currentGame.reset();
        freeze = false;
        gameStatus.setAlpha(0f);

    }

    Connect3 currentGame = new Connect3();
    boolean freeze = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetBoard();
        firstTurn();


    }
}
