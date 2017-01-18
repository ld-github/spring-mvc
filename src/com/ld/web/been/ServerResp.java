package com.ld.web.been;

import java.io.Serializable;

/**
 * 
 *<p>Title: ServerResp</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-30
 */
public class ServerResp implements Serializable {

    private static final long serialVersionUID = 5884116120954749381L;

    private final String RESP_CODE_SUCCESS = "00";
    private final String RESP_CODE_ERROR = "01";

    private String respCode; // 相应code

    private String respDesc; // 相应描述

    private Object data; // 响应数据

    public String getRespCode() {
        return respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public Object getData() {
        return data;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ServerResp(String respCode, String respDesc) {
        this.respCode = respCode;
        this.respDesc = respDesc;
    }

    public ServerResp(String respCode, String respDesc, Object data) {
        this(respCode, respDesc);
        this.data = data;
    }

    public ServerResp(boolean status, String respDesc) {
        this.respCode = status ? RESP_CODE_SUCCESS : RESP_CODE_ERROR;
        this.respDesc = respDesc;
    }

    public ServerResp(boolean status, String respDesc, Object data) {
        this(status, respDesc);
        this.data = data;
    }

}
