package com.slyworks.gads2020_android_project.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit() {

        String baseUrl = "https://gadsapi.herokuapp.com/";

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Retrofit getSubmitRetrofit() {

        String submitUrl = "https://docs.google.com/forms/d/e/";
        String fullUrl = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

        return new Retrofit.Builder()
                .baseUrl(submitUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GadsApi getHoursApi() {
        return getRetrofit().create(GadsApi.class);
    }

    public static GadsApi getIqApi() {
        return getRetrofit().create(GadsApi.class);
    }

    public static GadsApi submitProjectApi() {
        return getSubmitRetrofit().create(GadsApi.class);
    }
}
