package com.example.android.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editText = findViewById(R.id.editName);
    }

    public void onClickSave(View view) {
        String name = editText.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            editText.setError("Type name");
            return;
        }

        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        String userId = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users").document(userId).set(map)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                        finish();
                    }else {
                        Toast.makeText(ProfileActivity.this,"Not successful",Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
