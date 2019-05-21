package org.lesson09;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

public class Compiler {
    public static void compile(File file) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, file.getPath());
    }
}
