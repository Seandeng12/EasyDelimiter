package net.seandeng.delimiter.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * Line
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class Line {

    private Integer lineIndex;

    private Field field;

    private String fieldName;

    public Line(Integer lineIndex, Field field, String fieldName) {
        this.lineIndex = lineIndex;
        this.field = field;
        this.fieldName = fieldName;
    }
}
