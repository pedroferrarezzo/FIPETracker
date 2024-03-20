package br.com.fiap.fipetracker.screens

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.fipetracker.functions.checkInternetConnectivity

class ConsultaVeiculoScreenViewModel : ViewModel() {

    private val _codigoFipe = MutableLiveData<String>()
    val codigoFipe = _codigoFipe
    fun onCodigoFipeChanged(codigoFIPE: String) {
        _codigoFipe.value = codigoFIPE
    }

    private val _tipoVeiculo = MutableLiveData<Int>()
    val tipoVeiculo = _tipoVeiculo
    fun onTipoVeiculoChanged(tipo: Int) {
        _tipoVeiculo.value = tipo
    }

    private val _isError = MutableLiveData<Boolean>()
    val isError = _isError
    fun onIsErrorChanged(status: Boolean) {
        _isError.value = status
    }

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected = _isConnected
    fun verifyInternet(context: Context) {
        _isConnected.value = checkInternetConnectivity(context)
    }

    private val _context = MutableLiveData<Context>()
    val context = _context
}