package com.guo.etc.kernel.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/21.
 */
@Entity
@Table(name = "room")
public class Room {
    private int id;
    private String roomName;
    private String roomColor;
    private double roomSize;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "room_name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name = "room_color", nullable = false, insertable = true, updatable = true, length = 255)
    public String getRoomColor() {
        return roomColor;
    }

    public void setRoomColor(String roomColor) {
        this.roomColor = roomColor;
    }

    @Basic
    @Column(name = "room_size", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (Double.compare(room.roomSize, roomSize) != 0) return false;
        if (roomName != null ? !roomName.equals(room.roomName) : room.roomName != null) return false;
        if (roomColor != null ? !roomColor.equals(room.roomColor) : room.roomColor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + (roomColor != null ? roomColor.hashCode() : 0);
        temp = Double.doubleToLongBits(roomSize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
