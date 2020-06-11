package calestu.base.data.source.local

import calestu.base.data.Result
import calestu.base.data.entity.Categoria

interface CategoriaLocalDataSource {

    suspend fun getCategorias(): Result<List<Categoria>>

    suspend fun insertAll(categorias: List<Categoria>)

}
