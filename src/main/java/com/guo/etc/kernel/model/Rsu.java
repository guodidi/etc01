package com.guo.etc.kernel.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2016/4/15.
 */
@Entity
public class Rsu {
    private long id;
    private String rsuId;
    private String rsuName;
    private String roadId;
    private String rsuSite;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rsu_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRsuId() {
        return rsuId;
    }

    public void setRsuId(String rsuId) {
        this.rsuId = rsuId;
    }

    @Basic
    @Column(name = "rsu_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRsuName() {
        return rsuName;
    }

    public void setRsuName(String rsuName) {
        this.rsuName = rsuName;
    }

    @Basic
    @Column(name = "road_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    @Basic
    @Column(name = "rsu_site", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRsuSite() {
        return rsuSite;
    }

    public void setRsuSite(String rsuSite) {
        this.rsuSite = rsuSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rsu rsu = (Rsu) o;

        if (id != rsu.id) return false;
        if (rsuId != null ? !rsuId.equals(rsu.rsuId) : rsu.rsuId != null) return false;
        if (rsuName != null ? !rsuName.equals(rsu.rsuName) : rsu.rsuName != null) return false;
        if (roadId != null ? !roadId.equals(rsu.roadId) : rsu.roadId != null) return false;
        if (rsuSite != null ? !rsuSite.equals(rsu.rsuSite) : rsu.rsuSite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (rsuId != null ? rsuId.hashCode() : 0);
        result = 31 * result + (rsuName != null ? rsuName.hashCode() : 0);
        result = 31 * result + (roadId != null ? roadId.hashCode() : 0);
        result = 31 * result + (rsuSite != null ? rsuSite.hashCode() : 0);
        return result;
    }
}
