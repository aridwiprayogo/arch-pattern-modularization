package app.isfaaghyth.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.isfaaghyth.network.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by isfaaghyth on 20/04/19.
 * github: @isfaaghyth
 */

const val REQUEST_TIME_OUT = 60L

inline fun <reified T> services(): T {
    val gson = GsonBuilder()
        /**
         * setFieldNamingPolicy()
         * for convert lowercase with underscores
         * json:`user_name`, you can use `userName` as variable
         */
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    return retrofit.create(T::class.java)
}

inline fun <reified T> serviceCoroutines(): T {
    val gson = GsonBuilder()
        /**
         * setFieldNamingPolicy()
         * for convert lowercase with underscores
         * json:`user_name`, you can use `userName` as variable
         */
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

    return retrofit.create(T::class.java)
}