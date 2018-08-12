package io.samleo.zennerv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CanvasActivity extends AppCompatActivity {

    private CanvasView mainCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        mainCanvas = (CanvasView) findViewById(R.id.main_canvas);
    }


}
