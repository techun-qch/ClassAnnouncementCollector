package com.example.classannouncementcollector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.classannouncementcollector.entity.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, int resource, @NonNull List<Message> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Message message=getItem(position);
        @SuppressLint("ViewHolder") View view= LayoutInflater.from(getContext()).inflate(R.layout.message_item,parent,false);
        TextView tv_category_value=view.findViewById(R.id.item_category_value);
        TextView tv_message_value=view.findViewById(R.id.message_value);
        TextView tv_ddl_value=view.findViewById(R.id.ddl_value);

        if(message !=null){
            tv_category_value.setText(message.getCategory());
            tv_message_value.setText(message.getDetail());
            String origin_tv_ddl_value=message.getDeadline();
            tv_ddl_value.setText(origin_tv_ddl_value.substring(0,4)+"-"+
                            origin_tv_ddl_value.substring(4,6)+"-"+
                            origin_tv_ddl_value.substring(6)
                    );
        }

        return view;



    }
}
