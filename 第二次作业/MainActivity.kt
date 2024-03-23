package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private View mCommentView;
    private MyAdapter myAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        myAdapter = new MyAdapter(this, getData());
        mListView.setAdapter(myAdapter);

        mCommentView = findViewById(R.id.comment_view);
    }

    // build data
    private ArrayList<Item> getData() {
        int ITEM_COUNT = 20;
        ArrayList<Item> data = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            data.add(new Item(R.drawable.xiaona, "我校2022年考研详情", "成功上岸率高达90%", "昨天"));
            data.add(new Item(R.drawable.xueyan, "图书馆借书", "学生们借书的热情高涨", "昨天"));
            data.add(new Item(R.drawable.leishao, "2020级学生毕业", "应该毕业1500人，实际毕业1500人", "昨天"));
            data.add(new Item(R.drawable.yuhong, "食堂饭菜太贵", "食堂饭菜不好吃，还比较贵", "昨天"));
            data.add(new Item(R.drawable.lanshan, "学生学习压力过大", "学习废寝忘食学习，已经秃顶了", "昨天"));
            data.add(new Item(R.drawable.aa, "放假通知", "我想放假", "昨天"));
            data.add(new Item(R.drawable.bb, "过元宵", "一起放灯笼", "昨天"));
            data.add(new Item(R.drawable.cc, "过年", "吃团圆饭", "昨天"));
            data.add(new Item(R.drawable.dd, "校园体育", "操场跑20圈", "昨天"));
            data.add(new Item(R.drawable.ee, "就业情况", "全部就业了", "昨天"));
        }

        return data;
    }

    private class MyAdapter extends BaseAdapter implements ItemView.OnCommentListener {

        private Context context;
        private ArrayList<Item> mData;
        private Map<Integer, ItemView> mCachedViews = new HashMap<>();

        public MyAdapter(Context context, ArrayList<Item> mData) {
            this.context = context;
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;

            if (convertView != null) {
                view = convertView;
            } else {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listview_item, null, false);
            }

            if (view instanceof ItemView) {
                Item data = (Item) getItem(position);
                ((ItemView) view).setData(data);
                ((ItemView) view).setPosition(position);
                ((ItemView) view).setCommentListener(this);

                cacheView(position, (ItemView) view);
            }

            return view;
        }

        @Override
        public void onComment(int position) {
            showCommentView(position);
        }

        private void cacheView(int position, ItemView view) {
            Iterator<Map.Entry<Integer, ItemView>> entries = mCachedViews.entrySet().iterator();

            while (entries.hasNext()) {

                Map.Entry<Integer, ItemView> entry = entries.next();
                if (entry.getValue() == view && entry.getKey() != position) {
                    mCachedViews.remove(entry.getKey());
                    break;
                }
            }

            mCachedViews.put(position, view);

            Log.d("MainActivity", position + ", " + mCachedViews.size());
        }

        private void showCommentView(final int position) {
            mCommentView.setVisibility(View.VISIBLE);

            mCommentView.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText et = (EditText) mCommentView.findViewById(R.id.edit);
                    String s = et.getText().toString();

                    if (!TextUtils.isEmpty(s)) {

                        // update model
                        Comment comment = new Comment(s);
                        mData.get(position).getComments().add(comment);

                        // update view maybe
                        ItemView itemView = mCachedViews.get(position);

                        if (itemView != null && position == itemView.getPosition()) {
                            itemView.addComment();
                        }

                        et.setText("");
                        mCommentView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}

