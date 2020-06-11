package calestu.base.data.source.local

import androidx.room.*
import calestu.base.data.entity.Categoria

@Dao
interface CategoriasDao {

    @Query("SELECT * FROM Categorias")
    suspend fun getCategorias(): List<Categoria>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Categoria>)

}
