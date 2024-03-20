package br.com.fiap.fipetracker.functions

import br.com.fiap.fipetracker.model.Veiculo
import br.com.fiap.fipetracker.service.BrasilAPIFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun callBrasilAPIFIPE(codigoFIPE: String): List<Veiculo> {

    val callVeiculoService = BrasilAPIFactory().getVeiculoService().getVeiculoByFIPE(codigoFIPE)

    return suspendCoroutine { continuation ->
        callVeiculoService.enqueue(object : Callback<List<Veiculo>> {
            override fun onResponse(call: Call<List<Veiculo>>, response: Response<List<Veiculo>>) {
                response.body()?.let {
                    continuation.resume(it)
                } ?: continuation.resumeWithException(Throwable("INCORRECT_FIPE_CODE"))
            }

            override fun onFailure(call: Call<List<Veiculo>>, t: Throwable) {
                continuation.resumeWithException(Throwable("API_PROBLEM"))
            }
        })
    }
}
