package com.edu.nju.alley.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewRecordVO {

    private Integer id;

    public NewRecordVO(Integer id) {
        this.id = id;
    }

}
