//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter;

import android.content.Context;
import android.text.TextUtils;

import java.util.LinkedHashMap;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;

/**
 * School detail presenter
 */
public class SchoolDetailPresenter extends BasePresenter<SchoolDetailContract.ISchoolDetailView>
        implements SchoolDetailContract.ISchoolDetailPresenter {

    public SchoolDetailPresenter(Context context) {
        super(context);
    }

    @Override
    public void attachView(SchoolDetailContract.ISchoolDetailView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public void onGetSchoolDetailData(School school) {

        if(isViewAttached()){
            mView.updateNavBarTitle(school.getSchoolName());
        }

        //constructs disaplaybale model for the view
        LinkedHashMap<String, String> detailItemMap = new LinkedHashMap<>();

        detailItemMap.put("dbn", school.getDbn());
        detailItemMap.put("school name", school.getSchoolName());
        detailItemMap.put("overview", school.getOverviewParagraph());
        detailItemMap.put("address", school.getPrimaryAddressLine1()
                +"\n"+school.getCity()+"\n"+school.getStateCode()+"\n"+school.getZip());
        detailItemMap.put("borough", school.getBorough());
        detailItemMap.put("phone number", school.getPhoneNumber());
        detailItemMap.put("fax number", school.getFaxNumber());
        detailItemMap.put("email", school.getSchoolEmail());
        detailItemMap.put("website", school.getWebsite());

        StringBuilder academicOpportunities = new StringBuilder();
        academicOpportunities.append((!TextUtils.isEmpty(school.getAcademicOpportunities1())) ?
                school.getAcademicOpportunities1() : "N/A");
        academicOpportunities.append((!TextUtils.isEmpty(school.getAcademicOpportunities2())) ?
                school.getAcademicOpportunities2() : "");
        detailItemMap.put("academic opportunities",
                academicOpportunities.toString());

        detailItemMap.put("extracurricular activities",
                (!TextUtils.isEmpty(school.getExtracurricularActivities()) ?
                        school.getExtracurricularActivities() : "N/A"));

        detailItemMap.put("school sports", (!TextUtils.isEmpty(school.getSchoolSports()) ?
                school.getSchoolSports() : "N/A"));

        StringBuilder studentsAttendance = new StringBuilder();
        studentsAttendance.append((!TextUtils.isEmpty(school.getTotalStudents())) ?
                school.getTotalStudents() : "N/A");
        studentsAttendance.append(" | ");
        studentsAttendance.append((!TextUtils.isEmpty(school.getAttendanceRate())) ?
                school.getAttendanceRate() : "N/A");
        detailItemMap.put("total students | attendance rate", studentsAttendance.toString());

        if(school.getSatStats()!=null){
            String testTakers = school.getSatStats().getNumberOfTestTakers();
            String readingScore = school.getSatStats().getCriticalReadingAvgScore();
            String mathScore = school.getSatStats().getMathAvgScore();
            String writingScore = school.getSatStats().getWritingAvgScore();

            detailItemMap.put("no. of sat test takers", (!TextUtils.isEmpty(testTakers)) ?
                    testTakers : "N/A");

            StringBuilder satScores = new StringBuilder();
            satScores.append((!TextUtils.isEmpty(readingScore)) ? readingScore : "N/A");
            satScores.append(" | ");
            satScores.append((!TextUtils.isEmpty(mathScore)) ? mathScore : "N/A");
            satScores.append(" | ");
            satScores.append((!TextUtils.isEmpty(writingScore)) ? writingScore : "N/A");
            detailItemMap.put("avg. sat scores : reading | math | writing", satScores.toString());
        } else{
            detailItemMap.put("no. of sat test takers", "N/A");
            detailItemMap.put("avg. sat scores : reading | math | writing", "N/A");
        }

        if(isViewAttached()){
            mView.updateSchoolDetailItems(detailItemMap);
        }
    }
}

