package com.example.eatsy;

import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class post_exchange extends post_base {
//    private static final int PICK_IMAGE_REQUEST = 1;
//    private ImageButton uploadImage;
//    private Uri filePath;
//    private StorageReference storageReference;
//    private RadioGroup radioGroup;
//    private EditText quantityEditText;
//    private EditText titleEditText;
//    private EditText descriptionEditText;
//    private EditText pickupTimeEditText;
//    private EditText addressEditText;
//    private EditText WantEditText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post_exchange);
//        FirebaseApp.initializeApp(this);
//
//        // Set up Firebase App Check with the Play Integrity provider
//        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance());
//        storageReference = FirebaseStorage.getInstance().getReference();
//        setContentView(R.layout.activity_post_donate);
//        ImageButton go_back = findViewById(R.id.leftArrowButton);
//        radioGroup = findViewById(R.id.radioGroupQuantity);
//        quantityEditText = findViewById(R.id.editTextOther);
//        titleEditText = findViewById(R.id.title_editText);
//        descriptionEditText = findViewById(R.id.description_editText);
//        pickupTimeEditText = findViewById(R.id.pickuptime_editText);
//        addressEditText = findViewById(R.id.Address_editText);
//        WantEditText = findViewById(R.id.want_editText);
//        go_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        Button post = findViewById(R.id.button_post);
//        post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_post_exchange;
    }
    @Override
    protected void setupSpecificViews() {
        pickupTimeEditText = findViewById(R.id.pickuptime_editText);
        uploadImage = findViewById(R.id.cameraButton);
        wantEditText = findViewById(R.id.want_editText);
    }
    @Override
    protected boolean validateInputs() {
        boolean valid = true;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(selectedId == R.id.radioButtonOther){
            if (quantityEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a quantity or select a button", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }
        if(wantEditText.getText().toString().trim().isEmpty()){
            wantEditText.setError("Required");
            valid = false;
        }
        if (titleEditText.getText().toString().trim().isEmpty()) {
            titleEditText.setError("Required");
            valid = false;
        }

        if (pickupTimeEditText.getText().toString().trim().isEmpty()) {
            pickupTimeEditText.setError("Required");
            valid = false;
        }

        if (addressEditText.getText().toString().trim().isEmpty()) {
            addressEditText.setError("Required");
            valid = false;
        }

        if (filePath == null) {
            Toast.makeText(this, "Please upload an image", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
    protected void addPostToFirbase(){
        String quantity;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == R.id.radioButtonOther){
            quantity = quantityEditText.getText().toString().trim();
        }else{
            RadioButton radioButton = findViewById(selectedId);
            quantity = radioButton.getText().toString();
        }
        String title = titleEditText.getText().toString().trim();
        String description = "";
        if (null != descriptionEditText) {
            description = descriptionEditText.getText().toString().trim();
        }
        String image = filePath.toString();
        String pick_up_times = pickupTimeEditText.getText().toString().trim();
        String want = wantEditText.getText().toString().trim();
        //TODO revise these four string
//        String userName = "someUserName";
        String userName = getIntent().getStringExtra("USERNAME");
        String latitude = "111";
        String longtitude = "222";
        StorageReference ref = storageReference.child("user_post_img/" + UUID.randomUUID().toString());
        factory_exchange post = new factory_exchange(userName, title, description,want, quantity,
                                        pick_up_times,latitude, longtitude, image,filePath,ref);
        post.saveToFirebase();
    }

}