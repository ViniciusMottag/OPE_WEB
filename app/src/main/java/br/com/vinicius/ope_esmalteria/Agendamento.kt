package br.com.vinicius.ope_esmalteria

import com.google.gson.GsonBuilder

class Agendamento {

    var id:Long=0
    var nome = ""
    var data = ""
    var horario=""
    var foto=""

    override fun toString(): String {
        return "Agendamento(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}