/**
 * 
 */
package org.sogrey.datefromat;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 *
 */
public class SetDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	       Set<String> nameSet = new HashSet<String>();
	        nameSet.add("张三");
	        nameSet.add("李四");
	        nameSet.add("王五");
	        nameSet.add("张三");
	         
	        // 输出结果 张三  李四  王五
	        for(String name : nameSet){
	            System.out.print(name + "\t");
	        }
	        // List集合去除重复基础数据
	        ArrayList<String> nameList = new ArrayList<String>();
	        nameList.add("张三");
	        nameList.add("李四");
	        nameList.add("王五");
	        nameList.add("赵六");
	        nameSet.addAll(nameList);
	         
	        // 输出结果 张三  李四  王五  赵六
	        for(String name : nameSet){
	            System.out.print(name + "\t");
	        }
	         
	        // 去除编号和用户名一样的 对象，需要重写 equals 方法 和 hashCode方法
	        User admin = new User(1, "admin");
	        User user = new User(2, "user");
	        User user1 = new User(2, "user");
	        User admin1 = new User(3, "admin");
	         
	         
	        Set<User> userSet = new HashSet<User>();
	        userSet.add(admin);
	        userSet.add(user);
	        userSet.add(admin1);
	        userSet.add(user1);
	        // 输入结果 admin1  admin3  user2
	        for(User u : userSet){
	            System.out.print(u.username + u.id + "\t");
	        }
	         
	        System.out.println(user.equals(null));
	    }
	}
	 
	class User{
	     
	    protected Integer id;
	     
	    protected String username;
	     
	    public User(Integer id, String username){
	        this.id = id;
	        this.username = username;
	    }
	 
	 
	    /**
	     * 如果对象类型是User 的话 则返回true 去比较hashCode值
	     */
	    @Override
	    public boolean equals(Object obj) {
	        if(obj == null) return false;
	        if(this == obj) return true;
	        if(obj instanceof User){ 
	            User user =(User)obj;
//	          if(user.id = this.id) return true; // 只比较id
	            // 比较id和username 一致时才返回true 之后再去比较 hashCode
	            if(user.id == this.id && user.username.equals(this.username)) return true;
	            }
	        return false;
	    }
	 
	 
	 
	    /**
	     * 重写hashcode 方法，返回的hashCode 不一样才认定为不同的对象
	     */
	    @Override
	    public int hashCode() {
//	      return id.hashCode(); // 只比较id，id一样就不添加进集合
	        return id.hashCode() * username.hashCode();
	    }
}
