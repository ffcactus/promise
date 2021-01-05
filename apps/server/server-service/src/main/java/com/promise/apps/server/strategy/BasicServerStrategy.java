package com.promise.apps.server.strategy;

import com.promise.apps.server.exception.ServerStateException;
import com.promise.apps.server.model.Server;
import com.promise.apps.server.repository.ServerRepository;
import com.promise.apps.server.sdk.dto.ServerStateV1;
import com.promise.platform.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This strategy implements basic server operations.
 */
public class BasicServerStrategy {
    @Autowired
    private ServerRepository serverRepository;

    /**
     * Lock the server. Set the server state to <tt>Locked</tt>.
     *
     * @param id The server ID.
     * @return The previous server state.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Server lock(String id) {
        var server = serverRepository.findById(id);
        if (server.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var entity = server.get();
        if (entity.state.equals(ServerStateV1.Locked)) {
            throw new ServerStateException();
        }
        entity.state = ServerStateV1.Locked;
        return serverRepository.save(entity);
    }

    /**
     * Set server state.
     *
     * @param id    The server ID.
     * @param state The server state.
     * @return The updated server.
     */
    public Server setState(String id, ServerStateV1 state) {
        var server = serverRepository.findById(id);
        if (server.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var entity = server.get();
        entity.state = state;
        return serverRepository.save(entity);
    }

}
