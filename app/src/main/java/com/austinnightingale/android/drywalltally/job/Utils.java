package com.austinnightingale.android.drywalltally.job;

import com.austinnightingale.android.drywalltally.db.TallyArea;

import java.util.List;
import java.util.Locale;


public class Utils {

    public static String areaTotalSqFt(TallyArea tallyArea) {
        return Utils.strFromDouble(areaTotalFt(tallyArea));
    }

    public static String areaCeilingSqFt(TallyArea tallyArea) {
        return Utils.strFromDouble(areaCeilingFt(tallyArea));
    }

    public static String jobTotalFtString(List<TallyArea> tallyAreaList) {
        return Utils.strFromDouble(jobTotalFtNum(tallyAreaList));
    }

    public static String jobCeilingFtString(List<TallyArea> tallyAreaList) {
        return Utils.strFromDouble(jobCeilingFtNum(tallyAreaList));
    }

    public static double jobCeilingFtNum(List<TallyArea> tallyAreaList) {
        double ceilingSqFt = 0;
        for (TallyArea tallyArea : tallyAreaList) {
            ceilingSqFt += areaCeilingFt(tallyArea);
        }
        return ceilingSqFt;
    }

    public static double jobTotalFtNum(List<TallyArea> tallyAreaList) {
        double totalSqFt = 0;
        for (TallyArea tallyArea : tallyAreaList) {
            totalSqFt += areaTotalFt(tallyArea);
        }
        return totalSqFt;
    }

    public static double areaTotalFt(TallyArea tallyArea) {
        double totalSquare = 0;

        totalSquare += tallyArea.halfRegEight() * 8 * 4;
        totalSquare += tallyArea.halfRegNine() * 9 * 4;
        totalSquare += tallyArea.halfRegTen() * 10 * 4;
        totalSquare += tallyArea.halfRegTwelve() * 12 * 4;
        totalSquare += tallyArea.halfRegFourteen() * 14 * 4;
        totalSquare += tallyArea.halfRegSixteen() * 16 * 4;

        totalSquare += tallyArea.halfStretchTwelve() * 12 * 4.5;
        totalSquare += tallyArea.halfStretchFourteen() * 14 * 4.5;
        totalSquare += tallyArea.halfStretchSixteen() * 16 * 4.5;

        totalSquare += tallyArea.moldEight() * 8 * 4;
        totalSquare += tallyArea.moldTwelve() * 12 * 4;

        totalSquare += tallyArea.fiveEighthRegEight() * 8 * 4;
        totalSquare += tallyArea.fiveEighthRegNine() * 9 * 4;
        totalSquare += tallyArea.fiveEighthRegTen() * 10 * 4;
        totalSquare += tallyArea.fiveEighthRegTwelve() * 12 * 4;
        totalSquare += tallyArea.fiveEighthRegFourteen() * 14 * 4;
        totalSquare += tallyArea.fiveEightStretchTwelve() * 12 * 4.5;

        totalSquare += areaCeilingFt(tallyArea);

        return totalSquare;
    }

    public static double areaCeilingFt(TallyArea tallyArea) {
        double totalSquare = 0;

        totalSquare += tallyArea.ceilingEight() * 8 * 4;
        totalSquare += tallyArea.ceilingNine() * 9 * 4;
        totalSquare += tallyArea.ceilingTen() * 10 * 4;
        totalSquare += tallyArea.ceilingTwelve() * 12 * 4;
        totalSquare += tallyArea.ceilingFourteen() * 14 * 4;
        totalSquare += tallyArea.ceilingSixteen() * 16 * 4;

        return totalSquare;
    }

    private static String strFromDouble(double value) {
        if (value == (long) value)
            return String.format(Locale.ENGLISH,"%d", (long) value);
        else
            return String.format("%s", value);
    }

}
