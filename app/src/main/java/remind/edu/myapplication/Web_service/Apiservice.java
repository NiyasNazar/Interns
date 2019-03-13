package remind.edu.myapplication.Web_service;

import remind.edu.myapplication.Course_List.Response_course;
import remind.edu.myapplication.Generate_otp.Response_gen_otp;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiservice {
    @FormUrlEncoded
    @POST("user_login.php")
    Call<Response_gen_otp> generate_Otp(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("list_course.php")
    Call<Response_course> course_list();

}
