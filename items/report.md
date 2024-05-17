# [G15 - Good Food, Good Life] Report

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: <insert-link-to-firebase-repository>
   - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
   - Username: comp2100@anu.edu.au	Password: comp2100
   - Username: comp6442@anu.edu.au	Password: comp6442

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID      |      Name       |                                                                                                                                                                                                                                Role |
|:---------|:---------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| u7663368 | Vishakha Mathur | MainActivity, Login Activity, LoginUser (singleton pattern), Profile Activity and UI design(main page, login page, dashboard, profile page), uploading images on profile page from phone to firebase, singleton design pattern test |
| u7727175 |  Jinyang Zeng    |DataStream, MapSelection activity(GPS) and its UI design, Load show data in dashboard and detailed post page，postcard activity and its UI design，Generate post data from user input |
| u7777752 |     Lin Xi      |UI design (Search page and Search_detailed page), Tokenizer, Parser, Data Structure (Hashmap, AVLTree, Arraylist, Hashset, Trie), Data Fetching, Searching and filtering, Testing (Search), Design Patterns (Adapter, Builder) |
| u7773880 |   Zihan Yuan    |Add Activity, Post activity(post_donate, post_exchange, post_wanted), page redirection, two factory design patterns, Data Stream(get images from album and upload image to Firebase) |
| u7705128 |   Boxuan Lin     |Create 2600+ post instances and 2500 user accounts, storing all of the data in Firebase. Providing methods for downloading and updating data from Firebase. |


## Summary of Individual Contributions
1. **U7777752, Lin Xi** I have 20% contribution, as follows:<br>
**Code Contribution in the final App**
   - Search feature(medium) - class Search:[Search.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/Search.java?ref_type=heads) 
   - Search filter(easy) - class Search:[Search.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/Search.java?ref_type=heads) - class activity_search:[activity_search.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_search.xml?ref_type=heads)
   - Search Invalid(medium) - class Search:[Search.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/Search.java?ref_type=heads)
   - Data fetching - class StorageList:[StorageList.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/StorageList.java?ref_type=heads)
   - Store data in AVLTree - class AVLTree:[AVLTree.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/AVLTree.java?ref_type=heads) - class AVLTreeNode:[AVLTreeNode.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/AVLTreeNode.java?ref_type=heads)
   - Adapter pattern - class ListDataAdapter:[ListDataAdapter.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/ListDataAdapter.java?ref_type=heads)
   - Display data - class PostDetailActivity:[PostDetailActivity.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/PostDetailActivity.java?ref_type=heads) -class activity_post_detail:[activity_post_detail.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_post_detail.xml?ref_type=heads) 

2. **U7705128, Boxuan Lin**  I have 20% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Firebase Persistent(medium) - class [DataManager.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/DataManager.java?ref_type=heads), [FireStoreHelper.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/FireStoreHelper.java?ref_type=heads), [PostDataDownloader.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/PostDataDownloader.java?ref_type=heads), [UserDataDownloader.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/UserDataDownloader.java?ref_type=heads). <br>
     - DataFiles(easy) Local storage: [posts.json](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/raw/posts.json?ref_type=heads), [users_without_password.json](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/raw/users_without_password.json?ref_type=heads).
      - Singleton Design Pattern -  [DataManager.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/DataManager.java?ref_type=heads).

      
3.**U7663368, Vishakha Mathur** I have 20% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Login feature - class LoginActivity: [LoginActivity.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/LoginActivity.java?ref_type=heads).
    - Data Profile(displaying user details, uploading images to the firebase) - class ProfileAcitvity: [ProfileActivity.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/ProfileActivity.java?ref_type=heads).
    - Singleton Design Pattern - class LoginUser: [LoginUser.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/LoginUser.java?ref_type=heads#L8-34), [getInstance(), info(), error()](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/LoginUser.java?ref_type=heads#L14).
    - Other contribution: [MainActivity class](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/MainActivity.java?ref_type=heads), [DashboardActivity class](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/DashboardActivity.java?ref_type=heads),
      UI design of [activity_main.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_main.xml?ref_type=heads), [activity_login.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_login.xml?ref_type=heads),
      [activity_dashboard.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_dashboard.xml?ref_type=heads), [activity_profile.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_profile.xml?ref_type=heads).
    - UI Testing: [MainActivityUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/MainActivityUITest.java?ref_type=heads),
      [LoginActivityUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/LoginActivityUITest.java?ref_type=heads),
      [DashBoardActivityUITests.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/DashBoardActivityUITests.java?ref_type=heads),
      [AddPageUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/AddPageUITest.java?ref_type=heads),
      [MapSelectionUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/MapSelectionUITest.java?ref_type=heads).

    **Code and App Design**
    - Singleton Design Pattern.
    - ScrollView, LinearLayout, Picasso, pictures and icons. 

4. **U7727175, Jinyang Zeng**  I have 20% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Data Stream feature - function simulateDataStream: [simulateDataStream()](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/DashboardActivity.java#L121-145)
    - Load Show Data - class PostCard: [PostCard.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/PostCard.java)
,class DashboardActivity:[DashboardActivity.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/DashboardActivity.java)
,class PostAdapter: [PostAdapter.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/PostAdapter.java)
    - GPS feature - class MapSelection: [MapSelection.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/MapSelection.java)
, class PostCard: [PostCard.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/PostCard.java#L45-62)
    - Other contribution: [LocalJsonDataBase class](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/LocalJsonDataBase.java)
,[Post_base class](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_base.java#L53-116): addPostToFirebase()
,[FireStoreHelper class](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/datamanagement/FireStoreHelper.java#L38-49): createAndPost(Post post)
,[combined PostFT and Post class](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/b2f746ff6fd9f0a8d18f10b270be4494d815aa3b)
    - UI design of [activity_map_selection.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_map_selection.xml)
, [activity_post_card.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_post_card.xml)
, [item_post.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/item_post.xml)

5. **U7773880, Zihan Yuan**  I have 20% contribution, as follows: <br>
- **Code Contribution in the final App**
  - LoadShowData - class Post_base to upload and show images: [Post_base.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_base.java?ref_type=heads)
  - UI-Layout: modify most of the pages to incorporate layout adjustments in the UI components for portrait and landscape
    layout variants, as well as different screen sizes.
  - UI-Test: [PostdonateUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/PostdonateUITest.java?ref_type=heads), [PostexchangeUITest](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/PostexchangeUITest.java?ref_type=heads), [PostwantedUITest](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/PostwantedUITest.java?ref_type=heads).
  - Design Pattern: factory design patter: [Post_base.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_base.java), 
[Factory_donate.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/Factory_donate.java)
, [Factory_exchange.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/Factory_exchange.java)
, [Factory_wanted.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/Factory_wanted.java)
, [Post_donate](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_donate.java)
, [Post_exchange](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_exchange.java)
, [Post_wanted](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_wanted.java)
  - UI design: [activity_addpage.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_addpage.xml)
, [activity_post_donate.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_post_donate.xml),
, [activity_post_exchange.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_post_exchange.xml), [activity_post_wanted.xml](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/res/layout/activity_post_wanted.xml)
  - Other contribution: [Addpage.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/AddPage.java), 
[Post.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/Post.java)
- **Code and App Design**
    - UI-design: I designed a transparent add page for user to post, and three types of Post page. All of the icons come from [Icons8](https://icons8.com/).

## Application Description

EATSY is a platform where people can donate, exchange, ask for food ensuring responsible consumption and support the people who are in need. It focuses on the UN sustainable goal of zero hunger and responsible consumption and production.

### Main Page

This is the main page that appears when we start the app. The button takes us to the login page. 
<div align="center"> <img src="mainPage.png" alt="Main page" width="250px"> </div>

### Login Page

On this page, user is asked to fill his credentials login the app. 

<div align="center">
<img src="loginPage.png" alt="Login Page" width="250px"> 

</div>


### DashBoard Page
On this page, a user can see all sorts of posts. It can also take you to profile page. The add 
button opens 'Add page' where user can post something. It has a search icon brings the search page on click.
<div align="center"> <img src="dashBoard.png" alt="DashBoard page" width="250px"> 
</div>


### Profile Page
This page displays the user details such as user's name and password. It also allows the user to manage his profile picture and sign out from the app. 
<div align="center">
   <img src="profilePage.png" alt="Profile page" width="250px">
</div>

### Add page
Add page with a transparancy background. Users can chosse three types of post.
<div align="center">
    <img src="addpage.png" alt="Add page">
</div>

### Post pages
Post have three different type: donate, exchange and wanted.
1. Donate Page
   - Purpose: Allows users to create posts where they offer items as donation.
   - Photo Selection: Users are required to select photos from their album. This provides a clear idea of the item being donated.
   - Form Details: Users fill out a form including the item's title, description, quantity, pickup time, and pickup location.
   - Post Submission: Once the form is validated, the donation details are added to Firebase, making the post accessible to other users.
<div align="center">
    <img src="postdonate.png" alt="Post donate page">
</div>

2. Exchange Page
   - Purpose: Designed for users who want to exchange items with others.
   - Photo Selection: Similar to the donation page, users must upload photos of the items they wish to exchange.
   - Form Details: Users provide specifics such as the item's title, description, and the type of item they are willing to receive in exchange.
   - Post Submission: The validated exchange details are submitted to Firebase, making the post available to other users.
<div align="center">
    <img src="postexchangepage.png" alt="Post exchange page">
</div>

3. Wanted Page
   - Purpose: Allows users to post about items they want. This could be a request for donations or an expression of interest in exchanging for items.
   - No Photo Requirement: Unlike the other pages, the wanted page does not require users to upload photos.
   - Form Details: Users provide specifics including a description, the desired quantity, and the preferred pickup location.
   - Post Submission: The validated request is then added to Firebase, where it can be viewed by other users.

<div align="center">
    <img src="postwantedpage.png" alt="Post wanted page">
</div>

Post pages share a common theme of promoting community sharing and exchange, and we use Firebase to manage data storage and retrieval effectively.

### Search page
1. Search page

<div align="center">
    <img src="searchpage.png" alt="search page">
</div>

On this page, users can search for any food they want and choose how to get the food (i.e. donate, wanted and exchange).
When the user types in the food they want and selects any option, our app will retrieve the relevant data from the firebase database and display it.
When the user types in the food they want and selects any option, our app will retrieve the relevant data from the firebase database and display it.

2. Search results for apple

<div align="center">
    <img src="search_apple.png" alt="search results for **apple**">
</div>

When the user types apple and selects the donate type, our app will pick up the post_type as donate from firebase, and apple's post will appear in the post_title. Search results are not affected even if the user mixes upper and lower case.

<div align="center">
    <img src="search_apple_case.png" alt="search results for **APple**">
</div>

3. Search results for butter chicken

 <div align="center">
    <img src="search_butter_chicken.png" alt="search results for **butter chicken**">
</div>

When the user types butter chicken, our app will search butter chicken in our database and the results will appear.

4. Search results for I want to share some apples

<div align="center">
    <img src="search_sentence.png" alt="search results for **I want to share some apples**">
</div>

When the user types **I want to share some apples**, our app will pick up the posts which I or want or a or banana will appear in the post_title.

5. Search detailed page

<div align="center">
    <img src="search_detailed.png" alt="search detailed page">
</div>

When we click on any post under the search term, we can see the specific content of the post.
  
### Application Use Cases and or Examples

* John has a dozen of bananas, they are over ripe and going to get spoilt after two days. But he will not be able finish them on his own within two days.
  So, he is thinking of donating them rather than throwing away. 
* He heard about EATSY application and downloaded it but never used it before. He plans to use it this time. He logs in and starts the best user to donate his food.
* Upon login, he is taken to the dashboard page, where he sees several posts of people donating, exchanging and wanting different food items.
* He uses the search filter to find the people in wanted category, who is looking for bananas, especially those who are nearby (using GPS). 
* Then, he finds the 'Add' button on the bottom view of the dashboard, he clicked it and saw 
  three categories(donate, exchange and wanted) to post. He clicks on 'Donate' and is taken to the donate page where he is asked to put some details about his food item such as 
  quantity, description, location where people can get it from and so on. He successfully generates a post in the donate category and can even see it on the dashboard page. 
* He can also manage his account on profile page that has username, email and profile picture displayed. He can also change profile picture and click a sign out button to log out from the application.


*Target Users:*

* The people who want to actively contribute or participate in removing hunger and promote responsible food consumption.
* * Users can use search feature to filter the category of item they want. 
* * Users can post food item in any of the three categories(donate, exchange, wanted).
* * Users can maintain their account details, changes their profile picture and sign out from the app on profile page.
* * Users can see the location(GPS system) of the people who posted. This will allow them to find something that is close to their place or 
    they will have an idea of where to get this food from. 

*Following is the list of use cases of our application*
* Actor: User
* * Our actor will be the user since he/she will be interacting with the app in order to donate, exchange or get food. 

* Use Case 1: Login
* * The user can log into the app. 
* * Precondition: The user must have his/her account registered.
* * PostCondition: Once, the user is logged in, he/she is taken to dashboard page. 

* Use Case 2: View Posts
* * The user can have a look and go through the posts on dashboard.
* * Precondition: The user should be on dashboard.
* * PostCondition: Variety of posts can be viewed belonging to different category.

* Use Case 3: Search
* * The search catches the eye balls of the user. 
* *  Precondition: The user should be on the dashboard.
* * PostCondition: The user can search different food items based on the searched words.

* Use Case 4: Filter
* * The user can filter posts on the search page. 
* * * Precondition: The user has to be on the search page.
* * * PostCondition: The user has to click on the category which he/she wants so search for. Later, posts only for those categories are shown.
      If a users want that food item, there is a GPS system(address of who posted) on the post page that people can follow to get the food.
       

* Use Case 5: Add Post
* * The user can post his/her food item by clicking 'Add' button on the dashboard.
    On clicking the 'Add' button, the user is redirected to a different page 'AddPage', that has three options, Donate, Exchange and Wanted. 

* * * Precondition: The user has to be dashboard page.
* * * * For donate: The user has to click 'Donate' text on the AddPage and enter all the details about his food such as 
        name, quantity, location where it can be picked from and so on and  then finally post it. 
* * * * For exchange: The user has to click 'Exchange' text on the AddPage and enter all the details about his food such as
        name, quantity, location where it can be picked from and so on and  then finally post it.
* * * * For wanted: The user has to click 'Wanted' text on the AddPage and enter all the details about his food such as
        name, quantity, location where it can be picked from and so on and  then finally post it.
* * * PostCondition: The user can view his/her posts with the other posts on dashboard page or can find it through the search bar.

* Use Case 6: Profile Page
* * The users can maintain their profile page that shows their username and email. They also have option to upload their profile page.
* * * Precondition: The user has to be on the dashboard page, there is profile button on it that redirects to the profile page.
* * * Postcondition: The user can see the updated profile picture. 

* Use Case 7: Sign Out
* * Users can log out by clicking the sign out button on the profile.
* * * Precondition: The user has to be on the profile page.
* * * PostCondition: The user is redirected back to the login page and stops all the activities running behind.


<hr> 

### Application UML

![img_4.png](uml_draft.png)
<hr>

## Code Design and Decisions

### **Tokenizer and parser**

### **Parser**

**Grammar**

The grammar used in our project is designed to parse text consisting of space-separated words. The parser constructs a parse tree where each node represents a word and its children represent the words that follow it in the sequence.

**Advantages of the design**:

- **Simplicity**: The grammar is simple and straightforward, making it easy to implement and debug.
- **Extensibility**: While the current grammar is simple, it can be extended to include more complex features such as handling different types of tokens or incorporating operator precedence without a complete overhaul.

**Production Rules**:

- **Expression**::= Term { " " Term }
- **Term**::= Word

**Term** represents individual words, and **Expression** represents sequences of these words. An expression consists of one or more terms separated by spaces.

**Usage**:

- **Tokenizer**: The tokenizer in my project breaks down the input string into tokens based on spaces. Each token is either a word or a space. This tokenizer is utilized in the **matchToken**function within **Search** class to preprocess the input for parsing.
- **Parser**: The parser is used to construct a parse tree from the sequence of tokens generated by the tokenizer. It processes the tokens to build a hierarchical structure that represents the sequence in which words appear in the input.

**Construction**: 

- **Tokenizer**: It is implemented using simple string operations. The input string is split using the space character as a delimiter, generating an array of words which are then individually wrapped as **Token**

- **Parser**: The parser is implemented as a recursive descent parser. It starts by creating a node for the first token and then recursively processes the following tokens to build the tree. The recursive nature of the parser allows it to easily handle nested or sequential structures typical in language constructs.

**Advantages of the designs**:

- **Efficiency**: The tokenizer is efficient as it leverages built-in string methods which are optimized for performance.
- **Flexibility**: The parser is designed to be flexible and can be easily adjusted or extended to support more complex grammatical structures if needed.
- **Modularity**: The separation of tokenizing and parsing functions enhances modularity. This makes the code easier to manage and test, as each component can be developed and debuged independently.

**Scalability**: The parser uses recursive methods, making it scalable for extending the grammar without significantly altering the existing codebase. This is beneficial for maintaining and upgrading the system in the future.


### Data Structures


*I used the following data structures in my project:*
1. **HashMap**

- Objective: Used for storing and quickly accessing `Post` objects by their IDs within the `StorageList` class.
- Code Location: Defined in `StorageList` class. Utilized in methods such as `initPostData` and `initLocalData` to store and access `Post` instances.
- Reasons:
- Efficiency: `HashMap` offers O(1) time complexity for insertions and lookups, which is more efficient than an `ArrayList` for these operations.
- Key-value Access: For features like updating or retrieving `Post` data, direct access via post IDs (keys) is essential, eliminating the need for indexing which is crucial for performance.

2. **ArrayList**

- Objective: Used for storing lists of `Post` objects in a dynamically resizing array format, suitable for ordered collection which also supports random access.
- Code Locations: Utilized in `StorageList` and `Search` classes, particularly in methods like `initPostData` and `searchAll` for storing and managing collections of posts.
- Reasons:
- Random Access: Unlike `LinkedList`, `ArrayList` provides efficient random access to elements, which is beneficial where elements are frequently accessed by index.

3. **HashSet**

- Dynamics and Performance: Better performance in terms of memory as it stores items in a contiguous memory space and is generally faster in iterating over elements compared to `LinkedList`.
- Objective: Used in the `Search` class to ensure uniqueness and efficient lookup for `Post` objects when performing search operations.
- Code Locations: Used in the `searchByTest` method of the `Search` class to store unique results from keyword-based searches.
- Reasons:
- Uniqueness: Automatically prevents duplication of `Post` objects in search results.
- Efficiency: Offers O(1) complexity for add and check operations, ideal for scenarios where the integrity of uniqueness is more critical than ordering.

4. **AVLTree**

- Objective: Utilized to maintain a balanced search tree of `Post` objects, ensuring efficient order operations and balanced tree properties for quick search and retrieval.
- Code Locations: `StorageList` class uses `AVLTree` to manage posts in a sorted manner, particularly evident in the `buildTree` and `traverseTree` methods.
- Reasons:
- Balanced Search Operations: `AVLTree` maintains balance with rotations, providing O(log n) complexity for insertions, deletions, and searches.
- Ordering: Maintains elements in a sorted order, which is beneficial for range queries and ordered data retrieval operations.

<hr>

### Design Patterns
1. Factory – post <br>
     **Objective**: used for obtaining content from user input and convert it into a post class.<br>
     **Code Locations**: defined in [Class Post](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/Post.java?ref_type=heads),[Class factory_donate](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/post_donate.java?ref_type=heads),[Class factory_exchange](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/factory_exchange.java?ref_type=heads) and [Class factory_wanted](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/factory_wanted.java?ref_type=heads).<br>
      **Reasons**: We have three types of posts: Donate, Exchange and Wanted. Each type has specific functionalities. The factory pattern can be used to create an interface for creating instances of these different posts and uploading these posts’ details. By using this, we simplify the way to handle post creation and uploading posts.

2. Factory – render scene
    * **Objective**: The main goal is to centralize common functionalities such as setting up views, validating inputs, set up listeners and configuring scenes across different types of posts. This centralization reduced code duplication and improved maintainability.

    * **Code Locations**: Base class: [Post_base.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_base.java)
, and subclass: [Post_donate](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_donate.java)
   , [Post_exchange](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_exchange.java)
   , [Post_wanted](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/Post_wanted.java)  <br>
   * **Reasons**:  By centralizing common functionalities in the base class, there is a significant reduction in code duplication across subclasses.
 When changes are required, such as modifying how listeners are set, these can be made in one place, especially setting of post pages are very similar.
    
3. Singleton Design Pattern: 
   
    * **Objective** : We implemented singleton design pattern in the LoginUser class because there is only one instance of LoginUser required.
    This design pattern makes sure that class has only one instance and provides a global access to that instance. 
    * **Code Locations**: defined in [Class LoginUser, method getInstance, info and error](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/LoginUser.java?ref_type=heads).
    * **Reasons** : This class is used for user information and error messages that can be helpful to debug and check errors. It manages the components that require single point of access or control. 


4. Adapter Pattern<br>
**Objective:** Allows objects with incompatible interfaces to collaborate.<br>
**Code Locations:**
   - Used in the ListDataAdapter class, which adapts a list of Post objects to be usable in a ListView which expects data in a specific format.<br>
   
    **Reasons:** 
     - Interface Compatibility: Converts the interface of the List<Post> into the interface expected by the ListView, enabling seamless integration of complex data structures with UI components.
     - Reusability: Allows the same Post data to be reused in different list-based UI components without modifying the underlying data structure or the components themselves.

5. Builder Pattern<br>
**Objective:** Separates the construction of a complex object from its representation so that the same construction process can create different representations.<br>
**Code Locations:** Used in constructing complex Post objects, in scenarios where a Post object consists of various discrete parts that are assembled step-by-step.<br>
**Reasons:** Step-by-step Construction: Allows for constructing complex objects step-by-step, particularly useful when creating an object requires setting many attributes that could be optional.<br>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. 	Created an activity for login feature and used firebase authentication to help user log in. (easy)
   * Code: [Class LoginActivity,](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/LoginActivity.java?ref_type=heads#L25-74) and Class LoginUser
   * Implemented a singlton design pattern in LoginUser java class to ensure that only one instance of LoginUser required.<br>
   * Description of your implementation: We extracted current user from firebase. We connected all the credentials with xml resources. If the user clicks 
     on the login button, then we check by using 'signInWithEmailAndPassword' method from firebase whether the entered data is correct. If it's correct, the user is redirected to the DashBoardActivtiy
     and a toast message is generated i.e; "Login successful". Otherwise, the user remains on the login page and toast message says, "Authentication failed". 
   
   <br>

2. [DataFiles]. Description  ... ... (...)
   * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
   * Link to the Firebase repo: ...

3. [Search].(Medium) Created an activity for searching and filtering posts within the application, utilizing various data structures and methods to efficiently manage and display search results.
   * Code:[class Search](url)
   * Key Methods: onCreate, setCheckListener, setEditListener, searchData, searchAll, searchByTest
   * Related Classes: StorageList, ListDataAdapter, AVLTree, AVLTreeNode, Trie, TrieNode

    **Description of Implementation**

   * The Search activity is designed to process user input for searching and filtering posts based on categories like "Donate", "Need", and "Exchange". The functionality is extended by integrating Firebase Firestore to fetch real-time data, ensuring up-to-date information is always available.
   * Main Features:
   * Dynamic Filtering: Users can filter search results in real-time by selecting different categories through checkboxes. The application updates the displayed results immediately based on the selected criteria.
   * Keyword Search: Includes an input field where users can type keywords. The application parses these inputs to filter posts containing relevant information. This feature uses a custom tokenizer and parser to handle the input strings efficiently.

**Data Structure Usage**

   * HashMap: Used to store and quickly retrieve posts by unique identifiers.
   * ArrayList: Manages lists of posts for display and intermediate operations.
   * HashSet: Ensures unique search results, preventing duplicate entries in the display.
   * AVLTree: Maintains posts in a balanced manner to optimize search and retrieval operations based on sorted or ranked criteria.

**Performance Considerations**

   * The use of efficient data structures like HashMap and AVL Tree ensures that search operations are fast and responsive, even with a large dataset. The Singleton pattern in StorageList minimizes redundancy in data management, thereby improving memory usage and speed.

**Security and Data Integrity**

   * All interactions with Firebase are managed through secure authenticated sessions, ensuring that data retrieval and manipulation are protected against unauthorized access.This implementation not only fulfills the basic requirements of a search feature but also enhances user experience through quick responsiveness and accurate results. The modular approach in designing the Search class allows for easier maintenance and scalability, adapting to potential future enhancements like more complex search algorithms or additional filtering criteria.

### Custom Features
Feature Category: Firebase Integration <br>
1. [FB-Auth] Description of the feature (easy)
   * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
   * Description of your implementation: ... <br>
2. [Data-Profile] Created a ProfileActivity that displays name, email addresses profile picture of the user. It also has a signout button.(easy)
   * Code: [Class ProfileActivity, methods onActivityResult(), uploadImageToFirebase(), showing userdetails](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/pages/ProfileActivity.java?ref_type=heads).
   * Description of the code: The Json file users_without_password.json stores all the user details. This file was later read, serialized and 
     the user data is stores in terms of hashmap where is email is the key. So, email was obtained from firebase and using that as the key, we got username(value) and data was displayed.
     For profile picture, we used onclickListener for a button that opens gallery upon clicking. Then, we used a method, onActivityResult that passes uri of the image in the data and then it is replaced with the image icon.
     Then we uploaded the image to the firebase. 

Feature Category: Search-related features <br>

Feature Category: UI Design and Testing <br>
3. [UI-Layout] Incorporate suitable layout adjustments in the UI components for portrait and landscape
   layout variants, as well as different screen sizes. (easy)
    * Code: [all of xml files](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/tree/main/src/app/src/main/res/layout?ref_type=heads)
    * Description of your implementation: set appropriate constraints to all components to fit for portrait and landscape
      layout variants, and different screen sizes
4. [UI-Test] Complete UI tests using espresso (not covered in lectures/labs) of reasonable quality and
   coverage of the App. (hard)
    * Code: [all of UI Test files](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/tree/main/src/app/src/androidTest/java/com/example/eatsy?ref_type=heads)
    * Description of your implementation: UI tests are implemented using Espresso and JUnit to ensure functionality and user across various activities. 
Each test corresponds to different aspects of the app, such as adding pages, managing dashboards, user authentication and posts. 
These tests are designed for enhancing usability, and ensuring user experiences across pages.

Feature Category: Greater Data Usage, Handling and Sophistication <br>

  
5. [DataFiles] Boxuan Lin created a dataset with more than 2600 post data instances, covering all types of posts. All of the data was formatted in JSON and uploaded to Firestore for persistence.


6. [FB-Auth] (By Boxuan Lin) We use Firebase to implement the User Authentication/Authorisation of our app. User account data is stored on Google servers instead of locally, and is processed by Google, ensuring security.
7. [FB-Persist] (By Boxuan Lin) We used Firebase’s Firestore, a real-time, scalable database that stores data in collections and documents. We use it to persist the post data and user’s profile data for a well synchronization function.  For big files like photos, we store them in Cloud Storage for Firebase for its high scalability and simplified file upload and download capabilities.
Data Profile(easy):
      1. 
    2. It also gives the user an option to upload and change profile picture via media gallery to the firebase.
 3. There is sign out button that allows user to sign out from the app. 

8. [Search-Invalid] (By Lin Xi)(Medium)

      Handling Partially Valid and Invalid Search Queries
Objective: Enhance the search functionality to handle both partially valid and invalid search queries effectively without causing the application to crash, while still providing meaningful search results based on valid parts of the query.

      Subject: Partially valid and invalid search query handling.

      Description: The search feature of the application is designed to robustly handle errors in user input, ensuring that the application remains stable and responsive even when faced with partially valid or invalid search queries. The feature aims to parse user inputs and extract usable information to return the best possible results.

      What the feature entails:

      Modifying the Tokenizer/Parser: The application's tokenizer and parser have been enhanced to more effectively identify and separate valid elements from invalid inputs. This allows the application to process and respond to mixed-quality queries by ignoring or flagging invalid tokens while utilizing valid tokens for search operations.

      Error Handling Mechanisms: Implementations include try-catch blocks, input validation, and error logging to manage unexpected or malformed inputs gracefully.

      Feedback to Users: When invalid inputs are detected, the application provides feedback to the user, suggesting corrections or clarifying what parts of the query were processed.

      Feature Relevance: Tokenization and parsing are crucial for dissecting user input into manageable components that the system can understand and process, which is central to this feature.
      Code:[Search.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/Search.java?ref_type=heads)
9. [Search-Filter](By Lin Xi)(Medium): Sorting and Filtering Search Results
Objective: Implement functionality to sort and filter the list of items returned from searches, utilizing suitable UI components to allow users to refine their search results dynamically.

      Subject: Advanced sorting and filtering of search results.

      Description: This feature allows users to sort and filter search results based on various criteria such as date, relevance, type, etc. The implementation includes UI components that users interact with, such as dropdowns, checkboxes, and sliders, to adjust the filtering parameters dynamically.

      What the feature entails:

      Dynamic UI Components: Implement dropdown menus for sorting (e.g., ascending, descending), checkboxes for filtering specific types of posts (e.g., donations, requests), and sliders for range selections (e.g., date ranges, quantities).

      Backend Logic: Enhance the search mechanism to respond to these filters, sorting the internal data structures like ArrayLists and updating the display according to user preferences.

      Live Updates: The search results update in real-time as users adjust the filters, providing an interactive and responsive user experience.

      Feature Relevance: The ability to sort and filter enhances the usability of the search function, allowing users to more easily navigate large sets of data and find the items that best match their needs.
      Code:[Search.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/searchengine/Search.java?ref_type=heads)

### Surprise Features
1. On April 15, MainActivity2(for login) and MainActivity3(for register) were created, https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/f98af4945b0061f49a4ca2acbc6d33d979ff08a2, which were later changed to LoginActivity and RegisterActivity respectively.
   On April 17, MainActivity4(dashboard) was created and its name was changed to DashBoardActivity, https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/662671f382bf528651adb1e24d2254c0706f9e2e. 
   Earlier, the names were non-descriptive and didn't give the idea of the purpose of these activities. There was a lack of 
   clarity especially when the project was growing further. Thus, by refactoring their names, they became more descriptive. They convey the purpose of each activity like LoginActivity allows the user to login.
   DashBoardActivity manages dashboard interface. The codebase becomes easier to navigate. Also, giving meaningful names is a fundamental thing to write a clean code, enhance readability and success of the project. 
2. On April 22 and 27, three post pages were completed, as documented in Git commits SHA 1c750dd020486de45f2d71d651badedf155ed79c and 7ff755054cffd14a9f85094c56854b77540ebf31.
 The layout of these three posts was very similar, especially the donate and exchange pages, including nearly identical scenes, UI components and listeners.
 This resulted in a significant amount of code duplication. Subsequently, on May 1st, Git commit SHA ea0f48b72edbbfc472b0a74f6aa94d1ccebdc8ac added [Post_base.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/main/java/com/example/eatsy/Post_base.java?ref_type=heads),
which integrates the methods in these pages into a single base class. This refactoring improved the code by reducing redundancy and improving maintainability.
3. On April 29th,  the creation and modifications of the [DataDownloadCallback.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/3c90074ad8ad1a090a05025a851c3c17d77c6d7b/src/app/src/main/java/com/example/eatsy/DataDownloadCallback.java) , [UserDataDownloader.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/3c90074ad8ad1a090a05025a851c3c17d77c6d7b/src/app/src/main/java/com/example/eatsy/UserDataDownloader.java), [userFT.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/3c90074ad8ad1a090a05025a851c3c17d77c6d7b/src/app/src/main/java/com/example/eatsy/userFT.java), and [MyApplication.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/3c90074ad8ad1a090a05025a851c3c17d77c6d7b/src/app/src/main/java/com/example/eatsy/MyApplication.java) classes had completed. These changes were intended to fetch user data from Firebase during app initialization to load profiles and other information and they operated correctly. However, during a review after the lecture on May 1st, we discovered several code smells in these classes. <br>
(1) The usage of traditional callback methods (DataDownloadCallback<T>) DataDownloadCallback.java in makes the code for asynchronous tasks complex. In this code, we used a callback interface, DataDownloadCallback<T>, to manage actions after receiving data from Firebase asynchronously. This required explicit calls and rewriting, reducing readability. Additionally, chaining this method for retrieving post data led to callback hell, making the code harder to maintain and understand. <br>
(2) The UserDataDownloader class was highly coupled with the Firebase API and deeply integrated with specific data downloading logic. This meant that any changes to the Firebase API required modifications to the UserDataDownloader class. Additionally, fetching post data later required constructing another similar class, leading to code duplication. <br>
 Therefore, we began multiple refactoring efforts on the relevant code to fix these code smells: <br>
 (1) On May 1st, we adopted a more modern callback mechanism, CompletableFuture, with methods like thenAccept and exceptionally in [MyApplication.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/68a504693906148dbf87f88deeb6b40d58cc7dc0#732a56e6a329c71bac172a37e853851bbf6ecd9d) and [UserDataDownloader.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/68a504693906148dbf87f88deeb6b40d58cc7dc0#5692e99c4b400838bbf272d772f961894c071c5e). The exceptionally method provided a structured way to handle exceptions directly in the promise chain. For example, if the task was successful, we completed the future with userHashMap; otherwise, we handled errors inside the method with future.completeExceptionally(task.getException()). This approach allowed for further improvements and eliminated the need to define a separate callback interface. In MyApplication, using methods like thenAccept enabled us to handle the results of asynchronous operations coherently.   <br>
(2) After continuous refactoring, by May 14th, almost all code smells were resolved. Class abstraction and functional decoupling were well-executed, and an option to read JSON was added for offline scenarios. (See [UserDataDownloader.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/b9f38475eedc1fb9bae0ed4e30179fda3849933b#f22789495740ae703f554a767f864e8b85d7ce4a) and [DataDownloader.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/commit/b9f38475eedc1fb9bae0ed4e30179fda3849933b#9e60553f9053ed958b1caabf65ebc7d9ed519376)). <br>

   
- <br> <hr>


## Summary of Known Errors and Bugs

1. *Bug 1:*
   - Error encountered in all pages containing the method 'FirebaseAuth.getInstance().getCurrentUser().getEmail()' during UI tests. This issue arises because the 'getCurrentUser()' method returns null when pages are run individually in testing.
Despite I attempted to resolve this using Mockito and Mockito-inline, the problem persists.
     <br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

2. Tests for Singleton Design Pattern 
   - Code: [SingletonTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/test/java/com/example/eatsy/SingletonTest.java?ref_type=heads#L14-55).
   - *Number of test cases: 3*
   - Code coverage: Login feature of the app.
   - Test for same instance, 

3. Tests for factory design pattern
   - Code: [FactoryTest.java, entire file](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/test/java/com/example/eatsy/FactoryTest.java?ref_type=heads).
   - Number of test cases: 2
   - Code coverage: Coverage of all possible paths and conditions in the factory methods
   - Types of tests created and descriptions: The tests primarily focus on object construction correctness, ensuring that the factory methods create objects based on input parameters.
4. Tests for UI design(Post pages)
   - Code: [PostdonateUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/PostdonateUITest.java?ref_type=heads),
[PostexchangeUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/PostexchangeUITest.java?ref_type=heads), 
[PostwantedUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/PostwantedUITest.java?ref_type=heads),
   [AddPageUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/AddPageUITest.java?ref_type=heads),
   [DashBoardActivityUITests.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/DashBoardActivityUITests.java?ref_type=heads),
   [LoginActivityUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/LoginActivityUITest.java?ref_type=heads),
   [MainActivityUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/MainActivityUITest.java?ref_type=heads) and
   [MapSelectionUITest.java](https://gitlab.cecs.anu.edu.au/u7705128/gp-24s1/-/blob/main/src/app/src/androidTest/java/com/example/eatsy/MapSelectionUITest.java?ref_type=heads).
   - Number of test cases: 30
   - Code coverage: Tests interactions with UI components
   - Types of tests created and descriptions: 
     - Visibility and changes of UI components. 
     - Intent firing and activity lifecycle management. 
     - Input validations and response actions.
     <br> <hr>


## Team Management

### Meetings Records
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each commited within 2 days aftre the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- [Team Meeting 1](Meeting-No1.md)
- [Team Meeting 2](Meeting-No2.md)
- [Team Meeting 3](Meeting-No3.md)
- [Team Meeting 4](Meeting-No4.md)
- [Team Meeting 5](Meeting-No5.md)
- [Team Meeting 6](Meeting-No6.md)

<hr>

### Conflict Resolution Protocol
1. Form a culture of openness and transparency, encouraging team members to voice their thought.
2. Out team votes on the ideas. When conflicts happens, we cast a vote. Then the idea with the majority vote is selected.
3. Holding regular Saturday meetings to keep everyone aligned and address any occurred issues or adjustments needed in future plans.
4. When it comes to managing conflicts in code, we review the conflicting codes together and decide on the best resolution.
