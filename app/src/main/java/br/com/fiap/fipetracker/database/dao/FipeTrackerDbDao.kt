package br.com.fiap.fipetracker.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FipeTrackerDbDao {
    @Query("DELETE FROM sqlite_sequence")
    fun clearPrimaryKeyIndex()
}