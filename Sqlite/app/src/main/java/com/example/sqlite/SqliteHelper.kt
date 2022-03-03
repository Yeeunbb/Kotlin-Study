package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context, name:String, version:Int)
    : SQLiteOpenHelper(context, name, null, version){
    //파라미터로 받은 파일의 이름인 'name'이 내장 메모리에 존재 하지않으면 onCreate가 실행됨.

    override fun onCreate(db: SQLiteDatabase?) {
        val create = "create table memo (`no` integer primary key, content text, datetime integer)"
        db?.execSQL(create)
    }


    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //테이블에 변경사항이 있을 경우 호출됨
        //SqliteHelper()의 생성자를 호출할 때 기존 데이터 베이스와 version을 비교해서 높으면
        //호출된다.
    }

    //데이터 입력함수(insert)
    fun insertMemo(memo: Memo){
        //db 가져오기
        val wd = writableDatabase

        //Memo를 입력타입으로 변환
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        //db에 넣기
        wd.insert("memo", null, values)

        //db 닫기
        wd.close()
    }

    //데이터 조회함수
    fun selectMemo(): MutableList<Memo> {
        val list = mutableListOf<Memo>()

//        val select = "select no, content, datetime from memo"
        val select = "select * from memo"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)
        //쿼리를 실행하는 메서드 execSQL과 rawQuery의 차이점은 rawQuery는 반환값이 존재한다.

        while(cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndexOrThrow("no"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            val datetime = cursor.getLong(cursor.getColumnIndexOrThrow("datetime"))

            val memo = Memo(no, content, datetime)
            list.add(memo)
        }
        cursor.close()
        rd.close()

        return list
    }

    fun updateMemo(memo:Memo){
        val wd = writableDatabase

        //Memo를 입력타입으로 변환
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        wd.update("memo", values, "no = ${memo.no}", null)
        wd.close()
    }

    fun deleteMemo(memo:Memo){
        val wd = writableDatabase
//        val delete = "delete from memo where no = ${memo.no}"
//        wd.execSQL(delete)

        wd.delete("memo", "no=${memo.no}", null)
        wd.close()
    }
}