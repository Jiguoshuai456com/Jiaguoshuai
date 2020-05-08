package com.bw.movie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class ComingSoonResultBean {

    @Id
    private Long movieId;
    private String imageUrl;
    private String name;
    private long releaseTime;
    private int wantSeeNum;
    private int whetherReserve;

    @Generated(hash = 1158974634)
    public ComingSoonResultBean(Long movieId, String imageUrl, String name,
            long releaseTime, int wantSeeNum, int whetherReserve) {
        this.movieId = movieId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.releaseTime = releaseTime;
        this.wantSeeNum = wantSeeNum;
        this.whetherReserve = whetherReserve;
    }

    @Generated(hash = 1106705565)
    public ComingSoonResultBean() {
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getWantSeeNum() {
        return wantSeeNum;
    }

    public void setWantSeeNum(int wantSeeNum) {
        this.wantSeeNum = wantSeeNum;
    }

    public int getWhetherReserve() {
        return whetherReserve;
    }

    public void setWhetherReserve(int whetherReserve) {
        this.whetherReserve = whetherReserve;
    }
}
