grammar Grammar;

@header {
    package lab_02;

    import common.ReverseIndex;
    import java.util.*;
}

@members {
    private ReverseIndex index = null;
}

file[ReverseIndex index] returns [List<Set<Integer>> result]
    @init {
        $result = new LinkedList<>();
        this.index = index;
    }
    :   NEW_LINE? expression {
        $result.add($expression.result);
    }
    ( NEW_LINE expression {
        $result.add($expression.result);
    } )* NEW_LINE? EOF
    ;

expression returns [Set<Integer> result]
    :   orExpression {
        $result = $orExpression.result;
    }
    ;

orExpression returns [Set<Integer> result]
    :   andExpression {
        $result = $andExpression.result;
    }   ( OR andExpression {
        $result.addAll($andExpression.result);
    } )*
    ;

andExpression returns [Set<Integer> result]
    :   maybeNotExpression {
        $result = $maybeNotExpression.result;
    }   ( AND maybeNotExpression {
        $result.retainAll($maybeNotExpression.result);
    } )*
    ;

maybeNotExpression returns [Set<Integer> result]
    :   atom {
        $result = $atom.result;
    } | ( NOT atom {
        $result = index.universeSet();
        $result.removeAll($atom.result);
    } )
    ;

atom returns [Set<Integer> result]
    :   TERMINUS {
        $result = index.terminusSet($TERMINUS.text);
    } | ( LEFT_PARENTHESIS expression RIGHT_PARENTHESIS {
        $result = $expression.result;
    } )
    ;

WS
    :   ( ' '
        | '\t'
    )+ {
        skip();
    }
    ;

NEW_LINE
    :   ( '\r'
        | '\n'
    )+
    ;

OR
    :   'OR'
    ;

AND
    :   'AND'
    ;

NOT
    :   'NOT'
    ;

LEFT_PARENTHESIS
    :   '('
    ;

RIGHT_PARENTHESIS
    :   ')'
    ;

TERMINUS
    :   (
        ~( ' '
        | '\t'
        | '\r'
        | '\n'
        | '('
        | ')'
        )
    )+
    ;