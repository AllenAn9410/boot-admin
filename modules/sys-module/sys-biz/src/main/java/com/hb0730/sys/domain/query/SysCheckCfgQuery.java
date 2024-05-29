package com.hb0730.sys.domain.query;

import com.hb0730.common.api.BaseQuery;
import com.hb0730.query.annotation.Equals;
import com.hb0730.query.annotation.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author anx
 * @since 2024/05/18 15:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysCheckCfgQuery extends BaseQuery {

    /**
     * 拥有者号码
     */
    @Equals
    @Schema(description = "拥有者号码")
    private String ownPhone;

    /**
     * 项目名称
     */
    @Equals
    @Schema(description = "项目名称")
    private String projectName;

    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;
}
