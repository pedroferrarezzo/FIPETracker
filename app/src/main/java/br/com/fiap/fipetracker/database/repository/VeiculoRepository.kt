package br.com.fiap.fipetracker.database.repository

import android.content.Context
import br.com.fiap.fipetracker.database.dao.InstanceDatabase
import br.com.fiap.fipetracker.model.CodigoFipeAndMesReferencia
import br.com.fiap.fipetracker.model.Veiculo


class VeiculoRepository(context: Context) {
    private val veiculoDao = InstanceDatabase.getDatabase(context).veiculoDao()

    fun criarVeiculo(veiculo: Veiculo): Long {
        return veiculoDao.criarVeiculo(veiculo)
    }

    fun excluirVeiculo(params: CodigoFipeAndMesReferencia): Int {
        return veiculoDao.excluirVeiculo(params)
    }

    fun verificaVeiculoDb(codigoFipe: String, mesReferencia: String): Boolean {
        return veiculoDao.verificaVeiculoDb(codigoFipe, mesReferencia)
    }

    fun listaVeiculoSalvos(): List<Veiculo> {
        return veiculoDao.listaVeiculoSalvos()
    }

    fun listaVeiculoByFipeAndMesReferencia(
        codigoFipe: String,
        mesReferencia: String
    ): List<Veiculo> {
        return veiculoDao.listaVeiculoByFipeAndMesReferencia(codigoFipe, mesReferencia)
    }
}