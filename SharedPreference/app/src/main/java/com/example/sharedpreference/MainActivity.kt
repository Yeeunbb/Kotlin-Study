package com.example.sharedpreference

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_FIRST_OPEN = "key_first_open"
        const val KEY_SECOND_OPEN = "key_second_open"
    }

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val shared = getSharedPreferences("파일명", Context.MODE_PRIVATE)

        val first_open = shared.getBoolean(KEY_SECOND_OPEN, false)
        if(first_open){
            binding.imageView.visibility = View.GONE
        }

        val editor = shared.edit() // 수정을 위한 에디터를 꺼낸다.
        editor.putBoolean(KEY_FIRST_OPEN, false)
        editor.commit()
    }
}