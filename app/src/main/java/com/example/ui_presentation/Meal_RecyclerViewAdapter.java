package com.example.ui_presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Meal_RecyclerViewAdapter  extends RecyclerView.Adapter<Meal_RecyclerViewAdapter.MyViewHolder> {
   private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<MealModel> mealModelArrayList;

   public Meal_RecyclerViewAdapter(Context context, ArrayList<MealModel> mealModelArrayList
   ,RecyclerViewInterface recyclerViewInterface){
        this.context=context;
        this.mealModelArrayList=mealModelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
   }



    @NonNull
    @Override
    public Meal_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view =    inflater.inflate(R.layout.meal_item_list,parent,false);

        return new  Meal_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Meal_RecyclerViewAdapter.MyViewHolder holder, int position) {
            holder.meal_name.setText(mealModelArrayList.get(position).getMeal_Name());
            holder.meal_desc.setText(mealModelArrayList.get(position).getMeal_Desc());
            holder.imageView.setImageResource(mealModelArrayList.get(position).getImage());
   }

    @Override
    public int getItemCount() {
        return mealModelArrayList.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView meal_name, meal_desc;
       ImageView imageView;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            meal_name = itemView.findViewById(R.id.meal_name);
            meal_desc = itemView.findViewById(R.id.meal_desc);
            imageView = itemView.findViewById(R.id.meal_img);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface !=null ){
                            int pos  = getAdapterPosition();
                            if (pos!=RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos);
                            }
                    }
                }
            });
        }
    }
}
