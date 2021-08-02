//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for School
 */
public class School implements Parcelable {

    @SerializedName("dbn")
    private String mDbn;

    @SerializedName("school_name")
    private String mSchoolName;

    @SerializedName("boro")
    private String mBoro;

    @SerializedName("overview_paragraph")
    private String mOverviewParagraph;

    @SerializedName("academicopportunities1")
    private String mAcademicOpportunities1;

    @SerializedName("academicopportunities2")
    private String mAcademicOpportunities2;

    @SerializedName("neighborhood")
    private String mNeighborHood;

    @SerializedName("location")
    private String mLocation;

    @SerializedName("phone_number")
    private String mPhoneNumber;

    @SerializedName("fax_number")
    private String mFaxNumber;

    @SerializedName("school_email")
    private String mSchoolEmail;

    @SerializedName("website")
    private String mWebsite;

    @SerializedName("total_students")
    private String mTotalStudents;

    @SerializedName("extracurricular_activities")
    private String mExtracurricularActivities;

    @SerializedName("school_sports")
    private String mSchoolSports;

    @SerializedName("attendance_rate")
    private String mAttendanceRate;

    @SerializedName("primary_address_line_1")
    private String mPrimaryAddressLine1;

    @SerializedName("city")
    private String mCity;

    @SerializedName("zip")
    private String mZip;

    @SerializedName("state_code")
    private String mStateCode;

    @SerializedName("latitude")
    private String mLatitude;

    @SerializedName("longitude")
    private String mLongitude;

    @SerializedName("borough")
    private String mBorough;

    private SatStats mSatStats;

    protected School(Parcel in) {
        mDbn = in.readString();
        mSchoolName = in.readString();
        mBoro = in.readString();
        mOverviewParagraph = in.readString();
        mAcademicOpportunities1 = in.readString();
        mAcademicOpportunities2 = in.readString();
        mNeighborHood = in.readString();
        mLocation = in.readString();
        mPhoneNumber = in.readString();
        mFaxNumber = in.readString();
        mSchoolEmail = in.readString();
        mWebsite = in.readString();
        mTotalStudents = in.readString();
        mExtracurricularActivities = in.readString();
        mSchoolSports = in.readString();
        mAttendanceRate = in.readString();
        mPrimaryAddressLine1 = in.readString();
        mCity = in.readString();
        mZip = in.readString();
        mStateCode = in.readString();
        mLatitude = in.readString();
        mLongitude = in.readString();
        mBorough = in.readString();
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    public String getDbn() {
        return mDbn;
    }

    public String getSchoolName() {
        return mSchoolName;
    }

    public String getBoro() {
        return mBoro;
    }

    public String getOverviewParagraph() {
        return mOverviewParagraph;
    }

    public String getAcademicOpportunities1() {
        return mAcademicOpportunities1;
    }

    public String getAcademicOpportunities2() {
        return mAcademicOpportunities2;
    }

    public String getNeighborHood() {
        return mNeighborHood;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getFaxNumber() {
        return mFaxNumber;
    }

    public String getSchoolEmail() {
        return mSchoolEmail;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getTotalStudents() {
        return mTotalStudents;
    }

    public String getExtracurricularActivities() {
        return mExtracurricularActivities;
    }

    public String getSchoolSports() {
        return mSchoolSports;
    }

    public String getAttendanceRate() {
        return mAttendanceRate;
    }

    public String getPrimaryAddressLine1() {
        return mPrimaryAddressLine1;
    }

    public String getCity() {
        return mCity;
    }

    public String getZip() {
        return mZip;
    }

    public String getStateCode() {
        return mStateCode;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public String getBorough() {
        return mBorough;
    }

    public SatStats getSatStats() {
        return mSatStats;
    }

    public void setSatStats(SatStats satStats){
        this.mSatStats = satStats;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDbn);
        dest.writeString(mSchoolName);
        dest.writeString(mBoro);
        dest.writeString(mOverviewParagraph);
        dest.writeString(mAcademicOpportunities1);
        dest.writeString(mAcademicOpportunities2);
        dest.writeString(mNeighborHood);
        dest.writeString(mLocation);
        dest.writeString(mPhoneNumber);
        dest.writeString(mFaxNumber);
        dest.writeString(mSchoolEmail);
        dest.writeString(mWebsite);
        dest.writeString(mTotalStudents);
        dest.writeString(mExtracurricularActivities);
        dest.writeString(mSchoolSports);
        dest.writeString(mAttendanceRate);
        dest.writeString(mPrimaryAddressLine1);
        dest.writeString(mCity);
        dest.writeString(mZip);
        dest.writeString(mStateCode);
        dest.writeString(mLatitude);
        dest.writeString(mLongitude);
        dest.writeString(mBorough);
    }
}
