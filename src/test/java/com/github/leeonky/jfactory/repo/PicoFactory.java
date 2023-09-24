package com.github.leeonky.jfactory.repo;

import com.github.leeonky.jfactory.JFactory;
import com.github.leeonky.jfactory.Spec;
import com.github.leeonky.jfactory.cucumber.JData;
import com.github.leeonky.util.Classes;
import io.cucumber.core.backend.ObjectFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PicoFactory implements ObjectFactory {
    private final io.cucumber.picocontainer.PicoFactory delegate = new io.cucumber.picocontainer.PicoFactory();
    private final Object jData = jData();

    public static EntityManager entityManager = Persistence.createEntityManagerFactory("jfactory-repo").createEntityManager();

    @Override
    public void start() {
        delegate.start();
    }

    @Override
    public void stop() {
        delegate.stop();
    }

    @Override
    public boolean addClass(Class<?> glueClass) {
        return delegate.addClass(glueClass);
    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        if (JData.class.equals(glueClass))
            return (T) jData;
        return delegate.getInstance(glueClass);
    }

    private JData jData() {
        JFactory jFactory = new JFactory(new MybatisDataRepository());
        Classes.assignableTypesOf(Spec.class, "com.github.leeonky.jfactory.repo").forEach(jFactory::register);
        return new JData(jFactory);
    }
}
