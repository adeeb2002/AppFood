package com.example.food.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food.Activity.MainActivity;
import com.example.food.Models.UserModel;
import com.example.food.R;
import com.example.food.TableDataBase.DataBase;
import com.example.food.databinding.FragmentProfileBinding;
import com.example.food.databinding.FragmentProfileUserBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FragmentProfileUser extends Fragment {

    FragmentProfileUserBinding binding;
    DataBase dataBase;
    Context context;
    ArrayList<UserModel> user = new ArrayList<>();
    int id;
    int counterPassword;
    InputStream iStream = null;
    Uri uri = null;
    byte[] inputData;
    int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileUserBinding.inflate(inflater, container, false);
        context = container.getContext();
        dataBase = new DataBase(context);
        user = dataBase.getUserInDataBase();

        MainActivity mainActivity = (MainActivity) getActivity();


        try {
            id = mainActivity.getIdUser();
        } catch (NullPointerException e) {
            Toast.makeText(mainActivity, "The Id Null", Toast.LENGTH_SHORT).show();
        }


        binding.updateProfileCard.setVisibility(View.GONE);
        Toast.makeText(mainActivity, "" + id, Toast.LENGTH_SHORT).show();


        binding.cardCamera.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });


        UserModel userModels = null;
        for (UserModel userModel : user) {
            if (userModel.getIdUser() == id) {
                userModels = userModel;
            }
        }

        binding.cardEye.setOnClickListener(v -> {
            counterPassword++;
            if (binding.cardEye.isClickable()) {
                if (counterPassword % 2 == 0) {
                    binding.eyePasswordImg.setImageResource(R.drawable.eye_password_food_app);
                    binding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (counterPassword % 2 != 0) {
                    binding.eyePasswordImg.setImageResource(R.drawable.hied_eye_password_food_app__1_);
                    binding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        try {
            binding.UserNameET.setText(userModels.getUserName());
            binding.passwordET.setText(userModels.getPassowrd());
            binding.phoneET.setText(userModels.getPhone());
            binding.eamilET.setText(userModels.getEmail());
            binding.countryET.setText(userModels.getCountry());
            Bitmap bitmap = getImage(userModels.getImageUser());
            binding.profileImg.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(mainActivity, "The Visit Customer", Toast.LENGTH_SHORT).show();
        }

        RefreshData();

        binding.updateProfileCard.setOnClickListener(v -> {
            String userName = binding.UserNameET.getText().toString();
            String password = binding.passwordET.getText().toString();
            String phone = binding.phoneET.getText().toString();
            String country = binding.countryET.getText().toString();
            String emial = binding.eamilET.getText().toString();

            try {
                iStream = context.getContentResolver().openInputStream(uri);
            } catch (Exception e) {
                Log.d("TAG", "onCreateView: ");
            }
            try {
                inputData = getBytes(iStream);
            } catch (IOException e) {
                Log.d("TAG", "onCreateView: ");
            }


            UserModel users = new UserModel(userName, password);
            users.setEmail(emial);
            users.setPhone(phone);
            users.setCountry(country);
            users.setIdUser(id);
            users.setImageUser(inputData);

            dataBase.updateUser(users);
            Toast.makeText(mainActivity, "Done Update Data", Toast.LENGTH_SHORT).show();

        });

        return binding.getRoot();
    }


    private byte[] getBytes(InputStream iStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        try {
            while ((len = iStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Add Photo Please", Toast.LENGTH_SHORT).show();
        }

        return byteBuffer.toByteArray();
    }


    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        binding.profileImg.setImageURI(uri);
    }


    public void RefreshData() {
        if (index == 0 || index == -1) {
            if (binding.UserNameET.length() != 0 || binding.passwordET.length() != 0 || binding.eamilET.length() != 0 || binding.phoneET.length() != 0 || binding.countryET.length() != 0) {
                binding.updateProfileCard.setVisibility(View.VISIBLE);
            } else {
                binding.updateProfileCard.setVisibility(View.GONE);
            }
        }
        update();
        Refresh(1000);
    }

    private void Refresh(int milisacend) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RefreshData();
            }
        };
        handler.postDelayed(runnable, milisacend);
    }

    public void update() {
        String userName = binding.UserNameET.getText().toString();
        String password = binding.passwordET.getText().toString();
        String phone = binding.phoneET.getText().toString();
        String country = binding.countryET.getText().toString();
        String emial = binding.eamilET.getText().toString();
        for (UserModel userModel : user) {
            if (userModel.getIdUser() == id) {
                if (!userName.equals(userModel.getUserName()) || !password.equals(userModel.getPassowrd()) || !phone.equals(userModel.getPhone()) || !country.equals(userModel.getCountry()) || !emial.equals(userModel.getEmail())) {
                    binding.updateProfileCard.setVisibility(View.VISIBLE);
                } else if (!userName.equals(userModel.getUserName()) || !password.equals(userModel.getPassowrd()) || !phone.equals("") || !country.equals("") || !emial.equals("")) {
                    binding.updateProfileCard.setVisibility(View.VISIBLE);
                } else {
                    binding.updateProfileCard.setVisibility(View.GONE);
                }
            }
        }
    }
}