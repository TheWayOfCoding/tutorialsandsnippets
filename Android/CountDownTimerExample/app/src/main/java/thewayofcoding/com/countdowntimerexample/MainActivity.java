package thewayofcoding.com.countdowntimerexample;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/*
By: Scott Waldron
TheWayOfCoding.com

- All resources retain copyright (bitmaps, etc).
- You are not allowed to use these projects 
as tutorials or samples distributed with your own branding.
Basically, any use with the same theme as they were 
intended for with TheWayOfCoding.com is not allowed.

The MIT License (MIT)

Copyright (c) 2016 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
public class MainActivity extends Activity {
    Spinner timerValueSpinner;
    Button startTimerButton;
    Button stopTimerButton;
    TextView statusTextView;
    SimpleCountdownTimer timer;
    String[] timeValues;
    Resources resourcePointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerValueSpinner = (Spinner)this.findViewById(R.id.secondsSpinner);
        statusTextView = (TextView)this.findViewById(R.id.timerView);

        resourcePointer = getResources();
        timeValues = resourcePointer.getStringArray(R.array.seconds_list);

        //the button that will start the timer
        startTimerButton = (Button)this.findViewById(R.id.startButton);
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerValueSpinner.getSelectedItemPosition() > -1) {
                    int parsedSpinnerValue = 0;
                    parsedSpinnerValue = Integer.parseInt(
                            timeValues[timerValueSpinner.getSelectedItemPosition()]);

                    if (parsedSpinnerValue > 0) {
                        //if there is a timer already, end that before making a new one
                        if(timer != null) {
                            timer.cancel();
                        }

                        //initialize a new timer instance
                        timer = new SimpleCountdownTimer(parsedSpinnerValue
                                * SimpleCountdownTimer.oneSecond,
                                SimpleCountdownTimer.oneSecond,
                                statusTextView);

                        timer.start();
                    }
                }
            }
        });

        //the button that will stop the timer
        stopTimerButton = (Button)this.findViewById(R.id.stopButton);
        stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timer != null) {
                    timer.cancel();
                }
            }
        });
    }
}
