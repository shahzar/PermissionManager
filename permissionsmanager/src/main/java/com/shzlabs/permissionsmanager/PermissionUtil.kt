package com.shzlabs.permissionsmanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

object PermissionUtil {

    private fun useRuntimePermissions(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun hasPermission(activity: AppCompatActivity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(activity: AppCompatActivity, permissions: Array<String>, requestCode: Int) {
        if (useRuntimePermissions()) {
            activity.requestPermissions(permissions, requestCode)
        }
    }

    fun shouldAskForPermission(activity: AppCompatActivity, permission: String): Boolean {
        return useRuntimePermissions() && !hasPermission(activity, permission) && !hasAlreadyAskedPermission(activity, permission)
    }

    fun shouldAskForPermission(activity: AppCompatActivity, permissions: Array<String>): Boolean {

        for (permission in permissions) {
            if (useRuntimePermissions() && !hasPermission(activity, permission) && !hasAlreadyAskedPermission(activity, permission)) {
                return true
            }
        }

        return false
    }


    fun hasAlreadyAskedPermission(activity: AppCompatActivity, permission: String): Boolean {
        //TODO pick from storage
        return false
    }

    fun setPermissionAskAlreadyAsked(activity: AppCompatActivity, permission: String) {
        // TODO store to locate
    }

}