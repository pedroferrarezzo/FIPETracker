package br.com.fiap.fipetracker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.fipetracker.model.CodigoFipeAndMesReferencia
import br.com.fiap.fipetracker.model.Veiculo

@Dao
interface VeiculoDao {
    @Insert
    fun criarVeiculo(veiculo: Veiculo): Long

    @Delete(entity = Veiculo::class)
    fun excluirVeiculo(params: CodigoFipeAndMesReferencia): Int

    @Query("SELECT COUNT(*) from T_FIPET_VEICULO WHERE codigo_fipe = :codigoFipe AND mes_referencia = :mesReferencia")
    fun verificaVeiculoDb(codigoFipe: String, mesReferencia: String): Boolean

    @Query(
        "SELECT * FROM T_FIPET_VEICULO GROUP BY codigo_fipe, mes_referencia"
    )
    fun listaVeiculoSalvos(): List<Veiculo>

    @Query("SELECT * FROM T_FIPET_VEICULO WHERE codigo_fipe = :codigoFipe AND mes_referencia = :mesReferencia")
    fun listaVeiculoByFipeAndMesReferencia(codigoFipe: String, mesReferencia: String): List<Veiculo>


}

