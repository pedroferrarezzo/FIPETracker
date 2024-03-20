package br.com.fiap.fipetracker.functions

import br.com.fiap.fipetracker.model.Veiculo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.jsoup.Jsoup

// Funcao suspensa responsavel pelo webscrapping na página do Google Images
suspend fun webScrappingImageVehicle(listVeiculos: List<Veiculo>, scope: CoroutineScope) {

    // co-rotina responsavel pelo webscrapping na página do Google Images
    val deferred: Deferred<Unit> = scope.async(Dispatchers.IO) {
        val modelo = listVeiculos[0].modelo
        val marca = listVeiculos[0].marca
        var tipoVeiculo = ""

        when (listVeiculos[0].tipoVeiculo.toInt()) {
            1 -> tipoVeiculo = "Carro"
            2 -> tipoVeiculo = "Moto"
            3 -> tipoVeiculo = "Caminhão"
        }

        var urlImagemModelo = ""
        var urlImagemMarca = ""

        // URL utilizada para o webscrapping
        val urlModelo =
            "https://www.google.com/search?tbm=isch&q=${modelo}.png+$tipoVeiculo&filetype:png&tbs=itp:trans"
        val urlMarca =
            "https://www.google.com/search?tbm=isch&q=${marca}.png+logo+veiculo&filetype:png&tbs=itp:trans"

        // Obtencao das paginas HTML
        val documentModelo = Jsoup.connect(urlModelo).get()
        val documentMarca = Jsoup.connect(urlMarca).get()

        // Busca pelos elementos HTML que possuam um elemnto "src"
        val elementsModelo = documentModelo.getElementsByAttribute("src")
        val elementsMarca = documentMarca.getElementsByAttribute("src")

        // Busca pelo array de elementos retornados pela busca "documentModelo.getElementsByAttribute("src")"
        for (element in elementsModelo) {
            if (element.attr("src").startsWith("https://")) {
                urlImagemModelo = element.attr("src")
                break
            }
        }

        // Busca pelo array de elementos retornados por - "documentMarca.getElementsByAttribute("src")"
        for (element in elementsMarca) {
            if (element.attr("src").startsWith("https://")) {
                urlImagemMarca = element.attr("src")
                break
            }
        }

        for (veiculo in listVeiculos) {
            veiculo.urlImagemModelo = urlImagemModelo
            veiculo.urlImagemMarca = urlImagemMarca
        }
    }

    return deferred.await()
}