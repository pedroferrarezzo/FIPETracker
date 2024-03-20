package br.com.fiap.fipetracker.database.repository

import android.content.Context
import br.com.fiap.fipetracker.database.dao.InstanceDatabase

class FipeTrackerDbRepository(context: Context) {

    private val fipetrackerDbDao = InstanceDatabase.getDatabase(context).fipeTrackerDbDao()
    private val db = InstanceDatabase.getDatabase(context)

    fun excluirTodasTabelas() {
        return db.clearAllTables()
    }

    fun limparPrimaryKeySequence() {
        return fipetrackerDbDao.clearPrimaryKeyIndex()
    }
}