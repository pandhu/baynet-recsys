package com.pandhu.recsys.core;

import javax.persistence.*;

/**
 * Created by wahyuoi on 10/26/15.
 */
@Entity
@Table(name = "leaderboard")
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "id_user", nullable = false)
    int idUser;
    @Column(name = "point")
    int point;
    @Column(name = "xp")
    int xp;

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Leaderboard() {

    }
}
