package com.demo.newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
  private ArrayList<CategoryRVModel> categoryRVModels;
  private Context context;
  private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(ArrayList<CategoryRVModel> categoryRVModels, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVModels = categoryRVModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv,parent,false);
        return  new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
         CategoryRVModel categoryRVModel= categoryRVModels.get(position);
         holder.txt.setText(categoryRVModel.getCategory());
        Picasso.get().load(categoryRVModel.getCategoryImageUrl()).into(holder.img);
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        categoryClickInterface.onCategoryClick(position);
    }
});
    }

    @Override
    public int getItemCount() {
        return categoryRVModels.size();
    }


    public  interface CategoryClickInterface{
        void onCategoryClick(int positon);

    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txt;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt =itemView.findViewById(R.id.txt);
            img =itemView.findViewById(R.id.img);
        }
    }
}
