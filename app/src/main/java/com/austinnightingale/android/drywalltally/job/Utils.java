package com.austinnightingale.android.drywalltally.job;

import com.austinnightingale.android.drywalltally.db.Job;

/**
 * Created by Austin on 8/27/2016.
 */
public class Utils {

    public static double getCeilingSqFt(Job job) {
        double totalSquare = 0;
        totalSquare += job.ceilingEight() * 8 * 4;
        totalSquare += job.ceilingNine() * 9 * 4;
        totalSquare += job.ceilingTen() * 10 * 4;
        totalSquare += job.ceilingTwelve() * 12 * 4;
        totalSquare += job.ceilingFourteen() * 14 * 4;
        totalSquare += job.ceilingSixteen() * 16 * 4;
        return totalSquare;
    }

    public static double getTotalSqFt(Job job) {
        double totalSquare = 0;

        totalSquare += job.halfRegEight() * 8 * 4;
        totalSquare += job.halfRegNine() * 9 * 4;
        totalSquare += job.halfRegTen() * 10 * 4;
        totalSquare += job.halfRegTwelve() * 12 * 4;
        totalSquare += job.halfRegFourteen() * 14 * 4;
        totalSquare += job.halfRegSixteen() * 16 * 4;

        totalSquare += job.halfStretchTwelve() * 12 * 4.5;
        totalSquare += job.halfStretchFourteen() * 14 * 4.5;
        totalSquare += job.halfStretchSixteen() * 16 * 4.5;

        totalSquare += job.moldEight() * 8 * 4;
        totalSquare += job.moldTwelve() * 12 * 4;

        totalSquare += job.fiveEighthRegEight() * 8 * 4;
        totalSquare += job.fiveEighthRegNine() * 9 * 4;
        totalSquare += job.fiveEighthRegTen() * 10 * 4;
        totalSquare += job.fiveEighthRegTwelve() * 12 * 4;
        totalSquare += job.fiveEighthRegFourteen() * 14 * 4;
        totalSquare += job.fiveEightStretchTwelve() * 12 * 4.5;

        totalSquare += getCeilingSqFt(job);

        return totalSquare;
    }

    public static String printTotalSqFt(Job job) {
        {
            double d = getTotalSqFt(job);
            if (d == (long) d)
                return String.format("%d", (long) d);
            else
                return String.format("%s", d);
        }
    }

    public static String printCeilingSqFt(Job job) {
        {
            double d = getCeilingSqFt(job);
            if (d == (long) d)
                return String.format("%d", (long) d);
            else
                return String.format("%s", d);
        }
    }


}
