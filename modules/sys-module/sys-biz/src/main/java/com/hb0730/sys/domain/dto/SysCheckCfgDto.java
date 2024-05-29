package com.hb0730.sys.domain.dto;

import com.hb0730.common.api.domain.BaseDto;
import com.hb0730.data.core.identifier.IdGenerator;
import com.hb0730.query.annotation.Equals;
import com.hb0730.sys.domain.entity.SysCheckCfgItem;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author anx
 * @since 2024/05/18 15:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysCheckCfgDto extends BaseDto {


    private String id;


    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    private String projectName;

    /**
     * worktile项目
     */
    @NotBlank(message = "worktile项目ID不能为空")
    private String worktileProjectId;

    /**
     * worktime账号
     */
    @NotBlank(message = "worktime账号")
    private String worktileAccId;

    /**
     * worktime密码
     */
    @NotBlank(message = "项目名称不能为空")
    private String worktileAccPwd;

    /**
     * svn账号
     */
    @NotBlank(message = "svn账号不能为空")
    private String svnAccId;

    /**
     * svn密码
     */
    @NotBlank(message = "svn密码不能为空")
    private String svnAccPwd;


    /**
     * 拥有者手机号码
     */
    @NotBlank(message = "拥有者手机号码不能为空")
    private String ownPhone;


    /**
     * 是否启用
     */
    private Boolean enabled;
}
