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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * This class handles search and filter functionalities within the application.
 * It processes user inputs for search terms and selected filter criteria, queries data accordingly,
 * and updates the UI to display the search results.
 * Author: Lin Xi(u7777752)
 */
public class Search extends AppCompatActivity {
    private ListView mListVie;

    private Map<Integer,String> type = new HashMap<>();

    @Override
/**
 * This class handles search and filter functionalities within the application.
 * It processes user inputs for search terms and selected filter criteria, queries data accordingly,
 * and updates the UI to display the search results.
 */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
        });
        setCheckListener();
        setEditListener();
        searchAll();
    }

/**
     * Sets up listeners for three checkboxes, allowing users to filter posts based on types like "Donate", "Need", "Exchange".
            * It updates the type map according to selections and performs searches based on these filters.
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

    /**
     * Sets up a listener for the search input field. When the user submits a search,
     * the input is processed to extract keywords, which are then used to filter posts.
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
                        Test.Node node = matchToken(searchText);
                        if (node==null) {
                            Toast.makeText(Search.this, "Please enter only English letters", Toast.LENGTH_SHORT).show();
                        }else{
                            //searchText = searchText.replaceAll("[^a-zA-Z0-9\\s]", "");
                            searchByTest(node.toArray().toArray(new String[0]));
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }
    /**
     * Parses the input string into a syntax tree based on predefined grammar rules using a custom parser.
     * This method tokenizes the input string, creates tokens for each word and space, and then attempts
     * to parse these tokens into a structured syntax tree (AST).
     *
     * @param searchTest The input string from the search text box to be tokenized and parsed.
     * @return A Node representing the root of the syntax tree if parsing is successful; otherwise, null if an error occurs.
     */
    private Test.Node matchToken(String searchTest){
        List<Test.Token> tokens = new ArrayList<>();
        String[] s1 = searchTest.split(" ");
        for (int i=0;i<s1.length;i++) {
            tokens.add(new Test.Token(s1[i]));
            if(i!=s1.length-1){
                tokens.add(new Test.Token(" "));
            }
        }
        // parse token
        Test.Parser parser = new Test.Parser(tokens);
        try {

            Test.Node rootNode = parser.parseExp();
            return rootNode;
        } catch (Test.IllegalProductionException e) {
            return null;
        }
    }


    /**
     * Filters posts based on selected types from checkboxes.
     */
    private void searchData(){
        List<Post> resList = new ArrayList<>();
        StorageList.postList.forEach(document -> {
            if(!this.type.isEmpty() && this.type.containsValue(document.getPostType())){
                resList.add(document);
            }
        });
        mListVie = findViewById(R.id.lv);
        mListVie.setAdapter(new ListDataAdapter(Search.this, resList));
    }



    /**
     * Displays all posts without any filters.
     */
    private void searchAll(){
        mListVie = findViewById(R.id.lv);
        List<Post> list = new ArrayList<>();
        List<AVLTreeNode> avlTreeNodes = StorageList.avlTree.traverseTree();
        for (AVLTreeNode avlTreeNode : avlTreeNodes) {
            list.add((Post)avlTreeNode.data);
        }
        mListVie.setAdapter(new ListDataAdapter(Search.this,list));
    }


    /**
     * Searches posts based on user-entered keywords in the search text box.
     * @param keywords The search keywords to use for filtering posts.
     */
    private void searchByTest(String[] keywords) {
        Trie trie = new Trie();
        HashSet<Post> allResults = new HashSet<>();
        HashMap<Post, Integer> resultCountMap = new HashMap<>();

        // Insert keywords into Trie
        for (String keyword : keywords) {
            trie.insert(keyword.toLowerCase());
        }

        // Iterate through each keyword in the keyword array
        // Initiate query
        // Processing the results of query
        StorageList.postList.forEach(document -> {
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
        ArrayList<Post> sortedResults = new ArrayList<>(allResults);
        Collections.sort(sortedResults, (post1, post2) ->
                resultCountMap.get(post2).compareTo(resultCountMap.get(post1))
        );
        mListVie = findViewById(R.id.lv);
        mListVie.setAdapter(new ListDataAdapter(Search.this, sortedResults));
    }


}


