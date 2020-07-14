package calestu.base.data.repository

import calestu.base.data.Result
import calestu.base.data.Result.Error
import calestu.base.data.Result.Success
import calestu.base.data.entity.Frase
import calestu.base.data.source.local.CategoriaLocalDataSource
import calestu.base.data.source.local.FraseLocalDataSource
import calestu.base.data.source.local.FrasesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject
import kotlin.random.Random

class FraseRepositoryImpl @Inject constructor(
    private val frasesDao: FrasesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FraseRepository {

    override suspend fun getFraseById(fraseId: String): Result<Frase> = withContext(ioDispatcher) {
        return@withContext try {
            Success(frasesDao.getFraseById(fraseId))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getFrases(): Result<List<Frase>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(frasesDao.getFrases())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getRandom(): Result<Frase> = withContext(ioDispatcher) {
        return@withContext try {
            val totalRows = frasesDao.getCount()
            val rowRandom = Random.nextInt(0, totalRows)
            Success(frasesDao.getRandom(rowRandom))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun insertAll(frases: List<Frase>) = withContext(ioDispatcher) {
        frasesDao.insertAll(frases)
    }


//    private val cachedFrases: ConcurrentMap<String, Frase> = ConcurrentHashMap()
//
//    override suspend fun getFrase(): Result<Frase> {
//        return withContext(ioDispatcher) {
//
//            val rows = 15
//
//            val randInt = Random.nextInt(0, rows)
//
//            val localFrase = localDataSource.getFraseRandom(randInt)
//
//            (localFrase as? Success)?.let {
//
//                cachedFrases.put(it.data.id, it.data)
//
//                return@withContext Success(it.data)
//            }
//
//            return@withContext Error(Exception("Illegal state"))
//        }
//    }

//    override suspend fun getFrase(): Result<Frase> {
//        return withContext(ioDispatcher) {
//
//            val localFrase = localDataSource.getFraseRandom(cachedFrases.keys.toList())
//
//            (localFrase as? Result.Success)?.let {
//
//                cachedFrases.put(it.data.id, it.data)
//
//                return@withContext Result.Success(it.data)
//            }
//
//            return@withContext Result.Error(Exception("Illegal state"))
//        }
//    }

//    override suspend fun getFrases(): Result<List<Frase>> {
//        return withContext(ioDispatcher) {
//
//            val localFrases = localDataSource.getFrases()
//
//            (localFrases as? Result.Success)?.let {
//                return@withContext Result.Success(it.data)
//            }
//
//            return@withContext Result.Error(Exception("Illegal state"))
//        }
//    }
//
//    override suspend fun insertAll(frases: List<Frase>) = withContext(ioDispatcher) {
//        localDataSource.insertAll(frases)
//    }

}