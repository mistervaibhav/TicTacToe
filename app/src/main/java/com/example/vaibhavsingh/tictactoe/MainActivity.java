package com.example.vaibhavsingh.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // 0 = cross , 1 = dot //
    int activePlayer = 0 ;
    // 0 = player 1 , 1 = player 2 //

    boolean gameIsActive = true ;

    // 2 means empty slot //
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer ;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.dot);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions)
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {


                    // someone has won the game //

                    gameIsActive = false;

                    String winner = "Player 2";

                    if (gameState[winningPosition[0]] == 0) {

                        winner = "Player 1";
                    }

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won ");


                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver) {

                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }

        }


    }

    public void playAgain(View view){


        gameIsActive = true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0 ;

        for(int i=0 ; i < gameState.length ; i++){

            gameState[i]=2;
        }

        // GridLayout gridLayout = findViewById(R.id.gridLayout);// this line was causing a problem the line below solved it..//
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        for (int j =0; j < gridLayout.getChildCount(); j++){

            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
