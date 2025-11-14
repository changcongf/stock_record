package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.AttentionDTO;
import xyz.fcc.stock.record.dto.PageResult;
import xyz.fcc.stock.record.entity.Attention;
import xyz.fcc.stock.record.mapper.AttentionMapper;
import xyz.fcc.stock.record.service.AttentionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttentionServiceImpl implements AttentionService {

    private final AttentionMapper attentionMapper;

    @Override
    public int saveAttention(AttentionDTO attentionDTO) {
        Attention entity = convertToEntity(attentionDTO);
        entity.setUpdateTime(LocalDateTime.now());
        
        Attention existing = attentionMapper.selectAttentionByName(entity.getName());
        if (existing != null) {
            throw new RuntimeException("未注册");
        } else {
            entity.setCreateTime(LocalDateTime.now());
            entity.setTimes(1);
            return attentionMapper.insertOrUpdateAttention(entity);
        }
    }

    @Override
    public PageResult<AttentionDTO> getAttentionByType(String type, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Attention> entities = attentionMapper.selectAttentionByTypeWithPaging(type, offset, limit);
        long total = attentionMapper.countAttentionByType(type);

        List<AttentionDTO> dtos = entities.stream()
                .map(this::convertToDTO)
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
    public int incrementTimes(String name, String type) {
        // 先尝试递增，如果不存在则创建
        return attentionMapper.incrementTimesByName(name);
    }

    private Attention convertToEntity(AttentionDTO dto) {
        Attention entity = new Attention();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setCreateTime(dto.getCreateTime());
        entity.setUpdateTime(dto.getUpdateTime());
        entity.setTimes(dto.getTimes());
        return entity;
    }

    private AttentionDTO convertToDTO(Attention entity) {
        AttentionDTO dto = new AttentionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setTimes(entity.getTimes());
        return dto;
    }
}