package com.codinglife.service;

import com.codinglife.entity.ChapterDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codinglife.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-25
 */
public interface ChapterService extends IService<ChapterDo> {
    List<ChapterVo> getChapterVoByCourseId(String courseId);

    boolean deleteChapterByCharpterId(String chapterId);

    void deleteChapterByCourseId(String courseId);
}
