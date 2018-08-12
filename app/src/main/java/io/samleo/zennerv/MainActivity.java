package io.samleo.zennerv;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.widget.Button;

import android.view.View;
import android.widget.ImageView;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private FloatingActionButton cameraButton;
    private ImageView imageDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageDisplay = (ImageView) this.findViewById(R.id.image_display);
        this.cameraButton = (FloatingActionButton) this.findViewById(R.id.camera_button);
        this.cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        if (requestCode == CAMERA_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //Save the photo and export as JPG

            this.imageDisplay.setImageBitmap(photo);

            BitmapToJPG(photo);
        }
    }

    public File BitmapToJPG(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        String DTG = String.valueOf(System.currentTimeMillis());
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, DTG + ".jpg");

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}