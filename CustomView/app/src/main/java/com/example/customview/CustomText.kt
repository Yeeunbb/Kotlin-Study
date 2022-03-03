package com.example.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomText : AppCompatTextView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs:AttributeSet) : super(context, attrs) {
        //이 위젯을 태그로 썼을 때, 태그에 작성한 속성값들이 attrs에 들어온다.
        val attrList = context.obtainStyledAttributes(attrs, R.styleable.CustomText)
        //많은 속성이 넘어올 텐데, 그 중 내가 정의해둔것만 꺼낸다.
        val size = attrList.indexCount //속성이 몇개인지 체크

        for(i in 0 until size){
            val attr = attrList.getIndex(i)
            when(attr){
                R.styleable.CustomText_delimeter -> { //속성에 있는 delimeter를 꺼내서 구현
                    attrList.getString(attr)?.let{
                        process(it)
                    }
                }
            }
        }
    }
    constructor(context: Context, attrs:AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
    //일종의 '테마'와 같다고 보면 됨

    fun process(delimeter: String){
        if(text.length == 8){
            val first4 = text.substring(0, 4)
            val mid2 = text.substring(4,6)
            val last2 = text.substring(6)

            text = first4 + delimeter + mid2 + delimeter + last2
//            text = "$first4$delimeter$mid2$delimeter$last2"
        }
    }




}