package com.example.fragmenttest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmenttest.databinding.FragmentDetailragmentBinding

class Detailragment : Fragment() {

    lateinit var binding: FragmentDetailragmentBinding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        //context엔 나를 삽입한 Activity가 담겨있다
        super.onAttach(context)

        //이걸 해주는 이유는 mainActivity에 있는 goDetail함수를 쓰기 위해서
        if(context is MainActivity) mainActivity = context

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener{
            mainActivity.goBack()
        }
    }
}