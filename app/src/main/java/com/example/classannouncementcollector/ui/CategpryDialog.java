package com.example.classannouncementcollector.ui;

import static android.app.PendingIntent.getActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.classannouncementcollector.MainActivity;
import com.example.classannouncementcollector.R;
import com.example.classannouncementcollector.entity.MessageCategory;

public class CategpryDialog extends Dialog {

    private EditText ed_category_value;
    private Button button_submit;

    public CategpryDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.add_category_dialog);
        ed_category_value=findViewById(R.id.ed_category_value);
        button_submit=findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input =ed_category_value.getText().toString();
//                新增的类别添加到数据库中
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long id=MainActivity.messageCategoryDao.insertCategory(new MessageCategory(input));
                        if(id > 0){
                            Looper.prepare();
                            Toast.makeText(getContext(),"添加类别： "+input+" 成功",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
                dismiss();
            }
        });

    }


}
