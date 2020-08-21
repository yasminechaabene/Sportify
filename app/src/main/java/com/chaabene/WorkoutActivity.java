package com.chaabene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chaabene.domain.CountDownTimerWithPause;
import com.chaabene.domain.CurrentState;
import com.chaabene.domain.HistoryRecord;
import com.chaabene.domain.TrainingHelper;
import com.chaabene.domain.Workout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class WorkoutActivity extends Activity {

    CurrentState currentState;
    int week;
    int day;
    int level;
    int restTime;
    int[] counts;
    int currentCount;
    int countdownState = 1; // 1 - in progress 2 - countdown running 3 - countdown paused
    CountDownTimerWithPause countDownTimer;
    ProgressBar progressBar;
    TextView timerText;
    TextView countText;
    Button doneButton;
    TextView descriptionText;
    TextView instructionsText;
    LinearLayout countsList;
    LayoutInflater inflater;
    ImageView pushupsImage;
    ImageView squatsImage;
    ImageView situpsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        countdownState = 1;

        currentState = TrainingHelper.getCurrentState(this);
        level = currentState.level;
        if(level == 0)
            finish();

        inflater = LayoutInflater.from(this);

        descriptionText = (TextView)findViewById(R.id.w_description_label);
        instructionsText = (TextView)findViewById(R.id.w_instructions_label);
        doneButton = (Button)findViewById(R.id.w_done_button);

        countText = (TextView)findViewById(R.id.w_count);
        timerText = (TextView)findViewById(R.id.w_timer);
        progressBar = (ProgressBar)findViewById(R.id.w_timer_progress);
        countsList = (LinearLayout)findViewById(R.id.w_count_list);

        pushupsImage = (ImageView)findViewById(R.id.w_pushups);
        squatsImage = (ImageView)findViewById(R.id.w_squats);
        situpsImage = (ImageView)findViewById(R.id.w_situps);

        week = currentState.nextWorkoutWeek;
        day = currentState.nextWorkoutDay;

        Workout w = TrainingHelper.getWorkout(TrainingHelper.getWorkoutKey(week, day));
        counts = TrainingHelper.getCounts(w, level);
        restTime = w.restTime;

        descriptionText.setText(String.format("Week %d/Day %d/%s",week,day,TrainingHelper.getLevelDescription(level)));

        startCount();

        // Ads
        AdView mAdView = (AdView)findViewById(R.id.adView);
        //mAdView.setVisibility(View.GONE);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("87CFE888ED65416569041ABF6FCDB9B6") // my
                .build();
        mAdView.loadAd(adRequest);
    }

    public void onDoneClick(View v){
        if(countdownState == 1)
        {
            if(currentCount >= (counts.length - 1))
            {
                long milis = TrainingHelper.getCurrentTime();
                currentState = TrainingHelper.getCurrentState(this);
                currentState.lastWorkoutDay = day;
                currentState.lastWorkoutWeek = week;
                currentState.lastWorkoutCompletionTime = milis;

                Workout nextWorkout = TrainingHelper.getNextWorkout(week, day);
                if(nextWorkout == null)
                    currentState.isFinished = true;
                else{
                    currentState.nextWorkoutWeek = nextWorkout.week;
                    currentState.nextWorkoutDay = nextWorkout.day;
                }

                HistoryRecord hr = new HistoryRecord();
                hr.counts = counts;
                hr.day = day;
                hr.week = week;
                hr.level = level;
                hr.completionTime = milis;
                currentState.historyRecords.add(hr);
                TrainingHelper.saveCurrentState(this, currentState);

                Intent intent = new Intent(this, CongratulationsActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }else{
                startTimer(restTime);
            }
        }
        else if(countdownState == 2){
            countdownState = 3;
            countDownTimer.pause();
            doneButton.setText(getString(R.string.resume));
        }
        else if(countdownState == 3){
            countdownState = 2;
            countDownTimer.resume();
            doneButton.setText(getString(R.string.pause));
        }
    }

    private void startCount(){
        countdownState = 1;
        countText.setVisibility(View.VISIBLE);
        doneButton.setVisibility(View.VISIBLE);
        doneButton.setText(getString(com.chaabene.R.string.done));
        progressBar.setVisibility(View.GONE);
        timerText.setVisibility(View.GONE);

        pushupsImage.setVisibility(View.VISIBLE);
        squatsImage.setVisibility(View.VISIBLE);
        situpsImage.setVisibility(View.VISIBLE);

        String repetitionsCount = String.valueOf(counts[currentCount]);
        countText.setText(repetitionsCount);
        instructionsText.setText(String.format(getString(com.chaabene.R.string.workout_instructions), repetitionsCount, repetitionsCount, repetitionsCount));
        createCountsView();
    }

    private void createCountsView() {
        countsList.removeAllViews();

        for(int i = 0; i<counts.length; i++){
            int res = currentCount == i ? R.layout.workout_count_current : R.layout.workout_count;
            View row = inflater.inflate(res,null);
            TextView count = (TextView)row.findViewById(R.id.w_count_value);
            count.setText(String.valueOf(counts[i]));

            countsList.addView(row);
        }
    }

    private void startTimer(final int sec) {
        countdownState = 2;
        countText.setVisibility(View.GONE);
        //doneButton.setVisibility(View.GONE);
        doneButton.setText(getString(com.chaabene.R.string.pause));
        progressBar.setVisibility(View.VISIBLE);
        timerText.setVisibility(View.VISIBLE);
        timerText.setText(String.valueOf(sec));

        pushupsImage.setVisibility(View.GONE);
        squatsImage.setVisibility(View.GONE);
        situpsImage.setVisibility(View.GONE);

        instructionsText.setText(getString(com.chaabene.R.string.take_break_instructions));
        progressBar.setMax(sec);
        progressBar.setProgress(progressBar.getMax());
        countDownTimer = new CountDownTimerWithPause(sec * 1000, 1000, true) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                int seconds = (int)leftTimeInMilliseconds / 1000;
                progressBar.setProgress(seconds);
                timerText.setText(String.valueOf(seconds));
            }
            @Override
            public void onFinish() {
                currentCount ++;
                startCount();
            }
        };

        countDownTimer.create();
    }
}
