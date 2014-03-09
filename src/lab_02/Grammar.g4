grammar Grammar;

@header {
    package lab_02;

    import common.ReverseIndex;
    import java.util.*;
}

@members {
    private ReverseIndex index = null;
}

file[ReverseIndex index] returns [List<List<Integer>> result]
    @init {
        $result = new LinkedList<>();
        this.index = index;
    }
    :   NEW_LINE? query {
        $result.add($query.result);
    }
    ( NEW_LINE query {
        $result.add($query.result);
    } )* NEW_LINE? EOF
    ;

query returns [List<Integer> result]
@init {
    List<Integer> temp = null;
}
    :   orExpression {
        $result = new LinkedList($orExpression.result);
    } ( resultsCount {
        temp = new LinkedList<>();

        ListIterator<Integer> iterator = $result.listIterator();
        for (int i = 0; i < $resultsCount.value && iterator.hasNext(); i++) {
            temp.add(iterator.next());
        }

        $result = temp;
    } )?
    ;

resultsCount returns [int value]
    :   INT {
        $value = Integer.valueOf($INT.text);
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
    :   atomExpression {
        $result = $atomExpression.result;
    } | ( NOT atomExpression {
        $result = index.universeSet();
        $result.removeAll($atomExpression.result);
    } )
    ;

atomExpression returns [Set<Integer> result]
@init {
    String terminus = null;
}
    :   TERMINUS {
        terminus = $TERMINUS.text;
        $result = index.terminusSet(terminus);
    } | ( LEFT_PARENTHESIS orExpression RIGHT_PARENTHESIS {
        $result = $orExpression.result;
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

fragment DIGIT
    :   '0' .. '9'
    ;

INT
    :   DIGIT+
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