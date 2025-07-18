package com.rczy.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output dir>");
            System.exit(64);
        }
        String outputDir = args[0];
        defineAst(outputDir, "com.rczy.lox", "Expr", Arrays.asList(
            "Assign   : Token name, Expr value",
            "Binary   : Expr left, Token operator, Expr right",
            "Call     : Expr callee, Token paren, List<Expr> arguments",
            "Grouping : Expr expression",
            "Literal  : Object value",
            "Logical  : Expr left, Token operator, Expr right",
            "Unary    : Token operator, Expr right",
            "Variable : Token name"
        ));
        defineAst(outputDir, "com.rczy.lox", "Stmt", Arrays.asList(
            "Block      : List<Stmt> statements",
            "Class      : Token name, List<Stmt.Function> methods",
            "Expression : Expr expression",
            "Function   : Token name, List<Token> params, List<Stmt> body",
            "If         : Expr condition, Stmt thenBranch, Stmt elseBranch",
            "Print      : Expr expression",
            "Return     : Token keyword, Expr value",
            "Var        : Token name, Expr initializer",
            "While      : Expr condition, Stmt body"
        ));
    }

    private static void defineAst(String outputDir, String packageName, String baseName, List<String> types) throws IOException {
        String parh = String.format("%s/%s.java", outputDir, baseName);
        try (PrintWriter writer = new PrintWriter(parh, StandardCharsets.UTF_8)) {
            writer.println(String.format("package %s;", packageName));
            writer.println();
            writer.println("import java.util.List;");
            writer.println();
            writer.println(String.format("abstract class %s {", baseName));
            defineVisitor(writer, baseName, types);
            for (var type : types) {
                String className = type.split(":")[0].trim();
                String fields = type.split(":")[1].trim();
                defineType(writer, baseName, className, fields);
            }
            writer.println();
            writer.println("    abstract <R> R accept(Visitor<R> visitor);");
            writer.println("}");
        }
    }

    private static void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
        String[] fields = fieldList.split(", ");
        writer.println(String.format("    static class %s extends %s {", className, baseName));
        // fields
        for (var field : fields) {
            writer.println(String.format("        final %s;", field));
        }
        writer.println();
        // constructor
        writer.println(String.format("        %s(%s) {", className, fieldList));
        for (var field : fields) {
            String name = field.split(" ")[1];
            writer.println(String.format("            this.%s = %s;", name, name));
        }
        writer.println("        }");
        // visitor pattern
        writer.println();
        writer.println("        @Override");
        writer.println("        <R> R accept(Visitor<R> visitor) {");
        writer.println(String.format("            return visitor.visit%s%s(this);", className, baseName));
        writer.println("        }");
        writer.println("    }");
    }

    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
        writer.println("    interface Visitor<R> {");
        for (var type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println(String.format("        R visit%s%s(%s %s);", typeName, baseName, typeName, baseName.toLowerCase()));
        }
        writer.println("    }");
    }
}
