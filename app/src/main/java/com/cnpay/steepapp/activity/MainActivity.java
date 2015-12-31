package com.cnpay.steepapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cnpay.steepapp.R;

public class MainActivity extends BaseActivity {

    private Button mBtnSetLock;         //设置密码
    private Button mBtnVerifyLock;      //检验密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSetLock = (Button) findViewById(R.id.btn_set_lockpattern);
        mBtnVerifyLock = (Button) findViewById(R.id.btn_verify_lockpattern);

        setImmergeState();  //设置沉浸式状态栏
        initView();

        mBtnSetLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GestureEditActivity.class));
            }
        });

        mBtnVerifyLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GestureVerifyActivity.class));
            }
        });

    }


    /**
     * 初始标题栏
     */
    private void initView() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.base_title);
        setTitle(layout, "主页", "右侧标题", null);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
