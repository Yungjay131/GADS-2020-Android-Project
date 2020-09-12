package com.slyworks.gads2020_android_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.slyworks.gads2020_android_project.R;
import com.slyworks.gads2020_android_project.models.Skill_IQLeader;
import com.slyworks.gads2020_android_project.utils.Constants;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by Joshua Sylvanus, 7:27AM, 09/09/2020.
 */
public class Skill_IQLeaderAdapter extends RecyclerView.Adapter<Skill_IQLeaderAdapter.Skill_IQViewHolder> {
    //region Vars
    private WeakReference<Context> mContext;
    private List<Skill_IQLeader> mSkill_iqLeaders;
    public Skill_IQLeaderAdapter(List<Skill_IQLeader> leaders,Context context){
        mSkill_iqLeaders = leaders;
        mContext = new WeakReference<>(context);
    }
    @NonNull
    @Override
    public Skill_IQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.li_frag_skill_iq, parent, false);
        return new Skill_IQViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Skill_IQViewHolder holder, int position) {
        Skill_IQLeader leader  = mSkill_iqLeaders.get(position);

        String name = leader.getName();
        String country = leader.getCountry();
        int score = leader.getScore();
        String badgeUrl = leader.getBadgeUrl();

        Glide.with(mContext.get())
                .load(badgeUrl)
                .into(holder.ivBadge);

        holder.name.setText(name);
        holder.details.setText(String.format("%s skill IQ Score, %s", String.valueOf(score), country));
    }

    @Override
    public int getItemCount() {
        return Constants.RECYCLERVIEW_COUNT;
    }

    class Skill_IQViewHolder extends RecyclerView.ViewHolder{
     private TextView name, details;
     private ImageView ivBadge;
     public Skill_IQViewHolder(@NonNull View itemView) {
         super(itemView);
         name = itemView.findViewById(R.id.tvSkill_IQName);
         details = itemView.findViewById(R.id.tvSkill_IQDetails);
         ivBadge = itemView.findViewById(R.id.ivSkill_IQ);
     }
 }
}
