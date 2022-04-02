package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.entity.ChapterDo;
import com.codinglife.entity.vo.ChapterVo;
import com.codinglife.service.ChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-25
 */
@RestController
@Api("章节小节管理")
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    /**
     * 获取课程基本信息
     * @param courseId
     * @return
     */
    @GetMapping("getChapterVideo/{courseId}")
    public CommonResult getChapterVo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVoByCourseId(courseId);
        return CommonResult.success().data("allChapterVideo", list);
    }

    /**
     * 添加课程章节
     * @param chapter
     * @return
     */
    @PostMapping("addChapter")
    public CommonResult addChapter(@RequestBody ChapterDo chapter) {
        chapterService.save(chapter);
        return CommonResult.success();
    }

    /**
     * 查询根据id课程章节
     * @param chapterId
     * @return
     */
    @GetMapping("getChapterInfo/{chapterId}")
    public CommonResult getChapterInfo(@PathVariable String chapterId) {
        ChapterDo chapterById = chapterService.getById(chapterId);
        return CommonResult.success().data("chapter", chapterById);
    }

    /**
     * 根据章节id修改课程章节
     * @param chapter
     * @return
     */
    @PostMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody ChapterDo chapter) {
        chapterService.updateById(chapter);
        return CommonResult.success();
    }

    /**
     * 根据id删除课程章节
     * @param chapterId
     * @return
     */
    @DeleteMapping("deleteChapter/{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId) {
        boolean result = chapterService.deleteChapterByCharpterId(chapterId);
        if (result) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }
    }
}
