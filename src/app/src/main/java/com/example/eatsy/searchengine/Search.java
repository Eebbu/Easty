package com.example.eatsy.searchengine;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsy.Post;
import com.example.eatsy.PostAdapter;
import com.example.eatsy.R;
import com.example.eatsy.datamanagement.LocalJsonDataBase;
import com.example.eatsy.pages.DashboardActivity;
import com.example.eatsy.pages.PostCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * This class handles search and filter functionalities within the application.
 * It processes user inputs for search terms and selected filter criteria, queries data accordingly,
 * and updates the UI to display the search results.
 * Author: Lin Xi(u7777752)
 */
public class Search extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Post> postsToShow;
    private PostAdapter adapter;

    private Map<Integer, String> type = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageButton go_back = findViewById(R.id.leftArrowButton);
        go_back.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        setCheckListener();
        setEditListener();
        setSearchButtonListener(); // Set listener for the search button
        searchAll();




    }

    /**
     * Sets up listeners for the checkboxes, allowing users to filter posts based on types like "Donate", "Need", "Exchange".
     * It updates the type map according to selections and performs searches based on these filters.
     */
    protected void setCheckListener() {
        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);

        View.OnClickListener checkBoxClickListener = v -> {
            boolean isChecked1 = checkBox1.isChecked();
            boolean isChecked2 = checkBox2.isChecked();
            boolean isChecked3 = checkBox3.isChecked();

            if (isChecked1) {
                type.put(1, "donate");
            } else {
                type.remove(1);
            }

            if (isChecked2) {
                type.put(2, "wanted");
            } else {
                type.remove(2);
            }

            if (isChecked3) {
                type.put(3, "exchange");
            } else {
                type.remove(3);
            }

            if (!type.isEmpty()) {
                searchData();
            } else {
                searchAll();
            }
        };

        checkBox1.setOnClickListener(checkBoxClickListener);
        checkBox2.setOnClickListener(checkBoxClickListener);
        checkBox3.setOnClickListener(checkBoxClickListener);
    }

    /**
     * Sets up a listener for the search input field. When the user submits a search,
     * the input is processed to extract keywords, which are then used to filter posts.
     */
    protected void setEditListener() {
        EditText editText = findViewById(R.id.srch);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handleSearch();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Sets up a listener for the search button. When the user clicks the button,
     * the input is processed to extract keywords, which are then used to filter posts.
     */
    protected void setSearchButtonListener() {
        ImageView searchButton = findViewById(R.id.imageView3);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearch();
            }
        });
    }

    /**
     * Handles the search operation by extracting the search text,
     * parsing it into tokens, and performing the search.
     */
    private void handleSearch() {
        EditText editText = findViewById(R.id.srch);
        String searchText = editText.getText().toString().trim();
        if (searchText.isEmpty()) {
            searchAll();
        } else {
            Test.Node node = matchToken(searchText);
            if (node == null) {
                Toast.makeText(Search.this, "Please enter only English letters", Toast.LENGTH_SHORT).show();
            } else {
                searchByText(node.toArray().toArray(new String[0]));
            }
        }
    }

    /**
     * Parses the input string into a syntax tree based on predefined grammar rules using a custom parser.
     * This method tokenizes the input string, creates tokens for each word and space, and then attempts
     * to parse these tokens into a structured syntax tree (AST).
     *
     * @param searchTest The input string from the search text box to be tokenized and parsed.
     * @return A Node representing the root of the syntax tree if parsing is successful; otherwise, null if an error occurs.
     */
    private Test.Node matchToken(String searchTest) {
        List<Test.Token> tokens = new ArrayList<>();
        String[] s1 = searchTest.split(" ");
        for (int i = 0; i < s1.length; i++) {
            tokens.add(new Test.Token(s1[i]));
            if (i != s1.length - 1) {
                tokens.add(new Test.Token(" "));
            }
        }
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
    private void searchData() {
        if (!this.type.isEmpty()){
            List<Post> resList = new ArrayList<>();
            postsToShow.forEach(document -> {
                if (this.type.containsValue(document.getPostType())) {
                    resList.add(document);
                }
            });
            postsToShow = resList;
        }
        adapter = new PostAdapter(postsToShow);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            Post clickedPost = postsToShow.get(position);
            Intent intent = new Intent(Search.this, PostCard.class);
            intent.putExtra("clickedPost", clickedPost);
            startActivity(intent);
        });



    }

    /**
     * Displays all posts without any filters.
     */
    private void searchAll() {
        List<Post> list = new ArrayList<>();
        List<AVLTreeNode> avlTreeNodes = StorageList.avlTree.traverseTree();
        for (AVLTreeNode avlTreeNode : avlTreeNodes) {
            list.add((Post) avlTreeNode.data);
        }
        postsToShow = list;
        adapter = new PostAdapter(postsToShow);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Post clickedPost = postsToShow.get(position);
            Intent intent = new Intent(Search.this, PostCard.class);
            intent.putExtra("clickedPost", clickedPost);
            startActivity(intent);
        });
    }

    /**
     * Searches posts based on user-entered keywords in the search text box.
     * @param keywords The search keywords to use for filtering posts.
     */
    private void searchByText(String[] keywords) {
        Trie trie = new Trie();
        HashSet<Post> allResults = new HashSet<>();
        HashMap<Post, Integer> resultCountMap = new HashMap<>();

        for (String keyword : keywords) {
            trie.insert(keyword.toLowerCase());
        }

        StorageList.postList.forEach(document -> {
            String postTitle = document.getPostTitle().toLowerCase();
            String[] words = postTitle.split("\\s+");
            int count = 0;
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
                if (!word.equals("") && trie.searchPrefix(word)) {
                    count++;
                }
            }
            if (count > 0) {
                int totalCount = resultCountMap.getOrDefault(document, 0);
                resultCountMap.put(document, totalCount + count);
                allResults.add(document);
            }
        });

        ArrayList<Post> sortedResults = new ArrayList<>(allResults);
        Collections.sort(sortedResults, (post1, post2) ->
                resultCountMap.get(post2).compareTo(resultCountMap.get(post1))
        );

        postsToShow = sortedResults;
        searchData();
    }
}
