//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.View;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Model.School;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.R;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Util.TypefaceUtil;

/**
 * Recycler adapter for schools list
 */
public class SchoolsListRecyclerAdapter extends
        RecyclerView.Adapter<SchoolsListRecyclerAdapter.SchoolListItemViewHolder> {

    public interface SchoolListInterface{
        void onSchoolSelection(School school);
    }

    private List<School> mItems;

    private SchoolListInterface mListener;

    public SchoolsListRecyclerAdapter(SchoolListInterface listener) {
        this.mItems = new ArrayList<>();
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SchoolListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_list_item_layout, parent, false);
        return new SchoolListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolListItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final SchoolListItemViewHolder viewHolder = (SchoolListItemViewHolder) holder;

        viewHolder.mSchoolNameLabel.setTypeface(TypefaceUtil.get(TypefaceUtil.METROPOLIS_LIGHT));
        viewHolder.mSchoolNameLabel.setText(mItems.get(position).getSchoolName());

        viewHolder.mSchoolNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSchoolSelection(mItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void onUpdateItems(List<School> schools) {
        this.mItems.clear();
        this.mItems.addAll(schools);
        notifyDataSetChanged();
    }

    static class SchoolListItemViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mSchoolNameLayout;
        TextView mSchoolNameLabel;

        public SchoolListItemViewHolder(View itemView) {
            super(itemView);

            mSchoolNameLayout = (ConstraintLayout) itemView.findViewById(R.id.school_name_layout);
            mSchoolNameLabel = (TextView) itemView.findViewById(R.id.school_name_label);
        }

    }
}
