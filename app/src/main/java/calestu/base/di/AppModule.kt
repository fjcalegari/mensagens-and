package calestu.base.di

import android.content.Context
import androidx.room.Room
import calestu.base.data.source.local.AppDatabase
import calestu.base.util.DATABASE_NAME
import calestu.base.util.JsonDataFromAsset
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideJsonDataFromAsset(context: Context): JsonDataFromAsset {
        return JsonDataFromAsset(
            context.applicationContext
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}
