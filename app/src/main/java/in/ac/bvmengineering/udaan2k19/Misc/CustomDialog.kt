package `in`.ac.bvmengineering.udaan2k19.Misc

import `in`.ac.bvmengineering.udaan2k19.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.dialog.*


@SuppressLint("ValidFragment")
class CustomDialog(internal var context: Context, private var title: String, private var msg: String) : Dialog(context) {
    lateinit var okButton: Button
    var rect = Rect()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)
        setCancelable(true)
        title_text.text = title
        message.text = msg
        window!!.decorView.getWindowVisibleDisplayFrame(rect)
        val width = rect.width() * 0.9f
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setLayout(width.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        okButton = findViewById(R.id.dialog_button)
        okButton.setOnClickListener {
            // view.startAnimation(Constant.getAnimation(context))
            dismiss()
        }

    }

}
