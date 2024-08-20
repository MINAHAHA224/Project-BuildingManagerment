package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.repository.RentareaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RentareaRepositoryImpl implements RentareaRepository {
    @Override
    public List<RentareaEntity> findAll() {
        return null;
    }

    @Override
    public List<RentareaEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RentareaEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<RentareaEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(RentareaEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends RentareaEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends RentareaEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends RentareaEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RentareaEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends RentareaEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<RentareaEntity> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public RentareaEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends RentareaEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends RentareaEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends RentareaEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends RentareaEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RentareaEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends RentareaEntity> boolean exists(Example<S> example) {
        return false;
    }


}
