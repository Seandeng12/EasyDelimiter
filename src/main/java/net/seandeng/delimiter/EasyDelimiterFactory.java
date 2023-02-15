package net.seandeng.delimiter;


import net.seandeng.delimiter.builder.DelimiterWriterBuilder;

/**
 * 分割符写工厂类
 *
 * @author admin
 */
public class EasyDelimiterFactory {

    public static DelimiterWriterBuilder write() {
        return new DelimiterWriterBuilder();
    }

}
