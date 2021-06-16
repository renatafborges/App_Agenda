package com.example.minhaagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var edtNomeContatoCadastro : EditText
    private lateinit var edtTelefoneContatoCadastro : EditText
    private lateinit var rdgTipoContato : RadioGroup
    private lateinit var edtinfoAdicional : EditText
    private lateinit var btnSalvar : Button
    private lateinit var edtCampoPesquisa : EditText
    private lateinit var btnPesquisar : Button
    private lateinit var txtContatos: TextView
    private lateinit var btnExibirLista : Button

//    private var tipo: Int = -1

    private var contatos : MutableList<Contato> = mutableListOf()
    private var infoAdiconal: AdicionarTipo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
    }

    fun bindViews() {
        edtNomeContatoCadastro = findViewById(R.id.edtNome)
        edtTelefoneContatoCadastro = findViewById(R.id.edtTelefone)
        rdgTipoContato = findViewById(R.id.rdgTipo)
        edtinfoAdicional = findViewById(R.id.edtReferencia)
        btnSalvar = findViewById(R.id.btnSalvar)
        edtCampoPesquisa = findViewById(R.id.edtCampoPesquisa)
        btnPesquisar = findViewById(R.id.btnPesquisar)
        txtContatos = findViewById(R.id.txtContatos)
        btnExibirLista = findViewById(R.id.btnExibirLista)

////BOTÃO SALVAR
        btnSalvar.setOnClickListener {
            val nome = edtNomeContatoCadastro.text.toString()
            val telefone = edtTelefoneContatoCadastro.text.toString()
            val info = edtinfoAdicional.text.toString()

            if(nome.isEmpty()){
                Toast.makeText(this, "O nome do usuário não foi informado", Toast.LENGTH_SHORT)
                    .show()
//                Toast.makeText(this, getString(R.string.error_name), Toast.LENGTH_SHORT)
//                    .show()
            }
            else if(telefone.isEmpty()){
                Toast.makeText(this, "O telefone do usuário não foi informado", Toast.LENGTH_SHORT)
                    .show()
//                Toast.makeText(this, getString(R.string.error_number), Toast.LENGTH_SHORT)
//                    .show()
            }else{
                infoAdiconal?.let {
                    if(info.isEmpty()){
                        Toast.makeText(this, "Uma informação adicional precisa ser inserida", Toast.LENGTH_SHORT)
                            .show()
//                        Toast.makeText(this, getString(R.string.error_reference), Toast.LENGTH_SHORT)
//                            .show()
                    }else{
                        criarNovoContato(nome, telefone, it, info)
                    }
                }
                exibirListaContatos(txtContatos)
            }
            edtNomeContatoCadastro.text.clear()
            edtTelefoneContatoCadastro.text.clear()
            edtinfoAdicional.text.clear()
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

    /////FUNÇÃO RADIO BUTTON TIPOS CONTATO

    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            edtinfoAdicional = findViewById(R.id.edtReferencia)
            val foiChecado = view.isChecked

            when(view.id){
                R.id.rdTrabalho -> {
                    edtinfoAdicional.visibility = View.VISIBLE
                    if(foiChecado){
                        infoAdiconal = AdicionarTipo.TRABALHO
                       edtinfoAdicional.setHint("Email")
                    }
                }
                R.id.rdPessoal -> {
                    edtinfoAdicional.visibility = View.VISIBLE
                    if(foiChecado){
                        infoAdiconal = AdicionarTipo.PESSOAL
                        edtinfoAdicional.setHint("Referência")
                    }
                }
            }
        }
    }

//    fun onRadioButtonClicked(view: View) {
//        if (view is RadioButton){
//            val foiChecado = view.isChecked
//
//            when (view.id) {
//                R.id.rdPessoal -> {
//                    edtinfoAdicional.visibility = View.VISIBLE
//                    edtinfoAdicional.hint = "Referência"
//                    if (foiChecado) {
//                        tipo = Tipo.PESSOAL.id
//                    }
//                }
//                R.id.rdTrabalho -> {
//                    edtinfoAdicional.visibility = View.VISIBLE
//                    edtinfoAdicional.hint = "E-mail"
//                    if (foiChecado) {
//                        tipo = Tipo.TRABALHO.id
//                    }
//                }
//            }
//        }
//    }

    /// FUNCAO CRIAR NOVO CONTATO

    private fun criarNovoContato(nome: String, telefone: String, infoAdicional: AdicionarTipo, info: String){
        if(infoAdicional == AdicionarTipo.TRABALHO){
            contatos.add(
                Trabalho(nome,telefone,info)
            )
        }
        else if(infoAdicional == AdicionarTipo.PESSOAL){
            contatos.add(
                Pessoal(nome,telefone,info)
            )
        }
        /*contacts.add(
            Person(name,number,info)
        )*/
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
//            Toast.makeText(this, getString(R.string.no_contacts_found), Toast.LENGTH_SHORT)
//                .show()
        }
        txtContatos.text = mensagem
    }



///////////////////////////////////////////////

}