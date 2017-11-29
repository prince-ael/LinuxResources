package com.networkingexample.android.activitylifecycle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView activityStateDisplay;
    private static int STATE_COUNTER = 0;

    private AlertDialog.Builder aBuilder;
    private static final String PRESERVED_STATE = "preserved_state";

    static final String MSG_KEY = "activity_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityStateDisplay = (TextView) findViewById(R.id.tv_activity_state);

        if(savedInstanceState != null){

            if(savedInstanceState.containsKey(PRESERVED_STATE)){

                activityStateDisplay.setText(savedInstanceState.getString(PRESERVED_STATE));
            }
        }

        activityStateDisplay.append("\n\n"+STATE_COUNTER+". "+getString(R.string.onCreateText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onCreateText));

        STATE_COUNTER++;

        aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Focus Gainer--Component");
        aBuilder.setMessage("Check Log Or TextView For Knowing The Current State");
        aBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        activityStateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBuilder.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        activityStateDisplay.append("\n\n"+STATE_COUNTER+" "+getString(R.string.onStartText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onStartText));
        STATE_COUNTER ++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityStateDisplay.append("\n\n"+STATE_COUNTER+" "+getString(R.string.onResumeText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onResumeText));
        STATE_COUNTER ++;
    }

    @Override
    protected void onPause() {
        super.onPause();

        activityStateDisplay.append("\n\n"+STATE_COUNTER+" "+getString(R.string.onPauseText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onPauseText));
        STATE_COUNTER ++;
    }

    @Override
    protected void onStop() {
        super.onStop();

        activityStateDisplay.append("\n\n"+STATE_COUNTER+" "+getString(R.string.onStopText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onStopText));
        STATE_COUNTER ++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        activityStateDisplay.append("\n\n"+STATE_COUNTER+" "+getString(R.string.onDestroyText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onDestroyText));
        STATE_COUNTER = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        activityStateDisplay.append("\n\n"+STATE_COUNTER+" "+getString(R.string.onRestartText));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.onRestartText));
        STATE_COUNTER ++;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        activityStateDisplay.append("\n\n\n"+" "+getString(R.string.instanceState));
        Log.d("State Log"," "+STATE_COUNTER+". "+getString(R.string.instanceState));
        STATE_COUNTER = 0;

        String allStates = activityStateDisplay.getText().toString();
        outState.putString(PRESERVED_STATE, allStates);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.next){
            startActivity(new Intent(MainActivity.this , TestActivity.class).putExtra(MSG_KEY , "Hello From Main Activity"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
