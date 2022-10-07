package com.blackbirds.radiobuttoninrecyclerview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;

import com.blackbirds.radiobuttoninrecyclerview.Interface.IRecyclerItemClickListener;
import com.blackbirds.radiobuttoninrecyclerview.Model.UserResponse;
import com.blackbirds.radiobuttoninrecyclerview.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    Context context;
    List<UserResponse> userResponseList;
    private int lastCheckedPosition = -1;
    private IRecyclerItemClickListener iRecyclerItemClickListener;

    public UserListAdapter(Context context, List<UserResponse> userResponseList, IRecyclerItemClickListener iRecyclerItemClickListener) {
        this.context = context;
        this.userResponseList = userResponseList;
        this.iRecyclerItemClickListener = iRecyclerItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.radio_button_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.btnRadio.setText(userResponseList.get(position).getName());
        holder.btnRadio.setChecked(position == lastCheckedPosition);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatRadioButton btnRadio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            btnRadio = itemView.findViewById(R.id.btnRadio);
            btnRadio.setOnClickListener(view -> {
                int copyOfLastCheckedPosition = lastCheckedPosition;
                lastCheckedPosition = getAdapterPosition();
                notifyItemChanged(copyOfLastCheckedPosition);
                notifyItemChanged(lastCheckedPosition);
                iRecyclerItemClickListener.onItemClickListener(view, lastCheckedPosition);
            });
        }
    }
}
