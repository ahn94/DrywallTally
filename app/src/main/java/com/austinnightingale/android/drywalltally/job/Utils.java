package com.austinnightingale.android.drywalltally.job;

import com.austinnightingale.android.drywalltally.db.TallyArea;


public class Utils {

    public static double getCeilingSqFt(TallyArea tallyArea) {
        double totalSquare = 0;
        totalSquare += tallyArea.ceilingEight() * 8 * 4;
        totalSquare += tallyArea.ceilingNine() * 9 * 4;
        totalSquare += tallyArea.ceilingTen() * 10 * 4;
        totalSquare += tallyArea.ceilingTwelve() * 12 * 4;
        totalSquare += tallyArea.ceilingFourteen() * 14 * 4;
        totalSquare += tallyArea.ceilingSixteen() * 16 * 4;
        return totalSquare;
    }

    public static double getTotalSqFt(TallyArea tallyArea) {
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

        totalSquare += getCeilingSqFt(tallyArea);

        return totalSquare;
    }

    public static String printTotalSqFt(TallyArea tallyArea) {
        {
            double d = getTotalSqFt(tallyArea);
            if (d == (long) d)
                return String.format("%d", (long) d);
            else
                return String.format("%s", d);
        }
    }

    public static String printCeilingSqFt(TallyArea tallyArea) {
        {
            double d = getCeilingSqFt(tallyArea);
            if (d == (long) d)
                return String.format("%d", (long) d);
            else
                return String.format("%s", d);
        }
    }


}
