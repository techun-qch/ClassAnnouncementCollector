package com.example.classannouncementcollector.ui.home;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classannouncementcollector.MainActivity;
import com.example.classannouncementcollector.R;
import com.example.classannouncementcollector.databinding.FragmentHomeBinding;
import com.example.classannouncementcollector.entity.Message;
import com.example.classannouncementcollector.entity.MessageCategory;
import com.example.classannouncementcollector.ui.CategpryDialog;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private String select_category="默认";

    private Button bt_add_Category;

    private FragmentHomeBinding binding;
    public static Calendar calendar = Calendar.getInstance();

    // 截止日期默认3天后
    public static String date=calendar.get(Calendar.YEAR)+
            Integer.toString(calendar.get(Calendar.MONTH))+
            (calendar.get(Calendar.DAY_OF_MONTH)+3);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button bt_add=root.findViewById(R.id.bt_addMessages);
        Button bt_add_ddl=root.findViewById(R.id.bt_choose_ddl);
        bt_add_Category=root.findViewById(R.id.bt_add_Category);

        EditText editText=root.findViewById(R.id.ed_textline);

        Spinner spinner=root.findViewById(R.id.spinner_location);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MessageCategory> messageCategoryList =MainActivity.messageCategoryDao.getALLMessageCategory();
                int num=messageCategoryList.size();
                String[] strAll = new String[num+1];
                strAll[0]="默认";
                for(int i=1;i<=num;i++){
                    strAll[i]=messageCategoryList.get(i-1).getCategoryValue();
                }
                ArrayAdapter<String> adapter=
                        new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,strAll);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        select_category = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        }).start();
//


        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_input_message=editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        long id =MainActivity.messageDao.insertPerson(new Message(ed_input_message,date,select_category));
                        if(id > 0){
                            Looper.prepare();
                            Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();

            }
        });

        bt_add_ddl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出对话框

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(),
                        null,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                // 确认按钮
                datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                    // 确认年月日
                    int year = datePickerDialog.getDatePicker().getYear();
                    int monthOfYear = datePickerDialog.getDatePicker().getMonth() + 1;
                    int dayOfMonth = datePickerDialog.getDatePicker().getDayOfMonth();
                    date= year +Integer.toString(monthOfYear)+ dayOfMonth;
                    Toast.makeText(getActivity(),date,Toast.LENGTH_SHORT).show();

                    // 关闭dialog
                    datePickerDialog.dismiss();
                });
            }
        });

        bt_add_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategpryDialog categpryDialog =new CategpryDialog(getContext());
                categpryDialog.show();

            }
        });


//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}