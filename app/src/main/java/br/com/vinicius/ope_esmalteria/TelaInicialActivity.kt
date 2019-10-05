package br.com.vinicius.ope_esmalteria

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*


class TelaInicialActivity : DebugActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        botao_sair.setOnClickListener {cliqueSair()}


    }
    fun cliqueSair(){
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do OPE_Esmalteria");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_telainicial, menu)
        // vincular evento de buscar
        return true
    }




}
