package com.example.viewpagerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerfragment.databinding.ActivityMainBinding
import com.example.viewpagerfragment.databinding.ItemViewpagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            //1. 데이터 로드
            val list = loadData()
            //2. 어뎁터 생성
            val pagerAdapter = CustomPagerAdapter(list)
            //3. 어뎁터와 뷰페이저 연결
            viewPager.adapter = pagerAdapter
            //4. 탭 타이틀 목록 생성. 위의 list 데이터 사용
            val titles = listOf("월","화","수","목","금","토","일")
            //5. 탭레이아웃과 뷰페이저 연결
            TabLayoutMediator(tabLayout, viewPager){tab, position->
                tab.text = titles.get(position)

            }.attach()
        }
    }

    fun loadData() : List<Page> {
        val pageList = mutableListOf<Page>()
        pageList.add(Page(1, "흐림"))
        pageList.add(Page(2, "맑음"))
        pageList.add(Page(3, "구름"))
        pageList.add(Page(4, "비"))
        pageList.add(Page(5, "눈"))
        pageList.add(Page(6, "태풍"))
        pageList.add(Page(7, "안개"))
        return pageList
    }
}

class Holder(val binding:ItemViewpagerBinding) : RecyclerView.ViewHolder(binding.root){
    fun setItem(page: Page){
        with(binding){
            textDay.text = "${page.day} 일"
            textWeather.text = page.weather
        }
    }
}


class CustomPagerAdapter(val pageList:List<Page>) :RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(pageList.get(position))
    }
    override fun getItemCount() = pageList.size
}

data class Page(val day:Int, val weather: String)