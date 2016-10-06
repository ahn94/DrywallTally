package com.austinnightingale.android.drywalltally;


import com.austinnightingale.android.drywalltally.db.DAO;
import com.austinnightingale.android.drywalltally.db.HeightCharge;
import com.austinnightingale.android.drywalltally.db.Job;
import com.austinnightingale.android.drywalltally.db.TallyArea;
import com.austinnightingale.android.drywalltally.job.Report;
import com.austinnightingale.android.drywalltally.job.Utils;
import com.austinnightingale.android.drywalltally.jobs.DateFormat;

import java.util.List;

public class JobReport {

    
    private int jobId;
    private DAO doa;
    private Job job;
    private List<HeightCharge> heightCharges;
    private List<TallyArea> tallyAreas;

    public JobReport(int jobId, DAO doa) {
        this.jobId = jobId;
        this.doa = doa;
        initStats();
    }

    public void setJob(Job job) {
        this.job = job;
    }

    private void initStats() {
        job = doa.jobWithId(jobId);
        heightCharges = doa.heightChargeListFromJobId(jobId);
        tallyAreas = doa.tallyListByJobId(jobId);
    }

    public String getReport() {
        String report = job.jobName() + "\n\n";
        report += "Created on:\n";
        report += DateFormat.getLong(job.createdOn()) + "\n\n";
        report += getAddress(job) + "\n\n";

        report += getComment(job);
        report += "Ceiling - " + Utils.jobCeilingFtString(tallyAreas) + " Sq. Ft.\n";
        report += "Total - " + Utils.jobTotalFtString(tallyAreas) +  " Sq. Ft.\n\n";
        report += printOptions();
        report += printExtras();
        report += heightCharges(heightCharges);
        report += jobTallyTotals();
        if (tallyAreas.size() > 1) {
            report += printAreaTotals();
        }
        return report;
    }

    private String printExtras() {
        String report = "";
        report += "Extras\n";
        List<String[]> extras = Report.getExtraValueLabelPairs(job);
        for (String[] extra : extras) {
            report += addLine(Integer.parseInt(extra[0]), extra[1]);
        }
        report += "\n";
        return report;
    }

    private String printOptions() {
        String report = "";
        report += "Options\n";
        List<String[]> options = Report.getOptionValueLabelPairs(job);
        for (String[] option : options) {
            report += addLine(Integer.parseInt(option[0]), option[1]);
        }
        report += "\n";
        return report;
    }


    public String jobTallyTotals() {
        String report = "";
        if (tallyAreas.isEmpty()) {
            report += "\n----------------------------\n";
            report += "No tallies added to job.\n";
            report += "----------------------------\n";
            return report;
        }
        report += "\n\n----------------------------\n";
        report += "Job: " +job.jobName()+ "\n";
        report += "----------------------------\n";
        if (tallyAreas.size() == 1) {
            report += tallyAreas.get(0).areaName() + " is only job area added to job\n";
        }
        report += "1/2\" Lite\n";
        report += addLine(Utils.jobHalf8(tallyAreas), "8'");
        report += addLine(Utils.jobHalf9(tallyAreas), "9'");
        report += addLine(Utils.jobHalf10(tallyAreas), "10'");
        report += addLine(Utils.jobHalf12(tallyAreas), "12'");
        report += addLine(Utils.jobHalf14(tallyAreas), "14'");
        report += addLine(Utils.jobHalf16(tallyAreas), "16'");
        report += "\n";
        report += "1/2\" Stretch\n";
        report += addLine(Utils.jobHalfS12(tallyAreas), "12'");
        report += addLine(Utils.jobHalfS14(tallyAreas), "14'");
        report += addLine(Utils.jobHalfS16(tallyAreas), "16'");
        report += "\n";
        report += "5/8\" Regular\n";
        report += addLine(Utils.jobFiveE8(tallyAreas), "8'");
        report += addLine(Utils.jobFiveE9(tallyAreas), "9'");
        report += addLine(Utils.jobFiveE10(tallyAreas), "10'");
        report += addLine(Utils.jobFiveE12(tallyAreas), "12'");
        report += addLine(Utils.jobFiveE14(tallyAreas), "14'");
        report += "\n";
        report += "5/8\" Stretch\n";
        report += addLine(Utils.jobFiveES12(tallyAreas), "12'");
        report += "\n";
        report += "Ceilings\n";
        report += addLine(Utils.jobCeil8(tallyAreas), "8'");
        report += addLine(Utils.jobCeil9(tallyAreas), "9'");
        report += addLine(Utils.jobCeil10(tallyAreas), "10'");
        report += addLine(Utils.jobCeil12(tallyAreas), "12'");
        report += addLine(Utils.jobCeil14(tallyAreas), "14'");
        report += addLine(Utils.jobCeil16(tallyAreas), "16'");
        report += "\n";
        report += "Fire Resistant\n";
        report += addLine(Utils.jobFire8(tallyAreas), "8'");
        report += addLine(Utils.jobFire9(tallyAreas), "9'");
        report += addLine(Utils.jobFire10(tallyAreas), "10'");
        report += addLine(Utils.jobFire12(tallyAreas), "12'");
        report += addLine(Utils.jobFire14(tallyAreas), "14'");
        report += addLine(Utils.jobFire16(tallyAreas), "16'");
        report += "\n";
        report += "Mold Resistant\n";
        report += addLine(Utils.jobMold8(tallyAreas), "8'");
        report += addLine(Utils.jobMold8(tallyAreas), "12'");
        report += "\n";
        report += printHalfCeilingTotal();
        return report;
    }

    private String printHalfCeilingTotal() {
        String report = "";
        TallyArea jobAreas = Utils.getJobTally(job.jobName(), tallyAreas);
        Integer[] half = Report.getHalfTallies(jobAreas);
        Integer[] ceiling = Report.getCeilTallies(jobAreas);
        if (Report.getTallySum(half) > 0 && Report.getTallySum(ceiling) > 0) {
            report += "1/2\" Lite & Ceiling Total\n";
            report += addLine(Utils.tallyCeil8(jobAreas) + Utils.tallyHalf8(jobAreas), "8'");
            report += addLine(Utils.tallyCeil9(jobAreas) + Utils.tallyHalf9(jobAreas), "9'");
            report += addLine(Utils.tallyCeil10(jobAreas) + Utils.tallyHalf10(jobAreas), "10'");
            report += addLine(Utils.tallyCeil12(jobAreas) + Utils.tallyHalf12(jobAreas), "12'");
            report += addLine(Utils.tallyCeil14(jobAreas) + Utils.tallyHalf14(jobAreas), "14'");
            report += addLine(Utils.tallyCeil16(jobAreas) + Utils.tallyHalf16(jobAreas), "16'");
        }
        return report;
    }

    private String printAreaTotals() {
        String report = "";
        for (TallyArea area : tallyAreas) {

            Integer[] half = Report.getHalfTallies(area);
            Integer[] halfStretch = Report.getHalfStretchTallies(area);
            Integer[] five = Report.getFiveETallies(area);
            Integer[] fiveS = Report.getFiveEStretchTallies(area);
            Integer[] ceiling = Report.getCeilTallies(area);
            Integer[] fire = Report.getFireTallies(area);
            Integer[] mold = Report.getMoldTallies(area);

            if (Utils.areaTotalFt(area) == 0) {
                report += "\n----------------------------\n";
                report += "Area: " +area.areaName() + " is empty\n";
                report += "----------------------------\n";
                continue;
            }

            report += "\n\n----------------------------\n";
            report += "Area: " +area.areaName() + "\n";
            report += "----------------------------\n";

            if (Report.getTallySum(half) > 0) {
                report += "1/2\" Lite\n";
                report += addLine(Utils.tallyHalf8(area), "8'");
                report += addLine(Utils.tallyHalf9(area), "9'");
                report += addLine(Utils.tallyHalf10(area), "10'");
                report += addLine(Utils.tallyHalf12(area), "12'");
                report += addLine(Utils.tallyHalf14(area), "14'");
                report += addLine(Utils.tallyHalf16(area), "16'");
                report += "\n";
            }

            if (Report.getTallySum(halfStretch) > 0) {
                report += "1/2\" Stretch\n";
                report += addLine(Utils.tallyHalfS12(area), "12'");
                report += addLine(Utils.tallyHalfS14(area), "14'");
                report += addLine(Utils.tallyHalfS16(area), "16'");
                report += "\n";
            }

            if (Report.getTallySum(five) > 0) {
                report += "5/8\" Regular\n";
                report += addLine(Utils.tallyFiveE8(area), "8'");
                report += addLine(Utils.tallyFiveE9(area), "9'");
                report += addLine(Utils.tallyFiveE10(area), "10'");
                report += addLine(Utils.tallyFiveE12(area), "12'");
                report += addLine(Utils.tallyFiveE14(area), "14'");
                report += "\n";
            }

            if (Report.getTallySum(fiveS) > 0) {
                report += "5/8\" Stretch\n";
                report += addLine(Utils.tallyFiveES12(area), "12'");
                report += "\n";
            }

            if (Report.getTallySum(ceiling) > 0) {
                report += "Ceilings\n";
                report += addLine(Utils.tallyCeil8(area), "8'");
                report += addLine(Utils.tallyCeil9(area), "9'");
                report += addLine(Utils.tallyCeil10(area), "10'");
                report += addLine(Utils.tallyCeil12(area), "12'");
                report += addLine(Utils.tallyCeil14(area), "14'");
                report += addLine(Utils.tallyCeil16(area), "16'");
                report += "\n";
            }

            if (Report.getTallySum(fire) > 0) {
                report += "Fire Resistant\n";
                report += addLine(Utils.tallyFire8(area), "8'");
                report += addLine(Utils.tallyFire9(area), "9'");
                report += addLine(Utils.tallyFire10(area), "10'");
                report += addLine(Utils.tallyFire12(area), "12'");
                report += addLine(Utils.tallyFire14(area), "14'");
                report += addLine(Utils.tallyFire16(area), "16'");
                report += "\n";
            }

            if (Report.getTallySum(mold) > 0) {
                report += "Mold Resistant\n";
                report += addLine(Utils.tallyMold8(area), "8'");
                report += addLine(Utils.tallyMold8(area), "12'");
                report += "\n";
            }
            
            if (Report.getTallySum(half) > 0 && Report.getTallySum(ceiling) > 0) {
                report += "1/2\" Lite & Ceiling Total\n";
                report += addLine(Utils.tallyCeil8(area) + Utils.tallyHalf8(area), "8'");
                report += addLine(Utils.tallyCeil9(area) + Utils.tallyHalf9(area), "9'");
                report += addLine(Utils.tallyCeil10(area) + Utils.tallyHalf10(area), "10'");
                report += addLine(Utils.tallyCeil12(area) + Utils.tallyHalf12(area), "12'");
                report += addLine(Utils.tallyCeil14(area) + Utils.tallyHalf14(area), "14'");
                report += addLine(Utils.tallyCeil16(area) + Utils.tallyHalf16(area), "16'");
            }
        }

        return report;
    }

    private String heightCharges(List<HeightCharge> heightChargeList) {
        String str = "";
        if (heightChargeList.size() > 0) {
            str += "Height Charges\n";
        }
        for (HeightCharge charge : heightChargeList) {
            str += charge.heightCharge() + "\n";
        }
        str += (str.length() > 0) ? "\n" : "";
        return str;
    }

    private String getComment(Job job) {
        String commment = job.comment();
        if (commment.length() > 0) {
            return "Comment:\n" + commment + "\n\n";
        } else return "";
    }

    private String getAddress(Job job) {
        String address = job.address();
        if (address.length() > 0){
            return "Address:\n" + address;
        } else {
            return "Address: not added";
        }
    }

    public String addLine(int value, String label) {
        return String.format(
                "%-5s-   %s\n",
                value,
                label
        );
    }
}
