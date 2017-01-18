package test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ld.web.been.model.Dict;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictBiz;
import com.ld.web.biz.DictTypeBiz;
import com.ld.web.util.JsonMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-hibernate.xml" })
public class DictBizImplTest {

    @Resource
    private DictTypeBiz dictTypeBiz;

    @Resource
    private DictBiz dictBiz;

    @Test
    public void save() {

        for (int i = 104; i < 1000; i++) {
            List<Dict> dicts = new ArrayList<Dict>();
            DictType dictType = new DictType("type" + i, "type" + i, true, "type" + i, dicts);

            dicts.add(new Dict(dictType, "value" + i, "11", true, "remark1"));
            dicts.add(new Dict(dictType, "value" + i, "22", true, "remark2"));

            dictTypeBiz.save(dictType);
        }
    }

    @Test
    public void get() {
        DictType dictType = dictTypeBiz.get("type1");

        System.out.println(JsonMapper.getInstance().toJson(dictType));
        System.out.println(JsonMapper.getInstance().toJson(dictType.getDicts().get(0)));
        // dictBiz.delete(dictType.getDicts().get(0));
    }
}
