package activity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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



}