package com.example.hyperlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hyperlink.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding activitySignUpBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreencall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        settextforCreate();
        activitySignUpBinding.createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdata();
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

    public void checkdata(){
    String name = activitySignUpBinding.txtName.getText().toString();
        String phonenum = activitySignUpBinding.txtPhone.getText().toString();
        String email = activitySignUpBinding.txtEmail.getText().toString();
        String password = activitySignUpBinding.txtPassword.getText().toString();
        String address = activitySignUpBinding.txtAddress.getText().toString();
        if(name.isEmpty() || phonenum.isEmpty() || email.isEmpty()|| password.isEmpty()|| address.isEmpty()){
            Toast.makeText(this, "Please enter all data", Toast.LENGTH_SHORT).show();
        }
        else if(isEmailValid(email)&& phonenum.length()==10){
            User user = new User(name,phonenum,email,password,address);
            //open Phone verification activity
            Intent phoneverify = new Intent(SignUpActivity.this, PhoneVerifyActivity.class);
            phoneverify.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            phoneverify.putExtra("Name",user.getName());
            phoneverify.putExtra("Email",user.getEmail());
            phoneverify.putExtra("password",user.getPassword());
            phoneverify.putExtra("phonenum",user.getPhonenumber());
            phoneverify.putExtra("address",user.getAddress());
            startActivity(phoneverify);
        }
        else{
            if(!activitySignUpBinding.chkRect.isChecked()){
                Toast.makeText(SignUpActivity.this,"Please accept terms and conditions",Toast.LENGTH_SHORT).show();
            }else{
            Toast.makeText(SignUpActivity.this,"Please enter proper email and phone number",Toast.LENGTH_SHORT).show();
        }
        }
    }
    private boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public void settextforCreate(){
        String s= getResources().getString(R.string.create_new_account);
        SpannableString ss1=  new SpannableString(s);
        int size = (int) getResources().getDimension(R.dimen.txt_size);
        ss1.setSpan(new AbsoluteSizeSpan(size), 10, s.length(),0); // set size
        activitySignUpBinding.txtCreate.setText(ss1);


      //  String redString = getResources().getString(R.string.agree_text);
       // activitySignUpBinding.txtTerms.setText(Html.fromHtml(redString));
      //  activitySignUpBinding.txtTerms.setMovementMethod(LinkMovementMethod.getInstance());
        settextforterms();
    }
    public void settextforterms(){


        String s = getResources().getString(R.string.agree_textnew);
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
        ss.setSpan(clickableSpan, 10, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_resend)), 10, s.length(), 0);// set color
        ss.setSpan(new UnderlineSpan(), 10, s.length(),0);
        activitySignUpBinding.txtTerms.setText(ss);
        activitySignUpBinding.txtTerms.setMovementMethod(LinkMovementMethod.getInstance());
        activitySignUpBinding.txtTerms.setHighlightColor(Color.RED);

    }

}