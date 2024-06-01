package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JsfPage;
import com.hb0730.sys.domain.dto.SysCheckCfgDto;
import com.hb0730.sys.domain.dto.SysCheckCfgItemDto;
import com.hb0730.sys.domain.query.SysCheckCfgItemQuery;
import com.hb0730.sys.domain.query.SysCheckCfgQuery;
import com.hb0730.sys.service.SysCheckCfgItemService;
import com.hb0730.sys.service.SysCheckCfgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anx
 * @since 2024/05/18 15:15
 */
@RestController
@RequestMapping("/check/exe")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：检查配置")
public class CheckExeController {

    private final SysCheckCfgService sysCheckCfgService;
    private final SysCheckCfgItemService sysCheckCfgItemService;

    /**
     * 执行
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('check:exe:query')")
    public R<JsfPage<SysCheckCfgDto>> page(SysCheckCfgQuery query) {
        JsfPage<SysCheckCfgDto> res = sysCheckCfgService.page(query);
        return R.OK(res);
    }




}
