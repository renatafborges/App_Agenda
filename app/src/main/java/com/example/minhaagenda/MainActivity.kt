package com.example.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//private var contatos : MutableList<Contato> = mutableListOf()
//private var infoAdiconal: AdicionarTipo? = null

class MainActivity : AppCompatActivity() {

    private lateinit var edtCampoPesquisa : EditText
    private lateinit var btnPesquisar : Button
    private lateinit var txtContatos: TextView
    private lateinit var btnExibirLista : Button
    private lateinit var btnCadastrar : Button


    private var contatos : MutableList<Contato> = mutableListOf()
    private var infoAdiconal: AdicionarTipo? = null
    private lateinit var rvContatos : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtCampoPesquisa = findViewById(R.id.edtCampoPesquisa)
        btnPesquisar = findViewById(R.id.btnPesquisar)
        txtContatos = findViewById(R.id.txtContatos)
        btnExibirLista = findViewById(R.id.btnExibirLista)
        btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        rvContatos = findViewById<RecyclerView>(R.id.rvContatos)

        //DATASET
        val lista = mutableListOf<Contato>()
        Contato("Renata", "44", "Casa")
        Contato("Amanda", "21", "Trabalho")

        rvContatos.adapter = ContatosAdapter(dataSet = lista)
        rvContatos.layoutManager = LinearLayoutManager(this)


        btnCadastrar.setOnClickListener{
            adicionarContatoActivity()
        }

///////BOTÃO PESQUISAR
        btnPesquisar.setOnClickListener {
            val nomePesquisa = edtCampoPesquisa.text.toString()
            if(nomePesquisa.isEmpty()){
                Toast.makeText(this, "Insira o nome para pesquisa", Toast.LENGTH_SHORT)
                    .show()
            }else{
                buscarContato(nomePesquisa, txtContatos)
                btnExibirLista.visibility = View.VISIBLE

                btnExibirLista.setOnClickListener{
                    it.visibility = View.INVISIBLE
                    exibirListaContatos(txtContatos)
                }
            }
            edtCampoPesquisa.text.clear()
        }
    }

///////BOTÃO CADASTRAR - PROXACTIVITY


    ///FUNCAO CADASTRAR PROXIMA TELA

    fun adicionarContatoActivity(){
//        startActivity(Intent(this, SecondActivity::class.java))
        val intent = Intent(this, AdicionarContatoActivity::class.java)
        startActivity(intent)
    }



//////////EXIBIR LISTA CONTATOS

    private fun exibirListaContatos(txtContatos: TextView){
        contatos.sortBy { it.getNome() }
        txtContatos.visibility = View.VISIBLE
        var mensagem = ""
        for(contato in contatos){
            mensagem += "- Nome: ${contato.getNome()} Telefone: ${contato.getTelefone()}\n Informação adicional: ${contato.getInfo()}\n"
        }
        txtContatos.text = mensagem
    }

//////////////BUSCAR CONTATOS

    private fun buscarContato(nome: String, txtContatos: TextView){
        txtContatos.visibility = View.VISIBLE
        var mensagem = ""
        for(contato in contatos){
            if(nome == contato.getNome()){
                mensagem += "- Nome: ${contato.getNome()} Telefone: ${contato.getTelefone()}\n Informação adicional: ${contato.getInfo()}\n"
            }
        }
        if(mensagem == ""){
            Toast.makeText(this, "Nenhum contato encontrado na agenda", Toast.LENGTH_SHORT)
                .show()
        }
        txtContatos.text = mensagem
    }

////////COMPANION OBJECT

//    companion object{
//        val lista = mutableListOf(Contato)
//    }
}
