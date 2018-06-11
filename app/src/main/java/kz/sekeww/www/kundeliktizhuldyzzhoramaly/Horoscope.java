package kz.sekeww.www.kundeliktizhuldyzzhoramaly;

/**
 * Created by sekeww on 1/7/18.
 */

public class Horoscope {

    private String mName;
    private String mToday;
    private String mTomorrow;
    private String mMonth;
    private String mYear;

    public Horoscope(String name, String today, String tomorrow, String month, String year) {
        mName = name;
        mToday = today;
        mTomorrow = tomorrow;
        mMonth = month;
        mYear = year;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getToday() {
        return mToday;
    }

    public void setToday(String today) {
        mToday = today;
    }

    public String getTomorrow() {
        return mTomorrow;
    }

    public void setTomorrow(String tomorrow) {
        mTomorrow = tomorrow;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setMonth(String month) {
        mMonth = month;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }
}
