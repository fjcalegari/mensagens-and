package calestu.base.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import calestu.base.data.entity.Frase

@Dao
interface FrasesDao {

    @Query("SELECT * FROM Frases LIMIT 1 OFFSET :rnum")
    suspend fun getRandom(rnum: Int): Frase

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(frases: List<Frase>)

    @Query("SELECT COUNT(id) FROM Frases")
    suspend fun getCount(): Int

}
