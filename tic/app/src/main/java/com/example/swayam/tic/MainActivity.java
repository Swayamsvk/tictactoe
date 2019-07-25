package com.example.swayam.tic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button[][] buttons=new Button[3][3];
    private boolean player1Turn=true;
    private int roundcount;

    private int player1points;
    private int player2points;

    private TextView textViewplayer1;
    private TextView textViewplayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewplayer1=findViewById(R.id.p1);
        textViewplayer2=findViewById(R.id.p2);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonid="b"+i+j;
                int resid=getResources().getIdentifier(buttonid,"id",getPackageName());
                buttons[i][j]=findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player1Turn){
            ((Button)v).setText("X");

        }
        else{
            ((Button)v).setText("O");
        }
        roundcount++;
        if(checkforwin()){
            if(player1Turn)
            {player1wins();}

            else
            {
                player2wins();
            }
        }
        else if(roundcount==9)
        {draw();}
        else
        {
            player1Turn=!player1Turn;
        }


    }
    private boolean checkforwin()
    {
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++)
        {for(int j=0;j<3;j++)
        {
            field[i][j]=buttons[i][j].getText().toString();
        }

        }
        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!field[0][i].equals(""))
            {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&!field[0][0].equals(""))
        {
            return true;
        }
        if(field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&&!field[0][2].equals(""))
        {
            return true;
        }
        return false;
    }
    private void player1wins(){player1points++;
        Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
    updatepointstext();
    resetboard();
        if(player1points==3)
        { Toast.makeText(this,"player 1 is the winner",Toast.LENGTH_LONG).show();
        resetgame();



        }
        else{
            Toast.makeText(this,"continue",Toast.LENGTH_SHORT).show();


        }
    }
    private void player2wins(){player2points++;
        Toast.makeText(this,"Player 2 wins",Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
        if(player2points==3)
        { Toast.makeText(this,"player 2 is the winner",Toast.LENGTH_LONG).show();
        resetgame();



        }
        else{
            Toast.makeText(this,"continue",Toast.LENGTH_SHORT).show();


        }
    }
    private void draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetboard();
    }
    private void updatepointstext()
    {
        textViewplayer1.setText("Player 1:"+player1points);

        textViewplayer2.setText("Player 2:"+player2points);



    }
    private void resetboard()
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1Turn=true;
    }
    private void resetgame(){
        player1points=0;
        player2points=0;
        updatepointstext();
        resetboard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundcount",roundcount);
        outState.putInt("player 1 points",player1points);
        outState.putInt("player 2 points",player2points);
        outState.putBoolean("player 1 turn",player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundcount=savedInstanceState.getInt("roundcount");
        player1points=savedInstanceState.getInt("player1points");
        player2points=savedInstanceState.getInt("player2points");
        player1Turn=savedInstanceState.getBoolean("player1Turn");

    }

}
