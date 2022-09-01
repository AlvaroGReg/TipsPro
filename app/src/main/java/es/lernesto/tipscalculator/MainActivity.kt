package es.lernesto.tipscalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import es.lernesto.tipscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var darkTheme : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener{calculateTip()}
        binding.imageViewDarkTheme.setOnClickListener {
            swapDarkTheme()

        }

        binding.textInputLayout.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    private fun swapDarkTheme() {

        if (darkTheme){

        }else{

        }
        darkTheme = !darkTheme


    }

    private fun calculateTip(){

        val stringInTextField = binding.txtPrice.text.toString().replace(',', '.')
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

        if (binding.switchRoundUp.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.textViewTipResult.text = formattedTip
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}