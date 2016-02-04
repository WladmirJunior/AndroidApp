package com.wlad.minutrade.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlad.minutrade.R;
import com.wlad.minutrade.model.Client;

import java.util.List;

/**
 * Created by Wlad on 04/02/2016.
 */
public class ClientViewAdapter extends RecyclerView.Adapter<ClientViewAdapter.ClientViewHolder> {

    private List<Client> clientList;
    private LayoutInflater layoutInflater;

    public ClientViewAdapter(Context context, List<Client> clientList) {

        this.clientList = clientList;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.client_list, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {

            holder.cpf.setText(""+clientList.get(position).getCPF());
            holder.name.setText(clientList.get(position).getName());
            holder.email.setText(clientList.get(position).getEmail());
            holder.address.setText(clientList.get(position).getAddress());
            holder.number1.setText(clientList.get(position).getNumber1());
            holder.number2.setText(clientList.get(position).getNumber2());
            holder.status.setText(clientList.get(position).getMaritalStatus());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder{

        public TextView cpf;
        public TextView name;
        public TextView email;
        public TextView address;
        public TextView number1;
        public TextView number2;
        public TextView status;


        public ClientViewHolder(View itemView) {
            super(itemView);

            cpf = (TextView) itemView.findViewById(R.id.textView_cpf);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            email = (TextView) itemView.findViewById(R.id.textView_email);
            address = (TextView) itemView.findViewById(R.id.textView_address);
            number1 = (TextView) itemView.findViewById(R.id.textView_num1);
            number2 = (TextView) itemView.findViewById(R.id.textView_num2);
            status = (TextView) itemView.findViewById(R.id.textView_status);


        }
    }
}
