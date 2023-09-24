package com.github.leeonky.jfactory.repo.entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class ProductionOrder {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long workshopId;
    @TableField(value = "product_id", property = "product.id")
    private Product product;
    @TableField(exist = false)
    private ProductionProcess process;
    private String number;
    private String trackingNumber;
    private String customerName;
    private int quantity;
    private OrderStatus status;
    private Timestamp scheduledStartTime;
    private Timestamp scheduledEndTime;
    @TableField(value = "valid", updateStrategy = FieldStrategy.IGNORED)
    private Boolean valid;
    @TableField(exist = false)
    private final ProductionProgress progress = new ProductionProgress(this);
    @TableField(exist = false)
    private List<ProductionBatch> batches;
    @TableField(exist = false)
    private List<Workpiece> workpieces;
    @TableField(exist = false)
    private WorkpieceProgress workpieceProgress;

    public void checkSchedulingQuantity(int schedulingQuantity) {
        Integer batchesQuantity = batches.stream().map(ProductionBatch::getQuantity).reduce(0, Integer::sum);
        if (quantity - batchesQuantity < schedulingQuantity) {
            throw new BusinessException("排产总数不应超过工单总数");
        }
    }

    public OrderStatus getStatus() {
        if (progress.getScheduledQuantity() == 0) {
            return OrderStatus.PENDING;
        }
        if (progress.getFinishedQuantity() < quantity) {
            return OrderStatus.IN_PRODUCTION;
        }
        return OrderStatus.FINISHED;
    }

    public void setWorkpieceProgress(WorkpieceProgress workpieceProgress) {
        this.workpieceProgress = workpieceProgress;
    }

    public void unValid() {
        valid = false;
    }
}
