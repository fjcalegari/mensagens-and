package calestu.base.data.source.local

import calestu.base.data.Result
import calestu.base.data.Result.Error
import calestu.base.data.Result.Success
import calestu.base.data.entity.AppConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppConfigLocalDataSourceImpl @Inject constructor(
    private val appConfigDao: AppConfigDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppConfigLocalDataSource {

    override suspend fun getAppConfigByVersion(versionCode: Int): Result<AppConfig?> =
        withContext(ioDispatcher) {
            try {
                return@withContext Success(appConfigDao.getAppConfigByVersion(versionCode))
            } catch (e: Exception) {
                return@withContext Error(e)
            }
        }

    override suspend fun saveAppConfig(appConfig: AppConfig) = withContext(ioDispatcher) {
        appConfigDao.insertAppConfig(appConfig)
    }

    override suspend fun completeAppConfig(appConfig: AppConfig) = withContext(ioDispatcher) {
        appConfigDao.updateCompleted(appConfig.id, true)
    }

    override suspend fun completeAppConfig(entryid: String) = withContext(ioDispatcher) {
        appConfigDao.updateCompleted(entryid, true)
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        appConfigDao.deleteAppConfig()
    }

    override suspend fun deleteAppConfig(entryid: String) = withContext<Unit>(ioDispatcher) {
        appConfigDao.deleteAppConfigById(entryid)
    }

}