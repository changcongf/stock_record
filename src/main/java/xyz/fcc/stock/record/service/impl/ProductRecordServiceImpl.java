package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.entity.ProductRecord;
import xyz.fcc.stock.record.mapper.ProductRecordMapper;
import xyz.fcc.stock.record.mapper.ProductCommentMapper;
import xyz.fcc.stock.record.service.ProductRecordService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductRecordServiceImpl implements ProductRecordService {

    private final ProductRecordMapper productRecordMapper;
    private final ProductCommentMapper productCommentMapper;

    @Override
    public int saveProductRecord(ProductRecordDTO productRecordDTO) {
        ProductRecord entity = new ProductRecord();
        entity.setId(productRecordDTO.getId());
        entity.setCurrentDay(productRecordDTO.getCurrentDay());
        entity.setProduct(productRecordDTO.getProduct());
        entity.setContent(productRecordDTO.getContent());
        entity.setInfo("兼容处理"); // info字段兼容处理
        return productRecordMapper.insertProductRecord(entity);
    }

    @Override
    public PageResult<ProductRecordDTO> getProductRecordsByCondition(
            String product,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    ) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<ProductRecord> entities = productRecordMapper.selectProductRecordByConditionWithPaging(
                product, startDate, endDate, content, offset, limit
        );

        long total = productRecordMapper.countProductRecordsByCondition(product, startDate, endDate, content);

        List<ProductRecordDTO> dtos = entities.stream()
                .map(this::convertToDTOWithComments)
                .collect(Collectors.toList());

        return new PageResult<>(
                dtos,
                total,
                pageNum,
                pageSize,
                (total + pageSize - 1) / pageSize
        );
    }

    @Override
    public List<ProductCommentDTO> getCommentsByRecordId(Long recordId) {
        return productCommentMapper.selectProductCommentsByRecordId(recordId).stream()
                .map(this::convertCommentToDTO)
                .collect(Collectors.toList());
    }

    private ProductRecordDTO convertToDTOWithComments(ProductRecord entity) {
        ProductRecordDTO dto = new ProductRecordDTO();
        dto.setId(entity.getId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setProduct(entity.getProduct());
        dto.setContent(entity.getContent());
        dto.setInfo(entity.getInfo());
        dto.setComments(getCommentsByRecordId(entity.getId()));
        return dto;
    }

    private ProductCommentDTO convertCommentToDTO(xyz.fcc.stock.record.entity.ProductComment entity) {
        ProductCommentDTO dto = new ProductCommentDTO();
        dto.setId(entity.getId());
        dto.setProductRecordId(entity.getProductRecordId());
        dto.setProduct(entity.getProduct());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setContent(entity.getContent());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setInfo(entity.getInfo());
        return dto;
    }
}
