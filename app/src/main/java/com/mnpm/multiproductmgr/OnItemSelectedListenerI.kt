package com.mnpm.multiproductmgr


interface OnItemSelectedListenerI<T> {
    fun onContactoSeleccionado(posicion: T)
}