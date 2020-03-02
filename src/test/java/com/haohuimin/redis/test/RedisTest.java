package com.haohuimin.redis.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.DateUtil;
import com.bw.utils.RandomUtil;
import com.bw.utils.StringUtil;
import com.haohuimin.bean.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;
    
    @Test
	public  void testJDK() {
    	String name="";
    	String tel="13";
    	String gender="";
    	String emial="^[3-20].d$";
    	String birthday="";
    	ArrayList<String> list = new ArrayList<String>();	
    	ArrayList<User> arrayList = new ArrayList<User>();
    	for (int i = 1; i <=100000; i++) {
	        name = RandomUtil.getRandomChineseString(3);
    		gender = list.get(RandomUtil.getRandomNum(0,1));
	        for (int j = 0; j < 9; j++) {
				tel+=(Math.random()*10);
			}
	        emial+=StringUtil.isEmail("+ @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com");
	        DateUtil.getAgeByBirthday(birthday);
	        User user = new User(i, name, gender, tel, emial, birthday);
	        arrayList.add(user);
    	}
    	long start = System.currentTimeMillis();
    	redisTemplate.opsForList().leftPushAll("goods_list",arrayList);
    	long end = System.currentTimeMillis();
    	System.out.println("使用JDK系列化方式");
        System.out.println("保存数量10万个");
        System.out.println("所耗时间是"+(end-start));
    }
  
    @Test
    public void testJSON() {
    	String name="";
    	String tel="13";
    	String gender="";
    	String emial="";
    	String birthday="";
    	
    	ArrayList<String> list = new ArrayList<String>();	
    	ArrayList<User> arrayList = new ArrayList<User>();
    	for (int i = 1; i <=100000; i++) {
	        name = RandomUtil.getRandomChineseString(3);
    		gender = list.get(RandomUtil.getRandomNum(0,1));
	        for (int j = 0; j < 9; j++) {
				tel+=(Math.random()*10);
			}
	        emial+=StringUtil.isEmail("+ @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com");
	        DateUtil.getAgeByBirthday(birthday);
	        User user = new User(i, name, gender, tel, emial, birthday);
	        arrayList.add(user);
    	}
    	long start = System.currentTimeMillis();
    	redisTemplate.opsForList().leftPushAll("goods_list",arrayList);
    	long end = System.currentTimeMillis();
    	System.out.println("使用JSON系列化方式");
        System.out.println("保存数量10万个");
        System.out.println("所耗时间是"+(end-start));
    }
    
    @Test
    public void testHASH() {
    	String name="";
    	String tel="13";
    	String gender="";
    	String emial="";
    	String birthday="";
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("男");
    	list.add("女");
    	HashMap<String, String> map = new HashMap<String, String>();
    	for (int i = 1; i <=100000; i++) {
	        name = RandomUtil.getRandomChineseString(3);
    		gender = list.get(RandomUtil.getRandomNum(0,1));
	        for (int j = 0; j < 9; j++) {
				tel+=(Math.random()*10);
			}
	        emial+=StringUtil.isEmail("+ @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com");
	        DateUtil.getAgeByBirthday(birthday);
	        User user = new User(i, name, gender, tel, emial, birthday);
	        map.put(user.getId()+"", user.toString());
    	}
    	long start = System.currentTimeMillis();
    	redisTemplate.opsForList().leftPushAll("goods_hash",map);
    	long end = System.currentTimeMillis();
    	System.out.println("使用hash系列化方式");
        System.out.println("保存数量10万个");
        System.out.println("所耗时间是"+(end-start));
    }
}
