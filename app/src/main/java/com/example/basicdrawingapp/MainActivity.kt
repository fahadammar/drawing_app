package com.example.basicdrawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.basicdrawingapp.databinding.ActivityMainBinding
import com.example.basicdrawingapp.databinding.DialogBrushSizeBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.drawingView.setSizeForBrush(20.toFloat())

        brushIconClick(binding.brushSizeButton, binding)

    }

    private fun brushIconClick(brushSizeButton: ImageButton, mainBinding: ActivityMainBinding) {
        brushSizeButton.setOnClickListener {
            showBrushSizeChooserDialog(mainBinding)
        }
    }

    private fun showBrushSizeChooserDialog(mainBinding: ActivityMainBinding) {
        val brushBinding: DialogBrushSizeBinding = DialogBrushSizeBinding.inflate(layoutInflater)
        val brushDialog = Dialog(this)
        brushDialog.setContentView(brushBinding.root)

        buttonClicksForBrushSizes(brushBinding, mainBinding, brushDialog)

        brushDialog.show()
    }

    private fun buttonClicksForBrushSizes(
        brushBinding: DialogBrushSizeBinding,
        mainBinding: ActivityMainBinding,
        brushDialog: Dialog
    ) {
        brushBinding.smallBrush.setOnClickListener {
            mainBinding.drawingView.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        brushBinding.mediumBrush.setOnClickListener {
            mainBinding.drawingView.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        brushBinding.largeBrush.setOnClickListener {
            mainBinding.drawingView.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
    }
}