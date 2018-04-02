package com.path.controller.distance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.path.model.CenterNode;
import com.path.model.Distance;
import com.path.service.distance.DistanceService;
import com.path.util.JsonUtil;
import com.path.util.MapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author demo
 */
@Controller
@RequestMapping("distance")
public class DistanceController {
    @Resource
    private DistanceService distanceService;



    /**
     * 更新时间和距离
     * @param postData 时间和距离
     *   返回的剩余需要更新的条数
     */
    @RequestMapping("updatetimeanddis")
    public void updatetimeanddis(Distance postData){
        int result = 0;
        int remainCount = 0;
        Integer questionId = 1;
            postData.setDId(questionId);
            //不太优雅的设计，由于前端传过来的字符串有双引号，所以需要替换
            postData.setStartId(postData.getStartId().replaceAll("\"",""));
            postData.setEndId(postData.getEndId().replaceAll("\"",""));
            try {
                result = distanceService.updateByPrimaryKeySelective(postData);
                remainCount =  distanceService.checkRemainCount(1);
            }catch (Exception e){
                Map map = MapUtil.toMap(400, "更新失败"+e.getMessage(), result);
                JsonUtil.toJSON(map);
                return;
            }
        Map map = MapUtil.toMap(200, "更新成功", remainCount);
        JsonUtil.toJSON(map);

    }
    @RequestMapping("selectnullnode")
    public void selectnullnode(Integer questionId){
        questionId = 1;
        //初始化
        if (distanceService.selectIfNull(questionId).getDId()==0)
        {
            List<Distance> list = distanceService.produceAllWay(questionId);
            list.stream().forEach(e -> distanceService.insertSelective(e));
        }
        Distance distance = distanceService.selectNullNode( questionId);
        Map map = MapUtil.toMap(200, "下一条记录", distance);
        JsonUtil.toJSON(map);
    }


    public int getMaxFamily(int i) {
        StringBuilder max = new StringBuilder();
        if(i>100000000 || i< 1){
            return -1;
        }
        //使用桶排序
        int count[] = new int [10];
        while(i!=0)
        {
            count[i%10]++;
            i = i/10;
        }
        for (int j = 9; j >=0; j--) {
            while(count[j]!=0){
                max.append(j);
                count[j]--;
            }

        }
       return Integer.parseInt(max.toString());
    }

    public static void main(String[] args) {
       int N = 3;
       String s = "1A,1B,2C,2C";
       String t = "1B";
       solution(N,s,t);
    }
  private static String solution(int n , String s, String t){
        int hurt = 0;
        int destory = 0;
        int flag =0;
        final int HAVASHIP =2;//代表有没被击中的船
        final int HURTSHIP = 1;//代表被击中的船
        int map[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j]=0;
            }
        }
        //船的位置
       String shipLocation[] =  s.split(",");
        for (int i = 0; i < shipLocation.length; i++) {
           int a =  (int)shipLocation[i].charAt(0)-49;
           int b = (int)shipLocation[i].charAt(1)-65;
           map[a][b] =HAVASHIP;
        }
        //炮击位置
        String hitLocation[] =  t.split(",");
        for (int i = 0; i < hitLocation.length; i++) {
            int a =  (int)hitLocation[i].charAt(0)-49;
            int b = (int)hitLocation[i].charAt(1)-65;
            //船被击中就变为1
            if(map[a][b] == HAVASHIP){
                map[a][b] = HURTSHIP;
            }
        }
        //判断多少船受伤，多少船沉没
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1){
                    for (int k = 0; k < n; k++) {
                        if(map[i][k] == HAVASHIP || map[k][j] == HAVASHIP){
                            hurt++;
                            break;
                        }
                        flag =1;
                    }
                    if (flag ==1){
                        destory++;
                    }
                }
            }
        }
        return hurt+","+destory;
    }
}
