package com.example.footballapp.ui.details.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.R
import com.example.footballapp.databinding.BothTeamItemLayoutBinding
import com.example.footballapp.databinding.FirstTeamItemLayoutBinding
import com.example.footballapp.databinding.SecondTeamItemLayoutBinding
import com.example.footballapp.domain.models.*
import com.example.footballapp.utils.DEFAULT_PLAYER_IMG
import com.example.footballapp.utils.loadImage
import com.example.footballapp.utils.show

class MatchDetailRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val matchDetailsActions = ArrayList<Action>()

    private val FIRST_TEAM_HOLDER_TYPE = 1
    private val SECOND_TEAM_HOLDER_TYPE = 2
    private val BOTH_TEAM_HOLDER_TYPE = 3
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
            BOTH_TEAM_HOLDER_TYPE -> {
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
        if (action.isBothTeam) return BOTH_TEAM_HOLDER_TYPE
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
        fun setData(action: Action){
            action.player.forEach { player ->
                when(player?.teamType ){
                    MatchTeamType.TEAM1().teamType -> {
                        binding.firstTeamPlayerImg.loadImage( DEFAULT_PLAYER_IMG)
                        binding.playerName.setTextInfo(player.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)

                        when(player.actionType){
                            MatchActionType.GOAL().actionType -> {
                                when(player.goalType){
                                    GoalType.GOAL().goalType -> {
                                        binding.playerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.goals_by)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                        binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.goal_icon))
                                    }
                                    GoalType.OWN_GOAL().goalType -> {
                                        binding.playerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.own_goal)}", size = 10f, color = itemView.context.getColor(R.color._FF0000), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                        binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.own_goal_icon))
                                    }
                                }
                            }
                            MatchActionType.YELLOW_CARD().actionType -> {
                                binding.playerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.yellow_card))
                            }
                            MatchActionType.RED_CARD().actionType -> {
                                binding.playerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.unsportsmanlike_conduct)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.red_card))
                            }

                            MatchActionType.SUBSTITUTION().actionType ->{
//                                binding.firstTeamSubsOutLayout.root.show()
//                                binding.firstTeamSubsOutLayout.firstTeamPlayerSubsOutImg.loadImage(
//                                    DEFAULT_PLAYER_IMG)
//                                binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.substitution)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
//                                binding.firstTeamSubsOutLayout.playerSubsOutName.setTextInfo(action.player.get(1)?.playerName,12f, color = binding.root.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
//                                binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.substitution_in_img))

                            }
                        }
                    }
                }            }
        }
    }

    inner class SecondTeamViewHolder(val binding: SecondTeamItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(action: Action){
            action.player.forEach { player ->
                when(player?.teamType ){
                    MatchTeamType.TEAM2().teamType -> {
                        binding.secondTeamPlayerImg.loadImage( DEFAULT_PLAYER_IMG)
                        binding.secondPlayerName.setTextInfo(player.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)

                        when(player.actionType){
                            MatchActionType.GOAL().actionType -> {
                                when(player.goalType){
                                    GoalType.GOAL().goalType -> {
                                        binding.secondPlayerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.goals_by)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                        binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.goal_icon))
                                    }
                                    GoalType.OWN_GOAL().goalType -> {
                                        binding.secondPlayerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.own_goal)}", size = 10f, color = itemView.context.getColor(R.color._FF0000), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                        binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.own_goal_icon))
                                    }
                                }
                            }
                            MatchActionType.YELLOW_CARD().actionType -> {
                                binding.secondPlayerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.yellow_card))
                            }
                            MatchActionType.RED_CARD().actionType -> {
                                binding.secondPlayerBehaviour.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.red_card))
                            }

                            MatchActionType.SUBSTITUTION().actionType ->{
//                                binding.firstTeamSubsOutLayout.root.show()
//                                binding.firstTeamSubsOutLayout.firstTeamPlayerSubsOutImg.loadImage(
//                                    DEFAULT_PLAYER_IMG)
//                                binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.substitution)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
//                                binding.firstTeamSubsOutLayout.playerSubsOutName.setTextInfo(action.player.get(1)?.playerName,12f, color = binding.root.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
//                                binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.substitution_in_img))

                            }
                        }
                    }
                }            }
        }
    }
    inner class BothTeamViewHolder(val binding: BothTeamItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("UseCompatLoadingForDrawables")
        fun setData(action: Action){
            action.player.forEach { player ->
                when(player?.teamType ){
                    MatchTeamType.TEAM1().teamType -> {
                        binding.firstTeamPlayerImg.loadImage( DEFAULT_PLAYER_IMG)
                        binding.playerName.setTextInfo(action.player.get(0)?.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)

                        when(player.actionType){
                            MatchActionType.GOAL().actionType -> {
                                when(player.goalType){
                                    GoalType.GOAL().goalType -> {
                                        binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.goals_by)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                        binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.goal_icon))
                                    }
                                    GoalType.OWN_GOAL().goalType -> {
                                        binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.own_goal)}", size = 10f, color = itemView.context.getColor(R.color._FF0000), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                        binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.own_goal_icon))
                                    }
                                }
                            }
                            MatchActionType.YELLOW_CARD().actionType -> {
                                binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.yellow_card))
                            }
                            MatchActionType.RED_CARD().actionType -> {
                                binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                                binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.red_card))
                            }

                           MatchActionType.SUBSTITUTION().actionType ->{
                               binding.firstTeamSubsOutLayout.root.show()
                               binding.firstTeamSubsOutLayout.firstTeamPlayerSubsOutImg.loadImage(
                                   DEFAULT_PLAYER_IMG)
                               binding.playerName.setTextInfo(action.player.get(0)?.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)

                               binding.playerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.substitution)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                               binding.firstTeamSubsOutLayout.playerSubsOutName.setTextInfo(action.player.get(1)?.playerName,12f, color = binding.root.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.END)
                               binding.playerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.substitution_in_img))

                           }
                        }
                    }
                    MatchTeamType.TEAM2().teamType -> {
                        binding.secondTeamPlayerImg.loadImage(DEFAULT_PLAYER_IMG)
                        binding.secondPlayerName.setTextInfo(player.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)

                        when(player.actionType){
                            MatchActionType.GOAL().actionType -> {
                                when(player.goalType){
                                    GoalType.GOAL().goalType -> {
                                        binding.secondPlayerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.goals_by)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                        binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.goal_icon))
                                    }
                                    GoalType.OWN_GOAL().goalType -> {
                                        binding.secondPlayerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.own_goal)}", size = 10f, color = itemView.context.getColor(R.color._FF0000), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                        binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.own_goal_icon))
                                    }
                                }
                            }
                            MatchActionType.YELLOW_CARD().actionType -> {
                                binding.secondPlayerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.yellow_card))
                            }
                            MatchActionType.RED_CARD().actionType -> {
                                binding.secondPlayerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.tripping)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.red_card))
                            }

                            MatchActionType.SUBSTITUTION().actionType ->{
                                binding.secondTeamSubsOutLayout.root.show()
                                binding.secondTeamSubsOutLayout.secondTeamPlayerSubsOutImg.loadImage(
                                    DEFAULT_PLAYER_IMG)
                                binding.secondPlayerName.setTextInfo(player.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)

                                binding.secondPlayerName.setTextInfo(action.player.get(0)?.playerName,12f, color = binding.root.context.getColor(R.color.black), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondPlayerBehaviourTitle.setTextInfo(action.actionTime + "' ${itemView.context.getString(R.string.substitution)}", size = 10f, color = itemView.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondTeamSubsOutLayout.secondPlayerSubsOutName.setTextInfo(action.player.get(1)?.playerName,12f, color = binding.root.context.getColor(R.color._B0B0B0), maxLines = 1, ellipsize = TextUtils.TruncateAt.START)
                                binding.secondPlayerBehaviourImg.setImageDrawable(binding.root.context.getDrawable(R.drawable.substitution_in_img))
                            }
                        }
                    }
                }            }
        }
    }

    fun addPlayerActions(actions: List<Action>){
        this.matchDetailsActions.clear()
        this.matchDetailsActions.addAll(actions)
        notifyDataSetChanged()
    }
}