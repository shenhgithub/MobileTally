package com.lygedi.android.mobiletally.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.lygedi.android.mobiletally.R;
import org.mobile.library.global.Global;
import org.mobile.library.model.activity.BaseLoginActivity;

/**
 * 用户登录Activity
 *
 * @author sh
 * @version 2.0 2018/4/9
 * @since 1.0
 */

public class LoginActivity extends BaseLoginActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "LoginActivity.";

    /**
     * 版权文本
     */
    TextView copyrightTextView = null;


    @Override
    protected int onActivityLoginLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEdit() {
        // 文本框初始化
        userNameEditText = (EditText) findViewById(org.mobile.library.R.id.login_content_layout_user_name_editText);
        passwordEditText = (EditText) findViewById(org.mobile.library.R.id.login_content_layout_password_editText);
        userNameTextInputLayout = (TextInputLayout) findViewById(org.mobile.library.R.id
                .login_content_layout_user_name_textInputLayout);
        passwordTextInputLayout = (TextInputLayout) findViewById(org.mobile.library.R.id
                .login_content_layout_password_textInputLayout);
        copyrightTextView = (TextView) findViewById(R.id.login_content_layout_copyright_textview);

        // 绑定错误提示
        onBindEditHint();

        // 尝试填充数据
        if (Global.getApplicationConfig().getUserName() != null) {
            // 填充用户
            userNameEditText.setText(Global.getApplicationConfig().getUserName());
        }

        PackageInfo pi= null;
        try {
            pi = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        copyrightTextView.setText("Copyright © 2018  版本：v " + pi.versionName);
    }

    @Override
    protected boolean checkPassword(String password) {

        if (passwordTextInputLayout != null) {

            if (TextUtils.isEmpty(password)) {
                passwordTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_password_null_error));
                return false;
            }
        }

        return true;
    }

    @Override
    protected boolean checkUserName(String userName) {

        if (userNameTextInputLayout != null) {

            if (TextUtils.isEmpty(userName)) {
                userNameTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_user_name_null));
                return false;
            }

            if (userName.contains(" ")) {
                userNameTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_user_name_blank));
                return false;
            }

            if (userName.length() < 2) {
                userNameTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_user_name_short));
                return false;
            }
        }

        return true;
    }



    @Override
    protected boolean onLoginSuccess(String message) {

        goMain();
        return false;
    }

    /**
     * 跳转到主界面
     */
    private void goMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
