package com.shzlabs.permissionsmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetMenu(context: Context, listener: CustomListener) {

    private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)
    var view: View = LayoutInflater.from(context).inflate(R.layout.layout_bottomsheet, null)

    init {
        bottomSheetDialog.setContentView(view)

        val proceedButton : Button = view.findViewById(R.id.button_proceed)
        proceedButton.setOnClickListener { listener.onProceedToAskPermission() }
    }

    fun setDescription(@StringRes descRes: Int) {
        view.findViewById<TextView>(R.id.description).setText(descRes)
    }

    fun show() {
        bottomSheetDialog.show()
    }

    fun hide() {
        bottomSheetDialog.hide()
    }

    interface CustomListener {
        fun onProceedToAskPermission()
    }
}