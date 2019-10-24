package br.com.vinicius.ope_esmalteria

class Agendamento {
    var id:Long=0
    var nome = ""
    var data = ""
    var horario=""
    override fun toString(): String {
        return "Agendamento(nome='$nome')"
    }

}