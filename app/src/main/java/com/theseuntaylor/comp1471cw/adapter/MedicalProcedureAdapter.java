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
import com.theseuntaylor.comp1471cw.model.MedicalProcedureType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MedicalProcedureAdapter extends RecyclerView.Adapter<MedicalProcedureAdapter.MedicalProcedureViewHolder> {

    List<MedicalProcedureType> allMedicalProcedures;

    ItemClickListener itemClickListener;

    public MedicalProcedureAdapter(ArrayList<MedicalProcedureType> allMedicalProcedures, ItemClickListener itemClickListener) {
        this.allMedicalProcedures = allMedicalProcedures;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicalProcedureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_procedure, parent, false);
        return new MedicalProcedureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalProcedureViewHolder holder, int position) {
        populateRecyclerView(holder, position);
        handleOnClick(holder, position);
    }

    private void handleOnClick(MedicalProcedureViewHolder holder, int position) {
        holder.deleteMedicalProcedure.setOnClickListener(view -> itemClickListener.delete(position));
        holder.editMedicalProcedure.setOnClickListener(view -> itemClickListener.edit(position));
        holder.medicalProcedureName.setOnClickListener(view -> itemClickListener.view(position));
    }

    private void populateRecyclerView(@NotNull MedicalProcedureViewHolder holder, int position) {
        if (!allMedicalProcedures.isEmpty()) {
            MedicalProcedureType item = allMedicalProcedures.get(position);
            String medicalProcedureName = item.getMedicalProcedureName();

            holder.medicalProcedureName.setText(medicalProcedureName);
        }
    }

    @Override
    public int getItemCount() {
        return allMedicalProcedures.size();
    }

    public static class MedicalProcedureViewHolder extends RecyclerView.ViewHolder {
        public TextView medicalProcedureName;
        public ImageButton editMedicalProcedure, deleteMedicalProcedure;

        public MedicalProcedureViewHolder(@NonNull View itemView) {
            super(itemView);
            medicalProcedureName = itemView.findViewById(R.id.item_medicalProcedure);
            editMedicalProcedure = itemView.findViewById(R.id.item_editMedicalProcedureButton);
            deleteMedicalProcedure = itemView.findViewById(R.id.item_deleteMedicalProcedureButton);
        }
    }
}