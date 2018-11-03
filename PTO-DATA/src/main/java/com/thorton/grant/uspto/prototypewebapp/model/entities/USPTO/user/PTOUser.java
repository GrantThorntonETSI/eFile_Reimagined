package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PTOUsers")
public class PTOUser extends UserPersonalData {


    @OneToMany(mappedBy = "trademarkOwner")
    private Set<TradeMark> userTrademarks;




    public Set<TradeMark> getUserTrademarks() {
        return userTrademarks;
    }

    public void setUserTrademarks(Set<TradeMark> userTrademarks) {
        this.userTrademarks = userTrademarks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PTOUser ptoUser = (PTOUser) o;
        return Objects.equals(userTrademarks, ptoUser.userTrademarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userTrademarks);
    }

    @Override
    public String toString() {
        return "PTOUser{" +
                "userTrademarks=" + userTrademarks +
                '}';
    }
}
