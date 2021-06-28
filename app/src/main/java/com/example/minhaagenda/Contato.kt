package com.example.minhaagenda

    open class Contato( val nome: String, val telefone: String, var info: String){
        fun getNome(): String = nome
        fun getTelefone(): String = telefone
        fun getInfo(): String = info
}
