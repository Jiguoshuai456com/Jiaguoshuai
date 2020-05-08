package com.bw.movie.utils;

import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.CommentBean;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.bean.FindMoviesDetailBean;
import com.bw.movie.bean.FindMoviewByKeWordBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.InfoByRegionBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.bean.RegionBean;
import com.bw.movie.bean.RegionBean2;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.ReserveBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.SendBean;

import org.greenrobot.greendao.annotation.Unique;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Apis {

    //获取验证码
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<SendBean> getSendBean(@Field("email") String email);
    //注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegisterBean(@Field("nickName") String name, @Field("pwd") String pwd, @Field("email") String email, @Field("code") String code);
    //登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginBean> getLoginBean(@Field("email") String email, @Field("pwd") String pwd);
    //热门电影
    @GET("movie/v2/findHotMovieList")
    Observable<HotBean> getHotBean(@Query("page") int page, @Query("count") int count);
    //即将上映
    @GET("movie/v2/findComingSoonMovieList")
    Observable<ComingSoonBean> getComingSoonBean(@Query("page") int page, @Query("count") int count);
    //正在上映
    @GET("movie/v2/findReleaseMovieList")
    Observable<ReleaseBean> getReleaseBean(@Query("page") int page, @Query("count") int count);
    //搜索
    @GET("movie/v2/findMovieByKeyword")
    Observable<FindMoviewByKeWordBean> getFindMoviewByKeWordBean(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);
    //查询电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<FindMoviesDetailBean> getFindMoviesDetailBean(@Query("movieId") int movieId);
    //关注电影
    @GET("movie/v1/verify/followMovie")
    Observable<FollowMovieBean> getFollowMovieBean(@Query("movieId") int movieid);
    //取消关注电影
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<CancelFollowMovieBean> getCancelFollowMovieBean(@Query("movieId") int movieid);
    //查询电影评论
    @GET("movie/v2/findAllMovieComment")
    Observable<FindAllMovieCommentBean> getFindAllMovieCommentBean(@Query("movieId") int movieid, @Query("page") int page, @Query("count") int count);
    //添加用户评论
    @POST("movie/v1/verify/movieComment")
    @FormUrlEncoded
    Observable<CommentBean> getCommentBean(@Field("movieId") int movieid, @Field("commentContent") String commentContent, @Field("score") double score);
    //预约
    @POST("movie/v2/verify/reserve")
    @FormUrlEncoded
    Observable<ReserveBean> getReserveBean(@Field("movieId") int movieid);
    //查询推荐影院信息
    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecommendBean> getRecommendBean(@Query("page") int page, @Query("count") int count);
    //查询附近影院信息
    @GET("cinema/v1/findNearbyCinemas")
    Observable<NearbyBean> getNearbyBean(@Query("longitude") String longitude, @Query("latitude") String latitude, @Query("page") int page, @Query("count") int count);

    //区域
    @GET("tool/v2/findRegionList")
    Observable<RegionBean> getRegionBean();
    //区域第二个条目要
    @GET("cinema/v2/findCinemaByRegion")
    Observable<RegionBean2> getRegionBean2(@Query("regionId") int regionId);

    //根据电影id,区域id 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByRegion")
    Observable<InfoByRegionBean> getInfoByRegionBean(@Query("movieId") int movieId, @Query("regionId") int regionId, @Query("page") int page, @Query("count") int count);

    //选座
    @GET("movie/v2/findMovieSchedule")
    Observable<ScheduleBean> getScheduleBean(@Query("movieId") int movieId, @Query("cinemaId") int cinemaId);











}
