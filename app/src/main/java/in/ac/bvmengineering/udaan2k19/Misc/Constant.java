package in.ac.bvmengineering.udaan2k19.Misc;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import in.ac.bvmengineering.udaan2k19.R;

public class Constant {
    public final static String AUTOMOTIVE_PHILOSOPHER = "mechProd";
    public final static String BUILDER_OF_AZKABAN = "civil";
    public final static String CHAMBER_OF_CODERS = "cpit";
    public final static String HALF_WAVE_PRINCE = "ecel";
    public final static String ORDER_OF_OHMS = "ee";
    public static final String GOBLET_OF_WORKSHOPS = "workshop";
    public static final String SCAMANDERS_SUITCASE = "non-tech";
    public static final String MAD_HOLLOWS = "cultural";
    public static final String ANDROID_DEVELOPER = "Android Developer";
    public static final String DEVELOPER_HEAD = "Developer Head";
    public static final String WEB_DEVELOPER = "Web Developer";
    public static final String UI_DESIGNER = "UI Designer";
    public static final String[] EVENT_CATEGORIES = {BUILDER_OF_AZKABAN, AUTOMOTIVE_PHILOSOPHER, CHAMBER_OF_CODERS, HALF_WAVE_PRINCE, MAD_HOLLOWS, SCAMANDERS_SUITCASE, ORDER_OF_OHMS, GOBLET_OF_WORKSHOPS};
    public static final String[] DEVELOPERS = {DEVELOPER_HEAD, ANDROID_DEVELOPER, UI_DESIGNER, WEB_DEVELOPER};
    public static Animation getAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.button_animation);
    }
}

