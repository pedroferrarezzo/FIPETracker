package br.com.fiap.fipetracker.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.fipetracker.model.CodigoFipeAndMesReferencia
import kotlinx.coroutines.CoroutineScope

class VeiculoScreenViewModel : ViewModel() {

    private val _codigoFipeAndMesReferencia = MutableLiveData<CodigoFipeAndMesReferencia>()
    val codigoFipeAndMesReferencia: LiveData<CodigoFipeAndMesReferencia> =
        _codigoFipeAndMesReferencia

    fun setCodigoFipeAndMesReferencia(codigoFipe: String, mesReferencia: String) {
        _codigoFipeAndMesReferencia.value = CodigoFipeAndMesReferencia(codigoFipe, mesReferencia)
    }

    private val _expandedDropdownMenu = MutableLiveData<Boolean>()
    val expandedDropdownMenu: LiveData<Boolean> = _expandedDropdownMenu
    fun onExpandedDropdownMenu(expanded: Boolean) {
        _expandedDropdownMenu.value = expanded
    }

    private val _indexListVeiculoFipe = MutableLiveData<Int>()
    val indexListVeiculoFipe: LiveData<Int> = _indexListVeiculoFipe
    fun onIndexListVeiculoFipeChanged(index: Int) {
        _indexListVeiculoFipe.value = index
    }

    private val _scope = MutableLiveData<CoroutineScope>()
    val scope: LiveData<CoroutineScope> = _scope
}