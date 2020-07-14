package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.entity.Categoria
import calestu.base.data.entity.Frase

interface FraseRepository {

    suspend fun getFrases(): Result<List<Frase>>

    suspend fun getFraseById(fraseId: String): Result<Frase>

    suspend fun getRandom(): Result<Frase>

    suspend fun insertAll(frases: List<Frase>)

}