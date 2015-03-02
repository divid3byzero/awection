package com.buchner.awection.model.core.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auction_users")
public class AuctionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Integer> auctionId;

    @OneToMany(mappedBy = "userIds", fetch = FetchType.LAZY)
    private Article article;

    private String firstName;
    private String surname;
    private String mailAddress;
    private String pwHash;
    private long lrayId;

    public AuctionUser() {

    }

    public int getId() {

        return id;
    }

    public Set<Integer> getAuctionId() {

        return auctionId;
    }

    public void setAuctionId(Set<Integer> auctionId) {

        this.auctionId = auctionId;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public String getMailAddress() {

        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {

        this.mailAddress = mailAddress;
    }

    public String getPwHash() {

        return pwHash;
    }

    public void setPwHash(String pwHash) {

        this.pwHash = pwHash;
    }

    public long getLrayId() {

        return lrayId;
    }

    public void setLrayId(long lrayId) {

        this.lrayId = lrayId;
    }
}
