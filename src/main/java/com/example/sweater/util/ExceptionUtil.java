package com.example.sweater.util;

import com.example.sweater.model.AbstractBaseEntity;
import com.example.sweater.util.exception.LateToVote;
import com.example.sweater.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

import static com.example.sweater.util.TimeUtil.getVoteFinishTime;

public class ExceptionUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionUtil.class);

    private ExceptionUtil() {
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
            LOG.error("Not found entity with " + msg);
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkLateToVote(){
        if (!LocalTime.now().isBefore(getVoteFinishTime())){
            LOG.error("Vote can't be changed");
            throw new LateToVote("Vote can't be changed");
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            LOG.error(entity + " must be new (id = null)");
            throw new IllegalArgumentException(entity + " must be new (id = null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            LOG.error(entity + " must be with id = " + id);
            throw new IllegalArgumentException(entity + " must be with id = " + id);
        }
    }
}
