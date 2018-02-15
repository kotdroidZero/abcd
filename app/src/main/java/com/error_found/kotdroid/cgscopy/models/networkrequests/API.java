package com.error_found.kotdroid.cgscopy.models.networkrequests;


import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by Mukesh on 06-06-2016.
 */
public interface API {




    @GET(WebConstants.GET_ALL_GUESTS)
    Call<List<PojoLogin>> getGuests(@Query("session_id") String sessionId,
                                    @Query("status") String status);

    @GET(WebConstants.GET_ALL_GUESTS)
    Observable<Response<List<PojoLogin>>> getAllGuest(@Query("session_id") String sessionId,
                                                      @Query("status") String status);

    @FormUrlEncoded
    @POST(WebConstants.CHANGE_GUEST_STATUS)
    Call<PojoLogin> changeGuestStatus(@Field("session_id") String sessionId,
                                       @Field("guest_id") Integer guestId,
                                       @Field("status") Integer guestType);

    @FormUrlEncoded
    @POST(WebConstants.LOGIN)
    Call<PojoLogin> loginUser(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("device_type") String deviceType);




  /*  @Multipart
    @POST("partner/about-me")
    Observable<Response<PojoUserLogin>> saveBasicInfo(@Part("session_id") RequestBody sessionId,
                                                      @Part("service_ids") RequestBody serviceIds,
                                                      @Part MultipartBody.Part picture,
                                                      @Part MultipartBody.Part coverPicture,
                                                      @Part("name") RequestBody fullName,
                                                      @Part("email") RequestBody email,
                                                      @Part("password") RequestBody password,
                                                      @Part("country_code") RequestBody countryCode,
                                                      @Part("phone") RequestBody phone,
                                                      @Part("gender") RequestBody gender,
                                                      @Part("qualification") RequestBody qualification,
                                                      @Part("experience") RequestBody experience,
                                                      @Part("device_id") RequestBody deviceId,
                                                      @Part("device_type") RequestBody deviceType,
                                                      @Part("device_token") RequestBody deviceToken,
                                                      @Part("timezone") RequestBody timezone,
                                                      @Part("locale") RequestBody locale);

    @Multipart
    @POST("partner/work-photos")
    Observable<Response<PojoCommon>> uploadWorkPhotos(@PartMap Map<String, RequestBody> hashMap);*/


}
