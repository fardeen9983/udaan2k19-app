package `in`.ac.bvmengineering.udaan2k19.Misc

import `in`.ac.bvmengineering.udaan2k19.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewGroup
import kotlinx.android.synthetic.main.forgot_password_dialog.*

@SuppressLint("ValidFragment")
class ForgotPasswordDialog(internal var context: Context) : Dialog(context) {

    var rect = Rect()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_dialog)
        setCancelable(true)
        window!!.decorView.getWindowVisibleDisplayFrame(rect)
        val width = rect.width() * 0.9f
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setLayout(width.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)

        submit_button.setOnClickListener {
            if (message.text.toString().isEmpty() || message.text.toString().length != 10) {
                message.error = "Enter a valid phone no"
                return@setOnClickListener
            }
            dismiss()
        }

        cancel_button.setOnClickListener { dismiss() }
    }

}
