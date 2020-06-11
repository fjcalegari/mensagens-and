package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.entity.Categoria

interface CategoriaRepository {

    suspend fun getCategorias(): Result<List<Categoria>>

    suspend fun insertAll(categorias: List<Categoria>)

}