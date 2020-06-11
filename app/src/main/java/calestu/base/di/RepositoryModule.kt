package calestu.base.di

import calestu.base.data.repository.*
import calestu.base.data.source.local.AppConfigLocalDataSource
import calestu.base.data.source.local.AppDatabase
import calestu.base.data.source.local.CategoriaLocalDataSource
import calestu.base.data.source.local.FraseLocalDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    fun provideAppConfigRepository(
        appConfigLocalDataSource: AppConfigLocalDataSource,
        ioDispatcher: CoroutineDispatcher
    ): AppConfigRepository {
        return AppConfigRepositoryImpl(
            appConfigLocalDataSource,
            ioDispatcher
        )
    }

    @Provides
    fun provideCategoriaRepository(
        categoriaLocalDataSource: CategoriaLocalDataSource,
        ioDispatcher: CoroutineDispatcher
    ): CategoriaRepository {
        return CategoriaRepositoryImpl(
            categoriaLocalDataSource,
            ioDispatcher
        )
    }

    @Provides
    fun provideFraseRepository(
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): FraseRepository {
        return FraseRepositoryImpl(
            appDatabase.frasesDao(),
            ioDispatcher
        )
    }

}
