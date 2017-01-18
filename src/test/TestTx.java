package test;

import com.ld.web.util.HttpClientTool;

public class TestTx {

    public static void main(String[] args) {
        try {
            HttpClientTool.getInstance().post("http://127.0.0.1/item/manager/login", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
