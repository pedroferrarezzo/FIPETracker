package br.com.fiap.fipetracker.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.fipetracker.model.Veiculo

@Database(entities = [Veiculo::class], version = 2)
abstract class InstanceDatabase : RoomDatabase() {

    abstract fun veiculoDao(): VeiculoDao
    abstract fun fipeTrackerDbDao(): FipeTrackerDbDao

    // Padrão Singleton
    companion object {

        private lateinit var instance: InstanceDatabase

        fun getDatabase(context: Context): InstanceDatabase {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        InstanceDatabase::class.java,
                        "FIPETRACKER_DB"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}