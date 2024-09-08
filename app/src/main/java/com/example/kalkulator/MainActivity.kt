package com.example.kalkulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener untuk semua tombol
        binding.btn0.setOnClickListener { appendExpression("0", true) }
        binding.btn1.setOnClickListener { appendExpression("1", true) }
        binding.btn2.setOnClickListener { appendExpression("2", true) }
        binding.btn3.setOnClickListener { appendExpression("3", true) }
        binding.btn4.setOnClickListener { appendExpression("4", true) }
        binding.btn5.setOnClickListener { appendExpression("5", true) }
        binding.btn6.setOnClickListener { appendExpression("6", true) }
        binding.btn7.setOnClickListener { appendExpression("7", true) }
        binding.btn8.setOnClickListener { appendExpression("8", true) }
        binding.btn9.setOnClickListener { appendExpression("9", true) }
        binding.btnTitik.setOnClickListener { appendExpression(".", true) }

        binding.btnPlus.setOnClickListener { appendExpression("+", false) }
        binding.btnKurang.setOnClickListener { appendExpression("-", false) }
        binding.btnKali.setOnClickListener { appendExpression("*", false) }
        binding.btnBagi.setOnClickListener { appendExpression("/", false) }

        binding.btnPersen.setOnClickListener { appendExpression("%", false) }

        // Tombol AC untuk menghapus semua input
        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtResult.text = ""
        }

        // Tombol sama dengan untuk menghitung hasil ekspresi
        binding.btnSamaDengan.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()
                binding.txtResult.text = result.toString()
            } catch (e: Exception) {
                binding.txtResult.text = "Error"
            }
        }
    }

    // Fungsi untuk menambahkan angka/operator ke ekspresi
    private fun appendExpression(string: String, canClear: Boolean) {
        if (binding.txtResult.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        if (canClear) {
            binding.txtResult.text = ""
            binding.txtExpression.append(string)
        } else {
            binding.txtExpression.append(binding.txtResult.text)
            binding.txtExpression.append(string)
            binding.txtResult.text = ""
        }
    }
}
