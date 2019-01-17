package com.promise.platform.sdk.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the resource that can be retrieve by it's <tt>id</tt> property.
 *
 */
@Data
@NoArgsConstructor
public class BasicResource
{
    @Id
    public String id;
    public String uri;
    public String category;
    public String name;
    public Date createdAt;
    public Date updatedAt;

    /**
     * Initialize the properties of
     * {@link com.promise.platform.sdk.model.BasicResource}
     * 
     * @param target The <tt>BasicResource</tt> to be initialized.
     * @param baseUri Base URI of the resource.
     * @param name The name of the resource.
     */
    public static void Init(BasicResource target, String baseUri, String name)
    {
        target.id = UUID.randomUUID().toString();
        target.uri = baseUri + "/" + target.id;
        target.name = name;
        target.createdAt = new Date();
        target.updatedAt = target.createdAt;
    }
    
    /**
     * Copy the BasicResource from the source to target.
     * @param target The target object.
     * @param source The source object.
     */
    public static void model2dto(BasicResourceResponseV1 target, BasicResource source) {
        target.id = source.id;
        target.uri = source.uri;
        target.category = source.category;
        target.name = source.name;
        target.createdAt = source.createdAt;
        target.updatedAt = source.updatedAt;        
    }
}
