package com.example.minhaagenda

open class Contato( private val nome: String, private val telefone: String, private var info: String) {
    fun getNome(): String = nome
    fun getTelefone(): String = telefone
    fun getInfo(): String = info

}
