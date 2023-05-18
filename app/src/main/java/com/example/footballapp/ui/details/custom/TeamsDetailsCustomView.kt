package com.example.footballapp.ui.details.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.footballapp.R
import com.example.footballapp.databinding.TeamsLayouBinding
import com.example.footballapp.domain.models.MatchDetail
import com.example.footballapp.utils.DateTimeHelper
import com.example.footballapp.utils.loadImage


class TeamsDetailsCustomView(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet) {

    private lateinit var binding: TeamsLayouBinding
    private lateinit var mContext: Context

    init {
        init()

    }

    private fun init(){
        mContext = context
        binding = TeamsLayouBinding.inflate(
            LayoutInflater.from(mContext), this, true)
    }

    fun setData(match: MatchDetail){
        binding.firstTeamName.setTextInfo(match.match.team1?.teamName)
        binding.secondTeamName.setTextInfo(match.match.team2?.teamName)
        binding.firstTeamScoreResult.setTextInfo(match.match.team1?.score.toString(), 25f)
        binding.dot.setTextInfo(":", 25f)
        binding.secondTeamScoreResult.setTextInfo(match.match.team2?.score.toString(), 25f)
        match.match.team1?.ballPosition?.let {
            setBallPossession(it)
        }
        binding.firstTeamLogo.loadImage(match.match.team1?.teamImage.toString())
        binding.secondTeamLogo.loadImage(match.match.team2?.teamImage.toString())
        binding.matchDate.setTextInfo(DateTimeHelper.getMatchDate(match.match.matchDate!!.toLong()), 12f, mContext.getColor(R.color.green))
        binding.matchStadium.setTextInfo(match.match.stadiumAdress, 12f, mContext.getColor(R.color._B1B1B1))
    }

    private fun setBallPossession(firstTeamPossession: Int){
        binding.prgress.setProgress(firstTeamPossession)
        binding.ballPossessionTitle.setTextInfo(context.getString(R.string.ball_possession_title), color = mContext.getColor(R.color._B1B1B1))
        binding.firstTeamPossession.setTextInfo("$firstTeamPossession%", 12f, mContext.getColor(R.color._B1B1B1))
        val secondTeamsPosession = 100 - firstTeamPossession
        binding.secondTeamPossession.setTextInfo("$secondTeamsPosession%", 12f, mContext.getColor(R.color._B1B1B1))
        binding.firstHalfTv.setTextInfo(context.getString(R.string.first_half), size = 9f, color = mContext.getColor(R.color._B0B0B0))
        binding.firstHalfResultTv.setTextInfo(("1:1"),size = 9f, color = mContext.getColor(R.color._B0B0B0))

    }

}