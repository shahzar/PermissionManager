# Permission Manager

Kotlin library to handle Android Runtime permissions with bottom-sheet design.

[ ![Download](https://api.bintray.com/packages/shahzar/PermissionManager/permissionmanager/images/download.svg) ](https://bintray.com/shahzar/PermissionManager/permissionmanager/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


## Gradle Dependency
```
dependencies {
    implementation 'com.shzlabs.permissionmanager:permissionmanager:0.1.0'
}
```

##  Usage

```
val permissionManager: PermissionManager = //...

permissionManager = PermissionManager
    .with(this)
    .request("Manifest.permission.WRITE_EXTERNAL_STORAGE")
    .setDescription(R.string.permission_description)
    .setCallbacks(...)


permissionManager.ask()
```

Invoke `PermissionManager.onRequestPermissionsResult` to handle callbacks

```
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    permissionManager.onRequestPermissionResult(requestCode, permissions, grantResults.toTypedArray())
}
```