package calestu.base.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val index: Int,
    val name: String
) {
}
