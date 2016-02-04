package com.wlad.minutrade.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wlad.minutrade.R;
import com.wlad.minutrade.controller.ClientViewAdapter;
import com.wlad.minutrade.controller.DataBase;
import com.wlad.minutrade.controller.RecyclerItemClickListener;
import com.wlad.minutrade.model.Client;

import java.util.List;

/**
 * Created by Wlad on 04/02/2016.
 */
public class ListActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private List<Client> list;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list);

        dataBase = new DataBase(this);
        list = dataBase.retrieve();

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            final RecyclerView.Adapter adapter = new ClientViewAdapter(ListActivity.this, list);
            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }

                @Override
                public void onItemLongClick(View view, int position) {
                    dataBase.delete(list.get(position));
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            }));
    }
}
