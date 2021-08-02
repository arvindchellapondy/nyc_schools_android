//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.SatStats;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Service.NycSchoolsService;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Service.ServiceGenerator;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * School list presenter
 */
public class SchoolsListPresenter extends BasePresenter<SchoolsListContract.ISchoolsListView>
        implements SchoolsListContract.ISchoolsListPresenter {

    private static final String TAG = SchoolsListPresenter.class.getSimpleName();
    private static CompositeDisposable sDisposable;

    private List<School> mSchools;
    private HashMap<String, SatStats> mSatStatMap;

    public SchoolsListPresenter(Context context) {
        super(context);
        mSchools = new ArrayList<>();
        mSatStatMap = new HashMap<>();
        sDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(SchoolsListContract.ISchoolsListView view) {
        super.attachView(view);

        getSchools();
        getSatStats();
    }

    @Override
    public void detachView() {
        super.detachView();
        sDisposable.clear();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    /**
     *  Gets schools data
     * @return
     */
    public void getSchools() {

        NycSchoolsService nycSchoolsService = ServiceGenerator.getInstance()
                .getRetrofit().create(NycSchoolsService.class);

        nycSchoolsService.getSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<School>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        sDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<School> schools) {
                        mSchools.clear();
                        mSchools.addAll(schools);
                        if (isViewAttached()) {
                            mView.updateSchools(mSchools);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, e.getLocalizedMessage());
                        if(isViewAttached()){
                            mView.showError("Unable to retrieve data.");
                        }
                    }
                });
    }

    /**
     * Get Sat stats data
     */
    public void getSatStats() {

        NycSchoolsService nycSchoolsService = ServiceGenerator.getInstance()
                .getRetrofit().create(NycSchoolsService.class);

        nycSchoolsService.getSatStats()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<SatStats>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        sDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<SatStats> satStats) {
                        for (int i = 0, satStatsSize = satStats.size(); i < satStatsSize; i++) {
                            mSatStatMap.put(satStats.get(i).getDbn(), satStats.get(i));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void searchFor(String searchTerm) {
        if (!TextUtils.isEmpty(searchTerm)) {
            List<School> filteredList = mSchools
                    .stream()
                    .filter(school -> school.getSchoolName().toLowerCase()
                            .contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());

            if (isViewAttached()) {
                mView.updateSchools(filteredList);
            }
        } else {
            if (isViewAttached()) {
                mView.updateSchools(mSchools);
            }
        }
    }

    @Override
    public void onSchoolSelection(School selectedSchool) {

        selectedSchool.setSatStats(mSatStatMap.get(selectedSchool.getDbn()));

        if(isViewAttached()){
            mView.showSchoolDetail(selectedSchool);
        }
    }
}
