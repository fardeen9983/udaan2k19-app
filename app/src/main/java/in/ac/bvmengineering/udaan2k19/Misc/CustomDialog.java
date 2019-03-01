package in.ac.bvmengineering.udaan2k19.Misc;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.ac.bvmengineering.udaan2k19.R;

@SuppressLint("ValidFragment")
public class CustomDialog extends Dialog {
    Context context;
    Button okButton;
    Rect rect = new Rect();
    String title, msg;

    public CustomDialog(Context context, String title, String msg) {
        super(context);
        this.context = context;
        this.title = title;
        this.msg = msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        setCancelable(true);
        TextView t = findViewById(R.id.title_text);
        TextView m = findViewById(R.id.message);
        m.setText(msg);
        t.setText(title);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        float width = rect.width() * 0.9f;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout((int) width, ViewGroup.LayoutParams.WRAP_CONTENT);
        okButton = findViewById(R.id.dialog_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Constant.getAnimation(context));
                dismiss();
            }
        });

    }

}
