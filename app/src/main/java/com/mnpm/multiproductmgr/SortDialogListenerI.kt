package com.mnpm.multiproductmgr

import android.content.DialogInterface

interface SortDialogListenerI {
    companion object {
        const val SORT_NAME = 0
        const val SORT_YEAR = 1
        const val SORT_TYPE = 2
    }

    fun sortDialogSelected(dialog: DialogInterface, mode: Int)
}