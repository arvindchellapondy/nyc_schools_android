//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter;

import java.util.LinkedHashMap;


import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;

/**
 * School Detail contract that holds the School detail
 * presenter and view interface
 */
public interface SchoolDetailContract {

    interface ISchoolDetailView extends IView {

        void updateSchoolDetailItems(LinkedHashMap<String, String> items);

        void updateNavBarTitle(String title);
    }

    interface ISchoolDetailPresenter extends IPresenter<SchoolDetailContract.ISchoolDetailView> {

        void onGetSchoolDetailData(School school);

    }

}
