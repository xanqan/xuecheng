package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.MqMessageMapper;
import com.xuecheng.content.model.po.MqMessage;
import com.xuecheng.content.service.MqMessageService;
import org.springframework.stereotype.Service;

/**
 * @author 28961
 * @description 针对表【mq_message】的数据库操作Service实现
 * @createDate 2023-03-15 23:03:59
 */
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage>
        implements MqMessageService {

}




