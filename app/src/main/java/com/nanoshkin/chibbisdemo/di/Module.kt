package com.nanoshkin.chibbisdemo.di

import com.nanoshkin.chibbisdemo.BuildConfig
import com.nanoshkin.chibbisdemo.data.network.api.HitsApi
import com.nanoshkin.chibbisdemo.data.network.api.RestaurantsApi
import com.nanoshkin.chibbisdemo.data.network.api.ReviewsApi
import com.nanoshkin.chibbisdemo.data.repository.hits.HitsRepository
import com.nanoshkin.chibbisdemo.data.repository.hits.HitsRepositoryImpl
import com.nanoshkin.chibbisdemo.data.repository.restaurants.RestaurantsRepository
import com.nanoshkin.chibbisdemo.data.repository.restaurants.RestaurantsRepositoryImpl
import com.nanoshkin.chibbisdemo.data.repository.reviews.ReviewsRepository
import com.nanoshkin.chibbisdemo.data.repository.reviews.ReviewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantsApi {
        return retrofit
            .create(RestaurantsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHitsApi(retrofit: Retrofit): HitsApi {
        return retrofit
            .create(HitsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewsApi(retrofit: Retrofit): ReviewsApi {
        return retrofit
            .create(ReviewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestaurantRepository(restaurantsApi: RestaurantsApi): RestaurantsRepository {
        return RestaurantsRepositoryImpl(restaurantsApi = restaurantsApi)
    }

    @Provides
    @Singleton
    fun provideHitsRepository(hitsApi: HitsApi): HitsRepository {
        return HitsRepositoryImpl(hitsApi = hitsApi)
    }

    @Provides
    @Singleton
    fun provideReviewsRepository(reviewsApi: ReviewsApi): ReviewsRepository {
        return ReviewsRepositoryImpl(reviewsApi = reviewsApi)
    }
}