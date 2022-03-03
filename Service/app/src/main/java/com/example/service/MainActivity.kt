package com.example.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View

class MainActivity : AppCompatActivity() {

    lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceIntent = Intent(this, MyService::class.java)
    }

    fun serviceStart(view: View){
        serviceIntent.action = MyService.ACTION_CREATE
        startService(intent)
    }


    fun serviceStop(view: View){
        stopService(intent)
    }

    var myService : MyService? = null
    var isService = false
    val connection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            isService = true
            val binder = p1 as MyService.MyBinder
            myService = binder.getService()

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isService = false
        }
    }

    fun serviceBind(view: View){
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun serviceCommand(){
        myService?.create()
        myService?.delete()
    }

    fun serviceUnbind(view: View){

    }
}