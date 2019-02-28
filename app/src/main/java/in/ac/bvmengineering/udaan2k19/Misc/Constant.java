package in.ac.bvmengineering.udaan2k19.Misc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public class Constant {
    public static String AUTOMOTIVE_PHILOSOPHER = "mechProd";
    public static String BUILDER_OF_AZKABAN = "civil";
    public static String CHAMBER_OF_CODERS = "cpit";
    public static String HALF_WAVE_PRINCE = "ecel";
    public static String ORDER_OF_OHMS = "ee";
    public static String GOBLET_OF_WORKSHOPS = "workshop";
    public static String SCAMANDERS_SUITCASE = "non-tech";
    public static String MAD_HOLLOWS = "cultural";
    private static Dialog comingSoonDialog;
    public static String[] EVENT_CATEGORIES = new String[]{BUILDER_OF_AZKABAN, AUTOMOTIVE_PHILOSOPHER, CHAMBER_OF_CODERS, HALF_WAVE_PRINCE, MAD_HOLLOWS, SCAMANDERS_SUITCASE, ORDER_OF_OHMS};

    public static Dialog getComingSoonDialog(Context context, String msg) {
        if (comingSoonDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Coming Soon ...");
            builder.setMessage(msg);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                }
            });
            comingSoonDialog = builder.create();
        }
        return comingSoonDialog;
    }
}
