//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for SAT stats
 */
public class SatStats {

    @SerializedName("dbn")
    private String mDbn;

    @SerializedName("school_name")
    private String mSchoolName;

    @SerializedName("num_of_sat_test_takers")
    private String mNumberOfTestTakers;

    @SerializedName("sat_critical_reading_avg_score")
    private String mCriticalReadingAvgScore;

    @SerializedName("sat_math_avg_score")
    private String mMathAvgScore;

    @SerializedName("sat_writing_avg_score")
    private String mWritingAvgScore;

    public String getDbn() {
        return mDbn;
    }

    public String getSchoolName() {
        return mSchoolName;
    }

    public String getNumberOfTestTakers() {
        return mNumberOfTestTakers;
    }

    public String getCriticalReadingAvgScore() {
        return mCriticalReadingAvgScore;
    }

    public String getMathAvgScore() {
        return mMathAvgScore;
    }

    public String getWritingAvgScore() {
        return mWritingAvgScore;
    }
}
