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
import com.maxkeppeler.sheets.info.InfoSheet
import com.maxkeppeler.sheets.input.InputSheet
import com.maxkeppeler.sheets.input.type.InputEditText

class MainActivity : AppCompatActivity() {

    // Variável binding para bindar os elementos da tela
    private lateinit var binding: ActivityMainBinding
    // Variável booleana para indentificar se o card está aberto ou não
    private var aberto = false

    // Método principal onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o binding na tela (activity_main.xml)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Define o conteúdo visto como o root do binding (view "mãe": RelativeLayout)
        setContentView(binding.root)

        // Quando clicado no botão de saque, chama o método separarNotas()
        binding.saqueBtn.setOnClickListener { separarNotas() }
        // Quando clicado no botão de limpar, chama o método limpar()
        binding.limparBtn.setOnClickListener { limpar() }
        // Quando clicado no botão de collapse, chama o método mostrarExtratoCompleto()
        binding.collapseCardBtn.setOnClickListener { mostrarExtratoCompleto() }
    }

    @SuppressLint("SetTextI18n")
    // Método principal do aplicativo para fazer os cálculos e saídas
    fun separarNotas(){
        // Desativa o botão para que o usuário não clique duas vezes nele e crie dois BottomSheetDialogs
        binding.saqueBtn.isEnabled = false

        // Notas utilizadas
        var n200: Int
        var n100: Int
        var n50: Int
        var n20: Int
        var n10: Int
        var n5: Int
        var n2: Int
        var n1: Int

        // Total de notas
        var t200: Int
        var t100: Int
        var t50: Int
        var t20: Int
        var t10: Int
        var t5: Int
        var t2: Int
        var t1: Int

        // Diálogo de inputs
        InputSheet().show(this) {
            style(SheetStyle.BOTTOM_SHEET)
            title("Sacar dinheiro")
            titleColorRes(R.color.black)
            // Input sheet que o usuário insere o valor que ele deseja retirar
            with(InputEditText("valor") {
                required(true)
                inputType(TYPE_CLASS_NUMBER)
                drawable(R.drawable.ic_round_monetization_on_24)
                label("Insira o valor do saque")
            })
            // Quando o diálogo for fechado, ativa o botão novamente
            onClose { binding.saqueBtn.isEnabled = true }
            // Botão positivo que começa os cálculos e mostra as sáidas
            positiveButtonColorRes(R.color.green)
            onPositive("Sacar") { result ->
                // Total de notas = 5 para cada
                t200 = 5
                t100 = 5
                t50 = 5
                t20 = 5
                t10 = 5
                t5 = 5
                t2 = 5
                t1 = 5

                // Try catch para se caso o valor inserido não consiga ser convertido para Int
                try {
                    // Converte valor inserido para Int e passa para variável do valor inserido
                    var valorUser = result.getString("valor").toString().toInt()
                    // Cria a variável valorCompleto, que leva o valor inteiro inserido
                    val valorCompleto = valorUser

                // Se o valor inserido for maior que 1 bilhão
                if (valorCompleto > 1000000000){
                    // Mostra o toast
                    Toast.makeText(this@MainActivity, "Você com certeza não é tão rico assim", Toast.LENGTH_LONG).show()
                // Caso contrário (valor menor que 1 bilhão)
                }else{
                    // Lógica para o cálculo de separação de notas do caixa eletrônico
                    // EXEMPLO:
                    // Notas de 200 é igual ao valor inserido pelo usuário dividido por 200
                    // Se o valor de notas de 200 for maior que o valor de notas totais de 200 na máquina
                    // Então iguala o valores de notas de 200 e as notas totais
                    // (dessa forma será usado o máximo de notas que a máquina possuir)
                    // E então, o valor inserido do usuário será ele mesmo menos a quantidade de notas de 200 multiplicado por 200
                    // E a quantidade de notas que sobram das totais são as notas totais - a quantidade de notas utilizadas
                    // Indo para próxima nota, pega o valor inserido que ficou do cálculo com a nota de 200
                    // e repete o mesmo processo com o próximo valor.

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

                    // Se o valor das notas utilizadas for igual a 1
                    if (n200 == 1){
                        binding.saidaUser200.text = "Notas de R$200,00: $n200 nota."
                    }else{
                        // Mostra no plural
                        binding.saidaUser200.text = "Notas de R$200,00: $n200 notas."
                    }

                    if (n100 == 1){
                        binding.saidaUser100.text = "Notas de R$100,00: $n100 nota."
                    }else{
                        binding.saidaUser100.text = "Notas de R$100,00: $n100 notas."
                    }

                    if (n50 == 1){
                        binding.saidaUser50.text = "Notas de R$50,00: $n50 nota."
                    }else{
                        binding.saidaUser50.text = "Notas de R$50,00: $n50 notas."
                    }

                    if (n20 == 1){
                        binding.saidaUser20.text = "Notas de R$20,00: $n20 nota."
                    }else{
                        binding.saidaUser20.text = "Notas de R$20,00: $n20 notas."
                    }

                    if (n10 == 1){
                        binding.saidaUser10.text = "Notas de R$10,00: $n10 nota."
                    }else{
                        binding.saidaUser10.text = "Notas de R$10,00: $n10 notas."
                    }

                    if (n5 == 1){
                        binding.saidaUser5.text = "Notas de R$5,00: $n5 nota."
                    }else{
                        binding.saidaUser5.text = "Notas de R$5,00: $n5 notas."
                    }

                    if (n2 == 1){
                        binding.saidaUser2.text = "Notas de R$2,00: $n2 nota."
                    }else{
                        binding.saidaUser2.text = "Notas de R$2,00: $n2 notas."
                    }

                    if (n1 == 1){
                        binding.saidaUser1.text = "Notas de R$1,00: $n1 nota."
                    }else{
                        binding.saidaUser1.text = "Notas de R$1,00: $n1 notas."
                    }

                    // Esconde o aviso e o título
                    binding.titulo.visibility = View.INVISIBLE
                    binding.aviso.visibility = View.INVISIBLE

                    Handler(Looper.getMainLooper()).postDelayed({
                        // Mostra todas as saídas
                        binding.saidaUser200.visibility = View.VISIBLE
                        binding.saidaUser100.visibility = View.VISIBLE
                        binding.saidaUser50.visibility = View.VISIBLE
                        binding.saidaUser20.visibility = View.VISIBLE
                        binding.saidaUser10.visibility = View.VISIBLE
                        binding.saidaUser5.visibility = View.VISIBLE
                        binding.saidaUser2.visibility = View.VISIBLE
                        binding.saidaUser1.visibility = View.VISIBLE

                        // Mostra todos os outros elementos relevantes
                        binding.valorText.visibility = View.VISIBLE
                        binding.limparBtn.visibility = View.VISIBLE
                        binding.card.visibility = View.VISIBLE
                        binding.dividerCard.visibility = View.VISIBLE
                        binding.tituloCard.visibility = View.VISIBLE
                        binding.saque.visibility = View.VISIBLE

                        // Mostra o valor inserido pelo usuário sendo ele o valor completo
                        binding.saque.text = "R$$valorCompleto,00"

                        // Mostra o título com texto alterado para "Seu saque 💸"
                        binding.titulo.visibility = View.VISIBLE
                        binding.titulo.text = "Seu saque 💸"

                        // Usa uma animação de fade in
                        AnimationUtils.loadAnimation(this@MainActivity, androidx.appcompat.R.anim.abc_fade_in)
                    }, 500)

                    // Constantes para facilitar visualização do cálculo e encurtar linhas
                    val res1 = (200 * n200)
                    val res2 = (100 * n100)
                    val res3 = (50 * n50)
                    val res4 = (20 * n20)
                    val res5 = (10 * n10)
                    val res6 = (5 * n5)
                    val res7 = (2 * n2)
                    val res8 = (1 * n1)

                    // Se o valor das notas utilizadas for diferente de 0, então mostra o cálculo feito e o resultado do cálculo
                    // Caso contrário, limpa o texto dessa saída.
                    if(n200 != 0) binding.valor1.text = "(200 × $n200) = $res1,00" else binding.valor1.text = ""

                    if (n100 != 0) binding.valor2.text = "(100 × $n100 = ${100 * n100}) + $res1 = ${res2 + res1},00" else binding.valor2.text = ""

                    if (n50 != 0) binding.valor3.text = "(50 × $n50 = ${50 * n50}) + ${res2 + res1} = ${res3 + res2 + res1},00" else binding.valor3.text = ""

                    if (n20 != 0) binding.valor4.text = "(20 × $n20 = ${20 * n20}) + ${res3 + res2 + res1} = ${res4 + res3 + res2 + res1},00" else binding.valor4.text = ""

                    if (n10 != 0) binding.valor5.text = "(10 × $n10 = ${10 * n10}) + ${res4 + res3 + res2 + res1} = ${res5 + res4 + res3 + res2 + res1},00" else binding.valor5.text = ""

                    if (n5 != 0) binding.valor6.text = "(5 × $n5 = ${5 * n5}) + ${res5 + res4 + res3 + res2 + res1} = ${res6 + res5 + res4 + res3 + res2 + res1},00" else binding.valor6.text = ""

                    if (n2 != 0) binding.valor7.text = "(2 × $n2 = ${2 * n2}) + ${res6 + res5 + res4 + res3 + res2 + res1} = ${res7 + res6 + res5 + res4 + res3 + res2 + res1},00" else binding.valor7.text = ""

                    if (n1 != 0) binding.valor8.text = "(1 × $n1 = ${1 * n1}) + ${res7 + res6 + res5 + res4 + res3 + res2 + res1} = ${res8 + res7 + res6 + res5 + res4 + res3 + res2 + res1},00" else binding.valor8.text = ""

                    // Mostra o valor final alcançado com base nos cálculos. Caso não tenham notas suficientes para atingir o valor
                    // inserido pelo usuário, mostrará o máximo possível.
                    if (res8 + res7 + res6 + res5 + res4 + res3 + res2 + res1 == valorCompleto){
                        binding.valorFinal.text = "Valor final: R$$valorCompleto,00"
                    // Caso o valor não tenha sido alcançado, mostra o valor máximo atingido e um toast
                    }else{
                        binding.valorFinal.text = "Valor alcançado: R$${res8 + res7 + res6 + res5 + res4 + res3 + res2 + res1},00"
                        Toast.makeText(this@MainActivity, "Notas insuficientes", Toast.LENGTH_SHORT).show()
                    }
                    binding.valorFinal.visibility = View.VISIBLE

                    // AlertDialog para mostrar as notas restantes das que foram utilizadas
                    InfoSheet().show(this@MainActivity) {
                        style(SheetStyle.DIALOG)
                        title("Notas restantes")
                        titleColorRes(R.color.green)
                        // Mostra as notas restantes
                        content("Notas de R$200,00 restantes: $t200 " +
                                "\n\nNotas de R$100,00 restantes: $t100 " +
                                "\n\nNotas de R$50,00 restantes: $t50 " +
                                "\n\nNotas de R$20,00 restantes: $t20 " +
                                "\n\nNotas de R$10,00 restantes: $t10 " +
                                "\n\nNotas de R$5,00 restantes: $t5 " +
                                "\n\nNotas de R$2,00 restantes: $t2 " +
                                "\n\nNotas de R$1,00 restantes: $t1")
                        // Esconde os botões
                        displayButtons(false)
                    }
                }
                }catch (e: Exception){
                    // Se não conseguir fazer a conversão para Int, mostra o toast
                    Toast.makeText(this@MainActivity, "Você com certeza não é tão rico assim", Toast.LENGTH_LONG).show()
                }
            }
            // Botão negativo do diálogo que apenas o fecha
            negativeButtonColorRes(R.color.grey)
            onNegative { dismiss() }
        }
    }

    @SuppressLint("SetTextI18n")
    // Método para limpar as saídas e esconde-las
    fun limpar(){
        // Utilizando um Handler para fazer um delay para esconder e apagar as informações
        Handler(Looper.getMainLooper()).postDelayed({
            // Saídas
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

            // Outros componentes da tela
            binding.titulo.visibility = View.INVISIBLE
            binding.valorText.visibility = View.INVISIBLE
            binding.limparBtn.visibility = View.INVISIBLE
            binding.card.visibility = View.INVISIBLE
            binding.tituloCard.visibility = View.INVISIBLE
            binding.dividerCard.visibility = View.INVISIBLE
            binding.saque.visibility = View.INVISIBLE
            binding.saque.text = ""

            // Faz uma animação de fade out
            AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_out)

            // Define a variável aberto do card como false
            aberto = false

            // Delay de 300 milissegundos
        }, 300)

        // Handler a parte para mostrar o aviso e mudar o título
        // após ter apagado tudo no Handler anterior
        Handler(Looper.getMainLooper()).postDelayed({
            // Mostra o aviso
            binding.aviso.visibility = View.VISIBLE

            // Muda o título para "Sacar dinheiro 💵"
            binding.titulo.visibility = View.VISIBLE
            binding.titulo.text = "Sacar dinheiro 💵"

            // Faz uma animação de fade in
            AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in)

            // Delay de 800 milissegundos
        }, 800)

        // Mostra um toast confirmando que os registros foram limpos
        Toast.makeText(this, "Registros limpos", Toast.LENGTH_SHORT).show()
    }

    // Método para expandir e retrair as informaões do card
    private fun mostrarExtratoCompleto(){
        // Se o card não estiver aberto (aberto = false)
        if (!aberto){
            // Verifica se as saídas do do extrato estão diferentes de ""
            // ou seja, se estão vazias
            if (binding.valor1.text != ""){
                // Caso for verdadeiro, mostra a saída
                binding.valor1.visibility = View.VISIBLE
            }
            if (binding.valor2.text != ""){
                binding.valor2.visibility = View.VISIBLE
            }
            if (binding.valor3.text != ""){
                binding.valor3.visibility = View.VISIBLE
            }
            if (binding.valor3.text != ""){
                binding.valor3.visibility = View.VISIBLE
            }
            if (binding.valor4.text != ""){
                binding.valor4.visibility = View.VISIBLE
            }
            if (binding.valor5.text != ""){
                binding.valor5.visibility = View.VISIBLE
            }
            if (binding.valor6.text != ""){
                binding.valor6.visibility = View.VISIBLE
            }
            if (binding.valor7.text != ""){
                binding.valor7.visibility = View.VISIBLE
            }
            if (binding.valor8.text != ""){
                binding.valor8.visibility = View.VISIBLE
            }

            // Muda a imagem do botão do card para a flecha de retrair (fechar)
            binding.collapseCardBtn.setImageResource(R.drawable.ic_round_keyboard_arrow_up_24)

            // E define "aberto" como true
            aberto = true

        // Caso contrário (Se o card estiver aberto (aberto = true)
        }else{
            // Então quando o botão for clicado, todas as saídas serão escondidas
            binding.valor1.visibility = View.GONE
            binding.valor2.visibility = View.GONE
            binding.valor3.visibility = View.GONE
            binding.valor4.visibility = View.GONE
            binding.valor5.visibility = View.GONE
            binding.valor6.visibility = View.GONE
            binding.valor7.visibility = View.GONE
            binding.valor8.visibility = View.GONE

            // O botão do card mudará a imagem para a flecha de expandir (abrir)
            binding.collapseCardBtn.setImageResource(R.drawable.ic_round_keyboard_arrow_down_24)

            // E define "aberto" como false
            aberto = false
        }
    }
}