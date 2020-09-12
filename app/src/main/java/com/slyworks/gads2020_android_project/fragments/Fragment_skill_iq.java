package com.slyworks.gads2020_android_project.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.slyworks.gads2020_android_project.R;
import com.slyworks.gads2020_android_project.adapters.Skill_IQLeaderAdapter;
import com.slyworks.gads2020_android_project.adapters.TopLearnerAdapter;
import com.slyworks.gads2020_android_project.models.Skill_IQLeader;
import com.slyworks.gads2020_android_project.models.TopLearner;
import com.slyworks.gads2020_android_project.network.ApiClient;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Fragment_skill_iq extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    //region Vars
    private RecyclerView rvFragSkill_IQLeaders;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;
    private Skill_IQLeaderAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private WeakReference<Context> mContext;
    private Call<List<TopLearner>> mTopLearnerCall;
    private Call<List<Skill_IQLeader>> mSkill_iqLeaderCall;
    //endregion


    public Fragment_skill_iq(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = view.findViewById(R.id.progressBar);
        rvFragSkill_IQLeaders = view.findViewById(R.id.rvSkillIQ);
        rvFragSkill_IQLeaders.setLayoutManager(new LinearLayoutManager(mContext.get()));
        mSwipeRefreshLayout = view.findViewById(R.id.frag_skill_IQLeaders_srLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getIqLeaders();
    }

    public void getIqLeaders() {
        mSkill_iqLeaderCall = ApiClient.getIqApi().getIqScores();

        mProgressBar.setVisibility(View.VISIBLE);
        mSkill_iqLeaderCall.enqueue(new Callback<List<Skill_IQLeader>>() {
            @Override
            public void onResponse(Call<List<Skill_IQLeader>> call, Response<List<Skill_IQLeader>> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: successful");

                    mProgressBar.setVisibility(View.GONE);
                    List<Skill_IQLeader> Skill_IQLeaders = response.body();

                    mAdapter = new Skill_IQLeaderAdapter(Skill_IQLeaders, getContext());
                    mAdapter.notifyDataSetChanged();
                    rvFragSkill_IQLeaders.setAdapter(mAdapter);
                }
                else{
                    Log.e(TAG, "onResponse: failed");
                }
            }

            @Override
            public void onFailure(Call<List<Skill_IQLeader>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);

                Activity activity = ((Activity) mContext.get());

                Snackbar.make(activity.findViewById(R.id.fragLearningLeaders_layout), "An error occurred", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(getContext(), "An error occured: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onFailure: An error occured: " + t.getMessage());
            }
        });
    }
    @Override
    public void onRefresh(){
        //do network call again

            getIqLeaders();

    }
}




