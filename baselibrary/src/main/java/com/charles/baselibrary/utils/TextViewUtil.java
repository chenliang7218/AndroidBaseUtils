package com.charles.baselibrary.utils;

import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class TextViewUtil {
	/** 
	 * 下划线 
	 *  
	 * @param textView 
	 */  
	public static void addButtomLine(TextView textView) {
	    textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	}  
	  
	/** 
	 * 移除线 
	 *  
	 * @param textView 
	 */  
	public static void removeLine(TextView textView) {
	    textView.getPaint().setFlags(0); // 取消设置的的划线  
	  
	}  
	  
	/** 
	 * 设置中划线并加清晰 
	 *  
	 * @param textView 
	 */  
	public static void addLine(TextView textView) {
	    textView.getPaint().setFlags(  
	            Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
	  
	}  
	  
	/** 
	 * 中划线 
	 *  
	 * @param textView 
	 */  
	public static void addCenterLine(TextView textView) {
	    textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
	}  
	  
	/** 
	 * 抗锯齿 
	 *  
	 * @param textView 
	 */  
	public static void addjuchiLine(TextView textView) {
	    textView.getPaint().setAntiAlias(true);// 抗锯齿  
	}

	/**
	 * 设置价格输入
	 * 小数点后两位
	 * @param editText
	 */
	public static void setPricePoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
				if (s.toString().contains(".")) {
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
						s = s.toString().subSequence(0,
								s.toString().indexOf(".") + 3);
						editText.setText(s);
						editText.setSelection(s.length());
					}
				}
				if (".".equals(s.toString().trim().substring(0))) {
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);
				}

				if (s.toString().startsWith("0")
						&& s.toString().trim().length() > 1) {
					if (!".".equals(s.toString().substring(1, 2))) {
						editText.setText(s.subSequence(0, 1));
						editText.setSelection(1);
						return;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		});

	}

	/**
	 * 限制回车换行
	 *
	 * @param et
	 */
	public static void limitsEditEnter(EditText et) {
		et.setOnEditorActionListener(new EditText.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
			}
		});
	}

}
