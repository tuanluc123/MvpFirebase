package com.maituanluc.basic1.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maituanluc.basic1.R;
import com.maituanluc.basic1.data.model.User;
import com.maituanluc.basic1.ui.main.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> ds;
    Context context;
    FirebaseFirestore db;

    TextView txtTitleAdd;
    EditText etIdUser, etNameUser, etPhoneUser, etGenderUser;
    HandlerData mHandlerData;

//    public HandlerData getmHandlerData() {
//        return mHandlerData;
//    }
//
//    public void setmHandlerData(HandlerData mHandlerData) {
//        this.mHandlerData = mHandlerData;
//    }

    public interface HandlerData {
        void updateItem(int position);

        void removeItem(int position);

    }

    public UserAdapter(List<User> ds, Context context, HandlerData mHandlerData) {
        this.ds = ds;
        this.context = context;
        this.mHandlerData = mHandlerData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = ds.get(position);
        holder.etIdUser.setText(user.getId());
        holder.etNameUser.setText(user.getName());
        holder.etPhone.setText(user.getPhone());
        holder.etGender.setText(user.getGender());
        holder.etDocument.setText(user.getDocument());
        holder.btnDeleteUser.setOnClickListener(view -> {
            if (mHandlerData != null) mHandlerData.removeItem(position);
        });

        holder.btnUpdateUser.setOnClickListener(view -> {
            if (mHandlerData != null) mHandlerData.updateItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();//Danh sách nếu null thì sẽ bị error(Đã xử lý)
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView etIdUser, etNameUser, etPhone, etGender, etDocument;
        Button btnUpdateUser, btnDeleteUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etIdUser = itemView.findViewById(R.id.etIdUser);
            etNameUser = itemView.findViewById(R.id.etNameUser);
            etPhone = itemView.findViewById(R.id.etPhone);
            etGender = itemView.findViewById(R.id.etGender);
            etDocument = itemView.findViewById(R.id.etDocument);
            btnUpdateUser = itemView.findViewById(R.id.btnUpdateUser);
            btnDeleteUser = itemView.findViewById(R.id.btnDeleteUser);
        }
    }
}
