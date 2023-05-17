package com.example.footballapp.ui.details.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.footballapp.databinding.TeamsLayouBinding

class TeamsDetailsCustomView(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet) {

    private lateinit var binding: TeamsLayouBinding

    init {
       binding = TeamsLayouBinding.inflate(
            LayoutInflater.from(context), this, true)

        binding.firstTeamName.setTextTest("Barcelona")
        binding.secondTeamName.setTextTest("Huesca")
        binding.firstTeamScoreResult.setTextTest("5", 25f)
        binding.dot.setTextTest(":", 25f)
        binding.secondTeamScoreResult.setTextTest("1", 25f)
    }

}