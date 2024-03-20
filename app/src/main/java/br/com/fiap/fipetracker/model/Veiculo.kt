package br.com.fiap.fipetracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_FIPET_VEICULO")
data class Veiculo(
    @PrimaryKey(autoGenerate = true) var id_veiculo: Long = 0,
    var valor: String = "",
    var marca: String = "",
    var modelo: String = "",
    @ColumnInfo(name = "ano_modelo") var anoModelo: Long = 0,
    var combustivel: String = "",
    @ColumnInfo(name = "codigo_fipe") var codigoFipe: String = "",
    @ColumnInfo(name = "mes_referencia") var mesReferencia: String = "",
    @ColumnInfo(name = "tipo_veiculo") var tipoVeiculo: Short = 0,
    @ColumnInfo(name = "sigla_combustivel") var siglaCombustivel: String = "",
    @ColumnInfo(name = "data_consulta") var dataConsulta: String = "",
    @ColumnInfo(name = "url_imagem_modelo") var urlImagemModelo: String = "",
    @ColumnInfo(name = "url_imagem_marca") var urlImagemMarca: String = ""
)

data class CodigoFipeAndMesReferencia(
    @ColumnInfo(name = "codigo_fipe") var codigoFipe: String = "",
    @ColumnInfo(name = "mes_referencia") var mesReferencia: String = ""
)

