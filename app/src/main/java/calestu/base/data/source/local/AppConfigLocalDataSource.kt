package calestu.base.data.source.local

import calestu.base.data.Result
import calestu.base.data.entity.AppConfig

interface AppConfigLocalDataSource {

    suspend fun getAppConfigByVersion(versionCode: Int): Result<AppConfig?>

    suspend fun saveAppConfig(appConfig: AppConfig)

    suspend fun completeAppConfig(appConfig: AppConfig)

    suspend fun completeAppConfig(entryid: String)

    suspend fun deleteAll()

    suspend fun deleteAppConfig(entryid: String)
}
