package com.spring.post.convertor;

import com.spring.post.entity.status.PackageType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Converter
public class PackageTypeArrayOfEnumToStringConvertor implements AttributeConverter<PackageType[], String> {
    public static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(PackageType[] attribute) {
        if (attribute==null) {
            return null;
        }
        if (attribute.length == 1) {
            return attribute[0].toString();
        }
//        return Arrays.stream(attribute).filter(Objects::nonNull).map(Enum::toString).map(StringBuilder::new).reduce(new StringBuilder(""), (x, y) -> x.append(SEPARATOR).append(y)).toString();
        return Arrays.stream(attribute).filter(Objects::nonNull).map(Enum::toString).reduce("", (x, y) -> x+SEPARATOR+y);
    }

    @Override
    public PackageType[] convertToEntityAttribute(String dbData) {
        if (dbData==null || dbData.isEmpty()){
            return null;
        }
        final String[] split = dbData.split(", ");
        List<PackageType> packageType = new ArrayList<>();

        for (String s : split) {
            for (PackageType value : PackageType.values()) {
                if (s.equals(value.getType())) {
                    packageType.add(value);
                }
            }
        }
        PackageType[] packageType1 = new PackageType[packageType.size()];
        return packageType.toArray(packageType1);

//        return Arrays.stream(dbData.split(SEPARATOR)).filter(Objects::nonNull).map(PackageType::valueOf).toArray(PackageType[]::new);
    }
}
