package com.learn.chapter03.mapper;

import com.learn.chapter03.bean.Consumer;
import com.learn.chapter03.bean.Person;

import java.util.List;

/**
 * Created by sang on 17-1-13.
 */
public interface ConsumerMapper {
    public List<Consumer> getUser();
    public List<Consumer> getUser2();
    public List<Person> getPerson();

    public int insertConsumer(Consumer consumer);
    public int insertConsumer2(Consumer consumer);
    public int insertConsumer3(Consumer consumer);
    public int insertConsumer4(Consumer consumer);
    public int insertPerson(Person p);
}
