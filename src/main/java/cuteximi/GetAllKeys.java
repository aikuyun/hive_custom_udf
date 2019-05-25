package cuteximi;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.Iterator;

/**
 * @program: json_keys
 * @description: 获取 JSON字符串所有的 keys
 * @author: TSL
 * @create: 2019-05-23 21:55
 **/

public class GetAllKeys extends UDF {

    public String evaluate(String json_str){

        if(json_str.length() == 0){
            json_str = "{}";
        }

        // 解析 json 字符串到 Java Object
        JSONObject json = JSONObject.parseObject(json_str);

        String s = "";

        // 拿到迭代器
        Iterator it = json.keySet().iterator();

        // 开始迭代
        while (it.hasNext()){
            s += "," + it.next();
        }

        return s.length()==0?s:s.substring(1);
    }
}
