package com.austinnightingale.drywalltally.job;

import android.content.res.Resources;

import com.austinnightingale.drywalltally.R;
import com.austinnightingale.drywalltally.TallyApplication;
import com.austinnightingale.drywalltally.db.HeightCharge;
import com.austinnightingale.drywalltally.db.Job;
import com.austinnightingale.drywalltally.db.TallyArea;
import com.austinnightingale.drywalltally.jobs.DateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Austin on 8/24/2016.
 */
public class Report {

    /**
     *
     * TODO: report each job area separately
     *
     */

    public static String forJob(Job job, List<HeightCharge> heightChargeList, List<TallyArea> tallyAreas) {
        String report = job.jobName() + "\n\n";
        report += "Created on:\n";
        report += DateFormat.getLong(job.createdOn()) + "\n\n";
        report += getAddress(job) + "\n\n";

        report += getComment(job);
        report += "Ceiling - " + Utils.areaCeilingSqFt(tallyAreas.get(0)) + " Sq. Ft.\n";
        report += "Total - " + Utils.areaTotalSqFt(tallyAreas.get(0)) +  " Sq. Ft.\n\n";
        report += heightCharges(heightChargeList);
        report += "1/2\" Regular\n";
        report += addLine(tallyAreas.get(0).halfRegEight(), "8'");
        report += addLine(tallyAreas.get(0).halfRegNine(), "9'");
        report += addLine(tallyAreas.get(0).halfRegTen(), "10'");
        report += addLine(tallyAreas.get(0).halfRegTwelve(), "12'");
        report += addLine(tallyAreas.get(0).halfRegFourteen(), "14'");
        report += addLine(tallyAreas.get(0).halfRegSixteen(), "16'");
        report += "\n";
        report += "1/2\" Stretch\n";
        report += addLine(tallyAreas.get(0).halfStretchTwelve(), "12'");
        report += addLine(tallyAreas.get(0).halfStretchFourteen(), "14'");
        report += addLine(tallyAreas.get(0).halfStretchSixteen(), "16'");
        report += "\n";
        report += "5/8\" Regular\n";
        report += addLine(tallyAreas.get(0).fiveEighthRegEight(), "8'");
        report += addLine(tallyAreas.get(0).fiveEighthRegNine(), "9'");
        report += addLine(tallyAreas.get(0).fiveEighthRegTen(), "10'");
        report += addLine(tallyAreas.get(0).fiveEighthRegTwelve(), "12'");
        report += addLine(tallyAreas.get(0).fiveEighthRegFourteen(), "14'");
        report += "\n";
        report += "5/8\" Stretch\n";
        report += addLine(tallyAreas.get(0).fiveEightStretchTwelve(), "12'");
        report += "\n";
        report += "Ceilings\n";
        report += addLine(tallyAreas.get(0).ceilingEight(), "8'");
        report += addLine(tallyAreas.get(0).ceilingNine(), "9'");
        report += addLine(tallyAreas.get(0).ceilingTen(), "10'");
        report += addLine(tallyAreas.get(0).ceilingTwelve(), "12'");
        report += addLine(tallyAreas.get(0).ceilingFourteen(), "14'");
        report += addLine(tallyAreas.get(0).ceilingSixteen(), "16'");
        report += "\n";
        report += "Fire Resistant\n";
        report += addLine(tallyAreas.get(0).fireEight(), "8'");
        report += addLine(tallyAreas.get(0).fireNine(), "9'");
        report += addLine(tallyAreas.get(0).fireTen(), "10'");
        report += addLine(tallyAreas.get(0).fireTwelve(), "12'");
        report += addLine(tallyAreas.get(0).fireFourteen(), "14'");
        report += addLine(tallyAreas.get(0).fireSixteen(), "16'");
        report += "\n";
        report += "Mold Resistant\n";
        report += addLine(tallyAreas.get(0).moldEight(), "8'");
        report += addLine(tallyAreas.get(0).moldTwelve(), "12'");
        report += "\n";
        report += "Options\n";
        List<String[]> options = Report.getOptionValueLabelPairs(job);
        for (String[] option : options) {
            report += addLine(Integer.parseInt(option[0]), option[1]);
        }
        return report;
    }

    private static String heightCharges(List<HeightCharge> heightChargeList) {
        String str = "";
        if (heightChargeList.size() > 0) {
            str += "Height Charges\n";
        }
        for (HeightCharge charge : heightChargeList) {
            str += charge.heightCharge() + "\n";
        }
        str += (str.length() > 0) ? "\n\n" : "";
        return str;
    }

    private static String getComment(Job job) {
        String commment = job.comment();
        if (commment.length() > 0) {
            return "Comment:\n" + commment + "\n\n";
        } else return "";
    }

    private static String getAddress(Job job) {
        String address = job.address();
        if (address.length() > 0){
            return "Address:\n" + address;
        } else {
            return "Address: not added";
        }
    }

    public static String addLine(int value, String label) {
        return String.format(
                "%-5s-   %s\n",
                value,
                label
        );
    }

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
