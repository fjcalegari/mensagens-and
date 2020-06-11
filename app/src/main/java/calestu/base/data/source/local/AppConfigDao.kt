package calestu.base.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import calestu.base.data.entity.AppConfig

@Dao
interface AppConfigDao {

    @Query("SELECT * FROM AppConfig WHERE versionCode = :versionCode")
    suspend fun getAppConfigByVersion(versionCode: Int): AppConfig?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppConfig(appConfig: AppConfig)

    @Query("UPDATE AppConfig SET completed = :completed WHERE entryid = :entryid")
    suspend fun updateCompleted(entryid: String, completed: Boolean)

    @Query("DELETE FROM AppConfig WHERE entryid = :entryid")
    suspend fun deleteAppConfigById(entryid: String): Int

    @Query("DELETE FROM AppConfig")
    suspend fun deleteAppConfig()

}
