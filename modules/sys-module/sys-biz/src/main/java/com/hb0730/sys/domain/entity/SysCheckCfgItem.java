package com.hb0730.sys.domain.entity;

import com.alipay.sofa.rpc.common.json.JSONField;
import com.hb0730.data.core.domain.BaseEntity;
import com.hb0730.data.core.identifier.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/10
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "sys_check_cfg_item")
public class SysCheckCfgItem extends BaseEntity {
    /**
     * id
     */
    @Id
    @IdGenerator
    private String id;


    /**
     * 字典id
     */
    @ManyToOne(
        targetEntity = SysCheckCfg.class,
        fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "cfg_id")
    private SysCheckCfg sysCheckCfg;

    /**
     * svn路径
     */
    private String svnPath;
}
