package com.mnpm.multiproductmgr

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SortDialog : DialogFragment() {

    private var parent: SortDialogListenerI? = null

    fun setParent(parent: SortDialogListenerI) {
        this.parent = parent
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_sort)
                    .setItems(R.array.sorting) { dialog, which ->
                        parent?.sortDialogSelected(dialog, which)
                    }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
