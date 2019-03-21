package remind.edu.myapplication.Sub_details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Videos extends Fragment {
    RecyclerView recyclerview;

    public Videos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_videos, container, false);

        recyclerview=(RecyclerView)view.findViewById(R.id.recv_documents);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchfromserver();


        return view;
    }

    private void fetchfromserver() {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<JsonObject> call=apiservice.subdetails("sub1292412");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    Log.i("new",response.toString());
                    Log.i("new",new Gson().toJson(response.body()));

                    JSONArray dataArray = jsonObject.getJSONArray("videos");
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
