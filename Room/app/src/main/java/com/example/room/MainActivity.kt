package com.example.room

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.room.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    lateinit var helper:RoomHelper
    lateinit var memoAdapter:RecyclerAdapter
    val memoList = mutableListOf<Memo>()
    lateinit var memoDAO:RoomMemoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries() //공부할때만 쓴다.
            .build()
        //allowMainThreadQueries -> 서브스레드로 분기안해도 메인스레드에서 쿼리를 날릴수있다.
        //원래 룸은 메인스레드에서 쿼리를 날릴 수 없다.

        memoDAO = helper.roomMemoDao()

        memoAdapter = RecyclerAdapter(memoList)

        refreshAdaper()

        with(binding){
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@MainActivity)

            buttonSave.setOnClickListener{
                val content = editMemo.text.toString()
                if(content.isNotEmpty()){
                    val datetime = System.currentTimeMillis()
                    val memo = Memo(content, datetime)
                    editMemo.setText("")
                    insertMemo(memo)
                }
            }
        }

    }
    fun insertMemo(memo:Memo){
        CoroutineScope(Dispatchers.IO).launch {
            memoDAO.insert(memo)
            refreshAdaper()
        }
    }

    fun refreshAdaper() {
        //memoDAO를 사용하려면 서브 스레드를 사용해야한다.
        //ROOM과 관련된 코드는 서브스레드에서 실행시킨다.
        CoroutineScope(Dispatchers.IO).launch{
            memoList.clear()
            memoList.addAll(memoDAO.getAll())
            //화면을 갱신할때만 Main 스레드에서 실행해라.
            withContext(Dispatchers.Main){
                memoAdapter.notifyDataSetChanged()
            }
        }

    }
}