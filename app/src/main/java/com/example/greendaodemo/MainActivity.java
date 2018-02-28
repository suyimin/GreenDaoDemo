package com.example.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<UserInfo> lists;
    private RecyclerView recyclerView;
    private Button add, del, update, query;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        queryData();
        add = findViewById(R.id.add);
        del = findViewById(R.id.del);
        update = findViewById(R.id.update);
        query = findViewById(R.id.query);
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                addData();
                break;
            case R.id.del:
                deleteData();
                break;
            case R.id.update:
                updateData();
                break;
            case R.id.query:
                queryData();
                break;
            default:
                break;
        }
    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        private List<UserInfo> mLists;

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            MainViewHolder holder = new MainViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            final int pos = holder.getLayoutPosition();
            Log.d("onBindViewHolder", "position-->" + position + ",holder.getLayoutPosition()-->" + pos);
            holder.tv.setText(mLists.get(position).getName() + "--" + mLists.get(position).getAge() + "--" + mLists.get(position).getSex());
        }

        @Override
        public int getItemCount() {
            return mLists.size();
        }

        public void setData(List<UserInfo> lists) {
            mLists = lists;
            notifyDataSetChanged();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MainViewHolder(View view) {
                super(view);
                tv = view.findViewById(R.id.tv);
            }
        }
    }

    private void updateData() {
        if (!lists.isEmpty()) {
            UserInfo userInfo = lists.get(0);
            userInfo.setName("李四");
            UserDao.getInstance().updateUserData(userInfo);
            queryData();
        }
    }

    private void deleteData() {
        if (!lists.isEmpty()) {
            UserDao.getInstance().deleteUserData(lists.get(0));
            queryData();
        }

    }

    private void addData() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("张三");
        userInfo.setAge(18);
        userInfo.setSex(1);
        UserDao.getInstance().insertUserData(userInfo);
        queryData();
    }

    private void queryData() {
        lists = UserDao.getInstance().queryAllData();
        adapter.setData(lists);
        Toast.makeText(this, "查询到" + lists.size() + "条数据", Toast.LENGTH_SHORT).show();
    }
}
