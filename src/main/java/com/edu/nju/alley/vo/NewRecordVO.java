package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("NewRecordVO")
@Data
@NoArgsConstructor
public class NewRecordVO {

    @ApiModelProperty("新创建的实体id")
    private Integer id;

    public NewRecordVO(Integer id) {
        this.id = id;
    }

}
