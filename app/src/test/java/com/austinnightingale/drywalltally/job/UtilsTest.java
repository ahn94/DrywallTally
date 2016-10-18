package com.austinnightingale.drywalltally.job;

import com.austinnightingale.drywalltally.db.TallyArea;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class UtilsTest {

    private TallyArea test;

    @Before
    public void init() {
        test = TallyArea.builder()
                .jobID(0)
                .areaName("test")
                .halfRegEight(6).halfRegNine(6).halfRegTen(6).halfRegTwelve(6).halfRegFourteen(6).halfRegSixteen(6)
                .halfStretchTwelve(6).halfStretchFourteen(6).halfStretchSixteen(6)
                .fiveEighthRegEight(6).fiveEighthRegNine(6).fiveEighthRegTen(6).fiveEighthRegTwelve(6).fiveEighthRegFourteen(6)
                .fiveEightStretchTwelve(6)
                .ceilingEight(6).ceilingNine(6).ceilingTen(6).ceilingTwelve(6).ceilingFourteen(6).ceilingSixteen(6)
                .fireEight(6).fireNine(6).fireTen(6).fireTwelve(6).fireFourteen(6).fireSixteen(6)
                .moldEight(6).moldTwelve(6)
                .build();
    }

    @Test
    public void shouldAddTalliesToSingleTallyArea() {

        List<TallyArea> tallyAreas = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            TallyArea area = TallyArea.builder()
                    .jobID(0)
                    .areaName("job: " + i)
                    .halfRegEight(i)
                    .halfRegNine(i)
                    .halfRegTen(i)
                    .halfRegTwelve(i)
                    .halfRegFourteen(i)
                    .halfRegSixteen(i)
                    .halfStretchTwelve(i)
                    .halfStretchFourteen(i)
                    .halfStretchSixteen(i)
                    .fiveEighthRegEight(i)
                    .fiveEighthRegNine(i)
                    .fiveEighthRegTen(i)
                    .fiveEighthRegTwelve(i)
                    .fiveEighthRegFourteen(i)
                    .fiveEightStretchTwelve(i)
                    .ceilingEight(i)
                    .ceilingNine(i)
                    .ceilingTen(i)
                    .ceilingTwelve(i)
                    .ceilingFourteen(i)
                    .ceilingSixteen(i)
                    .fireEight(i)
                    .fireNine(i)
                    .fireTen(i)
                    .fireTwelve(i)
                    .fireFourteen(i)
                    .fireSixteen(i)
                    .moldEight(i)
                    .moldTwelve(i)
                    .build();
            tallyAreas.add(area);
        }
        assertTrue("combined tallies = total tally for job", test.equals(Utils.getJobTally(test.areaName(), tallyAreas)));

    }

}