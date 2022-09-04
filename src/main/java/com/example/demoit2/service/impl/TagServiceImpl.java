package com.example.demoit2.service.impl;

import com.example.demoit2.entity.items.TagEntity;
import com.example.demoit2.repository.ItemRepository;
import com.example.demoit2.repository.TagRepository;
import com.example.demoit2.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final ItemRepository itemRepository;

    @Resource
    private final DataSource dataSource;

    @Override
    public Optional<TagEntity> findTagById(Long id) {
        log.info("Tag with id:{}", id);
        return tagRepository.findById(id);
    }

    @Override
    public Optional<TagEntity> findTagsByItemId(Long id) {
        log.info("Tags with item id:{}", id);
        return tagRepository.findAllByItemId(id);
    }

    @Override
    public List<TagEntity> findAllTagsByName(String substring) {
        return new JdbcTemplate(dataSource).query(
                "select t.id, t.name from tags t where t.name like '%" + substring + "%'",
                (rs, i) -> new TagEntity(
                        rs.getLong("ID"),
                        rs.getString("NAME").trim()));
    }

    @Override
    public List<TagEntity> findAllTags() {
        log.info("Getting all tags");
        return tagRepository.findAll();
    }

    @Override
    public Optional<TagEntity> saveTag(TagEntity tag) {
        log.info("Saving new tag:{} to database", tag.getTagName());
        return Optional.of(tagRepository.save(tag));
    }

    @Override
    public void deleteTag(Long id) {
        log.info("Tag with id:{} deleted from the database", id);
        tagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addTagToItem(String name, Long itemId) {
        log.info("Add tag:{} to item:{}", name, itemId);

        var tag = tagRepository.findByTagName(name)
                .orElseGet(() -> tagRepository.save(new TagEntity(name)));
        var item = itemRepository.findById(itemId);
        item.map(i -> i.getTags().add(tag));
    }
}
