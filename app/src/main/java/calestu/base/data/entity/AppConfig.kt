package calestu.base.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "appConfig")
data class AppConfig(
    @PrimaryKey @ColumnInfo(name = "entryid") val id: String = UUID.randomUUID().toString(),
    val versionCode: Int,
    @ColumnInfo(name = "completed") val isCompleted: Boolean = false
) {
}
