package net.seandeng.delimiter.read.metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@EqualsAndHashCode
public class ReadWorkbook extends ReadBasicParameter {

    private InputStream inputStream;
}
