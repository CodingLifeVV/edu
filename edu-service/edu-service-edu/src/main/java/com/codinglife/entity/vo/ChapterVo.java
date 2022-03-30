package com.codinglife.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/25 16:51
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
