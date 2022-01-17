package com.example.inventory.ui.dependency;

import android.graphics.Color;
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
import com.example.inventory.data.model.DependencyComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder>{
    private ArrayList<Dependency> list;
    private OnManageDependencyListener listener;

    /**
     * Esta interfaz debe implementarla aquellas clases que quieran escuchar los evento que ocurren en la vista:
     * EDITAR
     * ELIMINAR
     * de ahi viene el nombre manage
     */
    public interface OnManageDependencyListener{
        //Si se hace click en una dependencia se edita (onClickListener)
        void onEditDependency(Dependency dependency);
        //Si se hace click largo en una dependencia se elimina (onLongClickListener)
        void onDeleteDependency(Dependency dependency);
    }

    /**
     * En el constructor por parte de l vista se debe indicar
     * que datos tengo
     * quien es tu listener
     */
    public DependencyAdapter(ArrayList<Dependency> list ,OnManageDependencyListener listener) {
        this.list = list;
        this.listener = listener;
    }

    /**
     * Se llama a este metodo tantas veces como elementos se visualicen en la pantalla del dispositivo SIEMPRE Y CUANDO
     * getItemCount() > 0
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DependencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {

        //Colores del tema material design
        ColorGenerator generator = ColorGenerator.MATERIAL;
        //Generar color aleatorio
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getName().substring(0,1), color);
        holder.icon.setImageDrawable(drawable);
        //holder.tvName.setText(list.get(position));
        //Cuando se actualiza la vista se indiica a la clase ViewHolder que dependencia es y quien es su listener
        holder.bind(list.get(position),listener);
        holder.tvName.setText(list.get(position).getName());
    }

    /**
     * Este metodo devuelve el numero de elementos del adapter y es utilizado por el sistema operativo cuando se
     * incializar el componentente Recycler View y si se deja a 0, NUNCA se muestran los elemementos del adapter
     * en el recycler ya que no se llama al metodo onCreateViewHolder
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            icon = itemView.findViewById(R.id.icon);
        }

        /**
         * Todos los metodos que se crean en la clase ViewHolder tienen acceso al elemento gracias al super(itemView)
         * @param dependency
         * @param listener
         */
        public void bind(Dependency dependency, OnManageDependencyListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditDependency(dependency);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteDependency(dependency);

                    //IMPORTANTE
                    //Se indica que consume el evento, y se evita la propagacion del evento en otras vistaas
                    return true;
                }
            });
        }
    }

    //region metodos que hay que implementar para actualizar la vista


    //Se debe llamar al metodo notifyDaraSetChanged para que.
    //1.- Anula la vista
    //2.- LLama al metodo onDraw() de todos los elementos de la nueva vista
    public void update(List<Dependency> list) {
        this.list.clear();
        this.list.addAll(list);

        // OJOOOOOOOO PARA EL EXAMEN ESTO HAY QUE HACERLO SI O SI
        notifyDataSetChanged();

    }

    public void delete(Dependency deleted) {
        list.remove(deleted);
        notifyDataSetChanged();
    }
    //endregion

    /**
     * Ordena la lista segun el metodo compareTo que esta en el PojoDependency
     */
    public void order() {
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void inverseOrder() {
        Collections.reverse(list);
        notifyDataSetChanged();
    }

    /**
     * Ordenar la vista en base a un objeto de una clase que implemente la interfaz Comparator
     */
    public void orderByDescription() {
        Collections.sort(list,new DependencyComparator());
        notifyDataSetChanged();
    }

    public void undo(Dependency deleted){
        list.add(deleted);
        notifyDataSetChanged();
    }
}
