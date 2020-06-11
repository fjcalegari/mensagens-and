package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.Result.Success
import calestu.base.data.Result.Error
import calestu.base.data.entity.AppConfig
import calestu.base.data.source.local.AppConfigLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppConfigRepositoryImpl @Inject constructor(
    private val localDataSource: AppConfigLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppConfigRepository {

    override suspend fun getAppConfigByVersion(versionCode: Int): Result<AppConfig> {
        return withContext(ioDispatcher) {

            val localAppConfig = localDataSource.getAppConfigByVersion(versionCode)

            (localAppConfig as? Success)?.let {
                it.data?.let {
                    return@withContext Success(it)
                }
            }

            val newAppConfig = AppConfig(versionCode = versionCode)
            localDataSource.saveAppConfig(newAppConfig)

            return@withContext Success(newAppConfig)
        }
    }

    override suspend fun saveAppConfig(appConfig: AppConfig) = withContext(ioDispatcher) {
        localDataSource.saveAppConfig(appConfig)
    }

    override suspend fun completeAppConfig(appConfig: AppConfig) = withContext(ioDispatcher) {
        localDataSource.completeAppConfig(appConfig)
    }

    override suspend fun completeAppConfig(entryid: String) = withContext(ioDispatcher) {
        localDataSource.completeAppConfig(entryid)
    }


}