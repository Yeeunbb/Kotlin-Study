package com.example.myapplicationkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationkotlin.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy {ActivitySubBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            to1.text = intent.getStringExtra("from1")
            to2.text = "${intent.getIntExtra("from2", 0)}"

            btnClose.setOnClickListener{
                val returnIntent = Intent()
                val message = editMessage.text.toString()
                returnIntent.putExtra("returnValue", message)
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}