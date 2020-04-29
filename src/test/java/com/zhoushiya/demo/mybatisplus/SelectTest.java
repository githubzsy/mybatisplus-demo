package com.zhoushiya.demo.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhoushiya.demo.dao.UserMapper;
import com.zhoushiya.demo.entity.User;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570l);
        System.out.println(user);
    }

    @Test
    public void selectByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1094590409767661570l, 1088248166370832385l));
        System.out.println(users);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        /**
         * map查询,key对应数据库中的列名,不是属性名
         */
        map.put("name", "王天风");
        // map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }


    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        /**
         * 1.名字包含雨且年龄小于40
         */
        queryWrapper.like("name", "雨").lt("age", 40);
        // 2.并且email不为空
        // queryWrapper.like("name", "雨").between("age", 20,40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * 姓名为王或者年龄大于等于30，按照年龄降序且按照id升序
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "王").or().ge("age", 30).orderByDesc("age").orderByAsc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * 创建日期为2019-02-14并且直属上级为王姓
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14").inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }


    /**
     * 姓王并且（年龄小于40或者email不为空）
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "王").and(a -> a.lt("age", 40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * 姓王或者（年龄小于40并且大于20并且email不为空）
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.likeRight("name", "王").or(a -> a.between("age", 20, 40).isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * (年龄小于40或者邮箱不为空) 并且名字为王姓
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //queryWrapper.and(a->a.lt("age",40).or().isNotNull("email")).likeRight("name","王");
        queryWrapper.nested(a -> a.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * 年龄是30,40,50
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.in("age", Arrays.asList(30, 40, 50));
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void selectBySuper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        // 只查询两列
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void selectBySuper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        // 只排除create_time
        queryWrapper.like("name", "雨").lt("age", 40).select(User.class, info -> info.getColumn().equals("create_time") == false);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void selectByCondition() {
        String name = "王";
        conditionTest(name);
    }

    private void conditionTest(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        // name不为空则加入到条件筛选
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void selectByWrapperEntity() {
        User whereUser = new User();
        whereUser.setName("刘");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(whereUser);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "王天风");
        params.put("age", null);
        queryWrapper.allEq(params);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void selectByWrapperMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);

        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄
     * select avg(age) avg_age,min(age) min_age,max(age) max_age from user
     * group by manager_id
     * having sum(age)<500
     */
    @Test
    public void selectByWrapperMaps2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manager_id").having("sum(age)<{0}", 500);

        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 只保存第一列
     */
    @Test
    public void selectByWrapperObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);

        List<Object> userList = userMapper.selectObjs(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查询记录数
     */
    @Test
    public void selectByWrapperCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "雨").lt("age", 40);

        int count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    /**
     * 只查询一条，返回多条会报错
     */
    @Test
    public void selectByWrapperOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "刘红雨").lt("age", 40);

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * 使用lambda表达式
     */
    @Test
    public void selectLambda() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.like(User::getName,"雨").lt(User::getAge,40);

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    /**
     * 使用lambda表达式
     * 姓王并且（年龄小于40或者email不为空）
     */
    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeRight(User::getName,"王").and(user->user.lt(User::getAge,40).or().isNotNull(User::getEmail));

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    /**
     * 使用lambda表达式
     */
    @Test
    public void selectLambda3() {
        List<User> users = new LambdaQueryChainWrapper<>(userMapper).like(User::getName,"雨").lt(User::getAge,40).list();
        System.out.println(users);
    }

    /**
     * 条件构造器自定义查询
     */
    @Test
    public void selectMy(){
        LambdaQueryWrapper<User> lambdaQueryWrapper=Wrappers.lambdaQuery();
        lambdaQueryWrapper.likeRight(User::getName,"王").and(user->user.lt(User::getAge,40).or().isNotNull(User::getEmail));
        List<User> users=userMapper.<User>selectAll(lambdaQueryWrapper);
        System.out.println(users);
    }

    /**
     * 分页查询
     */
    @Test
    public void SelectPage(){
        LambdaQueryWrapper<User> lambdaQueryWrapper=Wrappers.lambdaQuery();
        lambdaQueryWrapper.ge(User::getAge,25).orderByAsc(User::getAge);

        // 当前页,每页多少条,是否要查询总记录数(为true时,下面的总页数,总记录数才会有值)
        Page<User> page= new Page<>(0,3,false);
        IPage<User> iPage= userMapper.selectPage(page,lambdaQueryWrapper);

        System.out.println("总页数:"+iPage.getPages());
        System.out.println("总记录数:"+iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void selectMyPage(){
        LambdaQueryWrapper<User> lambdaQueryWrapper=Wrappers.lambdaQuery();
        lambdaQueryWrapper.ge(User::getAge,25).orderByAsc(User::getAge);

        // 当前页,每页多少条,是否要查询总记录数(为true时,下面的总页数,总记录数才会有值)
        Page<User> page= new Page<>(0,3);
        IPage<User> iPage= userMapper.selectUserPage(page,lambdaQueryWrapper);

        System.out.println("总页数:"+iPage.getPages());
        System.out.println("总记录数:"+iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
    }
}
