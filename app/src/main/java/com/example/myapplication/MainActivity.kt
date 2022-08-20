package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType.TYPE_CLASS_NUMBER
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.maxkeppeler.sheets.core.SheetStyle
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saqueBtn.setOnClickListener {
            separarNotas()
        }

        binding.limparBtn.setOnClickListener {
            limpar()
        }
    }

    @SuppressLint("SetTextI18n")
    fun separarNotas(){
        binding.saqueBtn.isEnabled = false
        var n200 = 0
        var n100 = 0
        var n50 = 0
        var n20 = 0
        var n10 = 0
        var n5 = 0
        var n2 = 0
        var n1 = 0

        var t200 = 0
        var t100 = 0
        var t50 = 0
        var t20 = 0
        var t10 = 0
        var t5 = 0
        var t2 = 0
        var t1 = 0

        InputSheet().show(this) {
            style(SheetStyle.BOTTOM_SHEET)
            title("Sacar dinheiro")
            titleColorRes(R.color.black)
            with(InputEditText("valor") {
                required(true)
                inputType(TYPE_CLASS_NUMBER)
                drawable(R.drawable.ic_round_monetization_on_24)
                label("Insira o valor do saque")
            })
            onClose { binding.saqueBtn.isEnabled = true }
            positiveButtonColorRes(R.color.green)
            onPositive("Sacar") { result ->
                t200 = 5
                t100 = 5
                t50 = 5
                t20 = 5
                t10 = 5
                t5 = 5
                t2 = 5
                t1 = 5

                try {
                    var valorUser = result.getString("valor").toString().toInt()
                    val valorCompleto = valorUser

                if (valorCompleto > 1000000000){
                    Toast.makeText(this@MainActivity, "Você com certeza não é tão rico assim", Toast.LENGTH_LONG).show()
                }else{

                    n200 = valorUser / 200
                    if (n200 > t200) {
                        n200 = t200
                    }
                    valorUser -= n200 * 200
                    t200 -= n200

                    n100 = valorUser / 100
                    if (n100 >= t100) {
                        n100 = t100
                    }
                    valorUser -= n100 * 100
                    t100 -= n100

                    n50 = valorUser / 50
                    if (n50 >= t50) {
                        n50 = t50
                    }
                    valorUser -= n50 * 50
                    t50 -= n50

                    n20 = valorUser / 20
                    if (n20 >= t20) {
                        n20 = t20
                    }
                    valorUser -= n20 * 20
                    t20 -= n20

                    n10 = valorUser / 10
                    if (n10 >= t10) {
                        n10 = t10
                    }
                    valorUser -= n10 * 10
                    t10 -= n10

                    n5 = valorUser / 5
                    if (n5 >= t5) {
                        n5 = t5
                    }
                    valorUser -= n5 * 5
                    t5 -= n5

                    n2 = valorUser / 2
                    if (n2 >= t2) {
                        n2 = t2
                    }
                    valorUser -= n2 * 2
                    t2 -= n2

                    n1 = valorUser / 1
                    if (n1 >= t1) {
                        n1 = t1
                    }
                    valorUser -= n1 * 1
                    t1 -= n1

                    if (n200 == 1){
                        binding.saidaUser200.text = "Notas de R$200,00: $n200 nota."

                    }else if(n200 == 0){
                        binding.saidaUser200.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser200.text = "Notas de R$200,00: $n200 notas."
                    }

                    if (n100 == 1){
                        binding.saidaUser100.text = "Notas de R$100,00: $n100 nota."
                    }else if(n100 == 0){
                        binding.saidaUser100.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser100.text = "Notas de R$100,00: $n100 notas."
                    }

                    if (n50 == 1){
                        binding.saidaUser50.text = "Notas de R$50,00: $n50 nota."
                    }else if(n50 == 0){
                        binding.saidaUser50.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser50.text = "Notas de R$50,00: $n50 notas."
                    }

                    if (n20 == 1){
                        binding.saidaUser20.text = "Notas de R$20,00: $n20 nota."
                    }else if(n20 == 0){
                        binding.saidaUser20.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser20.text = "Notas de R$20,00: $n20 notas."
                    }

                    if (n10 == 1){
                        binding.saidaUser10.text = "Notas de R$10,00: $n10 nota."
                    }else if(n10 == 0){
                        binding.saidaUser10.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser10.text = "Notas de R$10,00: $n10 notas."
                    }

                    if (n5 == 1){
                        binding.saidaUser5.text = "Notas de R$5,00: $n5 nota."
                    }else if(n5 == 0){
                        binding.saidaUser5.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser5.text = "Notas de R$5,00: $n5 notas."
                    }

                    if (n2 == 1){
                        binding.saidaUser2.text = "Notas de R$2,00: $n2 nota."
                    }else if(n2 == 0){
                        binding.saidaUser2.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser2.text = "Notas de R$2,00: $n2 notas."
                    }

                    if (n1 == 1){
                        binding.saidaUser1.text = "Notas de R$1,00: $n1 nota."
                    }else if(n1 == 0){
                        binding.saidaUser1.visibility = View.INVISIBLE
                    }else{
                        binding.saidaUser1.text = "Notas de R$1,00: $n1 notas."
                    }

                    binding.titulo.visibility = View.INVISIBLE
                    binding.aviso.visibility = View.INVISIBLE

                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.saidaUser200.visibility = View.VISIBLE
                        binding.saidaUser100.visibility = View.VISIBLE
                        binding.saidaUser50.visibility = View.VISIBLE
                        binding.saidaUser20.visibility = View.VISIBLE
                        binding.saidaUser10.visibility = View.VISIBLE
                        binding.saidaUser5.visibility = View.VISIBLE
                        binding.saidaUser2.visibility = View.VISIBLE
                        binding.saidaUser1.visibility = View.VISIBLE

                        binding.valorText.visibility = View.VISIBLE
                        binding.limparBtn.visibility = View.VISIBLE
                        binding.card.visibility = View.VISIBLE
                        binding.dividerCard.visibility = View.VISIBLE
                        binding.tituloCard.visibility = View.VISIBLE
                        binding.saque.visibility = View.VISIBLE
                        binding.saque.text = "R$$valorCompleto,00"
                        binding.titulo.visibility = View.VISIBLE
                        binding.titulo.text = "Seu saque 💸"
                        AnimationUtils.loadAnimation(this@MainActivity, androidx.appcompat.R.anim.abc_fade_in)
                    }, 500)

                    val res1 = (200 * n200)
                    val res2 = (100 * n100)
                    val res3 = (50 * n50)
                    val res4 = (20 * n20)
                    val res5 = (10 * n10)
                    val res6 = (5 * n5)
                    val res7 = (2 * n2)
                    val res8 = (1 * n1)

                    if(n200 != 0){
                        binding.valor1.text = "(200 × $n200) = $res1,00"
                        binding.valor1.visibility = View.VISIBLE
                    }else{binding.valor1.visibility = View.INVISIBLE}

                    if (n100 != 0) {
                        binding.valor2.text = "(100 × $n100 = ${100 * n100}) + $res1 = ${res2 + res1},00"
                        binding.valor2.visibility = View.VISIBLE
                    }else{binding.valor2.visibility = View.INVISIBLE}

                    if (n50 != 0) {
                        binding.valor3.text = "(50 × $n50 = ${50 * n50}) + ${res2 + res1} = ${res3 + res2 + res1},00"
                        binding.valor3.visibility = View.VISIBLE
                    }else{binding.valor3.visibility = View.INVISIBLE}

                    if (n20 != 0) {
                        binding.valor4.text = "(20 × $n20 = ${20 * n20}) + ${res3 + res2 + res1} = ${res4 + res3 + res2 + res1},00"
                        binding.valor4.visibility = View.VISIBLE
                    }else{binding.valor4.visibility = View.INVISIBLE}

                    if (n10 != 0) {
                        binding.valor5.text = "(10 × $n10 = ${10 * n10}) + ${res4 + res3 + res2 + res1} = ${res5 + res4 + res3 + res2 + res1},00"
                        binding.valor5.visibility = View.VISIBLE
                    }else{binding.valor5.visibility = View.INVISIBLE}

                    if (n5 != 0) {
                        binding.valor6.text = "(5 × $n5 = ${5 * n5}) + ${res5 + res4 + res3 + res2 + res1} = ${res6 + res5 + res4 + res3 + res2 + res1},00"
                        binding.valor6.visibility = View.VISIBLE
                    }else{binding.valor6.visibility = View.INVISIBLE}

                    if (n2 != 0) {
                        binding.valor7.text = "(2 × $n2 = ${2 * n2}) + ${res6 + res5 + res4 + res3 + res2 + res1} = ${res7 + res6 + res5 + res4 + res3 + res2 + res1},00"
                        binding.valor7.visibility = View.VISIBLE
                    }else{binding.valor7.visibility = View.INVISIBLE}

                    if (n1 != 0) {
                        binding.valor8.text = "(1 × $n1 = ${1 * n1}) + ${res7 + res6 + res5 + res4 + res3 + res2 + res1} = ${res8 + res7 + res6 + res5 + res4 + res3 + res2 + res1},00"
                        binding.valor8.visibility = View.VISIBLE
                    }else{binding.valor8.visibility = View.INVISIBLE}

                    binding.valorFinal.text = "Valor final: R$${res8 + res7 + res6 + res5 + res4 + res3 + res2 + res1},00"
                    binding.valorFinal.visibility = View.VISIBLE
                }
                }catch (e: Exception){
                    Toast.makeText(this@MainActivity, "Você com certeza não é tão rico assim", Toast.LENGTH_LONG).show()
                }
            }
            negativeButtonColorRes(R.color.grey)
            onNegative { dismiss() }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun limpar(){
        Handler(Looper.getMainLooper()).postDelayed({
            binding.saidaUser200.text = ""
            binding.saidaUser200.visibility = View.INVISIBLE
            binding.saidaUser100.text = ""
            binding.saidaUser100.visibility = View.INVISIBLE
            binding.saidaUser50.text = ""
            binding.saidaUser50.visibility = View.INVISIBLE
            binding.saidaUser20.text = ""
            binding.saidaUser20.visibility = View.INVISIBLE
            binding.saidaUser10.text = ""
            binding.saidaUser10.visibility = View.INVISIBLE
            binding.saidaUser5.text = ""
            binding.saidaUser5.visibility = View.INVISIBLE
            binding.saidaUser2.text = ""
            binding.saidaUser2.visibility = View.INVISIBLE
            binding.saidaUser1.text = ""
            binding.saidaUser1.visibility = View.INVISIBLE

            binding.titulo.visibility = View.INVISIBLE
            binding.valorText.visibility = View.INVISIBLE
            binding.limparBtn.visibility = View.INVISIBLE
            binding.card.visibility = View.INVISIBLE
            binding.tituloCard.visibility = View.INVISIBLE
            binding.dividerCard.visibility = View.INVISIBLE
            binding.saque.visibility = View.INVISIBLE
            binding.saque.text = ""
            AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_out)
        }, 300)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.aviso.visibility = View.VISIBLE
            binding.titulo.visibility = View.VISIBLE
            binding.titulo.text = "Sacar dinheiro 💵"
            AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in)
        }, 800)

        Toast.makeText(this, "Registros limpos", Toast.LENGTH_SHORT).show()
    }
}