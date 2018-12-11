package com.example.sweater.util;

import java.time.LocalTime;

public class TimeUtil {

    private static LocalTime voteFinishTime = LocalTime.of(11, 0);

    public static LocalTime getVoteFinishTime() {
        return voteFinishTime;
    }

    //for VoteService tests
    public static void setVoteFinishTime(LocalTime time){
        voteFinishTime = time;
    }
}
