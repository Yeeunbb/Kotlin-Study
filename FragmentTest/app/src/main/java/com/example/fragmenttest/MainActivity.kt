package com.example.fragmenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    val listFragment by lazy {ListFragment()}
    val detailFragment by lazy {Detailragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()

        binding.btnSend.setOnClickListener{
            listFragment.setValue("값 전달하기")
        }
    }

    fun goDetail(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, detailFragment)
        transaction.addToBackStack("detail")
        //뒤로가기 기능을 구현하기 위해 삽입한 코드임.
        //프래그먼트의 동작을 스택에 넣는다.
        transaction.commit()

    }

    fun goBack(){
        onBackPressed()
        //스택에 담긴게 빠진다.
    }

    fun setFragment(){

        val bundle = Bundle()
        bundle.putString("key 1", "List Fragment")
        bundle.putInt("key 2", 20211031)

        listFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        //3. 트랜잭션을 통해 프래그먼트 생성
        transaction.add(R.id.frameLayout, listFragment)
        transaction.commit()

    }
}