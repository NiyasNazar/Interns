package remind.edu.myapplication.Web_service;

import com.google.gson.JsonObject;

import remind.edu.myapplication.Course_List.Response_sublist;
import remind.edu.myapplication.Exams.Response_qlist;
import remind.edu.myapplication.Exams.Response_quiz_questions;
import remind.edu.myapplication.Generate_otp.Response_validate_otp;
import remind.edu.myapplication.Response_register;
import remind.edu.myapplication.Select_edu.Response_course;
import remind.edu.myapplication.Generate_otp.Response_gen_otp;
import remind.edu.myapplication.Sub_details.Response_sub_details;
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
    @FormUrlEncoded
    @POST("user_validate.php")
    Call<Response_validate_otp> verify_otp(@Field("mobileno") String mobile, @Field("otp") String otp);

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

    @GET("list_file.php?")
    Call<JsonObject> subdetails(@Query("subject") String subject);

    @GET("list_subject.php")
    Call<Response_sublist> sublist(@Query("courseid") String courseid);
    @GET ("list_exam.php")
    Call<Response_qlist>qlists();
    @GET ("exam_questions.php")
    Call<Response_quiz_questions>questions();



}
