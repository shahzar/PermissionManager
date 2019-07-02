package com.shzlabs.permissionsapp

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shzlabs.permissionsmanager.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var permissionManager: PermissionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askPermissions()
        trigger.setOnClickListener { askPermissions() }
    }

    fun askPermissions() {

        permissionManager = PermissionManager
            .with(this)
            .request(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS))
            .setDescription(R.string.permission_description)
            .setCallbacks(object : PermissionManager.PermissionCallback {
                override fun onPermissionAccepted(permission: String) {
                    Toast.makeText(this@MainActivity, "Accepted! \n" + permission, Toast.LENGTH_LONG).show()
                }

                override fun onPermissionRejected(permission: String) {
                    Toast.makeText(this@MainActivity, "Rejected! \n" + permission, Toast.LENGTH_LONG).show()
                }
            })

        permissionManager?.ask()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionManager?.onRequestPermissionResult(requestCode, permissions, grantResults.toTypedArray())
    }
}
