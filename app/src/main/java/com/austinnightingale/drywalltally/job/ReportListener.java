package com.austinnightingale.drywalltally.job;

/**
 * Created by Austin on 10/19/2016.
 */

public interface ReportListener {
    void sendReportWithOptions(boolean totalsOnly, boolean includeOptions, boolean includeHeightcharges);
}
