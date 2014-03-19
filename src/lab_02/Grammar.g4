grammar Grammar;

@header {
package lab_02;

import common.ReverseIndex;
import java.util.*;
}

@members {
private ReverseIndex index = null;
}

file[ReverseIndex index] returns [List<QueryResult> result]
    @init {
        this.index = index;
        $result = new ArrayList<>();
    }
    :   NEW_LINE? query {
        $result.add($query.queryResult);
    }
    ( NEW_LINE query {
        $result.add($query.queryResult);
    } )* NEW_LINE? EOF
    ;

query returns [QueryResult queryResult]
@init {
    String text = null;
    List<Integer> result = new ArrayList<>();
    List<String> matchedWords = new ArrayList<>();
    int count = Integer.MAX_VALUE;
}
    :   orExpression {
        text = $orExpression.text;
        result.addAll($orExpression.result);

        matchedWords.addAll($orExpression.matchedWords);
    } ( resultsCount {
        count = $resultsCount.value;
    } )? {
        $queryResult = new QueryResult(text, result, matchedWords, count);
    }
    ;

resultsCount returns [int value]
    :   INT {
        $value = Integer.valueOf($INT.text);
    }
    ;

orExpression returns [Set<Integer> result, Set<String> matchedWords]
    :   andExpression {
        $result = $andExpression.result;

        $matchedWords = $andExpression.matchedWords;
    }   ( OR andExpression {
        $result.addAll($andExpression.result);

        $matchedWords.addAll($andExpression.matchedWords);
    } )*
    ;

andExpression returns [Set<Integer> result, Set<String> matchedWords]
    :   maybeNotExpression {
        $result = $maybeNotExpression.result;

        $matchedWords = $maybeNotExpression.matchedWords;
    }   ( AND maybeNotExpression {
        $result.retainAll($maybeNotExpression.result);

        $matchedWords.addAll($maybeNotExpression.matchedWords);
    } )*
    ;

maybeNotExpression returns [Set<Integer> result, Set<String> matchedWords]
    :   atomExpression {
        $result = $atomExpression.result;

        $matchedWords = $atomExpression.matchedWords;
    } | ( NOT atomExpression {
        $result = index.universeSet();
        $result.removeAll($atomExpression.result);

        $matchedWords = new HashSet<>();
    } )
    ;

atomExpression returns [Set<Integer> result, Set<String> matchedWords]
@init {
    String terminus = null;
}
    :   TERMINUS {
        terminus = $TERMINUS.text;
        $result = index.terminusSet(terminus);

        $matchedWords = new HashSet<>();
        $matchedWords.add(terminus);
    } | ( LEFT_PARENTHESIS orExpression RIGHT_PARENTHESIS {
        $result = $orExpression.result;

        $matchedWords = $orExpression.matchedWords;
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