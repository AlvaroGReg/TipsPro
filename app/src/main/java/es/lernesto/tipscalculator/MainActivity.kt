package es.lernesto.tipscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.lernesto.tipscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener{calculateTip()}
    }

    private fun calculateTip(){

        val stringInTextField = binding.txtPrice.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if(cost == null){
            val toast = Toast.makeText(
                this, "Introduce a valid number", Toast.LENGTH_SHORT).show()
            return
        }
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButton_okay15 -> 0.15
            R.id.radioButton_good18 -> 0.18
            else -> 0.2
        }
        var tip = cost * tipPercentage

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.textViewTipresult.text = getString(R.string.tip_amount, formattedTip)
    }


}