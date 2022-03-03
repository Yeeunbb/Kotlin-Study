package com.example.sqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<Holder>(){
    var listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_recycler, parent, false)
//
//        return Holder(view)
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}


class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){
    fun setMemo(memo: Memo){
        with(binding){
            textNo.text = "${memo.no}"
            textContent.text = "${memo.content}"
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            val datetime = sdf.format(memo.datetime)
            textDate.text = "${datetime}"
        }

    }
}

//class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
//    fun setMemo(memo: Memo){
//        with(itemView){
//            textNo.text = "${memo.no}"
//            textContent.text = "${memo.content}"
//            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
//            val datetime = sdf.format(memo.datetime)
//            textDate.text = "${datetime}"
//        }
//    }
//}
