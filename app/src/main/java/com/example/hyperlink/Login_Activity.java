package com.example.hyperlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hyperlink.R;
import com.example.hyperlink.databinding.ActivityLoginBinding;


public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreencall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginBinding.txtCreatacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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

    public void loginclick(View view) {
        String email = loginBinding.editEmail.getText().toString();
        String password = loginBinding.editPassword.getText().toString();
        if (email.isEmpty()) {
            showtoast("Please enter Email");
        } else if (password.isEmpty()) {
            showtoast("Please enter password");
        } else if (!isValid(email)) {
            showtoast("Please enter valid Email address");
        } else {
            //check user
            DatabaseHelper databaseHelper = new DatabaseHelper(Login_Activity.this);
            Log.e("LoginActivity", "email:" + email + "  pawd:" + password);
            boolean isuserExist = databaseHelper.checkData(email, password);
            if (isuserExist) {

                Intent intent = new Intent(Login_Activity.this, DataActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                showtoast("Data not exist");
            }
        }
    }

    private void showtoast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}