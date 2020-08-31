package com.flink.ireview.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentApiResponse {
    private Long id;
    private String status;

    private String commentUserNickname;



    private String contentString;


}
