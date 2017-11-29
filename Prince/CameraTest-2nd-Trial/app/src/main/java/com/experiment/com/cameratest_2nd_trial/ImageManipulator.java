package com.experiment.com.cameratest_2nd_trial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by adaptive on 9/12/17.
 */

public class ImageManipulator {

    public static void resizeImageTo(String path, int width, int height, int x, int y ){

        Bitmap originalImage = BitmapFactory.decodeFile(path);

        Bitmap resized = Bitmap.createScaledBitmap(originalImage,width,height,true);

        Bitmap cropped = Bitmap.createBitmap(resized, x, y, 320, 320);

        File resizedImage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                "CRP_"+System.currentTimeMillis()+".webp");
        try {
            FileOutputStream out = new FileOutputStream(resizedImage);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            cropped.compress(Bitmap.CompressFormat.JPEG , 100 , baos);
            out.write(baos.toByteArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cropBitmap(Bitmap src, int cropWidth, int cropHeight, int x, int y){

        Bitmap cropped = Bitmap.createBitmap(src, x, y, cropWidth, cropHeight);
        try {
            FileOutputStream out = new FileOutputStream(new File(Environment.getExternalStorageDirectory()+"/CRP-N.jpg"));
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            cropped.compress(Bitmap.CompressFormat.JPEG , 100 , out);
//            out.write(baos.toByteArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
