package br.com.vinicius.ope_esmalteria

import android.content.Context


object AgendamentoService {
    fun getDisciplinas(context: Context):List<Agendamento>{
        val agendamentos = mutableListOf<Agendamento>()
        for (i in 1..10){
            val d = Agendamento()
            d.nome="Nome $i"
            d.data="Data $i"
            d.horario="Horario $i"
            agendamentos.add(d)
        }
        return agendamentos
    }
}