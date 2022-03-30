package com.codinglife.service.impl;

import com.codinglife.entity.VideoDo;
import com.codinglife.mapper.VideoMapper;
import com.codinglife.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDo> implements VideoService {

}
