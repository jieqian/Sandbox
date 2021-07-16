package com.sandbox.rancher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RancherResponse {
    public String baseType;
    public String code;
    public String message;
    public int status;
    public String type;
    public boolean success = false;
}
