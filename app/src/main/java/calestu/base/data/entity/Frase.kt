package calestu.base.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "frases")
data class Frase(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val frase: String,
    val autor: String? = ""
) {
}
