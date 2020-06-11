package calestu.base.domain.usecase

import calestu.base.BuildConfig
import calestu.base.data.Result
import calestu.base.data.Result.Error
import calestu.base.data.Result.Success
import calestu.base.data.entity.Categoria
import calestu.base.data.entity.Frase
import calestu.base.data.repository.AppConfigRepository
import calestu.base.data.repository.CategoriaRepository
import calestu.base.data.repository.FraseRepository
import calestu.base.util.CATEGORIA_DATA_FILENAME
import calestu.base.util.FRASES_DATA_FILENAME
import calestu.base.util.JsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class SyncUseCase @Inject constructor(
    private val appConfigRepository: AppConfigRepository,
    private val categoriaRepository: CategoriaRepository,
    private val fraseRepository: FraseRepository,
    private val jsonDataFromAsset: JsonDataFromAsset,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(): Result<Boolean> {
//        val categorias = coroutineScope {
//            return@coroutineScope async { getJsonDataFromAsset(CATEGORIA_DATA_FILENAME) }.await()
//        }

        val appConfigResult = appConfigRepository.getAppConfigByVersion(BuildConfig.VERSION_CODE)

//        val categorias = getJsonDataFromAsset(CATEGORIA_DATA_FILENAME)
//        return Success(categorias.size > 0)

        val forceFetch = (appConfigResult !is Success) || !appConfigResult.data.isCompleted
        if (forceFetch) {

            syncFrases()
            syncCategorias()

            appConfigRepository.completeAppConfig((appConfigResult as Success).data)
            return Success(true)

        } else {
            return Success(false)
        }
//        return Error(Exception("Error fetching from local data source"))

//        return withContext(ioDispatcher) {
//            categorias.let {
//                val len = it.size
//                return@withContext Success(it.size > 0)
//            }
//            delay(2000)
//            return@withContext Success(false)
//        }
    }

    private suspend fun syncFrases() = withContext(ioDispatcher) {
        fraseRepository.insertAll(getFraseJsonDataFromAsset(FRASES_DATA_FILENAME))
    }

    private suspend fun syncCategorias() = withContext(ioDispatcher) {
        categoriaRepository.insertAll(getCategoriaJsonDataFromAsset(CATEGORIA_DATA_FILENAME))
    }

    private suspend fun getFraseJsonDataFromAsset(fileName: String) =
        withContext(Dispatchers.Default) {
            jsonDataFromAsset.getJsonDataFromAsset(fileName)?.let {
                Timber.i("FRASES_DATA_FILENAME: $it")
                val fraseType = object : TypeToken<List<Frase>>() {}.type
                return@withContext Gson().fromJson(it, fraseType) as List<Frase>
            }
            return@withContext emptyList<Frase>()
        }

    private suspend fun getCategoriaJsonDataFromAsset(fileName: String) =
        withContext(Dispatchers.Default) {
            jsonDataFromAsset.getJsonDataFromAsset(fileName)?.let {
                Timber.i("CATEGORIA_DATA_FILENAME: $it")
                val categoriaType = object : TypeToken<List<Categoria>>() {}.type
                return@withContext Gson().fromJson(it, categoriaType) as List<Categoria>
            }
            return@withContext emptyList<Categoria>()
        }

//    applicationContext.assets.open(CATEGORIA_DATA_FILENAME).use { inputStream ->
//        JsonReader(inputStream.reader()).use { jsonReader ->
//            val categoriaType = object : TypeToken<List<Categoria>>() {}.type
//            val categoriasList: List<Categoria> = Gson().fromJson(jsonReader, categoriaType)
//
//            categoriaRepository.insertAll(categoriasList)
////                    val database = AppDatabase.getInstance(applicationContext)
////                    database.plantDao().insertAll(plantList)
//
//            Result.success()
//        }
//    }

}