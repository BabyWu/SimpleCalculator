package com.wuxianxi.calculatortest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	EditText et;
	Button btn_0;
	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;
	Button btn_5;
	Button btn_6;
	Button btn_7;
	Button btn_8;
	Button btn_9;
	Button btn_point;
	Button btn_plus;
	Button btn_minus;
	Button btn_multiply;
	Button btn_divide;
	Button btn_equal;
	Button btn_clear;
	Button btn_delete;
	
	Boolean clear_flay = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		et = (EditText) findViewById(R.id.id_et);
		btn_0 = (Button) findViewById(R.id.id_0);
		btn_1 = (Button) findViewById(R.id.id_1);
		btn_2 = (Button) findViewById(R.id.id_2);
		btn_3 = (Button) findViewById(R.id.id_3);
		btn_4 = (Button) findViewById(R.id.id_4);
		btn_5 = (Button) findViewById(R.id.id_5);
		btn_6 = (Button) findViewById(R.id.id_6);
		btn_7 = (Button) findViewById(R.id.id_7);
		btn_8 = (Button) findViewById(R.id.id_8);
		btn_9 = (Button) findViewById(R.id.id_9);
		btn_point = (Button) findViewById(R.id.id_point);
		btn_plus = (Button) findViewById(R.id.id_plus);
		btn_minus = (Button) findViewById(R.id.id_minus);
		btn_multiply = (Button) findViewById(R.id.id_multiply);
		btn_divide = (Button) findViewById(R.id.id_divide);
		btn_equal = (Button) findViewById(R.id.id_equal);
		btn_clear = (Button) findViewById(R.id.id_clear);
		btn_delete = (Button) findViewById(R.id.id_delete);

		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String str = et.getText().toString();
		switch (v.getId()) {
		case R.id.id_0:
		case R.id.id_1:
		case R.id.id_2:
		case R.id.id_3:
		case R.id.id_4:
		case R.id.id_5:
		case R.id.id_6:
		case R.id.id_7:
		case R.id.id_8:
		case R.id.id_9:
		case R.id.id_point:
			if (clear_flay) {
				str = "";
				clear_flay = false;
			}
			et.setText(str + ((Button) v).getText());
			break;
		case R.id.id_plus:
		case R.id.id_minus:
		case R.id.id_multiply:
		case R.id.id_divide:
			if (clear_flay) {
				str = "";
				clear_flay = false;
			}
			et.setText(str + " " + ((Button) v).getText() + " ");
			break;
		case R.id.id_clear:
			et.setText(null);
			clear_flay = false;
			break;
		case R.id.id_delete:
			if (clear_flay) {
				et.setText(null);
				clear_flay = false;
			}else if (str != null && !str.equals("")) {
				et.setText(str.substring(0, str.length() - 1));
			}
			break;
		case R.id.id_equal:
			clear_flay = true;
			getResult();
			break;
		default:
			break;
		}
	}

	private void getResult() {
		String str = et.getText().toString();
		if (str == null || str.equals("")) {
			return;
		}
		if (!str.contains(" ")) {
			return;
		}
		double result = 0;
		String s1 = str.substring(0, str.indexOf(" "));
		String op = str.substring(str.indexOf(" ")+1, str.indexOf(" ")+2);
		String s2 = str.substring(str.indexOf(" ")+3);
		if (!s1.equals("") && !s2.equals("")) {
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s2);
			if (op.equals("+")) {
				result = d1 + d2;
			} else if (op.equals("-")) {
				result = d1 - d2;
			} else if (op.equals("¡Á")) {
				result = d1 * d2;
			} else if (op.equals("¡Â")) {
				if (d2 == 0) {
					result = 0;
				} else {
					result = d1 / d2;
				}
			}
			if (!s1.contains(".") && !s2.contains(".")) {
				int r = (int) result;
				et.setText(r+"");
			} else {
				et.setText(result+"");
			}
		} else if (!s1.equals("") && s2.equals("")) {
			clear_flay = false;
			et.setText(str);
		} else if (s1.equals("") && !s2.equals("")) {
			double d1 = 0;
			double d2 = Double.parseDouble(s2);
			if (op.equals("+")) {
				result = d1 + d2;
			} else if (op.equals("-")) {
				result = d1 - d2;
			} else if (op.equals("¡Á")) {
				result = d1 * d2;
			} else if (op.equals("¡Â")) {
				if (d2 == 0) {
					result = 0;
				} else {
					result = d1 / d2;
				}
			}
			if (!s2.contains(".")) {
				int r = (int) result;
				et.setText(r+"");
			} else {
				et.setText(result+"");
			}
		} else {
			et.setText("");
		}
	}
}
