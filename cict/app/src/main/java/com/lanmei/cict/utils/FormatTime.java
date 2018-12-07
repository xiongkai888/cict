package com.lanmei.cict.utils;

import android.content.Context;

import com.lanmei.cict.R;
import com.xson.common.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class FormatTime {


    private long time;
    private boolean is12Hour;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat();//默认的时间格式
    private Date date = new Date();
    private Context context;

    public FormatTime(Context context) {
        this.context = context;
        this.time = System.currentTimeMillis();
        setApplyToTimeNoSecond();
    }

    /**
     * @param time 毫秒
     */
    public FormatTime(Context context, long time) {
        this(context);
        this.time = time * 1000;
        calendar.setTimeInMillis(this.time);
    }

    /**
     * @param s 毫秒 String类型
     */
    public FormatTime(Context context, String s) {
        this(context);
        if (StringUtils.isEmpty(s)) {
            s = CommonUtils.isZero;
        }
        this.time = Long.parseLong(s) * 1000;
        calendar.setTimeInMillis(this.time);
    }

    /**
     * @param time 毫秒
     */
    public void setTime(long time) {
        this.time = time * 1000;
        calendar.setTimeInMillis(this.time);

    }

    public String getAllTime() {
        return context.getString(R.string.format_all_time);
    }

    public String getTimeNoSecond() {
        return context.getString(R.string.format_time_no_second);
    }

    public String getTimeYearMonthDay() {
        return context.getString(R.string.format_time_year_month_day);
    }

    /**
     * @param s 毫秒  String 类型
     */
    public void setTime(String s) {
        if (StringUtils.isEmpty(s)) {
            s = CommonUtils.isZero;
        }
        this.time = Long.parseLong(s) * 1000;
        calendar.setTimeInMillis(this.time);

    }

    /**
     * 默认时间格式：时间戳格式为“yyyy-MM-dd HH:mm”
     */
    public String formatterTime() {
        date.setTime(time);
        return format.format(date);
    }
    /**
     * 默认时间格式：时间戳格式为“yyyy-MM-dd HH:mm”
     */
    public String formatterTime(String s) {
        if (StringUtils.isEmpty(s)) {
            s = CommonUtils.isZero;
        }
        this.time = Long.parseLong(s) * 1000;
        calendar.setTimeInMillis(this.time);

        date.setTime(time);
        return format.format(date);
    }

    /**
     * 通过替换 pattern 变换时间格式
     */
    public void setApplyPattern(String applyPattern) {
        format.applyPattern(applyPattern);
    }

    public void setApplyToAllTime() {
        format.applyPattern(getAllTime());
    }

    public void setApplyToTimeNoSecond() {
        format.applyPattern(getTimeNoSecond());
    }

    public void setApplyToTimeYearMonthDay() {
        format.applyPattern(getTimeYearMonthDay());
    }

    /**
     * @param is12Hour 是否12小时
     */
    public void setIs12Hour(boolean is12Hour) {
        this.is12Hour = is12Hour;
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getHour() {
        return calendar.get(is12Hour ? Calendar.HOUR : Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }



    /**
     * 获取一个月前或后的日期
     *
     * @param year
     * @param month
     * @param isNext true下个月 false 上个月
     * @return
     */
    public String[] getMonthAgoOrNext(int year, int month,boolean isNext) {
        String[] strings = null;
        try {
            Date date = new Date(dateToStampLongSub(year + "-" + month)*1000);
            //过去一月
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, isNext?1:-1);
            Date m = calendar.getTime();
            String mon = new SimpleDateFormat("yyyy-MM").format(m);
            strings = mon.split("-");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strings;
    }



    /**
     * 将时间转换为时间戳
     */
    public long dateToStampLong(String s) throws ParseException {
        setApplyToTimeNoSecond();
        Date date = format.parse(s);
        long ts = date.getTime() / 1000;
        return ts;
    }

    /**
     * @param s
     * @throws ParseException
     */
    public long dateToStampLongSub(String s) throws ParseException {
        Date date = format.parse(s);
        long ts = date.getTime() / 1000;
        return ts;
    }


    public String getTimeForWeek() {
        date.setTime(time);
        return format.format(date) + " " + getWeekStr();
    }


    public String getWeekStr() {
        String week = "";
        switch (getWeek()) {
            case 1:
                week = context.getString(R.string.sunday);
                break;
            case 2:
                week = context.getString(R.string.monday);
                break;
            case 3:
                week = context.getString(R.string.tuesday);
                break;
            case 4:
                week = context.getString(R.string.wednesday);
                break;
            case 5:
                week = context.getString(R.string.thursday);
                break;
            case 6:
                week = context.getString(R.string.friday);
                break;
            case 7:
                week = context.getString(R.string.saturday);
                break;

        }
        return week;
    }

    public String getFormatTime() {

        long curr = System.currentTimeMillis() / 1000;
        long item = curr - (this.time / 1000);

        if (item < 60) {
            return context.getString(R.string.just_now);
        } else if (item < 60 * 60) {
            return item / 60 + context.getString(R.string.minutes_ago);
        } else if (item < (60 * 60 * 24)) {
            return item / 60 / 60 + context.getString(R.string.hours_ago);
        } else if (item < (60 * 60 * 24 * 30)) {
            long day = item / 60 / 60 / 24;
            if (day == 1) {
                return context.getString(R.string.yesterday);
            } else if (day == 2) {
                return context.getString(R.string.day_before_yesterday);
            } else if (day > 2 && day < 11) {
                return day + context.getString(R.string.days_ago);
            } else {
                return formatterTime();
            }
        } else {
            return formatterTime();
        }
    }

    public String getAgoDateFormat() {

        long curr = System.currentTimeMillis() / 1000;
        long item = curr - (this.time / 1000);

        if (item < 60) {
            return context.getString(R.string.just_now);
        } else if (item < 60 * 60) {
            return item / 60 + context.getString(R.string.minutes_ago);
        } else if (item < (60 * 60 * 24)) {
            return item / 60 / 60 + context.getString(R.string.hours_ago);
        } else if (item < (60 * 60 * 24 * 30)) {
            return item / 60 / 60 / 24 + context.getString(R.string.days_ago);
        } else if (item < (60 * 60 * 24 * 30 * 12)) {
            return item / 60 / 60 / 24 / 30 + context.getString(R.string.months_ago);
        } else
            return item / 60 / 60 / 24 / 30 / 12 + context.getString(R.string.years_ago);
    }

    public Long getLong(int amount) {
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime().getTime();
    }


    public List<Long> getList() {
        List<Long> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                list.add(getLong(0));
            } else {
                list.add(getLong(1));
            }
        }
        return list;
    }


}
