package com.austinnightingale.drywalltally.job;

import com.austinnightingale.drywalltally.db.TallyArea;

import java.util.List;
import java.util.Locale;


public class Utils {

    public static String areaTotalSqFt(TallyArea tallyArea) {
        return Utils.strFromDouble(areaTotalFt(tallyArea));
    }

    public static String areaCeilingSqFt(TallyArea tallyArea) {
        return Utils.strFromDouble(areaCeilingFt(tallyArea));
    }

    public static String jobTotalFtString(List<TallyArea> tallyAreas) {
        return Utils.strFromDouble(jobTotalFtNum(tallyAreas));
    }

    public static String jobCeilingFtString(List<TallyArea> tallyAreas) {
        return Utils.strFromDouble(jobCeilingFtNum(tallyAreas));
    }

    public static int jobHalf8(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalf8(area);
        }
        return total;
    }

    public static int tallyHalf8(TallyArea area) {
        return area.halfRegEight();
    }

    public static int jobHalf9(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalf9(area);
        }
        return total;
    }

    public static int tallyHalf9(TallyArea area) {
        return area.halfRegNine();
    }

    public static int jobHalf10(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalf10(area);
        }
        return total;
    }
    
    public static int tallyHalf10(TallyArea area) {
        return area.halfRegTen();
    }

    public static int jobHalf12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalf12(area);
        }
        return total;
    }

    public static int tallyHalf12(TallyArea area) {
        return area.halfRegTwelve();
    }

    public static int jobHalf14(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalf14(area);
        }
        return total;
    }

    public static int tallyHalf14(TallyArea area) {
        return area.halfRegFourteen();
    }

    public static int jobHalf16(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalf16(area);
        }
        return total;
    }

    public static int tallyHalf16(TallyArea area) {
        return area.halfRegSixteen();
    }

    public static int jobHalfS12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalfS12(area);
        }
        return total;
    }

    public static int tallyHalfS12(TallyArea area) {
        return area.halfStretchTwelve();
    }

    public static int jobHalfS14(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalfS14(area);
        }
        return total;
    }

    public static int tallyHalfS14(TallyArea area) {
        return area.halfStretchTwelve();
    }

    public static int jobHalfS16(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyHalfS16(area);
        }
        return total;
    }

    public static int tallyHalfS16(TallyArea area) {
        return area.halfStretchSixteen();
    }

    public static int jobFiveE8(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFiveE8(area);
        }
        return total;
    }

    public static int tallyFiveE8(TallyArea area) {
        return area.fiveEighthRegEight();
    }

    public static int jobFiveE9(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFiveE9(area);
        }
        return total;
    }

    public static int tallyFiveE9(TallyArea area) {
        return area.fiveEighthRegNine();
    }

    public static int jobFiveE10(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFiveE10(area);
        }
        return total;
    }

    public static int tallyFiveE10(TallyArea area) {
        return area.fiveEighthRegTen();
    }

    public static int jobFiveE12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFiveE12(area);
        }
        return total;
    }

    public static int tallyFiveE12(TallyArea area) {
        return area.fiveEighthRegTwelve();
    }

    public static int jobFiveE14(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFiveE14(area);
        }
        return total;
    }

    public static int tallyFiveE14(TallyArea area) {
        return area.fiveEighthRegFourteen();
    }

    public static int jobFiveES12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFiveES12(area);
        }
        return total;
    }

    public static int tallyFiveES12(TallyArea area) {
        return area.fiveEightStretchTwelve();
    }

    public static int jobCeil8(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyCeil8(area);
        }
        return total;
    }
    
    public static int tallyCeil8(TallyArea area) {
        return area.ceilingEight();
    }
    
    public static int jobCeil9(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyCeil9(area);
        }
        return total;
    }

    public static int tallyCeil9(TallyArea area) {
        return area.ceilingNine();
    }

    public static int jobCeil10(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyCeil10(area);
        }
        return total;
    }

    public static int tallyCeil10(TallyArea area) {
        return area.ceilingTen();
    }

    public static int jobCeil12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyCeil12(area);
        }
        return total;
    }

    public static int tallyCeil12(TallyArea area) {
        return area.ceilingTwelve();
    }

    public static int jobCeil14(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyCeil14(area);
        }
        return total;
    }

    public static int tallyCeil14(TallyArea area) {
        return area.ceilingFourteen();
    }

    public static int jobCeil16(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyCeil16(area);
        }
        return total;
    }

    public static int tallyCeil16(TallyArea area) {
        return area.ceilingSixteen();
    }

    public static int jobFire8(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFire8(area);
        }
        return total;
    }

    public static int tallyFire8(TallyArea area) {
        return area.fireEight();
    }

    public static int jobFire9(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFire9(area);
        }
        return total;
    }

    public static int tallyFire9(TallyArea area) {
        return area.fireNine();
    }

    public static int jobFire10(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFire10(area);
        }
        return total;
    }

    public static int tallyFire10(TallyArea area) {
        return area.fireTen();
    }


    public static int jobFire12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFire12(area);
        }
        return total;
    }

    public static int tallyFire12(TallyArea area) {
        return area.fireTwelve();
    }


    public static int jobFire14(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFire14(area);
        }
        return total;
    }

    public static int tallyFire14(TallyArea area) {
        return area.fireFourteen();
    }


    public static int jobFire16(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyFire16(area);
        }
        return total;
    }

    public static int tallyFire16(TallyArea area) {
        return area.fireSixteen();
    }


    public static int jobMold8(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyMold8(area);
        }
        return total;
    }

    public static int tallyMold8(TallyArea area) {
        return area.moldEight();
    }


    public static int jobMold12(List<TallyArea> tallyAreas) {
        int total = 0;
        for (TallyArea area : tallyAreas) {
            total += tallyMold12(area);
        }
        return total;
    }

    public static int tallyMold12(TallyArea area) {
        return area.moldTwelve();
    }
    
    public static TallyArea getJobTally(String jobName, List<TallyArea> tallyAreas) {
        int half8 = 0;
        int half9 = 0;
        int half10 = 0;
        int half12 = 0;
        int half14 = 0;
        int half16 = 0;
        int halfS12 = 0;
        int halfS14 = 0;
        int halfS16 = 0;
        int five8 = 0;
        int five9 = 0;
        int five10 = 0;
        int five12 = 0;
        int five14 = 0;
        int fiveS12 = 0;
        int ceil8 = 0;
        int ceil9 = 0;
        int ceil10 = 0;
        int ceil12 = 0;
        int ceil14 = 0;
        int ceil16 = 0;
        int fire8 = 0;
        int fire9 = 0;
        int fire10 = 0;
        int fire12 = 0;
        int fire14 = 0;
        int fire16 = 0;
        int mold8 = 0;
        int mold12 = 0;

        for (TallyArea area : tallyAreas) {
            half8 += area.halfRegEight();
            half9 += area.halfRegNine();
            half10 += area.halfRegTen();
            half12 += area.halfRegTwelve();
            half14 += area.halfRegFourteen();
            half16 += area.halfRegSixteen();
            halfS12 += area.halfStretchTwelve();
            halfS14 += area.halfStretchFourteen();
            halfS16 += area.halfStretchSixteen();
            five8 += area.fiveEighthRegEight();
            five9 += area.fiveEighthRegNine();
            five10 += area.fiveEighthRegTen();
            five12 += area.fiveEighthRegTwelve();
            five14 += area.fiveEighthRegFourteen();
            fiveS12 += area.fiveEightStretchTwelve();
            ceil8 += area.ceilingEight();
            ceil9 += area.ceilingNine();
            ceil10 += area.ceilingTen();
            ceil12 += area.ceilingTwelve();
            ceil14 += area.ceilingFourteen();
            ceil16 += area.ceilingSixteen();
            fire8 += area.fireEight();
            fire9 += area.fireNine();
            fire10 += area.fireTen();
            fire12 += area.fireTwelve();
            fire14 += area.fireFourteen();
            fire16 += area.fireSixteen();
            mold8 += area.moldEight();
            mold12 += area.moldTwelve();
        }
        return TallyArea.builder()
                .jobID(0)
                .areaName(jobName)
                .halfRegEight(half8)
                .halfRegNine(half9)
                .halfRegTen(half10)
                .halfRegTwelve(half12)
                .halfRegFourteen(half14)
                .halfRegSixteen(half16)
                .halfStretchTwelve(halfS12)
                .halfStretchFourteen(halfS14)
                .halfStretchSixteen(halfS16)
                .fiveEighthRegEight(five8)
                .fiveEighthRegNine(five9)
                .fiveEighthRegTen(five10)
                .fiveEighthRegTwelve(five12)
                .fiveEighthRegFourteen(five14)
                .fiveEightStretchTwelve(fiveS12)
                .ceilingEight(ceil8)
                .ceilingNine(ceil9)
                .ceilingTen(ceil10)
                .ceilingTwelve(ceil12)
                .ceilingFourteen(ceil14)
                .ceilingSixteen(ceil16)
                .fireEight(fire8)
                .fireNine(fire9)
                .fireTen(fire10)
                .fireTwelve(fire12)
                .fireFourteen(fire14)
                .fireSixteen(fire16)
                .moldEight(mold8)
                .moldTwelve(mold12)
                .build();
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

        totalSquare += tallyArea.fireEight() * 8 * 4;
        totalSquare += tallyArea.fireNine() * 9 * 4;
        totalSquare += tallyArea.fireTen() * 10 * 4;
        totalSquare += tallyArea.fireTwelve() * 12 * 4;
        totalSquare += tallyArea.fireFourteen() * 14 * 4;
        totalSquare += tallyArea.fireSixteen() * 16 * 4;

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
