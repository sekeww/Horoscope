package kz.sekeww.www.kundeliktizhuldyzzhoramaly;


import android.content.res.Resources;


/**
 * Created by sekeww on 8/9/17.
 */

public class Compatibility {
    private int mPercentage;
    private int mMarriage;
    private String mMarriageDesc;
    private int mLuck;
    private String mLuckDesc;
    private int mSexual;
    private String mSexualDesc;
    private int mWealth;
    private String mWealthDesc;
    private int mChildren;
    private String mChildrenDesc;


    public int getPercentage() {
        return mPercentage;
    }

    public void setPercentage(int percentage) {
        mPercentage = percentage;
    }

    public int getMarriage() {
        return mMarriage;
    }

    public String getFormattedMarriage() {
        String mystring = "1.Тұрсмыстағы бақыт: %1$s";
        String marriageFormatted = mystring;
        marriageFormatted = String.format(mystring, "");
        switch (mMarriage) {
            case 1:
                marriageFormatted = String.format(mystring,"Нашар");
                break;
            case 2:
                marriageFormatted = String.format(mystring,"Орташа");
                break;
            case 3:
                marriageFormatted = String.format(mystring,"Жақсы");
                break;
        }
        return marriageFormatted;
    }

    public void setMarriage(int marriage) {
        mMarriage = marriage;
    }

    public String getMarriageDesc() {
        return mMarriageDesc;
    }

    public void setMarriageDesc(String marriageDesc) {
        mMarriageDesc = marriageDesc;
    }

    public int getLuck() {
        return mLuck;
    }

    public String getFormattedLuck() {
        String mystring = "2. Табысқа қатысты сәйкестілік: %1$s";
        String luckFormatted = mystring;
        luckFormatted = String.format(mystring, "");
        switch (mLuck) {
            case 1:
                luckFormatted = String.format(mystring,"Нашар");
                break;
            case 2:
                luckFormatted = String.format(mystring,"Орташа");
                break;
            case 3:
                luckFormatted = String.format(mystring,"Жақсы");
                break;
        }
        return luckFormatted;
    }

    public void setLuck(int luck) {
        mLuck = luck;
    }

    public String getLuckDesc() {
        return mLuckDesc;
    }

    public void setLuckDesc(String luckDesc) {
        mLuckDesc = luckDesc;
    }

    public int getSexual() {
        return mSexual;
    }

    public String getFormattedSexual() {
        String mystring = "3. Жыныстық қатынастағы сәйкестілік: %1$s";
        String sexualFormatted = mystring;
        sexualFormatted = String.format(mystring, "");
        switch (mSexual) {
            case 1:
                sexualFormatted = String.format(mystring,"Нашар");
                break;
            case 2:
                sexualFormatted = String.format(mystring,"Орташа");
                break;
            case 3:
                sexualFormatted = String.format(mystring,"Жақсы");
                break;
        }
        return sexualFormatted;
    }

    public void setSexual(int sexual) {
        mSexual = sexual;
    }

    public String getSexualDesc() {
        return mSexualDesc;
    }

    public void setSexualDesc(String sexualDesc) {
        mSexualDesc = sexualDesc;
    }

    public int getWealth() {
        return mWealth;
    }

    public String getFormattedWealth() {
        String mystring = "4. Байлық пен отбасы өсіміндегі сәйкестілік: %1$s";
        String wealthFormatted = mystring;
        wealthFormatted = String.format(mystring, "");
        switch (mWealth) {
            case 1:
                wealthFormatted = String.format(mystring,"Нашар");
                break;
            case 2:
                wealthFormatted = String.format(mystring,"Орташа");
                break;
            case 3:
                wealthFormatted = String.format(mystring,"Жақсы");
                break;
        }
        return wealthFormatted;
    }

    public void setWealth(int wealth) {
        mWealth = wealth;
    }

    public String getWealthDesc() {
        return mWealthDesc;
    }

    public void setWealthDesc(String wealthDesc) {
        mWealthDesc = wealthDesc;
    }

    public int getChildren() {
        return mChildren;
    }

    public String getFormattedChildren() {
        String mystring = "5. Бала тәрбиесіндегі сәйкестілік: %1$s";
        String childrenFormatted = mystring;
        childrenFormatted = String.format(mystring, "");
        switch (mChildren) {
            case 1:
                childrenFormatted = String.format(mystring,"Нашар");
                break;
            case 2:
                childrenFormatted = String.format(mystring,"Орташа");
                break;
            case 3:
                childrenFormatted = String.format(mystring,"Жақсы");
                break;
        }
        return childrenFormatted;
    }

    public void setChildren(int children) {
        mChildren = children;
    }

    public String getChildrenDesc() {
        return mChildrenDesc;
    }

    public void setChildrenDesc(String childrenDesc) {
        mChildrenDesc = childrenDesc;
    }
}
