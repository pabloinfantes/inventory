package com.example.inventory.ui.sections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.dependency.DependencyAdapter;

import java.util.ArrayList;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private ArrayList<Section> list;
    private OnManageSectionListener listener;

    public SectionAdapter(ArrayList<Section> list, OnManageSectionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public interface OnManageSectionListener{
        void onEditSection(Section section);
        void onDeleteSection(Section section);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getName().substring(0,1), color);
        holder.icon.setImageDrawable(drawable);

        holder.bind(list.get(position),listener);
        holder.tvNameSection.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameSection;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameSection =itemView.findViewById(R.id.tvNameSection);
            icon = itemView.findViewById(R.id.iconSection);
        }

        public void bind(Section section, OnManageSectionListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditSection(section);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteSection(section);
                    return true;
                }
            });
        }
    }
}
