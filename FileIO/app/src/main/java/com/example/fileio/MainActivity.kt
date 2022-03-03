package com.example.fileio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fileio.databinding.ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            val directory_name = "memo"
            val filename = "memo01.txt"

            btnSave.setOnClickListener {
                val content = textWrite.text.toString()
                writeTextFile(directory_name, filename, content)
            }

            val path = directory_name + "/" + filename
            val writeContent = readTextFile(path)
            textRead.text = writeContent
        }
    }

    fun readTextFile(path: String): String{
        val fullpath = File(filesDir.path + "/" + path)
        if(!fullpath.exists()) return ""

        val reader = FileReader(fullpath)
        val buffer = BufferedReader(reader)

        val result = StringBuilder() // StringBuffer()
        var temp: String? = ""

        while(true){
            temp = buffer.readLine()
            if(temp == null) break;
            result.append(temp).append("\n")
        }
        buffer.close()
        reader.close()
        return result.toString()
    }

    fun writeTextFile(directory: String, filename: String, content: String){
        //앱 기본 경로 / files / memo
        val dir = File(filesDir.path + "/" + directory)
        if(!dir.exists()) dir.mkdirs()

        val fullpath = dir.path + "/" + filename
        val writer = FileWriter(fullpath)
        val buffer = BufferedWriter(writer) //속도를 빠르게 하기 위해 버퍼로 감싼다.
        buffer.write(content)
        buffer.close()
        writer.close()

    }
}