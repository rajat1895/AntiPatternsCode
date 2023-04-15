import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TryStatement;

public class TryQuantity {
    public static void detectTryQuantity(String source, FlowMetrics flowMetrics) {
        ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setSource(source.toCharArray());
        ASTNode root = parser.createAST(null);
        root.accept(new Visitor(flowMetrics));
    }

    static class Visitor extends ASTVisitor {
        private FlowMetrics flowMetrics;

        public Visitor(FlowMetrics flowMetrics) {
            this.flowMetrics = flowMetrics;
        }

        @Override
        public boolean visit(TryStatement node) {
            flowMetrics.tryQuantity++;
            return true;
        }
    }
}
