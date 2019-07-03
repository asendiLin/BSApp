package com.sendi.course.inject

import com.sendi.course.api.CourseService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideCourseService(retrofit: Retrofit): CourseService {
        return retrofit.create(CourseService::class.java)
    }

}