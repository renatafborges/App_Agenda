package com.example.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_adicionar_contato.*

class AdicionarContatoActivity : AppCompatActivity() {

    private lateinit var edtNomeContatoCadastro : EditText
    private lateinit var edtTelefoneContatoCadastro : EditText
    private lateinit var rdgTipoContato : RadioGroup
    private lateinit var edtinfoAdicional : EditText
    private lateinit var btnSalvar : Button
    private lateinit var txtContatos: TextView
    private lateinit var btnExibirLista : Button
    private lateinit var btnRetornarMain: Button


    var contatos : MutableList<Contato> = mutableListOf()
    var infoAdiconal: AdicionarTipo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_contato)
        bindViews()
    }

    fun bindViews() {
        edtNomeContatoCadastro = findViewById(R.id.edtNome)
        edtTelefoneContatoCadastro = findViewById(R.id.edtTelefone)
        rdgTipoContato = findViewById(R.id.rdgTipo)
        edtinfoAdicional = findViewById(R.id.edtReferencia)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnRetornarMain = findViewById<Button>(R.id.btnRetornarMain)

////BOTÃO SALVAR
        btnSalvar.setOnClickListener {
            val nome = edtNomeContatoCadastro.text.toString()
            val telefone = edtTelefoneContatoCadastro.text.toString()
            val info = edtinfoAdicional.text.toString()

            if(nome.isEmpty()){
                Toast.makeText(this, "O nome do usuário não foi informado", Toast.LENGTH_SHORT)
                    .show()
            }
            else if(telefone.isEmpty()){
                Toast.makeText(this, "O telefone do usuário não foi informado", Toast.LENGTH_SHORT)
                    .show()
            }else{
                infoAdiconal?.let {
                    if(info.isEmpty()){
                        Toast.makeText(this, "Uma informação adicional precisa ser inserida", Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        criarNovoContato(nome, telefone, it, info)
                        Toast.makeText(this, "O cadastro foi realizado com sucesso", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

//                exibirListaContatos(txtContatos)
            }
            edtNomeContatoCadastro.text.clear()
            edtTelefoneContatoCadastro.text.clear()
            edtinfoAdicional.text.clear()
        }

////BOTÃO RETORNAR
        btnRetornarMain.setOnClickListener{
           retornarMainActivity()
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
    }

///FUNCAO RETORNAR TELA ANTERIOR

    fun retornarMainActivity(){
//        startActivity(Intent(this, SecondActivity::class.java))
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}