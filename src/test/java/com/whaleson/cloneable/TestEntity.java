package com.whaleson.cloneable;

import com.whaleson.cloneable.entity.AddressInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestEntity {
    private List<AddressInfo> addressInfoList;

    private ArrayList<AddressInfo> addressInfoArrayList;
}
