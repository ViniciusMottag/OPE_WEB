package br.com.vinicius.ope_esmalteria

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class AgendamentoAdapter (
    val agendamento: List<Agendamento>,
    val onClick: (Agendamento) -> Unit): RecyclerView.Adapter
    <AgendamentoAdapter.AgendamentoViewHolder>() {

    // ViewHolder com os elemetos da tela
    class AgendamentoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg: ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_agendamento)

        }

    }

    // Quantidade de disciplinas na lista

    override fun getItemCount() = this.agendamento.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentoViewHolder {
        // infla view no adapter
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_agendamento, parent, false)

        // retornar ViewHolder
        val holder = AgendamentoViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: AgendamentoViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto disciplina
        val agendamento = agendamento[position]

        // atualizar dados de disciplina

        holder.cardNome.text = agendamento.nome
        holder.cardProgress.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(agendamento.foto).fit().into(holder.cardImg,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        // adiciona evento de clique
        holder.itemView.setOnClickListener { onClick(agendamento) }
    }
}



