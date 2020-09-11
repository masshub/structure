package com.max.custom.toast;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.max.custom.R;

/**
 * This file is part of Toasty.
 * <p>
 * Toasty is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Toasty is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Toasty.  If not, see <http://www.gnu.org/licenses/>.
 */

@SuppressLint("InflateParams")
public class Toasty {
    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16; // in SP

    private static boolean tintIcon = true;
    private static boolean allowQueue = true;

    private static Toast lastToast = null;

    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    private static Context context;

    private Toasty() {
        // avoiding instantiation
    }

    public static void init(Application application) {
        context = application;

    }

    @CheckResult
    public static Toast normal(@StringRes int message) {
        return normal(message, Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message) {
        return normal(message, Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, Drawable icon) {
        return normal(message, Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, Drawable icon) {
        return normal(message, Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, int duration) {
        return normal(message, duration, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, int duration) {
        return normal(message, duration, null, false);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, int duration,
                               Drawable icon) {
        return normal(message, duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, int duration,
                               Drawable icon) {
        return normal(message, duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, int duration,
                               Drawable icon, boolean withIcon) {
        return custom(ToastyType.NORMAL, message, icon, duration, withIcon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, int duration,
                               Drawable icon, boolean withIcon) {
        return custom(ToastyType.NORMAL, message, icon, duration, withIcon, true);
    }

    @CheckResult
    public static Toast warning(@StringRes int message) {
        return warning(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@NonNull CharSequence message) {
        return warning(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@StringRes int message, int duration) {
        return warning(message, duration, true);
    }

    @CheckResult
    public static Toast warning(@NonNull CharSequence message, int duration) {
        return warning(message, duration, true);
    }

    @CheckResult
    public static Toast warning(@StringRes int message, int duration, boolean withIcon) {
        return custom(ToastyType.WARNING, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast warning(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(ToastyType.WARNING, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast info(@StringRes int message) {
        return info(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast info(@NonNull CharSequence message) {
        return info(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast info(@StringRes int message, int duration) {
        return info(message, duration, true);
    }

    @CheckResult
    public static Toast info(@NonNull CharSequence message, int duration) {
        return info(message, duration, true);
    }

    @CheckResult
    public static Toast info(@StringRes int message, int duration, boolean withIcon) {
        return custom(ToastyType.INFO, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast info(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(ToastyType.INFO, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@StringRes int message) {
        return success(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@NonNull CharSequence message) {
        return success(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@StringRes int message, int duration) {
        return success(message, duration, true);
    }

    @CheckResult
    public static Toast success(@NonNull CharSequence message, int duration) {
        return success(message, duration, true);
    }

    @CheckResult
    public static Toast success(@StringRes int message, int duration, boolean withIcon) {
        return custom(ToastyType.SUCCESS, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(ToastyType.SUCCESS, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@StringRes int message) {
        return error(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast error(@NonNull CharSequence message) {
        return error(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast error(@StringRes int message, int duration) {
        return error(message, duration, true);
    }

    @CheckResult
    public static Toast error(@NonNull CharSequence message, int duration) {
        return error(message, duration, true);
    }

    @CheckResult
    public static Toast error(@StringRes int message, int duration, boolean withIcon) {
        return custom(ToastyType.ERROR, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(ToastyType.ERROR, message, duration, withIcon, true);
    }

    @CheckResult
    public static Toast custom(ToastyType type, @StringRes int message, Drawable icon,
                               int duration, boolean withIcon) {
        return custom(type, message, icon, duration, withIcon, false);
    }

    @CheckResult
    public static Toast custom(ToastyType type, @NonNull CharSequence message, Drawable icon,
                               int duration, boolean withIcon) {
        return custom(type, message, icon, duration, withIcon, false);
    }

    @CheckResult
    public static Toast custom(ToastyType type, @StringRes int message, int duration,
                               boolean withIcon, boolean shouldTint) {
        return custom(type, message, null, duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(ToastyType type, @NonNull CharSequence message, int duration,
                               boolean withIcon, boolean shouldTint) {
        return custom(type, message, null, duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(ToastyType type, @StringRes int message, Drawable icon, int duration,
                               boolean withIcon, boolean shouldTint) {
        if (context == null)
            throw new NullPointerException("context can not be null,please init Toasty in Application.");
        return custom(type, context.getString(message), icon, duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(ToastyType type, @NonNull CharSequence message, Drawable icon, int duration,
                               boolean withIcon, boolean shouldTint) {
        if (context == null)
            throw new NullPointerException("context can not be null,please init Toasty in your Application.");
        @ColorInt int tintColor;
        @ColorInt int textColor = ToastyUtils.getColor(context, R.color.defaultTextColor);
        Drawable drawable;

        if (type == ToastyType.NORMAL) {
            tintColor = ToastyUtils.getColor(context, R.color.normalColor);
            drawable = null;
        } else if (type == ToastyType.WARNING) {
            tintColor = ToastyUtils.getColor(context, R.color.warningColor);
            drawable = ToastyUtils.getDrawable(context, R.drawable.ic_error_outline_white_24dp);
        } else if (type == ToastyType.ERROR) {
            tintColor = ToastyUtils.getColor(context, R.color.errorColor);
            drawable = ToastyUtils.getDrawable(context, R.drawable.ic_clear_white_24dp);
        } else if (type == ToastyType.SUCCESS) {
            tintColor = ToastyUtils.getColor(context, R.color.successColor);
            drawable = ToastyUtils.getDrawable(context, R.drawable.ic_check_white_24dp);
        } else {
            tintColor = ToastyUtils.getColor(context, R.color.infoColor);
            drawable = ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_24dp);
        }
        return custom(message, icon == null ? drawable : icon, tintColor, textColor, duration, withIcon, shouldTint);
    }


    @SuppressLint("ShowToast")
    @CheckResult
    public static Toast custom(@NonNull CharSequence message, Drawable icon,
                               @ColorInt int tintColor, @ColorInt int textColor, int duration,
                               boolean withIcon, boolean shouldTint) {
        if (context == null)
            throw new NullPointerException("context can not be null,please init Toasty in your Application.");
        boolean isLooper = Looper.getMainLooper() == Looper.myLooper();


        if(!isLooper){
            Looper.prepare();
        }

        final Toast currentToast = Toast.makeText(context, "", duration);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint)
            drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
        else
            drawableFrame = ToastyUtils.getDrawable(context, R.drawable.toast_frame);
        ToastyUtils.setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null)
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            ToastyUtils.setBackground(toastIcon, tintIcon ? ToastyUtils.tintIcon(icon, textColor) : icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setText(message);
        toastTextView.setTextColor(textColor);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        currentToast.setView(toastLayout);
        currentToast.setGravity(Gravity.CENTER, 0, 0);

        if (!allowQueue) {
            if (lastToast != null)
                lastToast.cancel();
            lastToast = currentToast;
        }

        currentToast.show();
        if(!isLooper){

            Looper.loop();
        }

        return currentToast;
    }

    public static class Config {
        private Typeface typeface = Toasty.currentTypeface;
        private int textSize = Toasty.textSize;

        private boolean tintIcon = Toasty.tintIcon;
        private boolean allowQueue = true;

        private Config() {
            // avoiding instantiation
        }

        @CheckResult
        public static Config getInstance() {
            return new Config();
        }

        public static void reset() {
            Toasty.currentTypeface = LOADED_TOAST_TYPEFACE;
            Toasty.textSize = 16;
            Toasty.tintIcon = true;
            Toasty.allowQueue = true;
        }

        @CheckResult
        public Config setToastTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        @CheckResult
        public Config setTextSize(int sizeInSp) {
            this.textSize = sizeInSp;
            return this;
        }

        @CheckResult
        public Config tintIcon(boolean tintIcon) {
            this.tintIcon = tintIcon;
            return this;
        }

        @CheckResult
        public Config allowQueue(boolean allowQueue) {
            this.allowQueue = allowQueue;
            return this;
        }

        public void apply() {
            Toasty.currentTypeface = typeface;
            Toasty.textSize = textSize;
            Toasty.tintIcon = tintIcon;
            Toasty.allowQueue = allowQueue;
        }
    }
}
