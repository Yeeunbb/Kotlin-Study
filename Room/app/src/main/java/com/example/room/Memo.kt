package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//클래스 자체는 테이블이고, 변수 하나하나가 컬럼이 된다.
@Entity(tableName = "room_memo")
class Memo {
    @PrimaryKey(autoGenerate = true) //no에 값이 없을 때 자동증가한 숫자값을 db에 입력해준다.
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var content: String = ""
    @ColumnInfo(name = "date") //데이터베이스에 들어갈 이름 재정의 가능
    var datetime: Long = 0

    constructor(content:String, datetime: Long){
        this.content = content
        this.datetime = datetime
    } //코드를 좀 더 짧게 사용하기 위해 생성자 사용
}