package remind.edu.myapplication.Sub_details;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.jibble.simpleftp.SimpleFTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Documents extends Fragment {
SharedPreferences preferences;
    RecyclerView recyclerview;
    public Documents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      View view= inflater.inflate(R.layout.fragment_documents, container, false);
      EditText ed_search_view=(EditText)view.findViewById(R.id.ed_search);
      preferences=getActivity().getSharedPreferences("subid", Context.MODE_PRIVATE);
      String subid=preferences.getString("subids",null);
        Typeface hintfont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Melbourne_reg.otf");
        ed_search_view.setTypeface(hintfont);
      recyclerview=(RecyclerView)view.findViewById(R.id.recv_documents);
      recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
fetchfromserver(subid);

      
      return view;
    }

    private void fetchfromserver(String sub) {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<JsonObject>call=apiservice.subdetails(sub);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    Log.i("new",response.toString());
                    Log.i("new",new Gson().toJson(response.body()));

                    JSONArray dataArray = jsonObject.getJSONArray("documents");
                    List<Documentss> docList = new ArrayList<Documentss>();
                    Documentss documentss;
                    for (int i=0; i<dataArray.length(); i++){
                        JSONObject dataObject = dataArray.getJSONObject(i);
                        documentss = new Documentss();
                        documentss.setTitle(dataObject.getString("title"));
                        documentss.setFileId(dataObject.getString("file_id"));
                        documentss.setUrl(dataObject.getString("url"));
                        docList.add(documentss);

                    }
                    Adapter_list_docs adapter_list_docs=new Adapter_list_docs(docList,getActivity());
                    recyclerview.setAdapter(adapter_list_docs);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });





    }


}







