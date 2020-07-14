package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.Result.Error
import calestu.base.data.Result.Success
import calestu.base.data.entity.Categoria
import calestu.base.data.source.local.CategoriaLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    private val categoriaLocalDataSource: CategoriaLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CategoriaRepository {


    override suspend fun getCategorias(): Result<List<Categoria>> {
        return withContext(ioDispatcher) {

            val localTasks = categoriaLocalDataSource.getCategorias()

            (localTasks as? Success)?.let {
                return@withContext Success(it.data)
            }

            return@withContext Error(Exception("Illegal state"))
        }
    }

    override suspend fun insertAll(categorias: List<Categoria>) {
        withContext(ioDispatcher) {
            categoriaLocalDataSource.insertAll(categorias)
        }
    }

}
