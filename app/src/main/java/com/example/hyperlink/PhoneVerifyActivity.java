package com.example.hyperlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hyperlink.databinding.ActivityPhoneVerifyBinding;

public class PhoneVerifyActivity extends AppCompatActivity {
    ActivityPhoneVerifyBinding phoneVerifyBinding;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreencall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    phoneVerifyBinding=DataBindingUtil.setContentView(this, R.layout.activity_phone_verify);
        settexthyperlinkResend();
        Intent intent = getIntent();
         user = new User();
        user.setName(intent.getStringExtra("Name"));
        user.setPhonenumber(intent.getStringExtra("phonenum"));
        user.setEmail(intent.getStringExtra("Email"));
        user.setPassword(intent.getStringExtra("password"));
        user.setAddress(intent.getStringExtra("address"));
        phoneVerifyBinding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });
    }
    public void FullScreencall() {
        if( Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else  {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void verify(){
        Log.e("PhoneActivity","data:");
        StringBuilder builder = new StringBuilder();
        builder.append(phoneVerifyBinding.firstchar.getText().toString());
        builder.append(phoneVerifyBinding.secondchar.getEditableText().toString());
        builder.append(phoneVerifyBinding.thirdchar.getText().toString());
        builder.append(phoneVerifyBinding.fourthchar.getText().toString());

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.insertdata(user);
        Log.e("PhoneActivity","data:"+user.toString());
        Intent intent = new Intent(PhoneVerifyActivity.this,DataActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }



    public void settexthyperlinkResend(){
        String s = getResources().getString(R.string.didn_t_receive_the_otp_resend);
        SpannableString ss = new SpannableString(s);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, s.length()-6, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_resend)), s.length()-6, s.length(), 0);// set color
        ss.setSpan(new UnderlineSpan(), s.length()-6, s.length(),0);
        phoneVerifyBinding.txtResend.setText(ss);
        phoneVerifyBinding.txtResend.setMovementMethod(LinkMovementMethod.getInstance());

    }
}