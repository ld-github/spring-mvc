package com.ld.web.been.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 *<p>Title: DictType</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-07
 */
@Entity
@Table(name = "t_dict_type", uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class DictType extends BaseModel {

    private static final long serialVersionUID = -7466333696886683399L;

    @Column(length = 32, nullable = false, unique = true)
    private String code;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    private boolean canView;

    @Column(length = 255)
    private String remark;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.REMOVE, CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "typeId")
    private List<Dict> dicts = new ArrayList<Dict>();

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean getCanView() {
        return canView;
    }

    public String getRemark() {
        return remark;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCanView(boolean canView) {
        this.canView = canView;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Dict> getDicts() {
        return dicts;
    }

    public void setDicts(List<Dict> dicts) {
        this.dicts = dicts;
    }

    public DictType(String code, String name, boolean canView, String remark, List<Dict> dicts) {
        this.code = code;
        this.name = name;
        this.canView = canView;
        this.remark = remark;
        this.dicts = dicts;
    }

    public DictType() {
    }

}
