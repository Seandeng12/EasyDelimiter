package net.seandeng.delimiter.analysis;

import net.seandeng.delimiter.read.metadata.ReadFile;

import java.util.List;

public interface DelimiterAnalyser {

    void analysis(List<ReadFile> readFileList, Boolean readAll);

    void finish();
}
