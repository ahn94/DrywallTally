package com.austinnightingale.android.drywalltally.job;

import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.jobs.DateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Austin on 8/24/2016.
 */
public class Report {

    public static String forJob(Job job, List<HeightCharge> heightChargeList) {
        String report = job.jobName() + "\n\n";
        report += "Created on:\n";
        report += DateFormat.getLong(job.createdOn()) + "\n\n";
        report += getAddress(job) + "\n\n";

        report += getComment(job);
        report += "Ceiling - " + Utils.printCeilingSqFt(job) + " Sq. Ft.\n";
        report += "Total - " + Utils.printTotalSqFt(job) +  " Sq. Ft.\n\n";
        report += heightCharges(heightChargeList);
        report += "1/2\" Regular\n";
        report += addLine(job.halfRegEight(), "8'");
        report += addLine(job.halfRegNine(), "9'");
        report += addLine(job.halfRegTen(), "10'");
        report += addLine(job.halfRegTwelve(), "12'");
        report += addLine(job.halfRegFourteen(), "14'");
        report += addLine(job.halfRegSixteen(), "16'");
        report += "\n";
        report += "1/2\" Stretch\n";
        report += addLine(job.halfStretchTwelve(), "12'");
        report += addLine(job.halfStretchFourteen(), "14'");
        report += addLine(job.halfStretchSixteen(), "16'");
        report += "\n";
        report += "5/8\" Regular\n";
        report += addLine(job.fiveEighthRegEight(), "8'");
        report += addLine(job.fiveEighthRegNine(), "9'");
        report += addLine(job.fiveEighthRegTen(), "10'");
        report += addLine(job.fiveEighthRegTwelve(), "12'");
        report += addLine(job.fiveEighthRegFourteen(), "14'");
        report += "\n";
        report += "5/8\" Stretch\n";
        report += addLine(job.fiveEightStretchTwelve(), "12'");
        report += "\n";
        report += "Ceilings\n";
        report += addLine(job.ceilingEight(), "8'");
        report += addLine(job.ceilingNine(), "9'");
        report += addLine(job.ceilingTen(), "10'");
        report += addLine(job.ceilingTwelve(), "12'");
        report += addLine(job.ceilingFourteen(), "14'");
        report += addLine(job.ceilingSixteen(), "16'");
        report += "\n";
        report += "Fire Resistant\n";
        report += addLine(job.fireEight(), "8'");
        report += addLine(job.fireNine(), "9'");
        report += addLine(job.fireTen(), "10'");
        report += addLine(job.fireTwelve(), "12'");
        report += addLine(job.fireFourteen(), "14'");
        report += addLine(job.fireSixteen(), "16'");
        report += "\n";
        report += "Mold Resistant\n";
        report += addLine(job.moldEight(), "8'");
        report += addLine(job.moldTwelve(), "12'");
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

    public static Integer[] getHalfTallies(Job job) {
        return new Integer[]{
                job.halfRegEight(),
                job.halfRegNine(),
                job.halfRegTen(),
                job.halfRegTwelve(),
                job.halfRegFourteen(),
                job.halfRegSixteen()
        };
    }

    public static Integer[] getHalfStretchTallies(Job job) {
        return new Integer[]{
                job.halfStretchTwelve(),
                job.halfStretchFourteen(),
                job.halfStretchSixteen()
        };
    }

    public static Integer[] getCeilTallies(Job job) {
        return new Integer[]{
                job.ceilingEight(),
                job.ceilingNine(),
                job.ceilingTen(),
                job.ceilingTwelve(),
                job.ceilingFourteen(),
                job.ceilingSixteen()
        };
    }

    public static Integer[] getFiveETallies(Job job) {
        return new Integer[]{
                job.fiveEighthRegEight(),
                job.fiveEighthRegNine(),
                job.fiveEighthRegTen(),
                job.fiveEighthRegTwelve(),
                job.fiveEighthRegFourteen(),
        };
    }

    public static Integer[] getFiveEStretchTallies(Job job) {
        return new Integer[]{
                job.fiveEightStretchTwelve()
        };
    }

    public static Integer[] getMoldTallies(Job job) {
        return new Integer[]{
                job.moldEight(),
                job.moldTwelve()
        };
    }

    public static Integer[] getFireTallies(Job job) {
        return new Integer[]{
                job.fireEight(),
                job.fireNine(),
                job.fireTen(),
                job.fireTwelve(),
                job.fireFourteen(),
                job.fireSixteen()
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
