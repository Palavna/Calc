package com.example.yana.culculatorhome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yana.culculatorhome.MathOperatorsType.*
import com.example.yana.culculatorhome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var firstValue: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener { pokaz("1", false) }
        binding.btnTwo.setOnClickListener { pokaz("2", false) }
        binding.btnThree.setOnClickListener { pokaz("3", false) }
        binding.btnFour.setOnClickListener { pokaz("4", false) }
        binding.btnFive.setOnClickListener { pokaz("5", false) }
        binding.btnSix.setOnClickListener { pokaz("6", false) }
        binding.btnSeven.setOnClickListener { pokaz("7", false) }
        binding.btnEight.setOnClickListener { pokaz("8", false) }
        binding.btnNine.setOnClickListener { pokaz("9", false) }
        binding.btnZero.setOnClickListener { pokaz("0", false) }
//        binding.btnPoint.setOnClickListener { pokaz(".", false) }

        binding.btnEqually.setOnClickListener { pokaz(EQUALS.mathType, true) }
        binding.btnPlus.setOnClickListener { pokaz(PLUS.mathType, true) }
        binding.btnMines.setOnClickListener { pokaz(MINUS.mathType, true) }
//        binding.btnQuota.setOnClickListener { pokaz("( )", true) }
//        binding.btnPercent.setOnClickListener { pokaz("%", true) }
        binding.btnDivide.setOnClickListener { pokaz(DIVIDE.mathType, true) }
        binding.btnMulti.setOnClickListener { pokaz(MULTIPLY.mathType, true) }
        binding.btnAc.setOnClickListener { pokaz(AC.mathType, true) }


        binding.btnAc.setOnClickListener {
            data.clear()
            binding.result.text = ""
            binding.etInputData.setText("")
        }
    }

    private fun pokaz(stroka: String, ochistka: Boolean) {
        if (stroka == EQUALS.mathType) {

        } else {
            writeText(stroka)
            binding.etInputData.setText(stroka)
        }
    }

    val data = arrayListOf<String>()

    fun writeText(stroka: String) {
        when (stroka) {
            PLUS.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(PLUS.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }
            MINUS.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(MINUS.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }
            DIVIDE.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(DIVIDE.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }
            MULTIPLY.mathType -> {
                if (data.isNotEmpty()) {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        data.add(MULTIPLY.mathType)
                    } else {
                        data[data.size - 1] = stroka
                    }
                }
            }

            else -> {
                if (data.isEmpty()) {
                    data.add(stroka)
                } else {
                    var item = data[data.size - 1]
                    if (isNotSymbol(item)) {
                        item += stroka
                        data[data.size - 1] = item
                    } else {
                        data.add(stroka)
                    }
                }
            }
        }
        binding.etInputData.setText(data.joinToString(separator = "") { it })
        calculate()
    }

    fun calculate() {
        var result: Long = 0
        var lastOperator: String? = null
        data.forEach {
            if (isSymbol(it)) {
                lastOperator = it
            } else {
                if (lastOperator == null)
                    result = it.toLong()
                else {
                    when (lastOperator) {
                        MULTIPLY.mathType -> result *= it.toLong()
                        DIVIDE.mathType -> result /= it.toLong()
                        MINUS.mathType -> result -= it.toLong()
                        PLUS.mathType -> result += it.toLong()
                    }
                }
            }
        }
        binding.result.text = result.toString()
    }

    private fun isNotSymbol(item: String): Boolean {
        return item != MULTIPLY.mathType && item != DIVIDE.mathType
                && item != MINUS.mathType && item != PLUS.mathType
    }

    private fun isSymbol(item: String): Boolean {
        return item == MULTIPLY.mathType || item == DIVIDE.mathType
                || item == MINUS.mathType || item == PLUS.mathType
    }
}