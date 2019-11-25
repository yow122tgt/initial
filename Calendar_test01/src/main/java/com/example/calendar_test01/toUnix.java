package com.example.calendar_test01;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class toUnix {
    public long epoch;

    @SuppressLint("SimpleDateFormat")
    public toUnix(String s)
    {
        {
            try {
                epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
}

}
