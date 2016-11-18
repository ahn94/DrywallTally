package com.austinnightingale.drywalltally.job;

import android.content.res.Resources;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.db.TallyArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Austin on 8/24/2016.
 */
public class Report {


    public static List<String[]> getExtraValueLabelPairs(Job job) {
        List<String[]> extras = new ArrayList<>();

        Resources res = TallyApplication.getContext().getResources();

        int CScrews = job.CScrews();
        if (CScrews > 0) {
            extras.add(new String[] {String.valueOf(CScrews), res.getQuantityString(R.plurals.job_report_coarse_screws, CScrews)} );
        }

        int dsa20 = job.dsa20();
        if (dsa20 > 0) {
            extras.add(new String[] {String.valueOf(dsa20), res.getQuantityString(R.plurals.job_report_dsa20, dsa20)} );
        }

        int shims = job.shims();
        if (shims > 0) {
            extras.add(new String[] {String.valueOf(shims), res.getQuantityString(R.plurals.job_report_shims, shims)} );
        }

        int liteBlue = job.liteBlue();
        if (liteBlue > 0) {
            extras.add(new String[] {String.valueOf(liteBlue), res.getQuantityString(R.plurals.job_report_lite_blue, liteBlue)} );
        }

        int magnumAP = job.magnumHp();
        if (magnumAP > 0) {
            extras.add(new String[] {String.valueOf(magnumAP), res.getQuantityString(R.plurals.job_report_magnum_ap, magnumAP)} );
        }

        int hot45 = job.hot45();
        if (hot45 > 0) {
            extras.add(new String[] {String.valueOf(hot45), res.getQuantityString(R.plurals.job_report_hot_45, hot45)} );
        }

        int hot5 = job.hot5();
        if (hot5 > 0) {
            extras.add(new String[] {String.valueOf(hot5), res.getQuantityString(R.plurals.job_report_hot_5, hot5)} );
        }

        int hot20 = job.hot20();
        if (hot20 > 0) {
            extras.add(new String[] {String.valueOf(hot20), res.getQuantityString(R.plurals.job_report_hot_20, hot20)} );
        }

        int hot90 = job.hot90();
        if (hot90 > 0) {
            extras.add(new String[] {String.valueOf(hot90), res.getQuantityString(R.plurals.job_report_hot_90, hot90)} );
        }

        int levelcoat = job.levelcoat();
        if (levelcoat > 0) {
            extras.add(new String[] {String.valueOf(levelcoat), res.getQuantityString(R.plurals.job_report_levelcoat_5gal, levelcoat)} );
        }

        int ultraflex325 = job.ultraflex325();
        if (ultraflex325 > 0) {
            extras.add(new String[] {String.valueOf(ultraflex325), res.getQuantityString(R.plurals.job_report_ultraflex_325, ultraflex325)} );
        }

        int ultraflex450 = job.ultraflex450();
        if (ultraflex450 > 0) {
            extras.add(new String[] {String.valueOf(ultraflex450), res.getQuantityString(R.plurals.job_report_ultraflex_450, ultraflex450)} );
        }
        
        int nocoat8ft90 = job.nocoat8ft90();
        if (nocoat8ft90 > 0) {
            extras.add(new String[] {String.valueOf(nocoat8ft90), res.getQuantityString(R.plurals.job_report_8ft_nocoat_90, nocoat8ft90)});
        }

        int nocoat10ft90 = job.nocoat10ft90();
        if (nocoat10ft90 > 0) {
            extras.add(new String[] {String.valueOf(nocoat10ft90), res.getQuantityString(R.plurals.job_report_10ft_nocoat_90, nocoat10ft90)});
        }

        int nocoat8ftBull = job.nocoat8ftBull();
        if (nocoat8ftBull > 0) {
            extras.add(new String[] {String.valueOf(nocoat8ftBull), res.getQuantityString(R.plurals.job_report_8ft_nocoat_bull, nocoat8ftBull)});
        }

        int nocoat10ftBull = job.nocoat10ftBull();
        if (nocoat10ftBull > 0) {
            extras.add(new String[] {String.valueOf(nocoat10ftBull), res.getQuantityString(R.plurals.job_report_10ft_nocoat_bull, nocoat10ftBull)});
        }

        int xcrack = job.xcrack();
        if (xcrack > 0) {
            extras.add(new String[] {String.valueOf(xcrack), res.getQuantityString(R.plurals.job_report_xcrack, xcrack)});
        }


        if (extras.size() == 0) {
            extras.add(new String[]{String.valueOf(0), "Extras Added"});
        }

        return extras;
    }
    
    public static List<String[]> getOptionValueLabelPairs(Job job) {

        List<String[]> options = new ArrayList<>();

        int bullCorner = job.bullCorners();
        int bullAdapter = job.bullAdapterFlag();
        if (bullCorner > 0) {
            if (bullAdapter == 1) {
                options.add(new String[]{String.valueOf(bullCorner), "Bullnose Corner w/ Adapter"});
            } else {
                options.add(new String[]{String.valueOf(bullCorner), "Bullnose Corner"});
            }
        }

        int windowWrap3 = job.windowWrap3S();
        if (windowWrap3 > 0) {
            options.add(new String[]{String.valueOf(windowWrap3), "3 Sided Window Wrap"});
        }

        int windowWrap4 = job.windowWrap4S();
        if (windowWrap4 > 0) {
            options.add(new String[]{String.valueOf(windowWrap4), "4 Sided Window Wrap"});
        }

        int archWrap = job.archWrap();
        if (archWrap > 0) {
            options.add(new String[]{String.valueOf(archWrap), "Arch Wrap"});
        }

        int archCorner = job.archCorner();
        if (archCorner > 0) {
            options.add(new String[]{String.valueOf(archCorner), "Arch Corner"});
        }

        int doorWrap = job.doorWrap();
        if (doorWrap > 0) {
            options.add(new String[]{String.valueOf(doorWrap), "Door Wrap"});
        }

        int openingWrap = job.openingWrap();
        if (openingWrap > 0) {
            options.add(new String[]{String.valueOf(openingWrap), "Opening Wrap"});
        }

        int lTrim = job.lTrim();
        if (lTrim > 0) {
            options.add(new String[]{String.valueOf(lTrim), "Linear Ft. L Trim"});
        }

        if (options.size() == 0) {
            options.add(new String[]{String.valueOf(0), "Options Added"});
        }

        return options;
    }

    public static Integer[] getHalfTallies(TallyArea tally) {
        return new Integer[]{
                tally.halfRegEight(),
                tally.halfRegNine(),
                tally.halfRegTen(),
                tally.halfRegTwelve(),
                tally.halfRegFourteen(),
                tally.halfRegSixteen()
        };
    }

    public static Integer[] getHalfStretchTallies(TallyArea tally) {
        return new Integer[]{
                tally.halfStretchTwelve(),
                tally.halfStretchFourteen(),
                tally.halfStretchSixteen()
        };
    }

    public static Integer[] getCeilTallies(TallyArea tally) {
        return new Integer[]{
                tally.ceilingEight(),
                tally.ceilingNine(),
                tally.ceilingTen(),
                tally.ceilingTwelve(),
                tally.ceilingFourteen(),
                tally.ceilingSixteen()
        };
    }

    public static Integer[] getFiveETallies(TallyArea tally) {
        return new Integer[]{
                tally.fiveEighthRegEight(),
                tally.fiveEighthRegNine(),
                tally.fiveEighthRegTen(),
                tally.fiveEighthRegTwelve(),
                tally.fiveEighthRegFourteen(),
        };
    }

    public static Integer[] getFiveEStretchTallies(TallyArea tally) {
        return new Integer[]{
                tally.fiveEightStretchTwelve()
        };
    }

    public static Integer[] getMoldTallies(TallyArea tally) {
        return new Integer[]{
                tally.moldEight(),
                tally.moldTwelve()
        };
    }

    public static Integer[] getFireTallies(TallyArea tally) {
        return new Integer[]{
                tally.fireEight(),
                tally.fireNine(),
                tally.fireTen(),
                tally.fireTwelve(),
                tally.fireFourteen(),
                tally.fireSixteen()
        };
    }


    public static int getTallySum(Integer[] values) {
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        return sum;
    }

}
