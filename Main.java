import egg.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.tam.ast.impl.TAMFactoryImpl;
import mg.egg.eggc.runtime.libjava.ISourceUnit;
import mg.egg.eggc.runtime.libjava.SourceUnit;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import mg.egg.eggc.runtime.libjava.problem.ProblemReporter;
import mg.egg.eggc.runtime.libjava.problem.ProblemRequestor;
import java.io.*;

public class Main{

    private static String ident(String s) {
        StringBuilder _builder = new StringBuilder(0);

        int nident = -1;
        for (String _s : s.split("\n")) {
            int variation = 0;
            for (char i : _s.toCharArray()) {
                if (i == '}') variation--;
                if (i == '{') variation++;
            }
            if (variation < 0) nident += variation;
            for (int i = 0; i < nident; i++)
                _builder.append("\t");
            _builder.append(_s).append("\n");
            if (variation > 0) nident += variation;
        }

        return _builder.toString();
    }

    public static void main (String[] args){
        try {
            ISourceUnit cu = new SourceUnit(args[0]);
            System.out.println("Fichier analysé : "+  args[0]);
            ProblemReporter prp = new ProblemReporter(cu);
            ProblemRequestor prq = new ProblemRequestor(true);
            MiniJava bloc = new MiniJava(prp);
            prq.beginReporting();
            bloc.set_eval(true);
            bloc.compile(cu);
            for(IProblem problem : prp.getAllProblems()) {
                prq.acceptProblem(problem);
            }
            prq.endReporting();

            System.out.println(ident("AST :"+bloc.get_ast()));

            if (prp.getAllProblems().isEmpty()) {
                System.out.println("Format: OK");
            } else {
                for(IProblem problem : prp.getAllProblems()) {
                    System.out.println(problem);
                }
                System.out.println("Format: ERROR");
            }
            if (bloc.get_ast().checkType()) {
                System.out.println( "Typing: OK" );

                try{
                    String filename = args.length == 1 ?
                            args[0].substring(0, args[0].lastIndexOf('.')).concat(".tam") :
                            args[1];
                    PrintWriter writer = new PrintWriter(filename, "UTF-8");
                    bloc.get_ast().allocateMemory(Register.SB, 0);
                    TAMFactory factory = new TAMFactoryImpl();
                    Fragment code = bloc.get_ast().getCode(factory);
                    writer.println(code);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println( "Typing: ERROR" );
            }
            System.exit(0);
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
