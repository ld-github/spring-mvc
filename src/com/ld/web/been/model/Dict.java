package com.ld.web.been.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 
 *<p>Title: Dict</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-07
 */
@Entity
@Table(name = "t_dict")
public class Dict extends BaseModel {

    private static final long serialVersionUID = 2422730999611951318L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    private DictType type;

    @Column(length = 64, nullable = false)
    private String value;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    private boolean canUpdate;

    @Column(length = 255)
    private String remark;

    public DictType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setType(DictType type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Dict(DictType type, String value, String name, boolean canUpdate, String remark) {
        this.type = type;
        this.value = value;
        this.name = name;
        this.canUpdate = canUpdate;
        this.remark = remark;
    }

    public Dict() {
    }

}
