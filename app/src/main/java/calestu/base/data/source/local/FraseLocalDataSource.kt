package calestu.base.data.source.local

import calestu.base.data.Result
import calestu.base.data.entity.Frase

interface FraseLocalDataSource {

    suspend fun getRandom(rnum: Int): Result<Frase>

    suspend fun insertAll(frases: List<Frase>)

    suspend fun getCount(): Result<Int>

}
