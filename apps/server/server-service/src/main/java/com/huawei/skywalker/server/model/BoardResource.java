package com.huawei.skywalker.server.model;

import java.util.List;
import java.util.stream.Collectors;

import com.huawei.skywalker.server.model.Server.BoardSummary;
import com.promise.apps.server.sdk.dto.BoardResourceV1;
import com.promise.apps.server.sdk.dto.BoardResourceV1.BoardV1;
import com.promise.platform.sdk.model.BasicResource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The board resource of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BoardResource extends BasicResource
{
    public BoardSummary summary;
    public List<Board> boards;
    
    /**
     * Convert model to response DTO.
     * 
     * @param model The model object.
     * @return The response DTO object.
     */
    public static BoardResourceV1 toResponse(BoardResource model)
    {
        var ret = new BoardResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = BoardSummary.toResponse(model.summary);
        ret.boards = model.boards.stream().map(Board::toResponse).collect(Collectors.toList());
        return ret;
    }
    
    /**
     * Represents the board in {@link BoardResource}
     *
     */
    public static class Board {
        /**
         * Convert model to response DTO.
         * 
         * @param model The model object.
         * @return The response DTO object.
         */
        public static BoardV1 toResponse(Board model)
        {
            var ret = new BoardV1();
            return ret;
        }
    }
}
