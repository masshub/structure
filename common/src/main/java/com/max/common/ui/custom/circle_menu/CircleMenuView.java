package com.max.common.ui.custom.circle_menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;

import com.max.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maker on 2020/8/29.
 * Description:
 */
public class CircleMenuView extends FrameLayout {

    private static final int DEFAULT_BUTTON_SIZE = 66;
    private static final float DEFAULT_DISTANCE = DEFAULT_BUTTON_SIZE * 1.5f;
    private static final float DEFAULT_RING_SCALE_RATIO = 1.3f;
    private static final float DEFAULT_CLOSE_ICON_ALPHA = 1.5f;

    private final List<View> mButtons = new ArrayList<>();
    private final Rect mButtonRect = new Rect();

    private Button mMenuButton;
    private RingEffectView mRingView;

    private boolean mClosedState = true;
    private boolean mIsAnimating = false;

    private int mIconMenu;
    private int mIconClose;
    private int mDurationRing;
    private int mLongClickDurationRing;
    private int mDurationOpen;
    private int mDurationClose;
    private int mDesiredSize;
    private int mRingRadius;
    private int mButtonSize;
    private int mButtonTitlesColor;
    private Boolean mIcons;

    private float mDistance;

    private EventListener mListener;

    private String mTitle = "环境";
    private List<Integer> mButtonTitles = new ArrayList<>();
    private List<Integer> mButtonColors = new ArrayList<>();

    private float mLastX;
    private float mLastY;
    private AutoFlingRunnable mFlingRunnable;
    private boolean isFling;
    private double mStartAngle = 0;
    private static final int FLINGABLE_VALUE = 300;
    private long mDownTime;
    private float mTmpAngle;
    private int mFlingableValue = FLINGABLE_VALUE;
    private int childCount;


    /**
     * 如果移动角度达到该值，则屏蔽点击
     */
    private static final int NOCLICK_VALUE = 3;

    /**
     * CircleMenu event listener.
     */
    public static class EventListener {
        /**
         * Invoked on menu button click, before animation start.
         *
         * @param view current CircleMenuView instance.
         */
        public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
        }

        /**
         * Invoked on menu button click, after animation end.
         *
         * @param view - current CircleMenuView instance.
         */
        public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
        }

        /**
         * Invoked on close menu button click, before animation start.
         *
         * @param view - current CircleMenuView instance.
         */
        public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
        }

        /**
         * Invoked on close menu button click, after animation end.
         *
         * @param view - current CircleMenuView instance.
         */
        public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
        }

        /**
         * Invoked on button click, before animation start.
         *
         * @param view        - current CircleMenuView instance.
         * @param buttonIndex - clicked button zero-based index.
         */
        public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
        }

        /**
         * Invoked on button click, after animation end.
         *
         * @param view        - current CircleMenuView instance.
         * @param buttonIndex - clicked button zero-based index.
         */
        public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int buttonIndex) {
        }

        /**
         * Invoked on button click, after animation end.
         *
         * @param view  - current CircleMenuView instance.
         * @param title - clicked button zero-based index.
         */
        public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, String title) {
        }

        /**
         * Invoked on button long click. Invokes {@see onButtonLongClickAnimationStart} and {@see onButtonLongClickAnimationEnd}
         * if returns true.
         *
         * @param view        current CircleMenuView instance.
         * @param buttonIndex clicked button zero-based index.
         * @return true if the callback consumed the long click, false otherwise.
         */
        public boolean onButtonLongClick(@NonNull CircleMenuView view, int buttonIndex) {
            return false;
        }

        /**
         * Invoked on button long click, before animation start.
         *
         * @param view        - current CircleMenuView instance.
         * @param buttonIndex - clicked button zero-based index.
         */
        public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
        }

        /**
         * Invoked on button long click, after animation end.
         *
         * @param view        - current CircleMenuView instance.
         * @param buttonIndex - clicked button zero-based index.
         */
        public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int buttonIndex) {
        }
    }

    private class OnButtonClickListener implements View.OnClickListener {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(final View view) {
            if (mIsAnimating) {
                return;
            }

            final Animator click = getButtonClickAnimation((Button) view);
            click.setDuration(mDurationRing);
            click.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (mListener != null) {
                        mListener.onButtonClickAnimationStart(CircleMenuView.this, mButtons.indexOf(view));
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mClosedState = true;
                    if (mListener != null) {
                        mListener.onButtonClickAnimationEnd(CircleMenuView.this, mButtons.indexOf(view));
                        mTitle = getResources().getString(mButtonTitles.get(mButtons.indexOf(view)));
//                        mMenuButton.setBackground(getResources().getDrawable(R.drawable.circle_button_shape));
                        mMenuButton.setBackgroundTintList(ColorStateList.valueOf(mButtonColors.get(mButtons.indexOf(view))));
                        mMenuButton.setText(mTitle);
//                        mTitle = getResources().getString()
//                        if (mIcons) {
//                        } else {
//                            mListener.onButtonClickAnimationEnd(CircleMenuView.this, mButtons.indexOf(view));
//                        }
                    }
                }
            });
            click.start();
        }
    }

    private class OnButtonLongClickListener implements View.OnLongClickListener {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onLongClick(final View view) {
            if (mListener == null) {
                return false;
            }

            final boolean result = mListener.onButtonLongClick(CircleMenuView.this, mButtons.indexOf(view));
            if (result && !mIsAnimating) {
                final Animator click = getButtonClickAnimation((Button) view);
                click.setDuration(mLongClickDurationRing);
                click.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mListener.onButtonLongClickAnimationStart(CircleMenuView.this, mButtons.indexOf(view));
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mClosedState = true;
                        mListener.onButtonLongClickAnimationEnd(CircleMenuView.this, mButtons.indexOf(view));
                    }
                });
                click.start();
            }

            return result;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleMenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleMenuView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs == null) {
            throw new IllegalArgumentException("No buttons icons or colors set");
        }

        final List<Integer> icons;
        final List<Integer> colors;
        final List<Integer> titles;

        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleMenuView, 0, 0);
        final int menuButtonColor;

        mDurationRing = a.getInteger(R.styleable.CircleMenuView_duration_ring, getResources().getInteger(android.R.integer.config_mediumAnimTime));
        mLongClickDurationRing = a.getInteger(R.styleable.CircleMenuView_long_click_duration_ring, getResources().getInteger(android.R.integer.config_longAnimTime));
        mDurationOpen = a.getInteger(R.styleable.CircleMenuView_duration_open, getResources().getInteger(android.R.integer.config_mediumAnimTime));
        mDurationClose = a.getInteger(R.styleable.CircleMenuView_duration_close, getResources().getInteger(android.R.integer.config_mediumAnimTime));

        final float density = context.getResources().getDisplayMetrics().density;
        final float defaultDistance = DEFAULT_DISTANCE * density;
        mDistance = a.getDimension(R.styleable.CircleMenuView_distance, defaultDistance);


//        mIconMenu = a.getResourceId(R.styleable.CircleMenuView_icon_menu, R.drawable.ic_menu_black_24dp);
        mIconClose = a.getResourceId(R.styleable.CircleMenuView_icon_close, R.drawable.ic_baseline_close_24);
        menuButtonColor = a.getColor(R.styleable.CircleMenuView_icon_color, getResources().getColor(R.color.color_4c8dae));
        mButtonTitlesColor = a.getColor(R.styleable.CircleMenuView_button_titles_color, Color.WHITE);

        mIcons = a.getBoolean(R.styleable.CircleMenuView_button_with_icon, true);
        mButtonSize = a.getInt(R.styleable.CircleMenuView_button_size, 200);

        try {
            final int titleArrayId = a.getResourceId(R.styleable.CircleMenuView_button_titles, 0);
            final int colorArrayId = a.getResourceId(R.styleable.CircleMenuView_button_colors, 0);
            final int iconArrayId = a.getResourceId(R.styleable.CircleMenuView_button_icons, 0);

            final TypedArray iconsIds = getResources().obtainTypedArray(iconArrayId);
            final TypedArray titleIds = getResources().obtainTypedArray(titleArrayId);
            final int[] colorsIds = getResources().getIntArray(colorArrayId);
            try {
                final int buttonsCount = Math.min(mIcons ? iconsIds.length() : titleIds.length(), colorsIds.length);
                childCount = buttonsCount;
                icons = new ArrayList<>(buttonsCount);
                colors = new ArrayList<>(buttonsCount);
                titles = new ArrayList<>(buttonsCount);
                for (int i = 0; i < buttonsCount; i++) {
                    icons.add(iconsIds.getResourceId(i, -1));
                    titles.add(titleIds.getResourceId(i, -1));
                    colors.add(colorsIds[i]);
                }
                mButtonTitles = titles;
                mButtonColors = colors;
            } finally {
                iconsIds.recycle();
            }


        } finally {
            a.recycle();
        }

        initLayout(context);
        initMenu(menuButtonColor);
        initButtons(context, icons, titles, colors);

    }


    /**
     * Constructor for creation CircleMenuView in code, not in xml-layout.
     *
     * @param context current context, will be used to access resources.
     * @param icons   buttons icons resource ids array. Items must be @DrawableRes.
     * @param colors  buttons colors resource ids array. Items must be @DrawableRes.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleMenuView(@NonNull Context context, @NonNull List<Integer> icons, @NonNull List<Integer> colors) {
        super(context);

        final float density = context.getResources().getDisplayMetrics().density;
        final float defaultDistance = DEFAULT_DISTANCE * density;

        mIconMenu = R.drawable.ic_menu_black_24dp;
        mIconClose = R.drawable.ic_close_black_24dp;

        mDurationRing = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        mLongClickDurationRing = getResources().getInteger(android.R.integer.config_longAnimTime);
        mDurationOpen = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        mDurationClose = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        mDistance = defaultDistance;

        initLayout(context);
        initMenu(Color.WHITE);
//        initButtons(context, icons, colors);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int w = resolveSizeAndState(mDesiredSize, widthMeasureSpec, 0);
        final int h = resolveSizeAndState(mDesiredSize, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (!changed && mIsAnimating) {
            return;
        }

//        mMenuButton.getContentRect(mButtonRect);

        mRingView.setStrokeWidth(mButtonSize);
        mRingView.setRadius(mRingRadius);

        final LayoutParams lp = (LayoutParams) mRingView.getLayoutParams();
        lp.width = right - left;
        lp.height = bottom - top;


        // 根据menu item的个数，计算角度
        // 根据menu item的个数，计算角度
        float angleDelay = 360 / (getChildCount() - 1);

        // 遍历去设置menuitem的位置
        for (int i = 0; i < childCount; i++)
        {
            final View child = mButtons.get(i);

            mStartAngle %= 360;

            // 计算，中心点到menu item中心的距离
            float tmp = mRingRadius / 1f - 200 / 2 - 0;
//
//            // tmp cosa 即menu item中心点的横坐标
            left = mRingRadius
                    / 1
                    + (int) Math.round(tmp
                    * Math.cos(Math.toRadians(mStartAngle)) - 1 / 2f
                    * 200);
//            // tmp sina 即menu item的纵坐标
            top = mRingRadius
                    / 1
                    + (int) Math.round(tmp
                    * Math.sin(Math.toRadians(mStartAngle)) - 1 / 2f
                    * 200);
//
            child.layout(left, top, left + 200, top + 200);
            // 叠加尺寸
            mStartAngle += angleDelay;
        }
    }

    private void initLayout(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.circle_menu, this, true);

        setWillNotDraw(true);
        setClipChildren(false);
        setClipToPadding(false);

        final float density = context.getResources().getDisplayMetrics().density;
        final float buttonSize = DEFAULT_BUTTON_SIZE * density;

        mRingRadius = (int) (buttonSize + (mDistance - buttonSize / 2));
        mDesiredSize = (int) (mRingRadius * 2 * DEFAULT_RING_SCALE_RATIO);

        mRingView = findViewById(R.id.ring_view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initMenu(int menuButtonColor) {
        final AnimatorListenerAdapter animListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mListener != null) {
                    if (mClosedState) {
                        mListener.onMenuOpenAnimationStart(CircleMenuView.this);
                    } else {
                        mListener.onMenuCloseAnimationStart(CircleMenuView.this);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mListener != null) {
                    if (mClosedState) {
                        mListener.onMenuOpenAnimationEnd(CircleMenuView.this);
                    } else {
                        mListener.onMenuCloseAnimationEnd(CircleMenuView.this);
                    }
                }

                mClosedState = !mClosedState;
            }
        };

        mMenuButton = findViewById(R.id.circle_menu_main_button);
//        mMenuButton.setImageResource(mIconMenu);
//        mMenuButton.setBackgroundResource(mIconMenu);
        mMenuButton.setText("环境");
        mMenuButton.setTextColor(Color.WHITE);
        FrameLayout.LayoutParams layoutParams = new LayoutParams(mButtonSize, mButtonSize);
        layoutParams.gravity = Gravity.CENTER;
        mMenuButton.setLayoutParams(layoutParams);
        mMenuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsAnimating) {
                    return;
                }

                final Animator animation = mClosedState ? getOpenMenuAnimation() : getCloseMenuAnimation();
                animation.setDuration(mClosedState ? mDurationClose : mDurationOpen);
                animation.addListener(animListener);
                animation.start();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initButtons(@NonNull Context context, @NonNull List<Integer> icons, @NonNull List<Integer> titles, @NonNull List<Integer> colors) {
        final int buttonsCount = Math.min(titles.size(), colors.size());
        for (int i = 0; i < buttonsCount; i++) {
            final Button button = new Button(context);
            if (mIcons) {
                button.setBackgroundResource(icons.get(i));
            } else {
                button.setText(context.getResources().getString(titles.get(i)));
                button.setTextColor(mButtonTitlesColor);
            }

            button.setBackground(context.getResources().getDrawable(R.drawable.circle_button_shape));
            button.setBackgroundTintList(ColorStateList.valueOf(colors.get(i)));
            button.setClickable(true);
            button.setOnClickListener(new OnButtonClickListener());
            button.setOnLongClickListener(new OnButtonLongClickListener());
            button.setScaleX(0);
            button.setScaleY(0);
            button.setLayoutParams(new LayoutParams(mButtonSize, mButtonSize));
            addView(button);
            mButtons.add(button);
        }
    }


    private void offsetAndScaleButtons(float centerX, float centerY, float angleStep, float offset, float scale) {
        for (int i = 0, cnt = mButtons.size(); i < cnt; i++) {
            final float angle = angleStep * i - 90;
            final float x = (float) Math.cos(Math.toRadians(angle)) * offset;
            final float y = (float) Math.sin(Math.toRadians(angle)) * offset;

            final View button = mButtons.get(i);
            button.setX(centerX + x);
            button.setY(centerY + y);
            button.setScaleX(1.0f * scale);
            button.setScaleY(1.0f * scale);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator getButtonClickAnimation(final @NonNull Button button) {
        final int buttonNumber = mButtons.indexOf(button) + 1;
        final float stepAngle = 360f / mButtons.size();
        final float rOStartAngle = (270 - stepAngle + stepAngle * buttonNumber);
        final float rStartAngle = rOStartAngle > 360 ? rOStartAngle % 360 : rOStartAngle;

        final float x = (float) Math.cos(Math.toRadians(rStartAngle)) * mDistance;
        final float y = (float) Math.sin(Math.toRadians(rStartAngle)) * mDistance;

        final float pivotX = button.getPivotX();
        final float pivotY = button.getPivotY();
        button.setPivotX(pivotX - x);
        button.setPivotY(pivotY - y);

        final ObjectAnimator rotateButton = ObjectAnimator.ofFloat(button, "rotation", 0f, 360f);
        rotateButton.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                button.setPivotX(pivotX);
                button.setPivotY(pivotY);
            }
        });

        final float elevation = mMenuButton.getElevation();

        mRingView.setVisibility(View.INVISIBLE);
        mRingView.setStartAngle(rStartAngle);

        final ColorStateList csl = button.getBackgroundTintList();
        if (csl != null) {
            mRingView.setStrokeColor(csl.getDefaultColor());
        }

        final ObjectAnimator ring = ObjectAnimator.ofFloat(mRingView, "angle", 360);
        final ObjectAnimator scaleX = ObjectAnimator.ofFloat(mRingView, "scaleX", 1f, DEFAULT_RING_SCALE_RATIO);
        final ObjectAnimator scaleY = ObjectAnimator.ofFloat(mRingView, "scaleY", 1f, DEFAULT_RING_SCALE_RATIO);
        final ObjectAnimator visible = ObjectAnimator.ofFloat(mRingView, "alpha", 1f, 0f);

        final AnimatorSet lastSet = new AnimatorSet();
        lastSet.playTogether(scaleX, scaleY, visible, getCloseMenuAnimation());

        final AnimatorSet firstSet = new AnimatorSet();
        firstSet.playTogether(rotateButton, ring);

        final AnimatorSet result = new AnimatorSet();
        result.play(firstSet).before(lastSet);
        result.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    bringChildToFront(mRingView);
                    bringChildToFront(button);
                } else {
                    button.setElevation(elevation + 1);
                    ViewCompat.setZ(mRingView, elevation + 1);

                    for (View b : mButtons) {
                        if (b != button) {
                            ((Button) b).setElevation(0);
                        }
                    }
                }

                mRingView.setScaleX(1f);
                mRingView.setScaleY(1f);
                mRingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    for (View b : mButtons) {
                        ((Button) b).setElevation(elevation);
                    }

                    ViewCompat.setZ(mRingView, elevation);
                }
            }
        });

        return result;
    }

    private Animator getOpenMenuAnimation() {
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mMenuButton, "alpha", DEFAULT_CLOSE_ICON_ALPHA);

        final Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        final Keyframe kf1 = Keyframe.ofFloat(0.5f, 60f);
        final Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        final PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        final ObjectAnimator rotateAnimation = ObjectAnimator.ofPropertyValuesHolder(mMenuButton, pvhRotation);
        rotateAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private boolean iconChanged = false;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final float fraction = valueAnimator.getAnimatedFraction();
                if (fraction >= 0.5f && !iconChanged) {
                    iconChanged = true;
                    mMenuButton.setBackgroundResource(mIconClose);
                    mMenuButton.setText("");
                }
            }
        });

        final float centerX = mMenuButton.getX();
        final float centerY = mMenuButton.getY();

        final int buttonsCount = mButtons.size();
        final float angleStep = 360f / buttonsCount;

        final ValueAnimator buttonsAppear = ValueAnimator.ofFloat(0f, mDistance);
        buttonsAppear.setInterpolator(new OvershootInterpolator());
        buttonsAppear.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                for (View view : mButtons) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        });
        buttonsAppear.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final float fraction = valueAnimator.getAnimatedFraction();
                final float value = (float) valueAnimator.getAnimatedValue();
                offsetAndScaleButtons(centerX, centerY, angleStep, value, fraction);
            }
        });

        final AnimatorSet result = new AnimatorSet();
        result.playTogether(alphaAnimation, rotateAnimation, buttonsAppear);
        result.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;
            }
        });

        return result;
    }

    private Animator getCloseMenuAnimation() {
        final ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(mMenuButton, "scaleX", 0f);
        final ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(mMenuButton, "scaleY", 0f);
        final ObjectAnimator alpha1 = ObjectAnimator.ofFloat(mMenuButton, "alpha", 0f);
        final AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(scaleX1, scaleY1, alpha1);
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                for (View view : mButtons) {
                    view.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mMenuButton.setRotation(60f);
                mMenuButton.setText(mTitle);
//                mMenuButton.setBackgroundResource();
                mMenuButton.setBackground(getResources().getDrawable(R.drawable.circle_button_shape));
            }
        });

        final ObjectAnimator angle = ObjectAnimator.ofFloat(mMenuButton, "rotation", 0);
        final ObjectAnimator alpha2 = ObjectAnimator.ofFloat(mMenuButton, "alpha", 1f);
        final ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(mMenuButton, "scaleX", 1f);
        final ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(mMenuButton, "scaleY", 1f);
        final AnimatorSet set2 = new AnimatorSet();
        set2.setInterpolator(new OvershootInterpolator());
        set2.playTogether(angle, alpha2, scaleX2, scaleY2);

        final AnimatorSet result = new AnimatorSet();
        result.play(set1).before(set2);
        result.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;
            }
        });
        return result;
    }

    public void setIconMenu(@DrawableRes int iconId) {
        mIconMenu = iconId;
    }

    @DrawableRes
    public int getIconMenu() {
        return mIconMenu;
    }

    public void setIconClose(@DrawableRes int iconId) {
        mIconClose = iconId;
    }

    @DrawableRes
    public int getIconClose() {
        return mIconClose;
    }

    /**
     * See {@link R.styleable#CircleMenuView_duration_close}
     *
     * @param duration close animation duration in milliseconds.
     */
    public void setDurationClose(int duration) {
        mDurationClose = duration;
    }

    /**
     * See {@link R.styleable#CircleMenuView_duration_close}
     *
     * @return current close animation duration.
     */
    public int getDurationClose() {
        return mDurationClose;
    }

    /**
     * See {@link R.styleable#CircleMenuView_duration_open}
     *
     * @param duration open animation duration in milliseconds.
     */
    public void setDurationOpen(int duration) {
        mDurationOpen = duration;
    }

    /**
     * See {@link R.styleable#CircleMenuView_duration_open}
     *
     * @return current open animation duration.
     */
    public int getDurationOpen() {
        return mDurationOpen;
    }

    /**
     * See {@link R.styleable#CircleMenuView_duration_ring}
     *
     * @param duration ring animation duration in milliseconds.
     */
    public void setDurationRing(int duration) {
        mDurationRing = duration;
    }

    /**
     * See {@link R.styleable#CircleMenuView_duration_ring}
     *
     * @return current ring animation duration.
     */
    public int getDurationRing() {
        return mDurationRing;
    }

    /**
     * See {@link R.styleable#CircleMenuView_long_click_duration_ring}
     *
     * @return current long click ring animation duration.
     */
    public int getLongClickDurationRing() {
        return mLongClickDurationRing;
    }

    /**
     * See {@link R.styleable#CircleMenuView_long_click_duration_ring}
     *
     * @param duration long click ring animation duration in milliseconds.
     */
    public void setLongClickDurationRing(int duration) {
        mLongClickDurationRing = duration;
    }

    /**
     * See {@link R.styleable#CircleMenuView_distance}
     *
     * @param distance in pixels.
     */
    public void setDistance(float distance) {
        mDistance = distance;
        invalidate();
    }

    /**
     * See {@link R.styleable#CircleMenuView_distance}
     *
     * @return current distance in pixels.
     */
    public float getDistance() {
        return mDistance;
    }

    /**
     * See {@link CircleMenuView.EventListener }
     *
     * @param listener new event listener or null.
     */
    public void setEventListener(@Nullable EventListener listener) {
        mListener = listener;
    }

    /**
     * See {@link CircleMenuView.EventListener }
     *
     * @return current event listener or null.
     */
    public EventListener getEventListener() {
        return mListener;
    }

    private void openOrClose(boolean open, boolean animate) {
        if (mIsAnimating) {
            return;
        }

        if (open && !mClosedState) {
            return;
        }

        if (!open && mClosedState) {
            return;
        }

        if (animate) {
            mMenuButton.performClick();
        } else {
            mClosedState = !open;

            final float centerX = mMenuButton.getX();
            final float centerY = mMenuButton.getY();

            final int buttonsCount = mButtons.size();
            final float angleStep = 360f / buttonsCount;

            final float offset = open ? mDistance : 0f;
            final float scale = open ? 1f : 0f;

//            mMenuButton.setImageResource(open ? mIconClose : mIconMenu);
//            mMenuButton.setBackgroundResource(open ? mIconClose : mIconMenu);
            if (open) {
                mMenuButton.setBackgroundResource(mIconClose);
            } else {
                mMenuButton.setText(mIconClose);
            }

//            mMenuButton.setAlpha(open ? DEFAULT_CLOSE_ICON_ALPHA : 1f);

            final int visibility = open ? View.VISIBLE : View.INVISIBLE;
            for (View view : mButtons) {
                view.setVisibility(visibility);
            }

            offsetAndScaleButtons(centerX, centerY, angleStep, offset, scale);
        }
    }

    /**
     * Open menu programmatically
     *
     * @param animate open with animation or not
     */
    public void open(boolean animate) {
        openOrClose(true, animate);
    }

    /**
     * Close menu programmatically
     *
     * @param animate close with animation or not
     */
    public void close(boolean animate) {
        openOrClose(false, animate);
    }

    private class AutoFlingRunnable implements Runnable
    {

        private float angelPerSecond;

        public AutoFlingRunnable(float velocity)
        {
            this.angelPerSecond = velocity;
        }

        public void run()
        {
            // 如果小于20,则停止
            if ((int) Math.abs(angelPerSecond) < 20)
            {
                isFling = false;
                return;
            }
            isFling = true;
            // 不断改变mStartAngle，让其滚动，/30为了避免滚动太快
            mStartAngle += (angelPerSecond / 30);
            // 逐渐减小这个值
            angelPerSecond /= 1.0666F;
            postDelayed(this, 30);
            // 重新布局
            requestLayout();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        // Log.e("TAG", "x = " + x + " , y = " + y);

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                mLastX = x;
                mLastY = y;
                mDownTime = System.currentTimeMillis();
                mTmpAngle = 0;

                // 如果当前已经在快速滚动
                if (isFling)
                {
                    // 移除快速滚动的回调
                    removeCallbacks(mFlingRunnable);
                    isFling = false;
                    return true;
                }

                break;
            case MotionEvent.ACTION_MOVE:

                /**
                 * 获得开始的角度
                 */
                float start = getAngle(mLastX, mLastY);
                /**
                 * 获得当前的角度
                 */
                float end = getAngle(x, y);

                // Log.e("TAG", "start = " + start + " , end =" + end);
                // 如果是一、四象限，则直接end-start，角度值都是正值
                if (getQuadrant(x, y) == 1 || getQuadrant(x, y) == 4)
                {
                    mStartAngle += end - start;
                    mTmpAngle += end - start;
                } else
                // 二、三象限，色角度值是付值
                {
                    mStartAngle += start - end;
                    mTmpAngle += start - end;
                }
                // 重新布局
                requestLayout();

                mLastX = x;
                mLastY = y;

                break;
            case MotionEvent.ACTION_UP:

                // 计算，每秒移动的角度
                float anglePerSecond = mTmpAngle * 1000
                        / (System.currentTimeMillis() - mDownTime);

                // Log.e("TAG", anglePrMillionSecond + " , mTmpAngel = " +
                // mTmpAngle);

                // 如果达到该值认为是快速移动
                if (Math.abs(anglePerSecond) > mFlingableValue && !isFling)
                {
                    // post一个任务，去自动滚动
                    post(mFlingRunnable = new AutoFlingRunnable(anglePerSecond));

                    return true;
                }

                // 如果当前旋转角度超过NOCLICK_VALUE屏蔽点击
                if (Math.abs(mTmpAngle) > NOCLICK_VALUE)
                {
                    return true;
                }

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 主要为了action_down时，返回true
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return true;
    }

    /**
     * 根据触摸的位置，计算角度
     *
     * @param xTouch
     * @param yTouch
     * @return
     */
    private float getAngle(float xTouch, float yTouch)
    {
        double x = xTouch - (mRingRadius / 2d);
        double y = yTouch - (mRingRadius / 2d);
        return (float) (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
    }

    /**
     * 根据当前位置计算象限
     *
     * @param x
     * @param y
     * @return
     */
    private int getQuadrant(float x, float y)
    {
        int tmpX = (int) (x - mRingRadius / 2);
        int tmpY = (int) (y - mRingRadius / 2);
        if (tmpX >= 0)
        {
            return tmpY >= 0 ? 4 : 1;
        } else
        {
            return tmpY >= 0 ? 3 : 2;
        }

    }

}
