package pageObject.category.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum FilterDirection {
    FROM("FROM"),
    TO("TO");


    private static final FilterDirection[] VALUES = values();

    private final String message;

    public static FilterDirection[] getValues(){
        return VALUES;
    }
}
