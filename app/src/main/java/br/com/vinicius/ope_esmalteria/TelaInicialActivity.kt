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
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*


class TelaInicialActivity : DebugActivity(),NavigationView.OnNavigationItemSelectedListener {
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

//    private fun configuraMenuLateral() {
//        // ícone de menu (hamburger) para mostrar o menu
//        var toogle = ActionBarDrawerToggle(this, layoutMenuLateralteral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//
//        layoutMenuLateral.addDrawerListener(toogle)
//        toogle.syncState()
//
//        menu_lateral.setNavigationItemSelectedListener(this)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_telainicial, menu)

        return true
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return true
    }



}
