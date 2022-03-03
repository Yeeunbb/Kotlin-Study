package com.example.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val DB_NAME = "sqlite.sql"
    val DB_VERSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val helper = SqliteHelper(this, DB_NAME, DB_VERSION)
        val adapter = RecyclerAdapter()

        val memos = helper.selectMemo()
        adapter.listData.addAll(memos)
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        with(binding){
            buttonSave.setOnClickListener{
                val content = editMemo.text.toString()
                if(content.isNotEmpty()){
                    val memo = Memo(null, content, System.currentTimeMillis())
                    helper.insertMemo(memo)
                    adapter.listData.add(memo)

                    //기존 작성 글 삭제
                    editMemo.setText("")
                    //목록 갱신
                    adapter.notifyItemInserted(adapter.listData.size)
                }
            }
        }
    }
}