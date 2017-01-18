package com.ld.web.been.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 *<p>Title: BaseModel</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-07
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 4686897694020369026L;

    @Id
    @GenericGenerator(name = "pk_gen", strategy = "uuid")
    @GeneratedValue(generator = "pk_gen")
    @Column(length = 32)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
