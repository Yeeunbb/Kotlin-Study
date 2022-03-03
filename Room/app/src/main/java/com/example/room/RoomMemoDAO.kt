package com.example.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoomMemoDAO {
//annotation을 붙였으므로 ROOM이 코드를 알아서 넣어준다.
    @Query("select * from room_memo")
    fun getAll() : List<Memo>

    @Insert(onConflict = REPLACE) //메모 no에 값이 있다면? 교체해라~
    fun insert(memo: Memo)

    @Delete
    fun delete(memo:Memo)
}