package com.slyworks.gads2020_android_project.network;

import com.slyworks.gads2020_android_project.models.TopLearner;
import com.slyworks.gads2020_android_project.models.Skill_IQLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GadsApi {

    @GET("api/hours")
    Call<List<TopLearner>> getHours();

    @GET("api/skilliq")
    Call<List<Skill_IQLeader>> getIqScores();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> submitProject(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.284483984") String projectLink
    );

}
