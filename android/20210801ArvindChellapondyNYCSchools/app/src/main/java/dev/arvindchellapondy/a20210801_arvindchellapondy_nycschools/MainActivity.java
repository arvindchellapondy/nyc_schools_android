//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter.SchoolsListContract;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter.SchoolsListPresenter;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.View.SchoolDetailFragment;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.View.SchoolsListRecyclerAdapter;

/**
 * Main Activity
 * View - displays the school list
 */
public class MainActivity extends AppCompatActivity implements SchoolsListContract.ISchoolsListView, SchoolsListRecyclerAdapter.SchoolListInterface {

    private SchoolsListPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private SchoolsListRecyclerAdapter mRecyclerAdapter;

    protected int mFragmentContainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.appTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new SchoolsListPresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    private void initView(){
        mProgressBar = (ProgressBar) findViewById(R.id.progress_circular);
        mRecyclerView = (RecyclerView) findViewById(R.id.schools_recyceler_view);

        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerAdapter = new SchoolsListRecyclerAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecyclerView.getContext(),
                llm.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(SEARCH_SERVICE);
        androidx.appcompat.widget.SearchView searchView =
                (androidx.appcompat.widget.SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.searchFor(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) < 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    private void switchFragment(Fragment fragment, boolean addToBackStack){
        mFragmentContainerId = R.id.fragment_container;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(!addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.replace(mFragmentContainerId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    @Override
    public void updateSchools(List<School> schools) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerAdapter.onUpdateItems(schools);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSchoolDetail(School school) {
        Fragment fragment = SchoolDetailFragment.createInstance(school);
        switchFragment(fragment,false);
    }

    @Override
    public void onSchoolSelection(School school) {
        mPresenter.onSchoolSelection(school);
    }
}