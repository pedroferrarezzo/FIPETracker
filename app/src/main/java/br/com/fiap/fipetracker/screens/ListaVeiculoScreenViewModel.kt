package br.com.fiap.fipetracker.screens

import androidx.lifecycle.MutableLiveData
import br.com.fiap.fipetracker.database.repository.FipeTrackerDbRepository
import br.com.fiap.fipetracker.database.repository.VeiculoRepository
import br.com.fiap.fipetracker.model.Veiculo

class ListaVeiculoScreenViewModel {
    private val _veiculoRepository = MutableLiveData<VeiculoRepository>()
    val veiculoRepository = _veiculoRepository

    private val _fipeTrackerDbRepository = MutableLiveData<FipeTrackerDbRepository>()
    val fipeTrackerDbRepository = _fipeTrackerDbRepository

    private val _listVeiculoSalvo = MutableLiveData<List<Veiculo>>()
    val listVeiculoSalvo = _listVeiculoSalvo
    fun onListVeiculoSalvoChanged(listVeiculo: List<Veiculo>) {
        _listVeiculoSalvo.value = listVeiculo
    }

    private val _listVeiculoFIPE = MutableLiveData<List<Veiculo>>()
    val listVeiculoFIPE = _listVeiculoFIPE
    fun onListVeiculoFipeChanged(listVeiculo: List<Veiculo>) {
        _listVeiculoFIPE.value = listVeiculo
    }

}