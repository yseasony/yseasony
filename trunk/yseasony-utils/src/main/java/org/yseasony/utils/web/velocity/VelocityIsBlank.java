package org.yseasony.utils.web.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

public class VelocityIsBlank extends Directive {

    @Override
    public String getName() {
        return "isNotBlank";
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer,
            Node node) throws IOException, ResourceNotFoundException,
            ParseErrorException, MethodInvocationException {
        if (node.jjtGetNumChildren() == 1) {
            return false;
        }
        SimpleNode valueNode = (SimpleNode) node.jjtGetChild(0);
        String value = (String) valueNode.value(context);
        if (StringUtils.isBlank(value)) {
            return false;
        } else {
            Node body = node.jjtGetChild(1);
            StringWriter sw = new StringWriter();
            body.render(context, sw);
            writer.write(sw.toString());
            return true;
        }
    }

}
