package com.lunchtasting.server.model;

import java.io.Serializable;

public abstract class PKModel<PK> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4772636662279566589L;

    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
