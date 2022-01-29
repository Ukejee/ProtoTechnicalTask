package com.ukejee.prototechnicaltask.data.api.videos

import com.ukejee.prototechnicaltask.data.api.videos.datasource.VideoNetworkDataSource
import com.ukejee.prototechnicaltask.data.api.videos.datasource.VideoRetrofitDataSource
import com.ukejee.prototechnicaltask.data.api.videos.endpoint.VideoEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object VideoDataModule {

    @Provides
    fun provideVideoEndpoint(retrofit: Retrofit): VideoEndpoint {
        return retrofit.create(VideoEndpoint::class.java)
    }

    @Provides
    fun provideVideoNetworkDataSource(videoEndpoint: VideoEndpoint): VideoNetworkDataSource {
        return VideoRetrofitDataSource(videoEndpoint)
    }

    @Provides
    fun provideVideoRepository(videoNetworkDataSource: VideoNetworkDataSource): VideoRepositoryContract {
        return VideoRepository(videoNetworkDataSource)
    }
 }
