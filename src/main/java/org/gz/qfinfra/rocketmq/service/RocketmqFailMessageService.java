package org.gz.qfinfra.rocketmq.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.gz.qfinfra.rocketmq.entity.RocketmqFailMessage;
import org.gz.qfinfra.rocketmq.repository.RocketmqFailMessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 17853
 */
@Slf4j
public class RocketmqFailMessageService {
    @Resource
    private  RocketmqFailMessageMapper failMessageMapper;


    /**
     * 保存失败消息到 MySQL
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveFailMessage(RocketmqFailMessage failMessage) {
        failMessageMapper.insert(failMessage);
        log.info("[失败消息持久化] 成功，id={}, messageId={}, topic={}",
                failMessage.getId(), failMessage.getMessageId(), failMessage.getTopic());
    }


    public List<RocketmqFailMessage> selectList(RocketmqFailMessage rocketmqFailMessage) {
        // 查询待补偿消息：状态0（待补偿）、已重试次数 < 最大重试次数
        LambdaQueryWrapper<RocketmqFailMessage> queryWrapper = new LambdaQueryWrapper<RocketmqFailMessage>()
                .eq(RocketmqFailMessage::getStatus, rocketmqFailMessage.getStatus())
                .lt(RocketmqFailMessage::getRetryCount, rocketmqFailMessage.getRetryCount())
                .last("LIMIT " + rocketmqFailMessage.getCompensateBatchSize());
        return  failMessageMapper.selectList(queryWrapper);
    }

    public void updateById(RocketmqFailMessage failMsg) {

        failMessageMapper.updateById(failMsg);
    }
}
