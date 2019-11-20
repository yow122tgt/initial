package com.example.calendar_test01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public Date date;


    public DateFormat(String s) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        date = sdf.parse(s);
    }



}
