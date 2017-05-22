package com.example.mehadihossain.z_ridedemo.util;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mehadi Hossain on 5/22/2017.
 */

public class CalculateWorkingDay {
    /*public CalculateWorkingDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.MARCH);
        //int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //System.out.println("Month:"+cal.get(Calendar.MONTH)+"Days:"+days);
        int th = 0;
        int maxDayInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int d = 1; d <= maxDayInMonth; d++) {
            cal.set(Calendar.DAY_OF_MONTH, d);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (Calendar.THURSDAY == dayOfWeek) {
                th++;
            }
        }
        System.out.println("Thursday:"+th);
    }*/


    public ArrayList<String> getDaysOfMonth(int month){

        //calculate total working day in month
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month-1);
        int th = 0;
        int maxDayInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int d = 1; d <= maxDayInMonth; d++) {
            cal.set(Calendar.DAY_OF_MONTH, d);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (Calendar.FRIDAY == dayOfWeek) {
                th++;
            }
        }
        int workingDays = maxDayInMonth-th;

        //populated days in array
        ArrayList<String> days = new ArrayList<>();
        for (int i=1;i<=workingDays;i++){
            days.add(Integer.toString(i));
        }
        return days;
    }
}
