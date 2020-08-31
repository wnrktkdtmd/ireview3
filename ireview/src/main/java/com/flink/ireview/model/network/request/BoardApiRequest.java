package com.flink.ireview.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardApiRequest {
    private Long id;
    private Integer categoryId;
    private String title;
    private String contentString;
    private String userAccount;
    //private Long userId;
    private String userNickname;
    private Integer totalView;
    private Integer totalRecommend;
    private Integer totalComment;
    private boolean manageBoard;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String image7;
    private String image8;

    private Long scrapCount;
}
