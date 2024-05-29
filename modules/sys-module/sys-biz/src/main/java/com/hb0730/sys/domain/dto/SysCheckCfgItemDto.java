package com.hb0730.sys.domain.dto;

import com.hb0730.common.api.domain.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author anx
 * @since 2024/05/18 15:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysCheckCfgItemDto extends BaseDto {

    private String id;


    /**
     * 配置ID
     */
    @NotBlank(message = "配置ID不能为空")
    private String cfgId;

    /**
     * svn路径
     */
    @NotBlank(message = "svn路径")
    private String svnPath;


    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 父项
     */
    private SysCheckCfgDto sysCheckCfg;
}
