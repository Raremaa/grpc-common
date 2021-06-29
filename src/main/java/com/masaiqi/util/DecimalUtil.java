package com.masaiqi.util;

import com.google.protobuf.ByteString;
import com.masaiqi.grpc.DecimalValue;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/29 21:22
 */
public class DecimalUtil {

    /**
     * 转换 {@link BigDecimal} -> protobuf的类型
     *
     * @param bigDecimal 需要转换的值
     * @return protobuf目标对象
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-04-22 15:31
     */
    public static DecimalValue parse2DecimalValue(BigDecimal bigDecimal) {
        // protobuf 是不允许null对象的，如果传入为空，这里也new一个实例返回
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }

        DecimalValue result = DecimalValue.newBuilder()
                .setScale(bigDecimal.scale())
                .setPrecision(bigDecimal.precision())
                .setValue(ByteString.copyFrom(bigDecimal.unscaledValue().toByteArray()))
                .build();
        return result;
    }

    /**
     * 转换 protobuf的类型 -> {@link BigDecimal}
     *
     * @param decimalValue 需要转换的 protobuf目标对象
     * @return {@link BigDecimal} 类型的值
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-04-22 15:31
     */
    public static BigDecimal parse2BigDecimal(DecimalValue decimalValue) {
        MathContext mc = new MathContext(decimalValue.getPrecision());
        byte[] val = decimalValue.getValue().toByteArray();
        if (val.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = new BigDecimal(
                new java.math.BigInteger(val),
                decimalValue.getScale(),
                mc);
        return result;
    }

    /**
     * 转换 {@link String} -> {@link BigDecimal}
     *
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-04-23 16:44
     */
    public static BigDecimal parse2BigDecimal(String value) {
        if (value != null && "" != value) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(value);
        }
    }

    /**
     * 转换 {@link String} -> {@link DecimalValue}
     *
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-04-23 16:43
     */
    public static DecimalValue parse2DecimalValue(String value) {
        BigDecimal bigDecimal;
        // protobuf 是不允许null对象的，如果传入为空，这里也new一个实例返回
        if (value != null && "" != value) {
            bigDecimal = BigDecimal.ZERO;
        } else {
            bigDecimal = new BigDecimal(value);
        }

        DecimalValue result = DecimalValue.newBuilder()
                .setScale(bigDecimal.scale())
                .setPrecision(bigDecimal.precision())
                .setValue(ByteString.copyFrom(bigDecimal.unscaledValue().toByteArray()))
                .build();
        return result;
    }


}
