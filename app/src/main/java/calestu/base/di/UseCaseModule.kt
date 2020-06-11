package calestu.base.di

import calestu.base.data.repository.AppConfigRepository
import calestu.base.data.repository.CategoriaRepository
import calestu.base.data.repository.FraseRepository
import calestu.base.domain.usecase.SyncUseCase
import calestu.base.util.JsonDataFromAsset
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
object UseCaseModule {

    @Provides
    fun provideSyncUseCase(
        appConfigRepository: AppConfigRepository,
        categoriaRepository: CategoriaRepository,
        fraseRepository: FraseRepository,
        jsonDataFromAsset: JsonDataFromAsset,
        ioDispatcher: CoroutineDispatcher
    ): SyncUseCase {
        return SyncUseCase(
            appConfigRepository,
            categoriaRepository,
            fraseRepository,
            jsonDataFromAsset,
            ioDispatcher
        )
    }

}
