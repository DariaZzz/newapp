package com.example.newapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.databinding.CoolmarketBinding;

public class MainActivity extends AppCompatActivity {
    public static String choose = "Вы выбрали: ";
    public static String delOrPick = "Способ доставки: ";
    public static String comm = "Ваш комментарий к заказу: ";

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CoolmarketBinding binding = CoolmarketBinding.inflate(getLayoutInflater());



        setContentView(binding.getRoot());
        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+binding.phone.getText().toString()));
                startActivity(intent);
            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Идет отправка", Toast.LENGTH_SHORT).show();
                if(binding.fruit.isChecked())
                    choose = choose + binding.fruit.getText().toString() + " ";
                if(binding.veg.isChecked())
                    choose = choose +  binding.veg.getText().toString() + " ";
                if(binding.milk.isChecked())
                    choose = choose +  binding.milk.getText().toString() + " ";
                if(binding.fruit.isChecked())
                    choose = choose +  binding.pasta.getText().toString() + " ";
                if(binding.pasta.isChecked())
                    choose = choose +  binding.sweet.getText().toString() + " ";
                if(binding.meet.isChecked())
                    choose = choose +  binding.fruit.getText().toString() + " ";
                choose += '\n';
                choose += "Способ получения: ";
                if(binding.delivery.isChecked())
                    choose += binding.delivery.getText().toString();
                else if(binding.pickup.isChecked())
                    choose += binding.pickup.getText().toString();
                else
                    choose += " Вы не выбрали способ получения, заказ будет отменен.";
                choose += "\n";
                choose += "Ваш комментарий к заказу: ";
                choose += binding.comm.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"my@yandex.ru"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Напоминание о доставке");
                intent.putExtra(Intent.EXTRA_TEXT,choose);
                startActivity(intent);
            }

        });
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose = "Вы выбрали: ";
                binding.comm.getText().clear();
                binding.fruit.setChecked(false);
                binding.veg.setChecked(false);
                binding.meet.setChecked(false);
                binding.sweet.setChecked(false);
                binding.milk.setChecked(false);
                binding.pasta.setChecked(false);
                binding.delivery.setChecked(false);
                binding.pickup.setChecked(false);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Возвращайтесь!", Toast.LENGTH_SHORT).show();
    }
}
