package in.ac.bvmengineering.udaan2k19.Misc;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import java.util.Objects;

import in.ac.bvmengineering.udaan2k19.R;

@SuppressLint("ValidFragment")
public class BottomPanel {
    private Context context;

    public BottomPanel(Context context) {
        this.context = context;
    }

    public Dialog showDialog() {
        final Dialog dialog = new Dialog(context, R.style.CustomDialog);
        WindowManager.LayoutParams layoutParams = Objects.requireNonNull(dialog.getWindow()).getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;

        dialog.setContentView(R.layout.bottom_sheet);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.setCancelable(true);
        return dialog;
    }
}
