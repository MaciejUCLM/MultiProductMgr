package com.mnpm.multiproductmgr

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteDialog : DialogFragment() {

    private var parent: DeleteDialogListenerI? = null

    fun setParent(parent: DeleteDialogListenerI) {
        this.parent = parent
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_delete)
                    .setPositiveButton(R.string.yes) { dialog, id ->
                        parent?.deleteDialogAccepted(dialog)
                    }
                    .setNegativeButton(R.string.no) { dialog, id ->
                        parent?.deleteDialogCancelled(dialog)
                    }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}