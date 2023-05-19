package com.example.footballapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.BothSubsLayoutBinding
import com.example.footballapp.databinding.BothTeamItemLayoutBinding
import com.example.footballapp.databinding.FirstTeamItemLayoutBinding
import com.example.footballapp.databinding.SecondTeamItemLayoutBinding
import com.example.footballapp.domain.models.*

class MatchDetailRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val matchDetailsActions = ArrayList<ActionTest>()

    private val FIRST_TEAM_HOLDER_TYPE = 1
    private val SECOND_TEAM_HOLDER_TYPE = 2
    private val BOTH_TEAM_TOGETHER_HOLDER_TYPE = 3
    private val SUBSTITUTION_TYPE = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            FIRST_TEAM_HOLDER_TYPE -> {
                val binding = FirstTeamItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                FirstTeamViewHolder(binding)
            }
            SECOND_TEAM_HOLDER_TYPE -> {
                val binding = SecondTeamItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                SecondTeamViewHolder(binding)
            }
            BOTH_TEAM_TOGETHER_HOLDER_TYPE -> {
                val binding = BothTeamItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                BothTeamViewHolder(binding)
            }

            else -> {
                val binding = FirstTeamItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                FirstTeamViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val action = matchDetailsActions.get(position)
        when(holder){
            is FirstTeamViewHolder -> {
                holder.setData(action)
            }
            is SecondTeamViewHolder -> {
                holder.setData(action)
            }
            is BothTeamViewHolder -> {
                holder.setData(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return matchDetailsActions.size
    }

    override fun getItemViewType(position: Int): Int {
        val action = matchDetailsActions.get(position)
        if (action.isBothTeam) return BOTH_TEAM_TOGETHER_HOLDER_TYPE
        action.player.forEachIndexed { index, playerTest ->
            when(playerTest?.teamType){
                MatchTeamType.TEAM1().teamType -> {
                    return FIRST_TEAM_HOLDER_TYPE
                }
                MatchTeamType.TEAM2().teamType -> {
                    return SECOND_TEAM_HOLDER_TYPE
                }
            }
        }
        return 0
    }

    inner class FirstTeamViewHolder(val binding: FirstTeamItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(action: ActionTest){

        }
    }

    inner class SecondTeamViewHolder(val binding: SecondTeamItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(action: ActionTest){

        }
    }
    inner class BothTeamViewHolder(val binding: BothTeamItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(action: ActionTest){
            action.player.forEach {
                when(it?.teamType ){
                    MatchTeamType.TEAM1().teamType -> {
                        when(it.actionType){
                           MatchActionType.SUBSTITUTION().actionType ->{
                               binding.firstTeamSubsOutLayout.root.visibility = View.VISIBLE

                           }
                        }
                    }
                    MatchTeamType.TEAM2().teamType -> {
                        when(it.actionType){
                            MatchActionType.SUBSTITUTION().actionType ->{
                                binding.secondTeamSubsOutLayout.root.visibility = View.VISIBLE
                            }
                        }
                    }
                }            }
        }
    }

    fun addPlayerActions(actions: List<ActionTest>){
        this.matchDetailsActions.clear()
        this.matchDetailsActions.addAll(actions)
        notifyDataSetChanged()
    }
}