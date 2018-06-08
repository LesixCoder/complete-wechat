package com.lunchtasting.server.model;



import java.io.Serializable;

/**
 * @author aroot
 */
public abstract class BasicPOModel extends PKModel<Long> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2923402033311175237L;

    public static final String PROP_ID = "id";

}