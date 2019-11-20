package com.example.calendar_test01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

    public class Date_Event {

        public static Date date;
        public static String reStr;
        public static String dayday[];
        public static String DEE[ ];
        public static int BB[ ];

            //public test02(String s , int day) throws Exception {
            public Date_Event(String s , int num,int day,int buttom[ ]) throws Exception {


                dayday = new String[num];
                DEE= new String[num];
                BB = new int[num];

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
                String str=s;
                Date dt=sdf.parse(str);
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(dt);
                //   rightNow.add(Calendar.YEAR,-1);//日期減1年
                //   rightNow.add(Calendar.MONTH,3);//日期加3個月
                for(int d=0;d<num;d++){
                    Date dt1=rightNow.getTime();
                    reStr = sdf.format(dt1);
                    dayday[d]=reStr.toString();

                    date = sdf.parse(dayday[d]);
                    DEE[d] = date.toString();

                    BB[d]=buttom[d];

                 //   System.out.println(dayday[d]);
                    rightNow.add(Calendar.DAY_OF_YEAR,day);//日期加10天
                }

               // System.out.println(dayday.length);
            }
    }

