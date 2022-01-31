package com.ukejee.prototechnicaltask.customView

import android.app.Activity
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ukejee.prototechnicaltask.R
import com.ukejee.prototechnicaltask.common.setImage

class HighlighterDialog(parentActivity: Activity) : Dialog(parentActivity) {

    private var peekThroughView: View? = null
    private var windowHeight: Int
    private var windowWidth: Int
    private var statusBarHeight: Int
    private var container: ConstraintLayout
    private var label: TextView
    private var highlightedView: AppCompatImageView

    init {
        setContentView(R.layout.highlighter_dialog)
        container = findViewById(R.id.container)
        label = findViewById(R.id.description_label)
        highlightedView = findViewById(R.id.highlighted_view)

        container.setOnClickListener { dismiss() }

        val usableView = parentActivity.window.findViewById<View>(Window.ID_ANDROID_CONTENT)
        windowHeight = usableView.height
        windowWidth = usableView.width
        statusBarHeight = getStatusBarHeight()

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId: Int =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun bitmapFromView(view: View): Bitmap? {
        //width and height must be > 0
        if (view.width <= 0 || view.height <= 0) {
           // Timber.e("width and height must be > 0 !: width: ${view.width}; height: ${view.height}")
            return null
        }

        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(canvas)
        return bitmap
    }

    override fun show() {
        drawPeekingView()
        super.show()
    }

    private fun drawPeekingView() {
        val bitmap = Bitmap.createBitmap(windowWidth, windowHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(
            ContextCompat.getColor(
                context,
                R.color.highlighter_dialog_background_color
            )
        )

        peekThroughView?.let {
            bitmapFromView(it)?.let { viewBitmap ->
                val xy = IntArray(2)
                it.getLocationOnScreen(xy)

                highlightedView.setImageBitmap(viewBitmap)

                positionLabel()
            }
        }

        container.background = BitmapDrawable(context.resources, bitmap)
    }

    private fun sizeInPixels(dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun positionLabel() {
        val layoutParams = label.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.startToStart = container.id
        layoutParams.endToEnd = container.id
        layoutParams.topToTop = ConstraintLayout.LayoutParams.UNSET
        layoutParams.bottomToTop = highlightedView.id
        label.layoutParams = layoutParams
    }

    fun setPeekThroughView(view: View) {
        peekThroughView = view
        drawPeekingView()
    }

    fun setHighlightedViewDescription(description: String) {
        label.text = description
    }

    fun setImageView(url: String) {
        highlightedView.setImage(url)
        positionLabel()
    }

}