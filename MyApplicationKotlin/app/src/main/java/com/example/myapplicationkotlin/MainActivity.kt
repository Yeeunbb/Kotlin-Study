package com.example.myapplicationkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationkotlin.databinding.ActivityMainBinding
import com.example.myapplicationkotlin.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //1. 데이터를 불러온다.
        val data = loadData()

        //2. 어뎁터를 생성
        val customAdapter = CustomAdapter(data)

        //3. 화면의 RecyclerView와 연결
        binding.recyclerView.adapter = customAdapter

        //4. 레이아웃 매니저 설정
        //리사이클러뷰는 레이아웃 매니저 설정에 따라 목록형태로 나열할수도있고
        //그리드 형태(격자무늬)로도 보여줄 수 있다.
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //목록 형태로 나열

    }

    fun loadData() : MutableList<Memo> {
        val memoList = mutableListOf<Memo>()
        for(no in 1..100) {
            val title = "이것이 안드로이드다 $no"
            val date = System.currentTimeMillis()
            val memo = Memo(no, title, date)
            memoList.add(memo)
        }
        return memoList
    } //100개의 메모 데이터를 가진 List를 넘겨받는다.
}

//RecyclerView에 정의된 Adapter<>를 사용할건데, 이걸 사용하기 위해선 정의를 해주어야 한다.
//리사이클러뷰 어뎁터는 데이터 전체를 관리하고, 아이템 레이아웃 하나하나를 관리하는 건 '홀더'이다.
//이 홀더 클래스를 안에 만들어주어야 한다.
//어뎁터가 이 홀더를 가지고 아이템 레이아웃에 값을 세팅할 것이다.
class CustomAdapter(val listData:MutableList<Memo>) : RecyclerView.Adapter<CustomAdapter.Holder>() {
//어뎁터가 사용할 변수(==데이터)를 생성자로 받는다.

    //화면에 보이는 만큼만 생성하고, 재사용하게끔 하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //activity의 경우 전체화면이니까, LayoutInflater만 넘겨주면 되는데,
        //adapter에서는 각각의 holder가 부분부분 쓰여지기 때문에, 표시되는 view의 정보도 함께 넘겨주어야 한다.
        return Holder(binding)
    }

    //1. 사용할 데이터를 꺼내고
    //2. 홀더에 데이터를 전달
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    //목록의 개수를 알려주는 함수
    override fun getItemCount() = listData.size

    //binding 앞에 val을 붙여주면 전역변수처럼 쓸 수 있다.)
    class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){
        lateinit var currentMemo : Memo
        //클릭처리는 init에서만 한다
        init{
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "클릭된 아이템: ${currentMemo.title}", Toast.LENGTH_SHORT)
            }
        }

        //3. 홀더는 받은 데이터를 화면에 출력
        fun setMemo(memo : Memo) {

            currentMemo = memo

            with(binding){
                textNo.text = "${memo.no}"
                textTitle.text = memo.title

                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val formattedDate = sdf.format(memo.timestamp)
                textDate.text = formattedDate
            }
        }
    }

}