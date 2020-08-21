package com.chaabene.domain;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TrainingHelper {
    public static Workout[] workouts = new Workout[]
            {
                    // week 1
                    // day 1
                    new Workout() {{
                        week = 1;
                        day = 1;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{2,3,2,2,3};
                        //average
                        counts2 = new int[]{6,6,4,4,5};
                        //hard
                        counts3 = new int[]{10,12,7,7,9};
                    }},
                    // day 2
                    new Workout() {{
                        week = 1;
                        day = 2;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{3,4,2,3,4};
                        //average
                        counts2 = new int[]{6,8,6,6,7};
                        //hard
                        counts3 = new int[]{10,12,8,8,12};
                    }},
                    // day 3
                    new Workout() {{
                        week = 1;
                        day = 3;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{4,5,4,4,5};
                        //average
                        counts2 = new int[]{8,19,7,7,5};
                        //hard
                        counts3 = new int[]{11,15,9,9,13};
                    }},
                    // week 2
                    // day 1
                    new Workout() {{
                        week = 2;
                        day = 1;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{4,6,4,4,6};
                        //average
                        counts2 = new int[]{9,11,8,8,11};
                        //hard
                        counts3 = new int[]{14,14,10,10,15};
                    }},
                    // day 2
                    new Workout() {{
                        week = 2;
                        day = 2;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{5,6,4,4,7};
                        //average
                        counts2 = new int[]{10,12,9,9,13};
                        //hard
                        counts3 = new int[]{14,16,12,12,17};
                    }},
                    // day 3
                    new Workout() {{
                        week = 2;
                        day = 3;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{5,7,5,5,8};
                        //average
                        counts2 = new int[]{12,13,10,10,15};
                        //hard
                        counts3 = new int[]{16,17,14,14,17};
                    }},
                    // week 3
                    // day 1
                    new Workout() {{
                        week = 3;
                        day = 1;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{10,12,7,7,9};
                        //average
                        counts2 = new int[]{12,17,13,13,17};
                        //hard
                        counts3 = new int[]{14,18,14,14,20};
                    }},
                    // day 2
                    new Workout() {{
                        week = 3;
                        day = 2;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{10,12,8,8,12};
                        //average
                        counts2 = new int[]{14,19,14,14,19};
                        //hard
                        counts3 = new int[]{20,25,15,15,25};
                    }},
                    // day 3
                    new Workout() {{
                        week = 3;
                        day = 3;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{11,13,9,9,13};
                        //average
                        counts2 = new int[]{16,21,15,15,21};
                        //hard
                        counts3 = new int[]{22,30,20,20,28};
                    }},
                    //week 4
                    // day 1
                    new Workout() {{
                        week = 4;
                        day = 1;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{12,14,11,10,16};
                        //average
                        counts2 = new int[]{18,22,16,16,25};
                        //hard
                        counts3 = new int[]{21,25,21,21,32};
                    }},
                    // day 2
                    new Workout() {{
                        week = 4;
                        day = 2;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{14,16,12,12,18};
                        //average
                        counts2 = new int[]{20,25,20,20,28};
                        //hard
                        counts3 = new int[]{25,29,25,25,36};
                    }},
                    // day 3
                    new Workout() {{
                        week = 4;
                        day = 3;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{16,18,13,13,20};
                        //average
                        counts2 = new int[]{23,28,23,23,17};
                        //hard
                        counts3 = new int[]{29,33,29,29,20};
                    }},
                    //week 5
                    // day 1
                    new Workout() {{
                        week = 5;
                        day = 1;
                        restTime = 15;
                        // easy
                        counts1 = new int[]{17,19,15,15,22};
                        //average
                        counts2 = new int[]{28,35,25,22,35};
                        //hard
                        counts3 = new int[]{36,40,30,24,40};
                    }},
                    // day 2
                    new Workout() {{
                        week = 5;
                        day = 2;
                        restTime = 10;
                        // easy
                        counts1 = new int[]{10,13,10,9,25};
                        //average
                        counts2 = new int[]{18,20,14,16,40};
                        //hard
                        counts3 = new int[]{19,22,18,22,45};
                    }},
                    // day 3
                    new Workout() {{
                        week = 5;
                        day = 3;
                        restTime = 10;
                        // easy
                        counts1 = new int[]{13,15,12,10,30};
                        //average
                        counts2 = new int[]{18,20,17,20,45};
                        //hard
                        counts3 = new int[]{20,24,20,22,50};
                    }},
                    //week 6
                    // day 1
                    new Workout() {{
                        week = 6;
                        day = 1;
                        restTime = 10;
                        // easy
                        counts1 = new int[]{25,30,20,15,40};
                        //average
                        counts2 = new int[]{40,50,25,25,50};
                        //hard
                        counts3 = new int[]{45,55,35,30,55};
                    }},
                    // day 2
                    new Workout() {{
                        week = 6;
                        day = 2;
                        restTime = 10;
                        // easy
                        counts1 = new int[]{14,15,14,10,44};
                        //average
                        counts2 = new int[]{20,23,20,18,53};
                        //hard
                        counts3 = new int[]{22,30,24,18,58};
                    }},
                    // day 3
                    new Workout() {{
                        week = 6;
                        day = 3;
                        restTime = 10;
                        // easy
                        counts1 = new int[]{13,17,16,14,50};
                        //average
                        counts2 = new int[]{22,30,25,18,55};
                        //hard
                        counts3 = new int[]{26,33,26,22,60};
                    }}
            };

    public static Workout getWorkout(int key){
        for(Workout w : workouts){
            if(getWorkoutKey(w) == key)
                return w;
        }

        return null;
    }

    public static int getWorkoutKey(Workout w){
        return getWorkoutKey(w.week,w.day);
    }

    public static int getWorkoutKey(int week, int day){
        return Integer.valueOf(String.valueOf(week)+String.valueOf(day));
    }

    public static Workout getNextWorkout(int week, int day){
        boolean found = false;
        for(int i = 0; i < workouts.length; i ++){
            if(found)
                return workouts[i];
            if(getWorkoutKey(workouts[i]) == getWorkoutKey(week, day))
                found = true;
        }
        return null;
    }
    /*public static int getNextWorkoutKey(int key){
        Workout w = getNextWorkout(key);
        if(w == null)
            return 0;

        return getWorkoutKey(w);
    }*/

    public static String getLevelDescription(int level){
        switch (level){
            case 1:
                return "Easy";
            case 2:
                return "Average";
            case 3:
                return "Above Average";
        }

        return "";
    }

    public static int[] getCounts(Workout w, int level){
        switch (level){
            case 1:
                return w.counts1;
            case 2:
                return w.counts2;
            case 3:
                return w.counts3;
        }

        return null;
    }

    public static CurrentState getCurrentState(Activity context){
        SharedPreferences mPrefs = context.getSharedPreferences("200", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("CurrentState", null);
        if(json == null)
            return new CurrentState();
        CurrentState obj = gson.fromJson(json, CurrentState.class);
        return obj;
    }

    public static void saveCurrentState(Activity context, CurrentState currentState){
        SharedPreferences mPrefs = context.getSharedPreferences("200", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String json = null;
        Gson gson = new Gson();
        json = gson.toJson(currentState);
        prefsEditor.putString("CurrentState", json);
        prefsEditor.commit();
    }

    public static long getCurrentTime(){
        Calendar c = Calendar.getInstance();
        return c.getTimeInMillis();
    }

    public static String getWorkoutDescription(int week, int day, int level) {
        Workout w = getWorkout(getWorkoutKey(week, day));
        return String.format("Week %d/Day %d (%s)", w.week, w.day, join(TrainingHelper.getCounts(w, level), ","));
    }

    public static String join(int r[],String d)
    {
        if (r.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        int i;
        for(i=0;i<r.length-1;i++)
            sb.append(String.valueOf(r[i])+d);
        return sb.toString()+ String.valueOf(r[i]);
    }

    public static String getDateString(long milliSeconds)
    {
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }
}