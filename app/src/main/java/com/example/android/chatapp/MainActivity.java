package com.example.android.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.chatapp.Chat.ChatActivity;
import com.example.android.chatapp.Contacts.ContactsActivity;
import com.example.android.chatapp.Contacts.ContactsAdapter;
import com.example.android.chatapp.Models.Chat;
import com.example.android.chatapp.Models.User;
import com.example.android.chatapp.interfaces.OnItemClickListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public ChatAdapter chatAdapter;
    private List<Chat> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(this,PhoneActivity.class));
        }
        recyclerView = findViewById(R.id.recycleView);
        initList();
        getChats();
    }

    public void onClickContacts(View view) {
        startActivity(new Intent(this, ContactsActivity.class));
    }

    private void initList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        chatAdapter = new ChatAdapter(this,list);
        recyclerView.setAdapter(chatAdapter);
        chatAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
              //  Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
               // intent.putExtra("user",list.get(position));
               // startActivity(intent);
            }
        });
    }

    private void getChats(){
        String myUserId = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("chats")
                .whereArrayContains("userIds",myUserId)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange change : snapshots.getDocumentChanges()){
                            switch (change.getType()){
                                case ADDED:
                                    Chat chat = change.getDocument().toObject(Chat.class);
                                        chat.setId(change.getDocument().getId());
                                        list.add(chat);
                                        break;
                            }
                        }
                        chatAdapter.notifyDataSetChanged();
                    }
                });
               /* .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot snapshots) {
                        for (DocumentSnapshot snapshot : snapshots) {
                            Chat chat = snapshot.toObject(Chat.class);
                            if (chat != null){
                            chat.setId(snapshot.getId());
                            list.add(chat);
                        }
                    }
                    chatAdapter.notifyDataSetChanged();
                }
         });*/
    }
}

