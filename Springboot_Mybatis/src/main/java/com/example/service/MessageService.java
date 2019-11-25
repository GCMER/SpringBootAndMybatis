package com.example.service;

import com.example.entity.Message;
import com.example.mapper.MessageMapper;
import com.example.utils.PageRequest;
import com.example.utils.PageResult;
import com.example.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;
    public PageResult getMessages(String pageNum,String pageSize){
        Integer start = Integer.parseInt(pageNum);
        Integer end = Integer.parseInt(pageSize);
        if (start==1){
            start=0;
        }
        if (0 != start) {
            start = end*start-end;
        }

        List<Message> list = messageMapper.getMessages(start,end);
        if (!CollectionUtils.isEmpty(list)){
            for(Message m : list){
                Date date = m.getCreateDate();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = df.format(date);
                m.setStringTime(time);
            }
        }
        System.out.println("ssdasasdasdasdasdasdasdasd"+list);
        Integer totalSize = messageMapper.getMsgCount();
        Integer totalPages = 0;
        if (0 != totalSize) {
            totalPages = totalSize/end;
            if ( 0!= totalSize%end){
                totalPages++;
            }
        }
        PageResult pageResult = new PageResult();
        pageResult.setContent(list);
        pageResult.setTotalSize(totalSize);
        pageResult.setTotalPages(totalPages);
        pageResult.setPageNum(Integer.parseInt(pageNum));
        return pageResult;
    }

    public String addMessage(String userName, String message, String ipAddress){
        String result = "2";

        if (StringUtils.isEmpty(message)) {
            result="0";
        } else if (StringUtils.isEmpty(userName)){
            result="1";
        }else{
            messageMapper.addMessage(userName,message,ipAddress);
        }
        return result;
    }

    public String delMessage(int id){

        messageMapper.delMessage(id);
        return "success";
    }

    public String replyMessage(int id,String replyMessage){
        messageMapper.replyMessage(id,replyMessage);
        return "success";
    }
}
