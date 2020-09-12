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
import com.slyworks.gads2020_android_project.adapters.TopLearnerAdapter;
import com.slyworks.gads2020_android_project.models.TopLearner;
import com.slyworks.gads2020_android_project.network.ApiClient;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by Joshua Sylvanus, 9:54PM, 06/09/2020.
 */
public class Fragment_learning_leaders extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    //region Vars
    private RecyclerView rvFragLearningLeaders;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;
    private TopLearnerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private WeakReference<Context> mContext;
    private Call<List<TopLearner>> mTopLearnerCall;
    //endregion

    public Fragment_learning_leaders(Context context){
        mContext  = new WeakReference<>(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void getTopLearners() {
        mTopLearnerCall = ApiClient.getHoursApi().getHours();

        mProgressBar.setVisibility(View.VISIBLE);
        mTopLearnerCall.enqueue(new Callback<List<TopLearner>>() {
            @Override
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: successful");

                    mProgressBar.setVisibility(View.GONE);
                    List<TopLearner> learners = response.body();
                    mAdapter = new TopLearnerAdapter(learners, getContext());
                    mAdapter.notifyDataSetChanged();
                    rvFragLearningLeaders.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<TopLearner>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);

                Activity activity = ((Activity)getContext());

                Snackbar.make(activity.findViewById(R.id.fragLearningLeaders_layout), "An error occurred", Snackbar.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onFailure: An error occurred: " + t.getMessage());
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = view.findViewById(R.id.progressBar);
        rvFragLearningLeaders = view.findViewById(R.id.rvFragLearningLeaders);
        rvFragLearningLeaders.setLayoutManager(new LinearLayoutManager(mContext.get()));
        mSwipeRefreshLayout = view.findViewById(R.id.frag_learning_leaders_srLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getTopLearners();
    }


    @Override
    public void onRefresh() {
        //do network call again
        if(mTopLearnerCall.isExecuted()){
            getTopLearners();
        }

    }
}