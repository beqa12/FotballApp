package com.example.footballapp.ui.details.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.footballapp.R
import com.example.footballapp.databinding.TeamsLayouBinding

class TeamsDetailsCustomView(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet) {

    private lateinit var binding: TeamsLayouBinding

    init {
       binding = TeamsLayouBinding.inflate(
            LayoutInflater.from(context), this, true)

        binding.firstTeamName.setData("Barcelona")
        binding.secondTeamName.setData("Huesca")
        binding.firstTeamScoreResult.setData("5", 25f)
        binding.dot.setData(":", 25f)
        binding.secondTeamScoreResult.setData("1", 25f)
        setBallPossession(80)
    }

    fun setBallPossession(firstTeamPossession: Int){
        binding.prgress.setProgress(firstTeamPossession)
        binding.ballPossessionTitle.setData(context.getString(R.string.ball_possession_title), color = context.getColor(R.color._B1B1B1))
        binding.firstTeamPossession.setData("$firstTeamPossession%", 12f, context.getColor(R.color._B1B1B1))
        val secondTeamsPosession = 100 - firstTeamPossession
        binding.secondTeamPossession.setData("$secondTeamsPosession%", 12f, context.getColor(R.color._B1B1B1))
        binding.firstHalfTv.setData(context.getString(R.string.first_half), size = 9f, color = context.getColor(R.color._B0B0B0))
        binding.firstHalfResultTv.setData(("1:1"),size = 9f, color = context.getColor(R.color._B0B0B0))

    }

}