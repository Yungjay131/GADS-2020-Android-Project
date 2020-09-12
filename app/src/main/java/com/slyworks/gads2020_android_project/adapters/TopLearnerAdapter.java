package com.slyworks.gads2020_android_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.slyworks.gads2020_android_project.R;
import com.slyworks.gads2020_android_project.models.TopLearner;
import com.slyworks.gads2020_android_project.utils.Constants;

import java.util.List;

/**
 * Created by Joshua Sylvanus, 7:17AM, 09/09/2020.
 */
public class TopLearnerAdapter extends RecyclerView.Adapter<TopLearnerAdapter.TopLearnersViewHolder> {
     //region Vars
     List<TopLearner> mTopLearners;
     Context mContext;
    //endregion
    //constructor
    public TopLearnerAdapter(List<TopLearner> topLearners, Context context){
       mTopLearners = topLearners;
       mContext = context;
    }
    @NonNull
    @Override
    public TopLearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.li_frag_learning_leaders, parent, false);
        return new TopLearnersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopLearnersViewHolder holder, int position) {

        TopLearner topLearner = mTopLearners.get(position);

        String name = topLearner.getName();
        String country = topLearner.getCountry();
        int hour = topLearner.getHours();
        String badgeUrl = topLearner.getBadgeUrl();

        Glide.with(mContext)
              .load(badgeUrl)
             .into(holder.ivBadge);
        holder.name.setText(name);
        holder.details.setText(String.format("%s learning hours, %s", Integer.toString(hour), topLearner.getCountry()));
    }

    @Override
    public int getItemCount() {
        return Constants.RECYCLERVIEW_COUNT;
    }

    class  TopLearnersViewHolder  extends RecyclerView.ViewHolder{
      private TextView name, details;
      private ImageView ivBadge;
    public TopLearnersViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tvTopLearnerName);
        details = itemView.findViewById(R.id.tvTopLearnerDetails);
        ivBadge = itemView.findViewById(R.id.ivTopLearner);
    }
}
}
