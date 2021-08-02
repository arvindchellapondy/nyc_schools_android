//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Service Generator Singleton class
 * genrates retorfit service using okhttp client
 */
public class ServiceGenerator {

    private static final int TIMEOUT = 60;
    private static ServiceGenerator mInstance;

    private static final String BASE_URL = "https://data.cityofnewyork.us/";

    public static synchronized ServiceGenerator getInstance() {
        if (mInstance == null) {
            mInstance = new ServiceGenerator();
        }
        return mInstance;
    }

    /**
     * Gets retorfit service
     * @return {@link Retrofit}
     */
    public Retrofit getRetrofit() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(BASE_URL);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.client(getOkHttpClient());
        return retrofitBuilder.build();
    }

    /**
     * Gets OkHttpClient built with interceptor
     * @return {@link OkHttpClient}
     */
    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(httpLoggingInterceptor);
        okHttpBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        return okHttpBuilder.build();
    }

}
