package com.mnpm.multiproductmgr

import android.content.DialogInterface

interface DeleteDialogListenerI {
    fun deleteDialogCancelled(dialog: DialogInterface)
    fun deleteDialogAccepted(dialog: DialogInterface)
}