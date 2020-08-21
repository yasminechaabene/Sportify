package com.chaabene;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaabene.domain.CurrentState;
import com.chaabene.domain.TrainingHelper;


public class MainActivity extends Activity {

    private CurrentState currentState;
    private int SELECT_LEVEL = 111;

    TextView descriptionText;
    RelativeLayout statusLayout;
    TextView startDateText;
    TextView levelText;
    TextView lastWorkoutText;
    TextView nextWorkoutText;
    Button startButton;
    Button startNewButton;
    Button continueButton;
    Button progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionText = (TextView)findViewById(R.id.description_text);
        startDateText = (TextView)findViewById(R.id.start_date_text);
        levelText = (TextView)findViewById(R.id.level_text);
        lastWorkoutText = (TextView)findViewById(R.id.last_workout_text);
        nextWorkoutText = (TextView)findViewById(R.id.next_workout_text);
        statusLayout = (RelativeLayout)findViewById(R.id.status_layout);
        startButton = (Button)findViewById(R.id.start_button);

        startNewButton = (Button)findViewById(R.id.start_new_button);
        continueButton = (Button)findViewById(R.id.continue_button);
        progressButton = (Button)findViewById(R.id.progress_button);
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.good);
       // progressButton.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View v) {
          //      mp.start();
          //  }
      //  });
    }

    @Override
    protected void onResume() {
        super.onResume();

        currentState = TrainingHelper.getCurrentState(this);

        if(currentState.level == 0){
            statusLayout.setVisibility(View.GONE);
            startButton.setVisibility(View.VISIBLE);
            startNewButton.setVisibility(View.GONE);
            continueButton.setVisibility(View.GONE);
            progressButton.setVisibility(View.GONE);
            descriptionText.setText(getString(com.chaabene.R.string.you_have_no_plan));
        }else{
            statusLayout.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.GONE);
            startNewButton.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.VISIBLE);
            progressButton.setVisibility(View.VISIBLE);

            levelText.setText(TrainingHelper.getLevelDescription(currentState.level));
            startDateText.setText(TrainingHelper.getDateString(currentState.startTime));

            if(currentState.lastWorkoutWeek > 0)
                lastWorkoutText.setText(TrainingHelper.getWorkoutDescription(currentState.lastWorkoutWeek, currentState.lastWorkoutDay, currentState.level));
            else
                lastWorkoutText.setText(getString(com.chaabene.R.string.none));

            if(currentState.nextWorkoutWeek > 0)
                nextWorkoutText.setText(TrainingHelper.getWorkoutDescription(currentState.nextWorkoutWeek, currentState.nextWorkoutDay, currentState.level));
            else
                nextWorkoutText.setText(getString(com.chaabene.R.string.none));

            if(currentState.isFinished == true)
            {
                descriptionText.setText(getString(com.chaabene.R.string.you_finished_plan));
                startButton.setVisibility(View.VISIBLE);
                startNewButton.setVisibility(View.GONE);
                continueButton.setVisibility(View.GONE);
                nextWorkoutText.setText(getString(com.chaabene.R.string.none));
            }else{
                descriptionText.setText(getString(com.chaabene.R.string.current_plan_details));
            }
        }
    }

    public void onStartClick(View v){
        Intent intent = new Intent(this, LevelActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivityForResult(intent, SELECT_LEVEL);
    }

    public void onStartNewClick(final View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getString(R.string.start_new));
        builder.setMessage(this.getString(R.string.start_new_question));
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onStartClick(v);
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onContinueClick(View v){
        Intent intent = new Intent(this, WorkoutActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void onProgressClick(View v){
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

    public void onInstructionsClick(View v){
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivityForResult(intent, SELECT_LEVEL);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == SELECT_LEVEL){
                Bundle extras = data.getExtras();
                int level = extras.getInt("level",0);
                if(level > 0){
                    currentState = new CurrentState();
                    currentState.startTime = TrainingHelper.getCurrentTime();
                    currentState.level = level;
                    currentState.nextWorkoutWeek = 1;
                    currentState.nextWorkoutDay = 1;
                    TrainingHelper.saveCurrentState(this, currentState);
                    //Intent intent = new Intent(this, WorkoutActivity.class);
                    //intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    //startActivity(intent);
                    onResume();
                }
            }
        }
    }
}
