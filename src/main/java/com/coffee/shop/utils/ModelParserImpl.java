package com.coffee.shop.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.coffee.shop.utils.interfaces.ModelParser;

public class ModelParserImpl implements ModelParser {

    private ModelMapper modelMapper;

    public ModelParserImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        D convertedObject = null;
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        D convertedObject = null;
        this.modelMapper.addMappings(propertyMap);
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }

	@Override
	public <S, D> List<D> convert(List<S> sourceList, Class<D> destinationClass) {
		List<D> convertedObjects = new ArrayList<>();
		for (S sObject: sourceList) {
			D convertedObject = this.modelMapper.map(sObject, destinationClass);
			convertedObjects.add(convertedObject);
		}
		return convertedObjects;
	}
	
	@Override
	public <S, D> Set<D> convert(Set<S> sourceList, Class<D> destinationClass) {
		Set<D> convertedObjects = new HashSet<>();
		for (S sObject: sourceList) {
			D convertedObject = this.modelMapper.map(sObject, destinationClass);
			convertedObjects.add(convertedObject);
		}
		return convertedObjects;
	}
}
