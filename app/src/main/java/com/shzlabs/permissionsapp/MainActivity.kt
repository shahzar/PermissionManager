package com.shzlabs.permissionsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shzlabs.permissionsmanager.PermissionManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askPermissions()
    }

    fun askPermissions() {
        PermissionManager.with(this).ask()
    }
}
