package com.example.classannouncementcollector.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classannouncementcollector.MainActivity;
import com.example.classannouncementcollector.MessageAdapter;
import com.example.classannouncementcollector.R;
import com.example.classannouncementcollector.databinding.FragmentDashboardBinding;
import com.example.classannouncementcollector.entity.Message;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    TextView tv_category_value;
    TextView tv_message_value;
    TextView tv_ddl_value;

    ListView listView;
    List<Message> messages;

    final Handler handler = new Handler();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_category_value=root.findViewById(R.id.item_category_value);
        tv_message_value=root.findViewById(R.id.message_value);
        tv_ddl_value=root.findViewById(R.id.ddl_value);
        listView=root.findViewById(R.id.list_view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                messages= MainActivity.messageDao.getALLMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MessageAdapter messageAdapter =new MessageAdapter(getContext(),R.layout.message_item,messages);
                        listView.setAdapter(messageAdapter);
                    }
                });
            }
        }).start();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}