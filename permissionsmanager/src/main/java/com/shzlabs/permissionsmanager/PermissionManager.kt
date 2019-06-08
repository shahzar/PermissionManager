package com.shzlabs.permissionsmanager

import android.content.Context
import java.lang.ref.WeakReference

class PermissionManager {


    private var bottomSheetMenu: BottomSheetMenu? = null

    companion object {

        private var contextWeakRef:WeakReference<Context>? = null
        private var permissionManagerWeakRef:WeakReference<PermissionManager>? = null


        fun with(context: Context): PermissionManager {
            contextWeakRef = WeakReference(context)
            permissionManagerWeakRef = WeakReference(PermissionManager())

            return getPermissionManager()
        }

        private fun getPermissionManager(): PermissionManager {
            return permissionManagerWeakRef?.get()!!
        }

        private fun getContext(): Context {
            return contextWeakRef?.get()!!
        }

    }

    fun ask() {
        bottomSheetMenu = BottomSheetMenu(getContext())
        bottomSheetMenu?.show()
    }



}