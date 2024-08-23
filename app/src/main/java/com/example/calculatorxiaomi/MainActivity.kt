package com.example.calculatorxiaomi

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorxiaomi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()
    }

    private fun onOperatorClicked() {
        binding.btnAC.setOnClickListener {

            binding.txtJavab.text = null
            binding.txtAmaliat.text = null


        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnParantezBaste.setOnClickListener {
            appendText(")")
        }
        binding.btnTaghsim.setOnClickListener {

            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar == '*' || myChar == '-' || myChar == '+') {
                    binding.txtAmaliat.text =
                        binding.txtAmaliat.text.substring(0, binding.txtAmaliat.length() - 1)
                    appendText("/")
                } else if (myChar == '/') {

                } else {
                    appendText("/")
                }
            }

        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar == '/' || myChar == '-' || myChar == '+') {
                    binding.txtAmaliat.text =
                        binding.txtAmaliat.text.substring(0, binding.txtAmaliat.length() - 1)
                    appendText("*")
                } else if (myChar == '*') {

                } else {
                    appendText("*")
                }
            }


        }
        binding.btnMenha.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar == '*' || myChar == '/' || myChar == '+') {
                    binding.txtAmaliat.text =
                        binding.txtAmaliat.text.substring(0, binding.txtAmaliat.length() - 1)
                    appendText("-")
                } else if (myChar == '-') {

                } else {
                    appendText("-")
                }
            } else {
                appendText("-")
            }
        }
        binding.btnJam.setOnClickListener {

            if (binding.txtAmaliat.text.isNotEmpty()) {
                val myChar = binding.txtAmaliat.text.last()
                if (myChar == '*' || myChar == '-' || myChar == '/') {
                    binding.txtAmaliat.text =
                        binding.txtAmaliat.text.substring(0, binding.txtAmaliat.length() - 1)
                    appendText("+")
                } else if (myChar == '+') {

                } else {
                    appendText("+")
                }
            } else {
                appendText("+")
            }
        }
        binding.btnPakidan.setOnClickListener {

            if (binding.txtAmaliat.text.isNotEmpty() && binding.txtJavab.text.isEmpty()) {

                val oldText = binding.txtAmaliat.text.toString()
                binding.txtAmaliat.text = oldText.substring(0, oldText.length - 1)

            }

        }
        binding.btnMosavi.setOnClickListener {

            try {
                val amaliat = ExpressionBuilder(binding.txtAmaliat.text.toString()).build()
                val result = amaliat.evaluate()

                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }

            } catch (e: Exception) {
                binding.txtJavab.text = null
                binding.txtAmaliat.text = null
                Toast.makeText(this, "لطفا مقادیر را درست وارد نمایید.", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {
            if (binding.txtAmaliat.text.isNotEmpty()) {
                appendText("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
            if (binding.txtAmaliat.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) {
                appendText("0.")
            } else if (binding.txtAmaliat.text.last() != '.') {
                appendText(".")
            }
        }

    }

    private fun appendText(text: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            if (text == "+" || text == "-" || text == "/" || text == "*") {
                binding.txtAmaliat.text = binding.txtJavab.text.toString()
                binding.txtAmaliat.append(text)
                binding.txtJavab.text = null
            } else {
                binding.txtAmaliat.text = null
                binding.txtAmaliat.append(text)
                binding.txtJavab.text = null
            }
        } else {
            binding.txtAmaliat.append(text)
        }

        val viewTree1: ViewTreeObserver = binding.hsTxtAmaliatMain.viewTreeObserver
        viewTree1.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.hsTxtAmaliatMain.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.hsTxtAmaliatMain.scrollTo(binding.txtAmaliat.width, 0)
            }
        })

    }
}


