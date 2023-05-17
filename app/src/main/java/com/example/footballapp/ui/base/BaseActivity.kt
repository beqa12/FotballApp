package com.example.footballapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    lateinit var binding: VB
    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

}