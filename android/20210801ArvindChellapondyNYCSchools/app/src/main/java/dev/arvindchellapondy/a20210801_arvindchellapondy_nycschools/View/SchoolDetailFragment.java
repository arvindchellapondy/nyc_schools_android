//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.NycSchoolsApplication;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter.SchoolDetailContract;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Presenter.SchoolDetailPresenter;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.R;

/**
 * School Detail Fragment
 */
public class SchoolDetailFragment extends Fragment implements SchoolDetailContract.ISchoolDetailView {

    private static final String TAG = SchoolDetailFragment.class.getSimpleName();
    private static final String SCHOOL_DETAIL = "school_detail";

    private RecyclerView mRecyclerView;

    private SchoolDetailRecyclerAdapter mRecyclerAdapter;

    private SchoolDetailPresenter mPresenter;

    public static SchoolDetailFragment createInstance(School school)
    {
        SchoolDetailFragment fragment = new SchoolDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(SCHOOL_DETAIL, school);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        mPresenter = new SchoolDetailPresenter(NycSchoolsApplication.getContext());
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_school_detail, container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.school_detail_recyclerview);
        initView();

        assert getArguments() != null;
        mPresenter.onGetSchoolDetailData((School) getArguments().getParcelable(SCHOOL_DETAIL));
        return view;
    }

    private void initView(){
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerAdapter = new SchoolDetailRecyclerAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecyclerView.getContext(),
                        llm.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void updateNavBarTitle(String title){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void updateSchoolDetailItems(LinkedHashMap<String, String> items) {
        mRecyclerAdapter.onUpdateSchoolDetail(items);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
