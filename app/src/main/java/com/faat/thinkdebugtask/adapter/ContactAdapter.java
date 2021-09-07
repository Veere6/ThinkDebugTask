package com.faat.thinkdebugtask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faat.thinkdebugtask.ContactDetailsActivity;
import com.faat.thinkdebugtask.R;
import com.faat.thinkdebugtask.model.ContactModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    List<ContactModel> contactModelList;
    Context context;

    public ContactAdapter(List<ContactModel> contactModelList, Context context) {
        this.contactModelList = contactModelList;
        this.context = context;
    }


    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);
        return new ContactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        ContactModel bean=contactModelList.get(position);
        String name=bean.getName();
        String[] nameparts = name.split(" ");
        String sort = ""+nameparts[0].charAt(0)
//                +nameparts[1].charAt(0)
                ;
        holder.shortname.setText(sort);
        holder.name.setText(bean.getName());
        holder.number.setText(bean.getMobile());

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, number, shortname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            shortname = itemView.findViewById(R.id.shortname);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ContactDetailsActivity.class);
                    intent.putExtra("id",contactModelList.get(getAdapterPosition()).getId());
                    intent.putExtra("name",contactModelList.get(getAdapterPosition()).getName());
                    intent.putExtra("email",contactModelList.get(getAdapterPosition()).getEmail());
                    intent.putExtra("address",contactModelList.get(getAdapterPosition()).getAddress());
                    intent.putExtra("gender",contactModelList.get(getAdapterPosition()).getGender());
                    intent.putExtra("mobile",contactModelList.get(getAdapterPosition()).getMobile());
                    intent.putExtra("home",contactModelList.get(getAdapterPosition()).getHome());
                    intent.putExtra("office",contactModelList.get(getAdapterPosition()).getOffice());
                    context.startActivity(intent);
                }
            });
        }
    }
}
