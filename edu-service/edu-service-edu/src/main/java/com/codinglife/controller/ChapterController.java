package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.entity.ChapterDo;
import com.codinglife.service.ChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 修改课程章节
     * @param chapter
     * @return
     */
    @PostMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody ChapterDo chapter) {
        chapterService.updateById(chapter);
        return CommonResult.success();
    }

    /**
     * 删除课程章节
     * @param chapterId
     * @return
     */
    @DeleteMapping("{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId) {
        boolean result = chapterService.deleteChapterByCharpterId(chapterId);
        if (result) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }
    }
}
