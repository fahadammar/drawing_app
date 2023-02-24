package com.example.basicdrawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.basicdrawingapp.databinding.ActivityMainBinding
import com.example.basicdrawingapp.databinding.DialogBrushSizeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var imageButtonCurrentPaint: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.drawingView.setSizeForBrush(20.toFloat())

        // brushIconClick to show the brush size dialog, via it we choose the brush size
        brushIconClick(binding.brushSizeButton, binding)

        val paintColorsLinearLayout = binding.paintColors
        imageButtonCurrentPaint = paintColorsLinearLayout[2] as ImageButton
        // setting the current ImageButton
        imageButtonCurrentPaint?.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )
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

    fun paintClicked(view: View) {
        if (view != imageButtonCurrentPaint) {
            // update the color
            val imageButton = view as ImageButton

            /**
             * Here the tag is used for swapping the current color with the previous color
             * The tag stored the selected view
             * */
            val colorTag = imageButton.tag.toString()
            // swap the backgrounds for last active and currently active image button
            imageButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pallet_pressed))
            imageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.pallet_normal
                )
            )
            // current view is updated with selected view in the form of ImageButton
            imageButtonCurrentPaint = view
        }
    }
}