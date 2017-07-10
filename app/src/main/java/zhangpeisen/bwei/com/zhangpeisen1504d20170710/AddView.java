package zhangpeisen.bwei.com.zhangpeisen1504d20170710;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * date:2017/7/10
 * auto:张培森
 */

public class AddView extends LinearLayout implements View.OnClickListener{

    private Context mContext;
    private Button btn_sub;
    private TextView tv_num;
    private Button btn_add;
    //设置默认值
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;


    public AddView(Context context) {
        this(context,null);
    }

    public AddView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context);

        //得到属性
        if (attrs != null) {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.NumberAddSubView, defStyleAttr, 0);
            int value = a.getInt(R.styleable.NumberAddSubView_value, 0);setValue(value);
            int maxValue = a.getInt(R.styleable.NumberAddSubView_maxValue, 0);setMaxValue(maxValue);
            int minValue = a.getInt(R.styleable.NumberAddSubView_minValue, 0);setMinValue(minValue);
            Drawable btnSubBackground = a.getDrawable(R.styleable.NumberAddSubView_btnSubBackground);
            if (btnSubBackground != null) btn_sub.setBackground(btnSubBackground);
            Drawable btnAddBackground = a.getDrawable(R.styleable.NumberAddSubView_btnAddBackground);
            if (btnAddBackground != null) btn_sub.setBackground(btnAddBackground);
            Drawable textViewBackground = a.getDrawable(R.styleable.NumberAddSubView_textViewBackground);
            if (textViewBackground != null){
                tv_num.setBackground(textViewBackground);
            }
            a.recycle();
        }
    }
    private void initView(Context context) {
        View.inflate(context,R.layout.view_amount,this);

        btn_sub = (Button) findViewById(R.id.btn_sub);
        tv_num = (TextView) findViewById(R.id.tv_num);
        btn_add = (Button) findViewById(R.id.btn_add);

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
    }

    public int getValue() {
        return value;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setValue(int value) {
        this.value = value;
        tv_num.setText(value + "");
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sub) {
             Toast.makeText(mContext,"减",Toast.LENGTH_SHORT).show();subNum();
            if (onButtonClickListenter != null) {
                onButtonClickListenter.onButtonSubClick(v, value);
            }


        } else if (v.getId() == R.id.btn_add) {
             Toast.makeText(mContext,"加",Toast.LENGTH_SHORT).show();
            addNum();
            if (onButtonClickListenter != null) {
                onButtonClickListenter.onButtonAddClick(v, value);
            }
        }


    }
    //减少数据
    private void subNum() {
        if (value > minValue) {
            value = value - 1;
            tv_num.setText(value + "");
        }
    }
    /**
     * 添加数据
     */
    private void addNum() {
        if (value < maxValue) {
            value = value + 1;
            tv_num.setText(value + "");
        }
    }


    public interface OnButtonClickListenter {
        /**
         * 当增加按钮被点击的时候回调该方法
         */
        public void onButtonAddClick(View view, int value);
        /**
         * 当减少按钮被点击的时候回调这个方法
         */
        public void onButtonSubClick(View view, int value);
    }
    private OnButtonClickListenter onButtonClickListenter;
    public void setOnButtonClickListenter(OnButtonClickListenter
                                                  onButtonClickListenter) {
        this.onButtonClickListenter = onButtonClickListenter;
    }
}
