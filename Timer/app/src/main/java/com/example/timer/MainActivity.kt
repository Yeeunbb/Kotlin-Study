package com.example.timer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.View
import com.example.timer.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            btnDownload.setOnClickListener {
                //Main스코프는 통신이나 파일에 있는 것을 쓸수가 없다.
                CoroutineScope(Dispatchers.Main).launch {
                    progress.visibility = View.VISIBLE
                    val url = editUrl.text.toString()

                    //loadImage를 쓰기 위해 withContext가 실행될 영역을 IO로 해서 새로운
                    //스코프를 만들어서 사용한다.
                    //loadImage의 반환값은 withContext의 반환값으로 사용할 수 있다.
                    val bitmap = withContext(Dispatchers.IO) {
                        loadImage(url)
                    }

                    imagePreView.setImageBitmap(bitmap)
                    progress.visibility = View.GONE
                }
            }
        }

    }


}

//코루틴에 사용할 함수는 top-level에 작성한다.
suspend fun loadImage(imageUrl: String) : Bitmap {
    val url = URL(imageUrl)
    val stream = url.openStream()

    return BitmapFactory.decodeStream(stream)
}