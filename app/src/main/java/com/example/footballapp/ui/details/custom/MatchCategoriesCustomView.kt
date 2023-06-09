package com.example.footballapp.ui.details.custom

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.example.footballapp.R
import com.example.footballapp.databinding.MatchCategoriesCustomLayoutBinding

class MatchCategoriesCustomView(context: Context, attributeSet: AttributeSet?): LinearLayout(context, attributeSet) {

    private var mContext: Context
    private var binding: MatchCategoriesCustomLayoutBinding
    private var categoryRadionBtn: RadioButton? = null
    private var radioBtbsList = ArrayList<RadioButton>()

    init {
        mContext = context
        binding = MatchCategoriesCustomLayoutBinding.inflate(
            LayoutInflater.from(mContext), this, true)

        addCategories()
    }

    private fun getListOfNames(): List<String>{
        return arrayListOf(mContext.getString(R.string.overview), mContext.getString(R.string.statistic), mContext.getString(R.string.line_up))
    }

    private fun addCategories(){
        getListOfNames().forEachIndexed { index, name ->
            categoryRadionBtn = RadioButton(mContext)
            categoryRadionBtn?.buttonDrawable = StateListDrawable()
            categoryRadionBtn?.id = index
            val params = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            params.setMargins(20, 0, 20, 0)
            categoryRadionBtn?.layoutParams = params
            categoryRadionBtn?.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color._B1B1B1
                )
            )
            categoryRadionBtn?.setPadding(30, 20, 30, 20)
            categoryRadionBtn?.text = name
            categoryRadionBtn?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            binding.radioBtnsContainer.addView(categoryRadionBtn)
            radioBtbsList.add(categoryRadionBtn!!)
        }
        binding.radioBtnsContainer.check(0)
        val firstRadioButton = findViewById<RadioButton>(0)
        firstRadioButton.setTextColor(ContextCompat.getColor(context, R.color.green))
        setListener()

    }

    private fun setListener() {
        binding.radioBtnsContainer.setOnCheckedChangeListener { radioGroup, checkedId ->
            radioBtbsList.forEach {
                if (checkedId == it.id) {
                    it.setTextColor(ContextCompat.getColor(context, R.color.green))
                } else {
                    it.setTextColor(ContextCompat.getColor(context, R.color._B1B1B1))
                }
            }
        }
    }


}