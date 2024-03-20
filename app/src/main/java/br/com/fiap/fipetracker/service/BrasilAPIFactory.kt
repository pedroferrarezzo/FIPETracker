package br.com.fiap.fipetracker.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BrasilAPIFactory {
    private val URL = "https://brasilapi.com.br/api/"

    private val brasilAPIFactory = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()

    fun getVeiculoService(): VeiculoService {
        return brasilAPIFactory.create(VeiculoService::class.java)
    }
}