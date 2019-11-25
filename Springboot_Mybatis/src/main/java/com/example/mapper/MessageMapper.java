package com.example.mapper;


import com.example.entity.Message;
import com.example.utils.PageRequest;
import com.example.utils.PageResult;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageMapper {

    // 查找所有留言
    List<Message> getMessages(int start,int end);

    Integer getMsgCount();

    // 添加留言
    void addMessage(String userName, String message, String ipAddress);

    // 删除留言
    void delMessage(int id);

    void replyMessage(int id,String replyMessage);

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);

    /**
     * 分页查询用户
     * @return
     */
    List<Message> selectPage();

}
