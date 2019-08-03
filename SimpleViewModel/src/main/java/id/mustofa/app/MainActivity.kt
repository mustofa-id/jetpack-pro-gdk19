package id.mustofa.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Habib Mustofa
 * Indonesia on 29/07/19.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculate.setOnClickListener { onCalculate() }

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        displayResult()
    }

    private fun onCalculate() {
        val length = editLength.text.toString()
        val width = editWidth.text.toString()
        val height = editHeight.text.toString()
        viewModel.squareArea(length, width, height)
        displayResult()
    }

    private fun displayResult() {
        val result = "Result: ${viewModel.result}"
        textResult.text = result
    }
}
