package calestu.base.data.source.local

import calestu.base.data.Result
import calestu.base.data.Result.Error
import calestu.base.data.Result.Success
import calestu.base.data.entity.Frase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FraseLocalDataSourceImpl @Inject constructor(
    private val frasesDao: FrasesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FraseLocalDataSource {

    override suspend fun getRandom(rnum: Int): Result<Frase>  = withContext(ioDispatcher) {
        return@withContext try {
            Success(frasesDao.getRandom(rnum))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getCount(): Result<Int> = withContext(ioDispatcher) {
        return@withContext try {
            Success(frasesDao.getCount())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun insertAll(frases: List<Frase>) = withContext(ioDispatcher) {
        frasesDao.insertAll(frases)
    }

}