package calestu.base.di

import calestu.base.data.source.local.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
object LocalModule {

    @Provides
    fun provideAppConfigLocalDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): AppConfigLocalDataSource {
        return AppConfigLocalDataSourceImpl(
            database.appConfigDao(), ioDispatcher
        )
    }

    @Provides
    fun provideCategoriaLocalDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): CategoriaLocalDataSource {
        return CategoriaLocalDataSourceImpl(
            database.categoriasDao(), ioDispatcher
        )
    }

    @Provides
    fun provideFraseLocalDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): FraseLocalDataSource {
        return FraseLocalDataSourceImpl(
            database.frasesDao(), ioDispatcher
        )
    }

}
