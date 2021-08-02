//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter;

import java.util.List;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;

/**
 * Schools list contract that binds interfaces
 * for School list presenter and view
 */
public interface SchoolsListContract {

    interface ISchoolsListView extends IView {

        void updateSchools(List<School> schools);

        void showError(String message);

        void showSchoolDetail(School school);
    }

    interface ISchoolsListPresenter extends IPresenter<ISchoolsListView>{

        void searchFor(String searchTerm);

        void onSchoolSelection(School selectedSchool);
    }
}
