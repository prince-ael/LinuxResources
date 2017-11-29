package com.sharingintent.prince.androidsharing;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Intent mShareIntent;

    private static final String MIMETYPE_TEXT = "text/plain";
    private static final String MIMETYPE_IMAGE = "image/jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_share){

            mShareIntent = ShareCompat.IntentBuilder.from(this).setType(MIMETYPE_TEXT).setText(getString(R.string.textToShare)).getIntent();
            handleIntent();
            return true;
        }

        if(item.getItemId() == R.id.action_share_image){

            File imageFile = new File("/storage/sdcard0/DCIM/Camera/IMG_20170526_162035.jpg");
            Uri uriToImage = Uri.fromFile(imageFile);

            mShareIntent = ShareCompat.IntentBuilder.from(this).setStream(uriToImage).setType(MIMETYPE_IMAGE).getIntent();
            handleIntent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleIntent() {

        if (mShareIntent == null) {
            Log.d("MainActivity", "mShareIntent is Null");
            return;
        }

        Intent chooserIntent = Intent.createChooser(mShareIntent , getString(R.string.title));

        if (mShareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooserIntent);
        } else {
            Toast.makeText(this, "No Suitable App To Handle The Intent", Toast.LENGTH_SHORT).show();
        }

    }
}
