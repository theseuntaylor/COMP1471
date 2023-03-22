package com.theseuntaylor.comp1471cw.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theseuntaylor.comp1471cw.ItemClickListener;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.TraysModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrayAdapter extends RecyclerView.Adapter<TrayAdapter.TrayViewHolder> {

    List<TraysModel> allTrays;

    ItemClickListener itemClickListener;

    public TrayAdapter(ArrayList<TraysModel> allTrays, ItemClickListener itemClickListener) {
        this.allTrays = allTrays;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tray_information, parent, false);
        return new TrayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrayViewHolder holder, int position) {
        populateRecyclerView(holder, position);
        handleOnClick(holder, position);
    }

    private void handleOnClick(TrayViewHolder holder, int position) {
        holder.deleteTray.setOnClickListener(view -> itemClickListener.deleteTray(position));
        holder.editTray.setOnClickListener(view -> itemClickListener.editTray(position));
    }

    private void populateRecyclerView(@NotNull TrayViewHolder holder, int position) {
        if (!allTrays.isEmpty()) {
            TraysModel item = allTrays.get(position);
            String trayName = item.getName();
            String trayStatus = item.getTraystatus();

            holder.trayName.setText(trayName);
            holder.trayStatus.setText(trayStatus);
        }
    }

    @Override
    public int getItemCount() {
        return allTrays.size();
    }

    public static class TrayViewHolder extends RecyclerView.ViewHolder {
        public TextView trayName, trayStatus;
        public ImageButton editTray, deleteTray;

        public TrayViewHolder(@NonNull View itemView) {
            super(itemView);
            trayName = itemView.findViewById(R.id.item_trayName);
            trayStatus = itemView.findViewById(R.id.item_status);
            editTray = itemView.findViewById(R.id.item_editTrayButton);
            deleteTray = itemView.findViewById(R.id.item_deleteTrayButton);
        }
    }
}