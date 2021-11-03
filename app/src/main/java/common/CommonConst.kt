package common

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object CommonConst
{
    public val requiredPermissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE
    )
}