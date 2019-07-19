package com.lco.lcoproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lco.lcoproject.Model.User;
import com.lco.lcoproject.R;
import com.lco.lcoproject.ViewDetailsUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> fullNameList;
    ArrayList<String> userNameList;
    ArrayList<String> profilePicList;
    List<User> listsearch;

    URL url;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView full_name, user_phone;

        public SearchViewHolder(View itemView) {
            super(itemView);
           profileImage =  itemView.findViewById(R.id.profileImage);
            full_name =  itemView.findViewById(R.id.full_name);
            user_phone =  itemView.findViewById(R.id.user_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user=listsearch.get(getAdapterPosition());
                    Intent i=new Intent(context, ViewDetailsUser.class);
                    i.putExtra("id",user.getUser_id());
                    i.putExtra("email",user.getEmail());
                    i.putExtra("name",user.getName());
                    i.putExtra("phoneno",user.getPhone());
                    i.putExtra("city",user.getCity());
                    i.putExtra("address",user.getAddr());
                    //i.putExtra("other",user.getOther());
                    context.startActivity(i);
                }
            });
        }
    }

    public SearchAdapter(Context context, ArrayList<String> fullNameList, ArrayList<String> userNameList, ArrayList<String> profilePicList) {
        this.context = context;
        this.fullNameList = fullNameList;
        this.userNameList = userNameList;
        this.profilePicList = profilePicList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.full_name.setText(fullNameList.get(position));
        holder.user_phone.setText(userNameList.get(position));
//        RequestOptions requestOptions = new RequestOptions();
//
//        //  Glide.with(context).load(profilePicList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.profileImage);
//        Glide.with(context)
//                .setDefaultRequestOptions(requestOptions)
//                .asBitmap()
//                .placeholder(R.mipmap.ic_launcher_round)
//                .into(holder.profileImage);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .asBitmap()
                .load(url).into(holder.profileImage);

        holder.full_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fullNameList.size();
    }
}