package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView pointATV;
    private TextView pointBTV;

    private Button teamA3points;
    private Button teamA2points;
    private Button teamAfreeThow;

    private Button teamB3points;
    private Button teamB2points;
    private Button teamBfreeThow;

    private Button resetBtn;

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();
    }

    private void initViews(){

        pointATV = (TextView) findViewById(R.id.pointsA);

        teamA2points = (Button) findViewById(R.id.two_pts);
        teamA3points = (Button) findViewById(R.id.thr_pts);
        teamAfreeThow = (Button) findViewById(R.id.free_throw);

        pointBTV = (TextView) findViewById(R.id.pointsB);

        teamB2points = (Button) findViewById(R.id.two_pts_B);
        teamB3points = (Button) findViewById(R.id.thr_pts_B);
        teamBfreeThow = (Button) findViewById(R.id.free_throw_B);

        resetBtn = (Button) findViewById(R.id.resetBtn);

    }

    private void initActions(){

        pointATV.setText(""+scoreTeamA);
        teamA2points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamA += 2;
                pointATV.setText(""+scoreTeamA);
            }
        });

        teamA3points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamA += 3;
                pointATV.setText(""+scoreTeamA);
            }
        });
        teamAfreeThow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamA += 1;
                pointATV.setText(""+scoreTeamA);
            }
        });
//=========================Team B==========================================

        pointBTV.setText(""+scoreTeamB);
        teamB2points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamB += 2;
                pointBTV.setText(""+scoreTeamB);
            }
        });

        teamB3points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamB += 3;
                pointBTV.setText(""+scoreTeamB);
            }
        });

        teamBfreeThow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamB += 1;
                pointBTV.setText(""+scoreTeamB);
            }
        });
 //======================Reset Button======================================

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scoreTeamA = 0;
                scoreTeamB = 0;

                pointATV.setText(""+scoreTeamA);
                pointBTV.setText(""+scoreTeamB);
            }
        });
    }
}
