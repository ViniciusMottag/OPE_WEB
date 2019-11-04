package br.com.vinicius.ope_esmalteria

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*


class TelaInicialActivity : DebugActivity(),NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private var agendamento = listOf<Agendamento>()
    private var REQUEST_CADASTRO=1
    private var REQUEST_REMOVE=2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        botao_Sair.setOnClickListener {cliqueSair()}
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()

        recyclerAgendamento?.layoutManager = LinearLayoutManager(context)
        recyclerAgendamento?.itemAnimator = DefaultItemAnimator()
        recyclerAgendamento?.setHasFixedSize(true)
    }
    override fun onResume() {
        super.onResume()
        // task para recuperar as disciplinas
        taskAgendamento()
    }
    fun taskAgendamento() {
        // Criar a Thread

        Thread {
            // Código para procurar as disciplinas
            // que será executado em segundo plano / Thread separada
            this.agendamento = AgendamentoService.getAgendamento(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                recyclerAgendamento?.adapter = AgendamentoAdapter(this.agendamento) { onClickAgendamento(it) }
            }
        }.start()


    }
    fun enviaNotificacao(agendamento: Agendamento) {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, AgendamentoActivity::class.java)
        // parâmetros extras
        intent.putExtra("agendamento", agendamento)
        // Disparar notificação
        NotificationUtil.create(this, 1, intent, "OPE", "Você tem nova atividade na ${agendamento.nome}")
    }



    // tratamento do evento de clicar em uma disciplina
    fun onClickAgendamento(agendamento: Agendamento) {
        Toast.makeText(context, "Clicou agendamento ${agendamento.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, AgendamentoActivity::class.java)
//        intent. putExtra("agendamento", agendamento)
        startActivityForResult(intent, REQUEST_REMOVE)
    }



    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)

    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_agendamento -> {
                Toast.makeText(this, "Clicou Disciplinas", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_buscar_agendamento -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }

        }

        // fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_telainicial, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskAgendamento()
        }
    }




}
