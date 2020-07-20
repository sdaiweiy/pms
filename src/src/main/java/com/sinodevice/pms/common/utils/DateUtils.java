package com.sinodevice.pms.common.utils;

import com.sinodevice.pms.core.bean.DatePartVO;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static DatePartVO getDatePart(int type) throws ParseException{
        DatePartVO datePart = new DatePartVO();
        Date now = new Date();
        String end = sdf.format(now);
        String start = "";
        Calendar c = Calendar.getInstance();
        c.setTime(now);

        switch (type){
            case 1:
                int day = c.get(Calendar.DAY_OF_WEEK);
                c.add(Calendar.DATE,-((day==1?8:day)-2));
                start = sdf.format(c.getTime());
                break;
            case 2:
                c.add(Calendar.DATE,-(c.get(Calendar.DATE)-1));
                start = sdf.format(c.getTime());
                break;
            case 3:
                c.add(Calendar.DATE,-(c.get(Calendar.DAY_OF_YEAR)-1));
                start = sdf.format(c.getTime());
                break;
            case 4:
                start = "";
                break;
        }
        datePart.setStart(start);
        datePart.setEnd(end);
        return datePart;
    }

    public static List<String> getDateList(String start_str,String end_str) throws ParseException{
        List<String> dateList = new ArrayList<>();
        if(StringUtils.isNotEmpty(start_str)){
            Date start = sdf.parse(start_str);
            Date end = sdf.parse(end_str);
            Calendar c = Calendar.getInstance();
            c.setTime(start);
            while (c.getTime().getTime()<=end.getTime()){
                dateList.add(sdf.format(c.getTime()));
                c.add(Calendar.DATE,1);
            }
        }
        return dateList;
    }

    public static List<DatePartVO> getMonthListOneYearByEnd(String date_str) throws ParseException{
        List<DatePartVO> dateList = new ArrayList<>();
        Date date = sdf.parse(date_str);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,-(c.get(Calendar.DATE)-1));
        dateList.add(new DatePartVO(sdf.format(c.getTime()),date_str));
        for(int i=1;i<12;i++){
            c.add(Calendar.DATE,-1);
            String end = sdf.format(c.getTime());
            c.add(Calendar.DATE,-(c.get(Calendar.DATE)-1));
            dateList.add(new DatePartVO(sdf.format(c.getTime()),end));
        }
        Collections.reverse(dateList);
        return dateList;
    }

    public static List<DatePartVO> getMonthListByStartToNow(String date_str) throws ParseException{
        List<DatePartVO> dateList = new ArrayList<>();
        Date date = sdf.parse(date_str);
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        do {
            c.add(Calendar.DATE,-(c.get(Calendar.DATE)-1));
            String start = sdf.format(c.getTime());
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            String end = sdf.format(c.getTime());
            dateList.add(new DatePartVO(start,end));
            c.add(Calendar.DATE,1);
        }while(c.getTime().getTime()<=now.getTime());
        return dateList;
    }
}
