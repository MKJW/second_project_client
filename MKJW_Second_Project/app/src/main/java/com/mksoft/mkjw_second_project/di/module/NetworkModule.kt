package com.mksoft.mkjw_second_project.di.module

import com.mksoft.mkjw_second_project.api.RegisterCourseAPI
import com.mksoft.mkjw_second_project.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@Suppress("unused")
class NetworkModule{

    @Provides
    @Singleton
    internal  fun provideRegisterCourseAPI(retrofit: Retrofit): RegisterCourseAPI{
        return retrofit.create(RegisterCourseAPI::class.java)
    }
    //필요한 API여기에 추가
    @Provides
    @Singleton
    internal fun provideRetrofitInterface():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}