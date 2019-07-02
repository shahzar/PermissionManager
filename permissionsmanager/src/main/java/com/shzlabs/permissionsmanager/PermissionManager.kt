package com.shzlabs.permissionsmanager

import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

class PermissionManager {

    private val REQUEST_CODE = 300
    @StringRes
    private var descRes: Int = R.string.pmx_default_description;

    private var bottomSheetMenu: BottomSheetMenu? = null
    private var permissions: ArrayList<String> = arrayListOf()
    private var callback: PermissionCallback? = null;

    companion object {

        private var contextWeakRef: WeakReference<AppCompatActivity>? = null
        private var permissionManagerWeakRef: WeakReference<PermissionManager>? = null


        fun with(context: AppCompatActivity): PermissionManager {
            contextWeakRef = WeakReference(context)
            permissionManagerWeakRef = WeakReference(PermissionManager())

            return getPermissionManager()
        }

        private fun getPermissionManager(): PermissionManager {
            return permissionManagerWeakRef?.get()!!
        }

        private fun getActivity(): AppCompatActivity {
            return contextWeakRef?.get()!!
        }

    }

    fun request(permission: String): PermissionManager {
        permissions.add(permission)
        return getPermissionManager()
    }

    fun request(permissionArr: Array<String>): PermissionManager {
        permissions.addAll(permissionArr)
        return getPermissionManager()
    }

    fun setDescription(@StringRes descRes: Int): PermissionManager {
        this.descRes = descRes
        return getPermissionManager()
    }

    fun ask() {
        //TODO Change callback to lambda ?
        bottomSheetMenu = BottomSheetMenu(getActivity() as Context, object : BottomSheetMenu.CustomListener {
            override fun onProceedToAskPermission() {
                bottomSheetMenu?.hide()
                handlePermission()
            }
        })

        bottomSheetMenu?.setDescription(descRes)

        if (!PermissionUtil.shouldAskForPermission(getActivity(), permissions.toTypedArray())) {
            return
        }

        bottomSheetMenu?.show()
    }

    fun setCallbacks(callback: PermissionCallback) : PermissionManager{
        this.callback = callback;
        return getPermissionManager()
    }

    fun onRequestPermissionResult(requestCode: Int, @NonNull permissions: Array<out String>, @NonNull grantResults: Array<Int>) {
        if (requestCode != REQUEST_CODE) {
            return
        }

        permissions.forEachIndexed { index, perm ->
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) callback?.onPermissionAccepted(perm) else callback?.onPermissionRejected(perm)
        }
    }

    private fun handlePermission() {
        PermissionUtil.requestPermission(getActivity(), permissions.toTypedArray(), REQUEST_CODE)
    }

    interface PermissionCallback {
        fun onPermissionAccepted(permission: String)
        fun onPermissionRejected(permission: String)
    }


}