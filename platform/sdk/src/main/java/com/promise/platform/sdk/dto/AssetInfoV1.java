package com.promise.platform.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssetInfoV1
{
    public String manufacturer;
    public String serialNumber;    
    public String partNumber;
    public String assertTag;
    public String sku;
    public String uuid;
    public String model;
}
