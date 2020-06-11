package calestu.base.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import calestu.base.data.entity.AppConfig
import calestu.base.data.entity.Categoria
import calestu.base.data.entity.Frase

@Database(entities = [AppConfig::class, Categoria::class, Frase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appConfigDao(): AppConfigDao

    abstract fun categoriasDao(): CategoriasDao

    abstract fun frasesDao(): FrasesDao

}
