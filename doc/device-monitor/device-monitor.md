# Device Monitor

Device Monitor是专门用于设备监控的模块。

> Q: 为什么将Device Monitor独立出来？  
> A: Device目前是有状态的，无法多实例。而Device Monitor只负责接收数据，可以做成无状态的。
> 另外Device Monitor的水平扩展需求应该强于Device，将其独立有利于节约资源。