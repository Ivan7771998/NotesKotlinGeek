package com.dev777popov.noteskotlingeek.ui.dialogs

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.dev777popov.noteskotlingeek.R

class LogOutDialog : DialogFragment() {

    companion object {
        val TAG = LogOutDialog::class.java.name + " TAG"

        fun createInstance(onLogout: (() -> Unit)) = LogOutDialog().apply {
            this.onLogout = onLogout
        }
    }

    var onLogout: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog = AlertDialog.Builder(context)
        .setTitle(R.string.logout_title)
        .setMessage(R.string.logout_message)
        .setPositiveButton(R.string.logout_ok) { _, _ -> onLogout?.invoke() }
        .setNegativeButton(R.string.logout_cancel) { _, _ -> dismiss() }
        .create()
}