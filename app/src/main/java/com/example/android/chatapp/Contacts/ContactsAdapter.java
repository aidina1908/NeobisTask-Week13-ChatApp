package com.example.android.chatapp.Contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.chatapp.Models.User;
import com.example.android.chatapp.R;
import com.example.android.chatapp.interfaces.OnItemClickListener;

import java.util.List;

public class ContactsAdapter  extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

        private List <com.example.android.chatapp.Models.User> list;
        private LayoutInflater inflater;
        private OnItemClickListener onItemClickListener;

        public ContactsAdapter(Context context, List<User> list){
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_user, parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(list.get(position));
                    
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                });
            }

            public void bind(com.example.android.chatapp.Models.User user) {
                textView.setText(user.getName());
            }
        }
}
