//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.R;
import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Util.TypefaceUtil;

/**
 * Recycler Adapter for School Detail
 */
public class SchoolDetailRecyclerAdapter extends
        RecyclerView.Adapter<SchoolDetailRecyclerAdapter.SchoolDetailItemViewHolder> {

    private LinkedHashMap<String, String> mItems;
    private List<String> mItemKeys;

    public SchoolDetailRecyclerAdapter(){
        this.mItems = new LinkedHashMap<>();
        this.mItemKeys = new ArrayList<>();
    }

    @NonNull
    @Override
    public SchoolDetailItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_detail_item_layout, parent, false);
        return new SchoolDetailItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolDetailItemViewHolder holder, int position) {
        final SchoolDetailItemViewHolder viewHolder = (SchoolDetailItemViewHolder) holder;

        viewHolder.mItemTitleLabel.setTypeface(TypefaceUtil.get(TypefaceUtil.METROPOLIS_MEDIUM));
        viewHolder.mItemTitleDescription.setTypeface(TypefaceUtil.get(TypefaceUtil.METROPOLIS_LIGHT));

        String title = mItemKeys.get(position);
        String description = mItems.get(title);
        viewHolder.mItemTitleLabel.setText(title);
        viewHolder.mItemTitleDescription.setText(description);

    }

    @Override
    public int getItemCount() {
        return mItemKeys.size();
    }

    public void onUpdateSchoolDetail(LinkedHashMap<String, String> itemMap){
        this.mItems.clear();
        this.mItems = itemMap;

        this.mItemKeys.clear();
        this.mItemKeys = new ArrayList<>(mItems.keySet());
    }

    static class SchoolDetailItemViewHolder extends RecyclerView.ViewHolder {

        TextView mItemTitleLabel;
        TextView mItemTitleDescription;

        public SchoolDetailItemViewHolder(View itemView) {
            super(itemView);

            mItemTitleLabel = (TextView) itemView.findViewById(R.id.school_detail_item_title_label);
            mItemTitleDescription = (TextView) itemView.findViewById(R.id.school_detail_item_description_label);

        }

    }
}
