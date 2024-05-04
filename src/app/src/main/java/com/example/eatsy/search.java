package com.example.eatsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class search extends AppCompatActivity {
    private ListView mListVie;
    private List<PostFT> postList = new ArrayList<>();

    private Map<Integer,String> type = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setCheckListener();
        setEditListener();
        searchAll();
    }


    protected void setCheckListener(){
        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);

        View.OnClickListener checkBoxClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked1 = checkBox1.isChecked();
                boolean isChecked2 = checkBox2.isChecked();
                boolean isChecked3 = checkBox3.isChecked();

                String text1 = checkBox1.getText().toString();
                String text2 = checkBox2.getText().toString();
                String text3 = checkBox3.getText().toString();

                // 在这里处理点击事件，并使用 isChecked 和 text 进行相应操作
                if (isChecked1) {
                    // CheckBox 1 被选中
                    type.put(1,"donate");
                } else {
                    // CheckBox 1 被取消选中
                    type.remove(1);
                }

                if (isChecked2) {
                    // CheckBox 2 被选中
                    type.put(2,"wanted");
                } else {
                    // CheckBox 2 被取消选中
                    type.remove(2);
                }

                if (isChecked3) {
                    // CheckBox 3 被选中
                    type.put(3,"exchange");
                } else {
                    // CheckBox 3 被取消选中
                    type.remove(3);
                }
                if(!type.isEmpty()){
                    searchData();
                }else{
                    searchAll();
                }
            }
        };
        // 为每个 CheckBox 设置相同的点击监听器
        checkBox1.setOnClickListener(checkBoxClickListener);
        checkBox2.setOnClickListener(checkBoxClickListener);
        checkBox3.setOnClickListener(checkBoxClickListener);
    }


    protected void setEditListener(){
        EditText editText = findViewById(R.id.srch);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String searchText = editText.getText().toString().trim();
                    if(searchText.isEmpty()){
                        searchAll();
                    }else{
                        searchText = searchText.replaceAll("[^a-zA-Z0-9\\s]", "");
                        String[] tabs = searchText.split(" ");
                        searchByTest(tabs);
                    }
                    return true;
                }
                return false;
            }
        });
    }



    private void searchData(){
        List<PostFT> resList = new ArrayList<>();
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");
        postsCollectionRef.whereIn("postType", new ArrayList<>(type.values()))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        task.getResult().forEach(document -> {
                            String postID = document.getId();
                            String userID = document.getString("userID");
                            String userName = document.getString("userName");
                            String postType = document.getString("postType");
                            String postTitle = document.getString("postTitle");
                            String postDescription = document.getString("postDescription");
                            String quantity = document.getString("quantity");
                            String pickUpTimes = document.getString("pickUpTimes");
                            String latitude = document.getString("latitude");
                            String longitude = document.getString("longitude");
                            ArrayList<String> images = (ArrayList<String>) document.get("images");
                            PostFT post = new PostFT(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
                            resList.add(post);
                        });
                        this.postList = resList;
                        mListVie = findViewById(R.id.lv);
                        mListVie.setAdapter(new ListDataAdapter(search.this, this.postList));
                    }
                });
    }




    private void searchAll(){
        List<PostFT> resList = new ArrayList<>();
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");
        postsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                task.getResult().forEach(document -> {
                    String postID = document.getId();
                    String userID = document.getString("userID");
                    String userName = document.getString("userName");
                    String postType = document.getString("postType");
                    String postTitle = document.getString("postTitle");
                    String postDescription = document.getString("postDescription");
                    String quantity = document.getString("quantity");
                    String pickUpTimes = document.getString("pickUpTimes");
                    String latitude = document.getString("latitude");
                    String longitude = document.getString("longitude");
                    ArrayList<String> images = (ArrayList<String>) document.get("images");
                    PostFT post = new PostFT(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
                    resList.add(post);
                });
                this.postList = resList;
                mListVie = findViewById(R.id.lv);
                mListVie.setAdapter(new ListDataAdapter(search.this, this.postList));
            }
        });
    }



    private void searchByTest2(String[] keywords){
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");

        HashSet<PostFT> allResults = new HashSet<>();
        HashMap<PostFT, Integer> resultCountMap = new HashMap<>();

        AtomicInteger completedQueries = new AtomicInteger(); // 已完成的查询数

        for (String keyword : keywords) {
            postsCollectionRef.whereGreaterThan("postTitle",   keyword)
                    .whereLessThan("postTitle",keyword+ "\uf8ff")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            task.getResult().forEach(document -> {
                                String postID = document.getId();
                                String userID = document.getString("userID");
                                String userName = document.getString("userName");
                                String postType = document.getString("postType");
                                String postTitle = document.getString("postTitle");
                                String postDescription = document.getString("postDescription");
                                String quantity = document.getString("quantity");
                                String pickUpTimes = document.getString("pickUpTimes");
                                String latitude = document.getString("latitude");
                                String longitude = document.getString("longitude");
                                ArrayList<String> images = (ArrayList<String>) document.get("images");
                                PostFT post = new PostFT(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);
                                int count = resultCountMap.getOrDefault(post, 0);
                                resultCountMap.put(post, count + 1);
                                allResults.add(post);
                            });
                        } else {
                            // 处理查询失败
                            Exception exception = task.getException();
                            // 处理异常
                        }

                        completedQueries.getAndIncrement();

                        // 检查是否所有查询都完成
                        if (completedQueries.get() == keywords.length) {
                            // 根据出现次数对结果进行排序
                            ArrayList<PostFT> sortedResults = new ArrayList<>(allResults);
                            Collections.sort(sortedResults, (post1, post2) ->
                                    resultCountMap.get(post2).compareTo(resultCountMap.get(post1))
                            );
                            // 处理每个查询结果
                            this.postList = sortedResults;
                            mListVie = findViewById(R.id.lv);
                            mListVie.setAdapter(new ListDataAdapter(search.this, this.postList));
                        }
                    });
        }
    }



    private void searchByTest(String[] keywords) {
        Trie trie = new Trie();
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");
        HashSet<PostFT> allResults = new HashSet<>();
        HashMap<PostFT, Integer> resultCountMap = new HashMap<>();

        // 向Trie中插入关键词
        for (String keyword : keywords) {
            trie.insert(keyword.toLowerCase());
        }

        // 遍历关键词数组中的每个关键词
        // 发起查询
        postsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // 处理查询结果
                task.getResult().forEach(document -> {
                    String postTitle = document.getString("postTitle").toLowerCase();
                    String[] words = postTitle.split("\\s+"); // 分割标题为单词

                    // 计算标题中每个关键词的出现次数
                    int count = 0;
                    for (String word : words) {
                        word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
                        if (!word.equals("") &&  trie.searchPrefix(word)) {
                            count++;
                        }
                    }

                    // 如果标题中包含了当前关键词，将文档加入到结果集合中，并增加计数器
                    if (count > 0) {
                        String postID = document.getId();
                        String userID = document.getString("userID");
                        String userName = document.getString("userName");
                        String postType = document.getString("postType");
                        String postDescription = document.getString("postDescription");
                        String quantity = document.getString("quantity");
                        String pickUpTimes = document.getString("pickUpTimes");
                        String latitude = document.getString("latitude");
                        String longitude = document.getString("longitude");
                        ArrayList<String> images = (ArrayList<String>) document.get("images");
                        PostFT post = new PostFT(userID, userName, postType, postTitle, postDescription, quantity, pickUpTimes, latitude, longitude, images);

                        int totalCount = resultCountMap.getOrDefault(post, 0);
                        resultCountMap.put(post, totalCount + count);
                        allResults.add(post);
                    }
                });
            } else {
                // 处理查询失败
                Exception exception = task.getException();
                // 处理异常
            }

            // 增加已完成的查询数量

            // 检查是否所有查询都已完成
            // 根据出现次数对结果进行排序
            ArrayList<PostFT> sortedResults = new ArrayList<>(allResults);
            Collections.sort(sortedResults, (post1, post2) ->
                    resultCountMap.get(post2).compareTo(resultCountMap.get(post1))
            );
            // 处理每个查询结果
            this.postList = sortedResults;
            mListVie = findViewById(R.id.lv);
            mListVie.setAdapter(new ListDataAdapter(search.this, this.postList));
        });
    }



}


