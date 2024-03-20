package br.com.fiap.fipetracker.service

import br.com.fiap.fipetracker.model.Veiculo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface VeiculoService {
    @GET("fipe/preco/v1/{codigoFIPE}")
    fun getVeiculoByFIPE(@Path("codigoFIPE") codigoFIPE: String): Call<List<Veiculo>>
}