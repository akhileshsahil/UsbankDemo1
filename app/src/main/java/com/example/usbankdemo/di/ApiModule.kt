package com.example.usbankdemo.di

import android.content.Context
import com.example.usbankdemo.Constants
import com.example.usbankdemo.api.Api
import com.example.usbankdemo.repository.Repository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun getApiInterface(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    internal fun getRepository(api: Api, context: Context): Repository {
        return Repository(api, context)
    }

   /* @Provides
    @Singleton
    internal fun provideViewModel(repository: Repository): ViewModelFactory {
        return ViewModelFactory(repository)
    }*/
}
