package com.example.yana.culculatorhome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.yana.culculatorhome.data.MathOperatorsType.*
import com.example.yana.culculatorhome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener { viewModel.pokaz("1", false) }
        binding.btnTwo.setOnClickListener { viewModel.pokaz("2", false) }
        binding.btnThree.setOnClickListener { viewModel.pokaz("3", false) }
        binding.btnFour.setOnClickListener { viewModel.pokaz("4", false) }
        binding.btnFive.setOnClickListener { viewModel.pokaz("5", false) }
        binding.btnSix.setOnClickListener { viewModel.pokaz("6", false) }
        binding.btnSeven.setOnClickListener { viewModel.pokaz("7", false) }
        binding.btnEight.setOnClickListener { viewModel.pokaz("8", false) }
        binding.btnNine.setOnClickListener { viewModel.pokaz("9", false) }
        binding.btnZero.setOnClickListener { viewModel.pokaz("0", false) }

        binding.btnEqually.setOnClickListener { viewModel.pokaz(EQUALS.mathType, true) }
        binding.btnPlus.setOnClickListener { viewModel.pokaz(PLUS.mathType, true) }
        binding.btnMines.setOnClickListener { viewModel.pokaz(MINUS.mathType, true) }
        binding.btnDivide.setOnClickListener { viewModel.pokaz(DIVIDE.mathType, true) }
        binding.btnMulti.setOnClickListener { viewModel.pokaz(MULTIPLY.mathType, true) }
        binding.btnAc.setOnClickListener { viewModel.pokaz(AC.mathType, true) }

        viewModel.totalResult.observe(this,{
            binding.result.text = it
        })

        viewModel.result.observe(this, {
            binding.etInputData.setText(it)
        })


        binding.btnAc.setOnClickListener {
            viewModel.data.clear()
            binding.result.text = ""
            binding.etInputData.setText("")
        }
    }
}