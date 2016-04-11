package com.guo.etc.kernel.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2016/4/11.
 */
@Entity
public class Vehicle {
    private long id;
    private String vehicleId;
    private String vehicleOwner;
    private String vehicleType;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vehicle_id", nullable = true, insertable = true, updatable = true, length = 20)
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "vehicle_owner", nullable = true, insertable = true, updatable = true, length = 255)
    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    @Basic
    @Column(name = "vehicle_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != vehicle.id) return false;
        if (vehicleId != null ? !vehicleId.equals(vehicle.vehicleId) : vehicle.vehicleId != null) return false;
        if (vehicleOwner != null ? !vehicleOwner.equals(vehicle.vehicleOwner) : vehicle.vehicleOwner != null)
            return false;
        if (vehicleType != null ? !vehicleType.equals(vehicle.vehicleType) : vehicle.vehicleType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (vehicleId != null ? vehicleId.hashCode() : 0);
        result = 31 * result + (vehicleOwner != null ? vehicleOwner.hashCode() : 0);
        result = 31 * result + (vehicleType != null ? vehicleType.hashCode() : 0);
        return result;
    }
}
