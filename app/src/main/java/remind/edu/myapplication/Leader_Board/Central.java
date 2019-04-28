package remind.edu.myapplication.Leader_Board;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Central extends Fragment {


    public Central() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_mock_up, container, false);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("dash",MODE_PRIVATE);
        String  course_id=sharedPreferences.getString("dash",null);
        final RecyclerView recyclerview=(RecyclerView)view.findViewById(R.id.recv);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_list_leaderexm> call=apiservice.Leadersublist(course_id,"2");
        call.enqueue(new Callback<Response_list_leaderexm>() {
            @Override
            public void onResponse(Call<Response_list_leaderexm> call, Response<Response_list_leaderexm> response) {
                Response_list_leaderexm response_list_leaderexm=response.body();
                List<LExam> datalist=response_list_leaderexm.getExams();
                LeaderAdapterlistsubcentral adapter=new LeaderAdapterlistsubcentral(datalist,getActivity());
                recyclerview.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Response_list_leaderexm> call, Throwable t) {

            }
        });
        return view;
    }

}
