package calestu.base.data.source.local

import calestu.base.data.Result
import calestu.base.data.Result.Error
import calestu.base.data.Result.Success
import calestu.base.data.entity.Categoria
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriaLocalDataSourceImpl internal constructor(
    private val categoriasDao: CategoriasDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CategoriaLocalDataSource {

    override suspend fun getCategorias(): Result<List<Categoria>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(categoriasDao.getCategorias())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun insertAll(categorias: List<Categoria>) = withContext(ioDispatcher) {
        categoriasDao.insertAll(categorias)
    }

}
