package com.zipcodewilmington.streams.conversions;

import com.zipcodewilmington.streams.anthropoid.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/25/17.
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this class
 */
public class TestConversionAgent<T extends ConversionAgent> {
    private final T conversionAgent;

    private List<Person> personList;
    private Person[] personArray;
    private Stream<Person> personStream;

    public TestConversionAgent(T conversionAgent) {
        this.conversionAgent = conversionAgent;
    }

    @Before
    public void setup() {
        this.personStream = conversionAgent.toStream();
        this.personList = conversionAgent.toList();
        this.personArray = conversionAgent.toArray();
    }

    @Test
    public void testCount() {
        int listSize = personList.size();
        int arrayLength = personArray.length;
        int streamCount = (int) personStream.count();

        Assert.assertEquals(listSize, arrayLength);
        Assert.assertEquals(listSize, streamCount);
    }

    @Test
    public void testToList() {
        for (int i = 0; i < personList.size(); i++) {
            long listId = personList.get(i).personalId.getValue();
            long arrayId = personArray[i].personalId.getValue();

            Assert.assertEquals(listId, arrayId);
        }
    }


    @Test
    public void testToStream() {
        List<Person> people = personStream.collect(Collectors.toList());

        for (int i = 0; i < people.size(); i++) {
            long arrayId = personArray[i].personalId.getValue();
            long streamId = people.get(i).personalId.getValue();

            Assert.assertEquals(streamId, arrayId);
        }
    }

    @Test
    public void testToArray() {
        for (int i = 0; i < personArray.length; i++) {
            long arrayId = personArray[i].personalId.getValue();
            long listId = personList.get(i).personalId.getValue();

            Assert.assertEquals(listId, arrayId);
        }
    }
}