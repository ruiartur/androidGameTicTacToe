package com.ruiartur.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiartur.tictactoe.R.id.resultLayout;

public class MainActivity extends AppCompatActivity {

    //Variable that informs active play 0 is X and 1 is O
    private int activePlayer =0;
    //Array to store witch position already been played
    private int playedPositions[] = {2,2,2,2,2,2,2,2,2};

    private int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    private boolean gameIsActive = true;

    @BindView(R.id.resultLayout)
    LinearLayout resultLayout;

    @BindView(R.id.winnerPlayer)
    ImageView winnerImage ;

    @BindView(R.id.textView)
    TextView textResult;

    @BindView(R.id.gridLayout)
    GridLayout gridLayout;

    @BindView(R.id.displayPlayer)
    LinearLayout currentPlayerLayout;

    @BindView(R.id.playerTurn)
    ImageView currentPlayerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
    }


    public void play(View view){

        ImageView imageView = (ImageView) view;


        int tagPostition = Integer.parseInt(imageView.getTag().toString());

        if(playedPositions[tagPostition]==2 && gameIsActive) {
            if (activePlayer == 0) {
                currentPlayerLayout.setVisibility(View.VISIBLE);
                currentPlayerImage.setImageResource(R.drawable.oplayer);
                currentPlayerImage.animate().alpha(1).setDuration(1500);
                imageView.setImageResource(R.drawable.xplayer);
                playedPositions[tagPostition] = 0;
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.oplayer);
                currentPlayerImage.setImageResource(R.drawable.xplayer);
                currentPlayerImage.animate().alpha(1).setDuration(1500);
                playedPositions[tagPostition] = 1;
                activePlayer = 0;
            }

            LinearLayout resultLayout = (LinearLayout) findViewById(R.id.resultLayout);
            for(int[] winPos : winningPositions){
                if(playedPositions[winPos[0]]== playedPositions[winPos[1]] &&
                        playedPositions[winPos[1]]== playedPositions[winPos[2]] &&
                        playedPositions[winPos[2]]!= 2){

                    resultLayout.setVisibility(View.VISIBLE);
                    textResult.setText("The Winner is ");
                    if(playedPositions[winPos[0]]==0){
                        winnerImage.setImageResource(R.drawable.xplayer);
                    }else{
                        winnerImage.setImageResource(R.drawable.oplayer);
                    }
                    currentPlayerLayout.setVisibility(View.GONE);
                    gameIsActive = false;
                }
            }
            if(gameIsActive) {
                boolean checkDraw = true;
                for (int i = 0; i < playedPositions.length; i++) {
                    if (playedPositions[i] == 2) {
                        checkDraw = false;
                        break;
                    }
                }
                if (checkDraw) {
                    textResult.setText(" It's a Draw!");
                    currentPlayerLayout.setVisibility(View.GONE);
                    resultLayout.setVisibility(View.VISIBLE);
                    winnerImage.setImageResource(0);
                    gameIsActive=false;
                }
            }

        }

    }

    public void playAgain(View view){
        LinearLayout resultLayout = (LinearLayout) findViewById(R.id.resultLayout);
        gameIsActive = true;
        resultLayout.setVisibility(View.GONE);
        activePlayer = 0;

        for(int i =0 ; i < playedPositions.length ; i++){
            playedPositions[i] = 2;
        }

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
}
