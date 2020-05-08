package com.bw.movie.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bw.movie.bean.ComingSoonResultBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMING_SOON_RESULT_BEAN".
*/
public class ComingSoonResultBeanDao extends AbstractDao<ComingSoonResultBean, Long> {

    public static final String TABLENAME = "COMING_SOON_RESULT_BEAN";

    /**
     * Properties of entity ComingSoonResultBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MovieId = new Property(0, Long.class, "movieId", true, "_id");
        public final static Property ImageUrl = new Property(1, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property ReleaseTime = new Property(3, long.class, "releaseTime", false, "RELEASE_TIME");
        public final static Property WantSeeNum = new Property(4, int.class, "wantSeeNum", false, "WANT_SEE_NUM");
        public final static Property WhetherReserve = new Property(5, int.class, "whetherReserve", false, "WHETHER_RESERVE");
    }


    public ComingSoonResultBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ComingSoonResultBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMING_SOON_RESULT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: movieId
                "\"IMAGE_URL\" TEXT," + // 1: imageUrl
                "\"NAME\" TEXT," + // 2: name
                "\"RELEASE_TIME\" INTEGER NOT NULL ," + // 3: releaseTime
                "\"WANT_SEE_NUM\" INTEGER NOT NULL ," + // 4: wantSeeNum
                "\"WHETHER_RESERVE\" INTEGER NOT NULL );"); // 5: whetherReserve
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMING_SOON_RESULT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ComingSoonResultBean entity) {
        stmt.clearBindings();
 
        Long movieId = entity.getMovieId();
        if (movieId != null) {
            stmt.bindLong(1, movieId);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(2, imageUrl);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getReleaseTime());
        stmt.bindLong(5, entity.getWantSeeNum());
        stmt.bindLong(6, entity.getWhetherReserve());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ComingSoonResultBean entity) {
        stmt.clearBindings();
 
        Long movieId = entity.getMovieId();
        if (movieId != null) {
            stmt.bindLong(1, movieId);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(2, imageUrl);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
        stmt.bindLong(4, entity.getReleaseTime());
        stmt.bindLong(5, entity.getWantSeeNum());
        stmt.bindLong(6, entity.getWhetherReserve());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ComingSoonResultBean readEntity(Cursor cursor, int offset) {
        ComingSoonResultBean entity = new ComingSoonResultBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // movieId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // imageUrl
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.getLong(offset + 3), // releaseTime
            cursor.getInt(offset + 4), // wantSeeNum
            cursor.getInt(offset + 5) // whetherReserve
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ComingSoonResultBean entity, int offset) {
        entity.setMovieId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImageUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setReleaseTime(cursor.getLong(offset + 3));
        entity.setWantSeeNum(cursor.getInt(offset + 4));
        entity.setWhetherReserve(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ComingSoonResultBean entity, long rowId) {
        entity.setMovieId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ComingSoonResultBean entity) {
        if(entity != null) {
            return entity.getMovieId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ComingSoonResultBean entity) {
        return entity.getMovieId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}