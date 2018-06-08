package com.lunchtasting.server.orm.ibatis;

import com.lunchtasting.server.model.PKModel;

public interface GenericDAO<T extends PKModel<?>> {

    public void create(T t);

    public void update(T t);

    public void remove(T t);

    public T getById(Long id);

}
