package com.example.android.chatapp.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.chatapp.Chat.ChatActivity;
import com.example.android.chatapp.Models.User;
import com.example.android.chatapp.R;
import com.example.android.chatapp.interfaces.OnItemClickListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        recyclerView = findViewById(R.id.recycleView);
        initList();
        getContacts();
    }

    private void getContacts() {
        FirebaseFirestore.getInstance().collection("users")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot snapshots) {
                        for(DocumentSnapshot snapshot : snapshots){
                            User user = snapshot.toObject(User.class);
                            if (user != null){
                                user.setId(snapshot.getId());
                            }
                            list.add(user);
                        }
                        contactsAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void initList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        contactsAdapter = new ContactsAdapter(this,list);
        recyclerView.setAdapter(contactsAdapter);
        contactsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
                intent.putExtra("user",list.get(position));
                startActivity(intent);
            }
        });
    }
}
