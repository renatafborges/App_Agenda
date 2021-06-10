package com.example.minhaagenda

data class Contato(
    var nome: String,
    var telefone: String,
    var tipo: Int,
    var referencia: String) {

    open fun exibirRegistro(): String {
        return "$nome - $telefone do tipo ${tipo}"
    }
}

//classe pessoal
//classe trabalho, as duas herdam de contato (com nome, telefone e tipo)
//
