package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import co.kr.freemon2.R

class JoinProfileActivity : AppCompatActivity()
{
    private val REQUEST_PICK_IMAGE = 2


    var imgProfileCamera = findViewById<ImageView>(R.id.joinProfile_imageViewIcon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_profile)

        imgProfileCamera.setOnClickListener(openGallery)
    }

    private fun openGallery() {
        Intent(Intent.ACTION_GET_CONTENT).also { intent ->
            intent.type = "image/*"
            intent.resolveActivity(packageManager)?.also {
                startActivityForResult(intent, REQUEST_PICK_IMAGE)
            }
        }
    }

}