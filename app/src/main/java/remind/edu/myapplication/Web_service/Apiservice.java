package remind.edu.myapplication.Web_service;

import remind.edu.myapplication.Response_register;
import remind.edu.myapplication.Select_edu.Response_course;
import remind.edu.myapplication.Generate_otp.Response_gen_otp;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Apiservice {
    @FormUrlEncoded
    @POST("user_login.php")
    Call<Response_gen_otp> generate_Otp(@Field("mobileno") String mobile);


    @GET("list_course.php")
    Call<Response_course> course_list();


    @GET("list_subcourseone.php?")
    Call<Response_course> maincatlist(@Query("courseid") String courseid);
    @FormUrlEncoded
    @POST("user_add.php")
    Call<Response_register> doregister(@Field("name") String name,
                                       @Field("mobileno") String mobile,
                                       @Field("email") String email,
                                       @Field("addr") String addr,
                                       @Field("course") String course,
                                       @Field("device_id") String device_id);
}
