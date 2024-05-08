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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/*This class is for searching and filtering.
        It is used to process user input,
        search data,
        and display search results on the interface.
 */
public class search extends AppCompatActivity {
    private ListView mListVie;
    private List<PostFT> postList = new ArrayList<>();

    private Map<Integer,String> type = new HashMap<>();

    @Override
/*
    When the user clicks the back button
    (go_back),
    it ends the current activity
    and returns to DashboardActivity
 */
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

/*
    Three checkboxes (checkBox1, checkBox2, checkBox3) are provided,
    allowing users to filter posts based on different types
    (e.g. "Donate", "Need", "Exchange").
    Based on the checkboxes selected by the user,
    update a type map that stores the selected types
    and call the searchData method to filter posts based on these types,
    or call the searchAll method to display all posts if no checkboxes are selected.

 */

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

                // Handle the click event here and use isChecked and text to act accordingly
                if (isChecked1) {
                    // CheckBox 1 is selected
                    type.put(1,"donate");
                } else {
                    // CheckBox 1 is cancelled
                    type.remove(1);
                }

                if (isChecked2) {
                    // CheckBox 2 is selected
                    type.put(2,"wanted");
                } else {
                    // CheckBox 2 cancelled
                    type.remove(2);
                }

                if (isChecked3) {
                    // CheckBox 3 is selected
                    type.put(3,"exchange");
                } else {
                    // CheckBox 3 cancelled
                    type.remove(3);
                }
                if(!type.isEmpty()){
                    searchData();
                }else{
                    searchAll();
                }
            }
        };
        // Set the same click listener for each CheckBox
        checkBox1.setOnClickListener(checkBoxClickListener);
        checkBox2.setOnClickListener(checkBoxClickListener);
        checkBox3.setOnClickListener(checkBoxClickListener);
    }

    /*
        Through a text input box (editText),
        the user can enter search keywords.
        Text searches are handled via the setEditListener method,
        which sanitizes the user input,
        removes non-alphanumeric characters,
        and splits the text by spaces to obtain an array of keywords.
        Call the searchByTest method to search based on the keyword array.
     */
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


    /*
        Filter posts based on the type selected by the user via checkbox.
     */
    private void searchData(){
        List<PostFT> resList = new ArrayList<>();
        this.postList.forEach(document -> {
            if(!this.type.isEmpty() && this.type.containsValue(document.getPostType())){
                resList.add(document);
            }
        });
        mListVie = findViewById(R.id.lv);
        mListVie.setAdapter(new ListDataAdapter(search.this, resList));
    }



    /*
        Show all posts without any filters.
     */
    private void searchAll(){
        mListVie = findViewById(R.id.lv);
        mListVie.setAdapter(new ListDataAdapter(search.this, StorageList.postList));
    }


    /*
        Search posts based on keywords entered by the user.
        It uses a custom Trie data structure to match keyword prefixes
        and counts the number of matching keywords in each post title for sorting and display.
     */
    private void searchByTest(String[] keywords) {
        Trie trie = new Trie();
        CollectionReference postsCollectionRef = FirestoreHelper.getCollectionRef("posts");
        HashSet<PostFT> allResults = new HashSet<>();
        HashMap<PostFT, Integer> resultCountMap = new HashMap<>();

        // Insert keywords into Trie
        for (String keyword : keywords) {
            trie.insert(keyword.toLowerCase());
        }

        // Iterate through each keyword in the keyword array
        // Initiate query
        // Processing the results of query
        this.postList.forEach(document -> {
            String postTitle = document.getPostTitle().toLowerCase();
            String[] words = postTitle.split("\\s+"); // Split title into words
            // Count the occurrences of each keyword in the title
            int count = 0;
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
                if (!word.equals("") &&  trie.searchPrefix(word)) {
                    count++;
                }
            }
            // If the title contains the current keyword,
            // add the document to the result set and increment the counter
            if (count > 0) {
                int totalCount = resultCountMap.getOrDefault(document, 0);
                resultCountMap.put(document, totalCount + count);
                allResults.add(document);
            }
        });
        // Increase the number of completed queries
        // Check if all queries have completed
        // Sort the results according to the number of occurrences
        ArrayList<PostFT> sortedResults = new ArrayList<>(allResults);
        Collections.sort(sortedResults, (post1, post2) ->
                resultCountMap.get(post2).compareTo(resultCountMap.get(post1))
        );
        mListVie = findViewById(R.id.lv);
        mListVie.setAdapter(new ListDataAdapter(search.this, sortedResults));
    }


}


