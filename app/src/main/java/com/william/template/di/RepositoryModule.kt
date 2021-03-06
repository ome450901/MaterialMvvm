package com.william.template.di

import com.william.template.database.AppDatabase
import com.william.template.network.TmdbApi
import com.william.template.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author WeiYi Yu
 * @date 2020-07-08
 */

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(appDatabase: AppDatabase, tmdbApi: TmdbApi): MovieRepository {
        return MovieRepository(appDatabase, tmdbApi)
    }

}