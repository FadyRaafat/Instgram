package com.fady.instgramclone.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tapadoo.alerter.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val REQUEST_TIME_OUT: Long = 5
    private const val HEADER_ACCEPT = "Accept"
    private const val HEADER_CONTENT_TYPE = "Content-Type"
    private const val HEADER_ACCEPT_VALUE = "application/json"

    @Provides
    @Singleton
    fun provideHeadersInterceptor() = Interceptor { chain ->
        var newRequest = chain.request()
        val url = newRequest.url.newBuilder().build()

        newRequest = newRequest.newBuilder().addHeader(HEADER_ACCEPT, HEADER_ACCEPT_VALUE)
            .addHeader(HEADER_CONTENT_TYPE, HEADER_ACCEPT_VALUE).url(url).build()

        chain.proceed(newRequest)
    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        logging: HttpLoggingInterceptor,
    ): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder().readTimeout(REQUEST_TIME_OUT, TimeUnit.MINUTES)
                .connectTimeout(REQUEST_TIME_OUT, TimeUnit.MINUTES)
                .addInterceptor(headersInterceptor).addNetworkInterceptor(logging).build()
        } else {
            OkHttpClient.Builder().readTimeout(REQUEST_TIME_OUT, TimeUnit.MINUTES)
                .connectTimeout(REQUEST_TIME_OUT, TimeUnit.MINUTES)
                .addInterceptor(headersInterceptor).build()
        }
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://jsonplaceholder.typicode.com/").build()
}