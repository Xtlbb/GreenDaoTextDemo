package com.sy.greendaotextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sy.greendaotextdemo.bean.UserBean;
import com.sy.greendaotextdemo.gen.UserBeanDao;
import com.sy.greendaotextdemo.manager.GreenDaoManager;
import com.sy.greendaotextdemo.util.MD5Util;


/**
 * 集成GreenDao的demo，增删改查。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();
    private Button saveNamePwd;
    private Button queryNamePwd;
    private EditText saveName;
    private EditText savePwd;
    private EditText queryName;
    private EditText queryPwd;

    private EditText updateName;
    private EditText updatePwd;
    private Button updateUser;


    private String mSaveName;
    private String mSavePwd;
    private String mQueryName;
    private String mQueryPwd;
    private UserBeanDao mUserBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mUserBeanDao = GreenDaoManager.getInstance().getUserBeanDao();
    }

    private void initView() {
        saveNamePwd = (Button) findViewById(R.id.bt_tomdpw);
        saveNamePwd.setOnClickListener(this);
        queryNamePwd = (Button) findViewById(R.id.bt_querypw);
        queryNamePwd.setOnClickListener(this);
        saveName = (EditText) findViewById(R.id.et_inputname);
        savePwd = (EditText) findViewById(R.id.et_inputpw);
        queryName = (EditText) findViewById(R.id.et_queryname);
        queryPwd = (EditText) findViewById(R.id.et_querpw);
       updateName = (EditText) findViewById(R.id.et_updataname);
       updatePwd = (EditText) findViewById(R.id.et_updatapwd);
        updateUser = (Button) findViewById(R.id.bt_updatapwd);
        updateUser.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_tomdpw:
                mSaveName = saveName.getText().toString();
                mSavePwd = savePwd.getText().toString();
                String mMdPwd = MD5Util.encode(mSavePwd);
                //插入到数据库中
                insertData(mSaveName,mMdPwd);

             break;
            case R.id.bt_querypw:
                mQueryName = queryName.getText().toString();
                mQueryPwd = queryPwd.getText().toString();
                String mMdQueryPwd = MD5Util.encode(mQueryPwd);
                //执行查询操作
                queryData(mQueryName,mMdQueryPwd);

                break;
            case R.id.bt_updatapwd:
                String name = updateName.getText().toString();
                String pwd = updatePwd.getText().toString();
                updateData(name,pwd);

                break;
        }

    }

    //创建用户
    private void insertData(String name,String pwd) {

        UserBean giou = new UserBean(null, name,pwd);
        Log.d(TAG, "insertData: 插入用户信息"+name+"密码"+pwd);
        long insert = mUserBeanDao.insert(giou);
        Log.d(TAG, "添加数据成功！！" + insert);
    }

    //查询用户
    private void queryData(String name,String pwd) {
        Log.d(TAG, "queryData: 用户信息"+"用户名--"+name+"密码"+pwd);
        UserBean findUser = mUserBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq(name),UserBeanDao.Properties.Password.eq(pwd)).build().unique();
        if (findUser !=null){
            Log.d(TAG, "queryData: 用户存在"+"用户名"+findUser.getName()+"用户密码"+findUser.getPassword());
        }else {
            Log.d(TAG, "queryData: 用户不存在");

        }

    }


    //更新用户信息
    private void updateData(String name,String pwd) {
        UserBean findUser = mUserBeanDao.queryBuilder().where(UserBeanDao.Properties.Password.eq(name)).build().unique();
        if (findUser != null) {
            String updatePw = MD5Util.encode(pwd);
            findUser.setPassword(updatePw);
            mUserBeanDao.update(findUser);
            Log.d(TAG, "修改成功");
        } else {
            Log.d(TAG, "修改失败");
        }
    }
}
