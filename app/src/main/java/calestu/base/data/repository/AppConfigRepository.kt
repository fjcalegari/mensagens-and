package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.entity.AppConfig

interface AppConfigRepository {

    suspend fun getAppConfigByVersion(versionCode: Int): Result<AppConfig>

    suspend fun saveAppConfig(appConfig: AppConfig)

    suspend fun completeAppConfig(appConfig: AppConfig)

    suspend fun completeAppConfig(entryid: String)

}