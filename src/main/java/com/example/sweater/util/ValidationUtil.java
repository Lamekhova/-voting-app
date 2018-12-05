package com.example.sweater.util;

import com.example.sweater.model.AbstractBaseEntity;
import com.example.sweater.util.exception.LateToChangeVote;
import com.example.sweater.util.exception.NotFoundException;

import java.time.LocalTime;

import static com.example.sweater.util.TimeUtil.getVoteFinishTime;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundObjectWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkLateToChangeVote(){
        if (!LocalTime.now().isBefore(getVoteFinishTime())){
            throw new LateToChangeVote("Vote can't be changed");
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

}
