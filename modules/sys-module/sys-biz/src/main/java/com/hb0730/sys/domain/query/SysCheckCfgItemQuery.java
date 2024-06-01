package com.hb0730.sys.domain.query;

import com.hb0730.common.api.BaseQuery;
import com.hb0730.query.annotation.Equals;
import com.hb0730.query.jpa.annotation.Query;
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
public class SysCheckCfgItemQuery extends BaseQuery {

    @Query(value = "id", joinName = "cfg")
    private String cfgId;

    @Query(value = "ownPhone")
    private String ownPhone;

    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;
}
