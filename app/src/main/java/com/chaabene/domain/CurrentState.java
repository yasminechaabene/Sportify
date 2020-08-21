package com.chaabene.domain;

import java.util.ArrayList;
import java.util.List;


public class CurrentState {
    public Boolean isFinished;
    public long startTime;
    public int level;
    public int lastWorkoutWeek;
    public int lastWorkoutDay;
    public long lastWorkoutCompletionTime;
    public int nextWorkoutWeek;
    public int nextWorkoutDay;

    public List<HistoryRecord> historyRecords;

    public CurrentState(){
        historyRecords = new ArrayList<HistoryRecord>();
        isFinished = false;
    }
}
