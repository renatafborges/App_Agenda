package com.example.minhaagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var edtNomeContatoCadastro : EditText
    private lateinit var edtCelularContatoCadastro : EditText
    private lateinit var rdgTipoContato : RadioGroup
    private lateinit var edtReferenciaContato : EditText
//    private lateinit var edtEmailContato : EditText
    private lateinit var btnSalvar : Button
    private lateinit var edtPesquise : EditText
    private lateinit var btnPesquisar : Button
    private lateinit var btnExibir : Button

    private var tipo: Int = -1

    private var contatos : MutableList<Contato> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
    }

    fun bindViews() {
        edtNomeContatoCadastro = findViewById(R.id.edtNome)
        edtCelularContatoCadastro = findViewById(R.id.edtTelefone)
        edtReferenciaContato = findViewById(R.id.edtReferenciaContato)
//        edtEmailContato = findViewById(R.id.edtEmailContato)
        btnSalvar = findViewById(R.id.btnSalvar)

        edtPesquise = findViewById(R.id.edtPesquise)
        rdgTipoContato = findViewById(R.id.rdgTipo)
        btnPesquisar = findViewById(R.id.btnPesquisar)

        btnSalvar.setOnClickListener {
            val nomeDigitado = edtNomeContatoCadastro.text.toString()
            val celularDigitado = edtCelularContatoCadastro.text.toString()
        //    val tipoSelecionado = rdgTipoContato.checkedRadioButtonId
            val referenciaDigitada = edtReferenciaContato.text.toString()
//            val emailDigitado = edtEmailContato.text.toString()

            tipo?.let{
            registrarContato(nomeDigitado, celularDigitado, tipo, referenciaDigitada)
        }
    }

        btnPesquisar.setOnClickListener {
            val pesquisa = edtPesquise.text.toString()
            val resultado = contatos.find { contato -> contato.nome == pesquisa }
            if (resultado != null){
                Toast.makeText(this,
                    resultado.exibirRegistro(),
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,
                    "Não foi possível encontrar um contato",
                    Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun registrarContato(nome: String, telefone: String, tipo: Int, referencia: String){
        contatos.add(
            Contato(nome = nome, telefone = telefone, tipo = tipo, referencia = referencia) //named parameters

        )
        //val contato = Contato(nome = nome, telefone = telefone, tipo = tipo, referencia = referencia, email = email)
        Toast.makeText(this,
            contatos.last().exibirRegistro(),
            Toast.LENGTH_SHORT).show()
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val foiChecado = view.isChecked

            when (view.id) {
                R.id.rdPessoal -> {
                    edtReferenciaContato.visibility = View.VISIBLE
                    edtReferenciaContato.hint = "Referência"
                    if (foiChecado) {
                        tipo = Tipo.PESSOAL.id
                    }
                }
                R.id.rdTrabalho -> {
                    edtReferenciaContato.visibility = View.VISIBLE
                    edtReferenciaContato.hint = "E-mail"
                    if (foiChecado) {
                        tipo = Tipo.TRABALHO.id
                    }
                }
            }
        }
    }
}