package com.hb0730.sys.repository;

import com.hb0730.data.core.repository.BaseJpaRepository;
import com.hb0730.sys.domain.entity.SysCheckCfgItem;
import com.hb0730.sys.domain.entity.SysDictItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/5
 */
@Repository
public interface SysCheckCfgItemRepository extends BaseJpaRepository<SysCheckCfgItem, String> {

    default boolean existsCheckCfgPath(String id) {
        return count(id) > 0;
    }

    /**
     * 系统配置数量
     *
     * @param cfgId 系统配置ID
     * @return 数量
     */
    @Query("select count(1) from SysCheckCfgItem where sysCheckCfg.id = ?1")
    int count(String cfgId);

}
