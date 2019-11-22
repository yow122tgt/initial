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
            public Date_Event(String 藥單日期 , int 要吃幾天,int 幾天一次,int 是否吃藥[ ]) throws Exception {


                dayday = new String[要吃幾天];
                DEE= new String[要吃幾天];
                BB = new int[要吃幾天];

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
                String str=藥單日期;
                Date dt=sdf.parse(str);
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(dt);
                //   rightNow.add(Calendar.YEAR,-1);//日期減1年
                //   rightNow.add(Calendar.MONTH,3);//日期加3個月
                for(int d=0;d<要吃幾天;d++){
                    Date dt1=rightNow.getTime();
                    reStr = sdf.format(dt1);
                    dayday[d]=reStr.toString();

                    date = sdf.parse(dayday[d]);
                    DEE[d] = date.toString();

                    BB[d]=是否吃藥[d];

                 //   System.out.println(dayday[d]);
                    rightNow.add(Calendar.DAY_OF_YEAR,幾天一次);//日期加10天
                }

               // System.out.println(dayday.length);
            }
    }

