package com.example.footballapp.ui.details.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.footballapp.R
import com.example.footballapp.utils.dpToPxf

class CTextView(context: Context, attributeSet: AttributeSet): AppCompatTextView(context, attributeSet) {

    private lateinit var mContext: Context

    init {
        mContext = context
        this.textSize = 12f
        this.setTextColor(mContext.getColor(R.color.black))
        this.typeface = Typeface.DEFAULT_BOLD
    }

    fun setTextTest(text: String? = null, size: Float? = null, color: Int? = null){
        text?.let {
            this.text = it
        }
        size?.let {
            this.textSize = it
        }
        color?.let {
            this.setTextColor(it)
        }
    }
}