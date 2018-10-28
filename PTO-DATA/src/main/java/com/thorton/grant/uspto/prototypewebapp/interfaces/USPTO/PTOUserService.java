package com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO;


import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.PTOUser;

public interface PTOUserService extends CrudService<PTOUser, Long> {

    public PTOUser findByEmail(String email);




}
