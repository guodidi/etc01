package com.guo.etc.kernel.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/4/15.
 */
@Entity
public class Record {
    private long id;
    private String vehicleId;
    private String vehicleType;
    private String rsuId;
    private String roadId;
    private Timestamp tradeTime;
    private Long fee;
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
    @Column(name = "vehicle_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "vehicle_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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
    @Column(name = "road_id", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    @Basic
    @Column(name = "trade_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Basic
    @Column(name = "fee", nullable = true, insertable = true, updatable = true)
    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (id != record.id) return false;
        if (vehicleId != null ? !vehicleId.equals(record.vehicleId) : record.vehicleId != null) return false;
        if (vehicleType != null ? !vehicleType.equals(record.vehicleType) : record.vehicleType != null) return false;
        if (rsuId != null ? !rsuId.equals(record.rsuId) : record.rsuId != null) return false;
        if (roadId != null ? !roadId.equals(record.roadId) : record.roadId != null) return false;
        if (tradeTime != null ? !tradeTime.equals(record.tradeTime) : record.tradeTime != null) return false;
        if (fee != null ? !fee.equals(record.fee) : record.fee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (vehicleId != null ? vehicleId.hashCode() : 0);
        result = 31 * result + (vehicleType != null ? vehicleType.hashCode() : 0);
        result = 31 * result + (rsuId != null ? rsuId.hashCode() : 0);
        result = 31 * result + (roadId != null ? roadId.hashCode() : 0);
        result = 31 * result + (tradeTime != null ? tradeTime.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "rsu_site", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRsuSite() {
        return rsuSite;
    }

    public void setRsuSite(String rsuSite) {
        this.rsuSite = rsuSite;
    }
}
