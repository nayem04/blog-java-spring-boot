package com.blogjavaspringboot.common.utilities;

import com.blogjavaspringboot.common.exceptions.NotFoundException;

public final class ExceptionUtil {
    public static NotFoundException getNotFoundException(String name, Long id) {
        return new NotFoundException("Could not find '" + name + "' with id : " + id);
    }

    public static NotFoundException getNotFoundException(String name, String code) {
        return new NotFoundException("Could not find '" + name + "' by : " + code);
    }
}
