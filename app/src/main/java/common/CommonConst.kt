package common

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object CommonConst
{
    public val requiredPermissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    object DbResponseCode
    {
        public val SUCCESS = "S"
        public val FAIL = "F"
        public val ERROR = "E"
    }
}