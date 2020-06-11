package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.entity.Frase

interface FraseRepository {

    suspend fun getRandom(): Result<Frase>

    suspend fun insertAll(frases: List<Frase>)

}