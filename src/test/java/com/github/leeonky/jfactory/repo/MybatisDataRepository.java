package com.github.leeonky.jfactory.repo;

import com.github.leeonky.jfactory.DataRepository;
import com.github.leeonky.jfactory.repo.entity.Order;
import com.github.leeonky.jfactory.repo.entity.Product;
import com.github.leeonky.jfactory.repo.mapper.OrderMapper;
import com.github.leeonky.jfactory.repo.mapper.ProductMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

class MybatisDataRepository implements DataRepository {

    @Override
    public <T> Collection<T> queryAll(Class<T> type) {
        if (type.equals(Product.class))
            return (Collection<T>) query(ProductMapper.class, ProductMapper::findAll);
        else if (type.equals(Order.class))
            return (Collection<T>) query(OrderMapper.class, OrderMapper::findAll);
        return Collections.emptyList();
    }

    @Override
    public void clear() {
    }

    @Override
    public void save(Object object) {
        if (object instanceof Product) {
            execute(ProductMapper.class, productMapper -> productMapper.save((Product) object));
        } else if (object instanceof Order)
            execute(OrderMapper.class, productMapper -> productMapper.save((Order) object));
    }

    public <T, M> T query(Class<M> mapperType, Function<M, T> function) {
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
            try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
                return function.apply(sqlSession.getMapper(mapperType));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <M> void execute(Class<M> mapperType, Consumer<M> consumer) {
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
            try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
                consumer.accept(sqlSession.getMapper(mapperType));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
