package com.shzlabs.permissionsmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetMenu(private val context: Context) {

    private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_bottomsheet, null)
        bottomSheetDialog.setContentView(view)
    }

    fun show() {
        bottomSheetDialog.show()
    }

}