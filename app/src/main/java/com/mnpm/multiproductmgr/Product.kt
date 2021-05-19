package com.mnpm.multiproductmgr

class Product {
    private var nombre: String? = null
    private var telefono: String? = null
    private var tipo = 0 // 0:familia; 1:amigo; 2:trabajo
    private var email: String? = null
    private var direccion: String? = null

    constructor(nom: String?, tel: String?, tip: Int, em: String?, dir: String?) {
        nombre = nom
        telefono = tel
        tipo = tip
        email = em
        direccion = dir
    }

    fun getNombre(): String? {
        return nombre
    }

    fun getTelefono(): String? {
        return telefono
    }

    fun getTipo(): Int {
        return tipo
    }

    fun getEmail(): String? {
        return email
    }

    fun getDireccion(): String? {
        return direccion
    }
}