package com.example.joel.sendinrequestobjects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name =(EditText)findViewById(R.id.name);
        final EditText email =(EditText)findViewById(R.id.email);
        final EditText address =(EditText)findViewById(R.id.address);
        final EditText phone_number =(EditText)findViewById(R.id.phone_number);
        final EditText password =(EditText)findViewById(R.id.password);

        Button createAccountButton =(Button)findViewById(R.id.signUpBtn);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User(
                        name.getText().toString(),
                        email.getText().toString(),
                        address.getText().toString(),
                        password.getText().toString(),
                        Integer.parseInt(phone_number.getText().toString())
                );

                sendNetworkRequest(user);
            }
        });


    }

    private static String token;

    private void sendNetworkRequest(User user){
        //create retrofit instance
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient clientLog = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://dev.forhey.com/sales/api/")
                .client(clientLog)
                .addConverterFactory(GsonConverterFactory.create());

        //get client and call object for request
        Retrofit retrofit = builder.build();




        UserClient client = retrofit.create(UserClient.class);

        Call<User> call = client.createAccounts(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "token:"+ response.body().getToken(), Toast.LENGTH_LONG).show();
                token = response.body().getToken();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Oops Something went wrong", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });


    }



}
