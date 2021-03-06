package com.example.christina.healthgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class easyFiveAfrica extends Activity implements SensorEventListener{

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    public ImageView egyptImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_five_africa);

        count = (TextView) findViewById(R.id.stepView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        egyptImage = (ImageView) findViewById(R.id.egyptImage);

        Drawable temp = getResources().getDrawable(R.drawable.egypt);
        egyptImage.setImageDrawable(temp);


    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            count.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
