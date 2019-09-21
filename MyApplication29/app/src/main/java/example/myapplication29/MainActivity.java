package example.myapplication29;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    final String TAG = "测试MainActivity";
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.w(TAG, "onCreate...");
        //setContentView(R.layout.activity_main);

        orientation = getResources().getConfiguration().orientation;
        if(orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.activity_main_portrait);
            Log.d(TAG,"现在为竖屏模式portrait...");
        }
        else
        {
            setContentView(R.layout.activity_main_landscape);
            Log.d(TAG,"现在为横屏模式landscape...");
        }

    }

    @Override
    protected void onDestroy()
    {
        Log.w(TAG, "onDestroy...");
        super.onDestroy();
    }

    // 发生切换屏幕事件，Acitivity即将销毁... 所以要先把当前界面的状态数据备份一下 ...
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        // 取出当前值
        String title = ((EditText) findViewById(R.id.id_title)).getText().toString();
        String author = ((EditText) findViewById(R.id.id_author)).getText().toString();
        String content = ((EditText) findViewById(R.id.id_content)).getText().toString();
        // 存入Bundle
        outState.putString("title", title);
        outState.putString("author", author);
        outState.putString("content", content);
    }

    // Acitivty已经重新创建...从备份状态数据里取出, 恢复显示...
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        // 从Bundle取出数据
        String title = savedInstanceState.getString("title");
        String author = savedInstanceState.getString("author");
        String content = savedInstanceState.getString("content");

        // 恢复显示
        ((EditText) findViewById(R.id.id_title)).setText(title);
        ((EditText) findViewById(R.id.id_author)).setText(author);
        ((EditText) findViewById(R.id.id_content)).setText(content);
    }
}

