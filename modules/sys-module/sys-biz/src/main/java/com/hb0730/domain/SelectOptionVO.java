package com.hb0730.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/7
 */
@Data
@EqualsAndHashCode
@ToString
public class SelectOptionVO implements Serializable {
    private String label;
    private String value;
}
